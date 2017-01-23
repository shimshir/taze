package de.admir.taze;

import de.admir.taze.event.OrderEventHandler;
import de.admir.taze.model.PickupTypeEnum;
import de.admir.taze.model.Session;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.model.product.Product;
import de.admir.taze.repository.OrderRepository;
import de.admir.taze.repository.ProductRepository;
import de.admir.taze.service.MailService;
import de.admir.taze.service.OrderService;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.data.Offset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static de.admir.taze.Constants.API_REST_CONTEXT_PATH;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.PUT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application.properties", "classpath:application-test.properties"})
public class ApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;

    private HttpHeaders commonHeaders = new HttpHeaders();

    {
        commonHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testHealthCheck() {
        String body = restTemplate.getForObject("/health", String.class);
        assertThat(body).contains("UP");
    }

    @Test
    public void testCreateSession() {
        ResponseEntity<Session> postSessionRes = createNewSession();
        assertThat(postSessionRes.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(postSessionRes.getHeaders().containsKey(HttpHeaders.LOCATION));
        String sessionLocation = postSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION);
        ResponseEntity<Session> getSessionRes = restTemplate.getForEntity(sessionLocation, Session.class);
        assertThat(getSessionRes.getBody().getUuid().getId()).isNotNull();
        assertThat(getSessionRes.getBody().getIpAddress()).isNotNull();
    }

    @Test
    public void testCreateCart() {
        String sessionLink = createNewSession().getHeaders().getFirst(HttpHeaders.LOCATION);
        ResponseEntity<String> createCartRes = createNewOrder(sessionLink, OrderStatusEnum.CART.name());
        assertThat(createCartRes.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ResponseEntity<String> getCartRes = restTemplate.getForEntity(createCartRes.getHeaders().getFirst(HttpHeaders.LOCATION), String.class);
        JSONObject getCartResBody = new JSONObject(getCartRes.getBody());
        assertThat(getCartResBody.getLong("id")).isNotNull();
        assertThat(getCartResBody.getString("status")).isNotNull().isEqualTo(OrderStatusEnum.CART.name());
        assertThat(getCartResBody.getJSONObject("_links").getJSONObject("session").getString("href")).isNotNull().isNotEqualTo(StringUtils.EMPTY);
    }

    @Test
    public void testSearchOrderBySessionUuidIdAndStatusId() {
        ResponseEntity<Session> createSessionRes = createNewSession();
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("sessionUuid", createSessionRes.getBody().getUuid().getId());
        urlParams.put("status", OrderStatusEnum.CART.name());

        ResponseEntity<String> findNonExistingOrderRes = restTemplate.getForEntity(API_REST_CONTEXT_PATH +
                "/orders/search/findBySessionUuidIdAndStatus", String.class, urlParams);
        assertThat(findNonExistingOrderRes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        createNewOrder(createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION), OrderStatusEnum.CART.name());

        ResponseEntity<String> findExistingOrderRes = restTemplate.getForEntity(API_REST_CONTEXT_PATH +
                "/orders/search/findBySessionUuidIdAndStatus?sessionUuid={sessionUuid}&status={status}", String.class, urlParams);
        assertThat(findExistingOrderRes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testAddOrderEntryWithProductToOrder() {
        ResponseEntity<Session> createSessionRes = createNewSession();
        ResponseEntity<String> createOrderRes = createNewOrder(createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION), OrderStatusEnum.CART.name());
        String createdOrderLocation = createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION);

        Product product = productRepository.findByCode("chicken").get();
        final int productAmount = 42;
        JSONObject orderEntryReqJson = new JSONObject()
                .put("amount", productAmount)
                .put("order", createdOrderLocation)
                .put("product", restTemplate.getRestTemplate().getUriTemplateHandler().expand(API_REST_CONTEXT_PATH) + "/products/" + product.getId());
        HttpEntity<String> orderEntryHttpEntity = new HttpEntity<>(orderEntryReqJson.toString(), commonHeaders);

        ResponseEntity<String> addOrderEntryWithProductToOrderRes = restTemplate.postForEntity(API_REST_CONTEXT_PATH +
                "/orderEntries", orderEntryHttpEntity, String.class);
        assertThat(addOrderEntryWithProductToOrderRes.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> getOrderEntriesRes = restTemplate.getForEntity(new JSONObject(createOrderRes.getBody())
                        .getJSONObject("_links")
                        .getJSONObject("entries")
                        .getString("href") + "?projection=with-product",
                String.class);

        JSONArray orderEntriesResJson = new JSONObject(getOrderEntriesRes.getBody()).getJSONObject("_embedded").getJSONArray("orderEntries");
        assertThat(orderEntriesResJson.length()).isEqualTo(1);
        JSONObject orderEntryResJson = orderEntriesResJson.getJSONObject(0);
        assertThat(orderEntryResJson.getInt("amount")).isEqualTo(productAmount);
        JSONObject productResJson = orderEntryResJson.getJSONObject("product");
        assertThat(productResJson.getLong("id")).isEqualTo(product.getId());
        assertThat(productResJson.getString("code")).isEqualTo(product.getCode());

        ResponseEntity<String> updatedOrderRes = restTemplate.getForEntity(createdOrderLocation, String.class);
        JSONObject updatedOrderJson = new JSONObject(updatedOrderRes.getBody());
        assertThat(updatedOrderJson.getDouble("totalPrice")).isCloseTo(
                product.getPricePerUnit().multiply(new BigDecimal(productAmount)).add(PickupTypeEnum.COLLECT.getPrice()).doubleValue(), Offset.offset(0.001)
        );
        JSONObject orderPickupTypePatchReqJson = new JSONObject();
        orderPickupTypePatchReqJson.put("pickupType", PickupTypeEnum.DELIVERY.name());
        HttpEntity<String> orderPickupTypePatchReqEntity = new HttpEntity<>(orderPickupTypePatchReqJson.toString(), commonHeaders);
        ResponseEntity<String> orderPickupTypePatchRes = restTemplate.exchange(createdOrderLocation, PATCH, orderPickupTypePatchReqEntity, String.class);
        assertThat(orderPickupTypePatchRes.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> orderPatchedPickupTypeGetRes = restTemplate.getForEntity(createdOrderLocation, String.class);
        JSONObject orderPatchedPickupTypeGetJson = new JSONObject(orderPatchedPickupTypeGetRes.getBody());
        assertThat(orderPatchedPickupTypeGetJson.getDouble("totalPrice")).isCloseTo(
                product.getPricePerUnit().multiply(new BigDecimal(productAmount)).add(PickupTypeEnum.DELIVERY.getPrice()).doubleValue(), Offset.offset(0.001)
        );
    }

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderEventHandler orderEventHandler;

    @Test
    public void testPutOrderStatusToOrdered() {
        OrderEventHandler orderEventHandlerSpy = spy(orderEventHandler);
        MailService mailServiceMock = mock(MailService.class);
        doNothing().when(mailServiceMock).sendConfirmationEmail(any());

        orderEventHandlerSpy.setMailService(mailServiceMock);

        orderService.setOrderEventHandler(orderEventHandlerSpy);

        ResponseEntity<Session> createSessionRes = createNewSession();
        final String sessionLocation = createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION);
        ResponseEntity<String> createOrderRes = createNewOrder(sessionLocation, OrderStatusEnum.CART.name());

        JSONObject orderedOrderJson = new JSONObject(createOrderRes.getBody())
                .put("status", OrderStatusEnum.ORDERED.name())
                .put("session", sessionLocation);

        HttpEntity<String> httpEntity = new HttpEntity<>(orderedOrderJson.toString(), commonHeaders);
        ResponseEntity<String> putOrderRes = restTemplate.exchange(createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION), PUT, httpEntity, String.class);
        assertThat(putOrderRes.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        ResponseEntity<String> updatedOrderRes = restTemplate.getForEntity(createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION), String.class);
        JSONObject updatedOrderJson = new JSONObject(updatedOrderRes.getBody());
        assertThat(updatedOrderJson.getString("status")).isEqualTo(OrderStatusEnum.ORDERED.name());

        // This should be in a unit test

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderEventHandlerSpy, times(1)).handleOrderBeforeSave(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getStatus()).isEqualTo(OrderStatusEnum.ORDERED);
        verify(orderEventHandlerSpy, times(1)).handleOrderAfterSave(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getStatus()).isEqualTo(OrderStatusEnum.ORDERED);

        verify(mailServiceMock, times(1)).sendConfirmationEmail(any());

        //
    }

    @Test
    public void testPutOrderStatusToConfirmedWithoutToken() {
        OrderEventHandler orderEventHandlerSpy = spy(orderEventHandler);

        orderService.setOrderEventHandler(orderEventHandlerSpy);

        ResponseEntity<Session> createSessionRes = createNewSession();
        final String sessionLocation = createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION);
        ResponseEntity<String> createOrderRes = createNewOrder(sessionLocation, OrderStatusEnum.CART.name());

        JSONObject modifiedOrderJson = new JSONObject(createOrderRes.getBody())
                .put("status", OrderStatusEnum.CONFIRMED.name())
                .put("session", sessionLocation);

        HttpEntity<String> httpEntity = new HttpEntity<>(modifiedOrderJson.toString(), commonHeaders);
        ResponseEntity<String> putOrderRes = restTemplate.exchange(createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION), PUT, httpEntity, String.class);
        assertThat(putOrderRes.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(new JSONObject(putOrderRes.getBody()).getString("message")).isEqualTo("Invalid confirmation token!");

        verify(orderEventHandlerSpy, times(0)).handleOrderBeforeSave(any());
        verify(orderEventHandlerSpy, times(0)).handleOrderAfterSave(any());
    }

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testPutOrderStatusToConfirmedWithToken() {
        OrderEventHandler orderEventHandlerSpy = spy(orderEventHandler);
        MailService mailServiceMock = mock(MailService.class);
        doNothing().when(mailServiceMock).sendConfirmationEmail(any());
        orderEventHandlerSpy.setMailService(mailServiceMock);
        orderService.setOrderEventHandler(orderEventHandlerSpy);

        ResponseEntity<Session> createSessionRes = createNewSession();
        final String sessionLocation = createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION);
        ResponseEntity<String> createOrderRes = createNewOrder(sessionLocation, OrderStatusEnum.CART.name());

        JSONObject orderedOrderJson = new JSONObject(createOrderRes.getBody())
                .put("status", OrderStatusEnum.ORDERED.name())
                .put("session", sessionLocation);

        HttpEntity<String> orderedHttpEntity = new HttpEntity<>(orderedOrderJson.toString(), commonHeaders);
        restTemplate.exchange(createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION), PUT, orderedHttpEntity, String.class);
        String tokenString = orderRepository.findOne(orderedOrderJson.getLong("id")).getToken().getValue().getId();

        JSONObject confirmedOrderJson = orderedOrderJson.put("status", OrderStatusEnum.CONFIRMED.name());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(commonHeaders.getContentType());
        headers.put("X-Confirmation-Token", Collections.singletonList(tokenString));

        HttpEntity<String> confirmedHttpEntity = new HttpEntity<>(confirmedOrderJson.toString(), headers);
        ResponseEntity<String> putConfirmedOrderRes = restTemplate.exchange(createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION), PUT, confirmedHttpEntity, String.class);

        assertThat(putConfirmedOrderRes.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderEventHandlerSpy, times(2)).handleOrderBeforeSave(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getStatus()).isEqualTo(OrderStatusEnum.CONFIRMED);
        verify(orderEventHandlerSpy, times(2)).handleOrderAfterSave(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getStatus()).isEqualTo(OrderStatusEnum.CONFIRMED);
    }

    private ResponseEntity<Session> createNewSession() {
        HttpEntity<String> httpEntity = new HttpEntity<>("{}", commonHeaders);
        return restTemplate.postForEntity(API_REST_CONTEXT_PATH + "/sessions", httpEntity, Session.class);
    }

    private ResponseEntity<String> createNewOrder(String sessionLink, String status) {
        String orderReqBody = String.format("{\"session\": \"%s\", \"status\": \"%s\"}", sessionLink, status);
        HttpEntity<String> httpEntity = new HttpEntity<>(orderReqBody, commonHeaders);
        return restTemplate.postForEntity(API_REST_CONTEXT_PATH + "/orders", httpEntity, String.class);
    }
}
