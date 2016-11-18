package de.admir.taze;

import de.admir.taze.model.Session;
import de.admir.taze.model.product.Product;
import de.admir.taze.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import java.util.HashMap;
import java.util.Map;

import static de.admir.taze.Constants.API_REST_CONTEXT_PATH;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ApiIntegrationTest {
    private static final String CART_STATUS_ID = "CART";
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
        assertThat(body).isEqualTo("OK");
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
        ResponseEntity<String> createCartRes = createNewOrder(sessionLink, CART_STATUS_ID);
        assertThat(createCartRes.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ResponseEntity<String> getCartRes = restTemplate.getForEntity(createCartRes.getHeaders().getFirst(HttpHeaders.LOCATION), String.class);
        JSONObject getCartResBody = new JSONObject(getCartRes.getBody());
        assertThat(getCartResBody.getLong("id")).isNotNull();
        assertThat(getCartResBody.getJSONObject("status").getString("id")).isNotNull().isEqualTo(CART_STATUS_ID);
        assertThat(getCartResBody.getJSONObject("_links").getJSONObject("session").getString("href")).isNotNull().isNotEqualTo(StringUtils.EMPTY);
    }

    @Test
    public void testSearchOrderBySessionUuidIdAndStatusId() {
        ResponseEntity<Session> createSessionRes = createNewSession();
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("sessionUuid", createSessionRes.getBody().getUuid().getId());
        urlParams.put("status", CART_STATUS_ID);

        ResponseEntity<String> findNonExistingOrderRes = restTemplate.getForEntity(API_REST_CONTEXT_PATH +
                "/orders/search/findBySessionUuidIdAndStatusId", String.class, urlParams);
        assertThat(findNonExistingOrderRes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        createNewOrder(createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION), CART_STATUS_ID);

        ResponseEntity<String> findExistingOrderRes = restTemplate.getForEntity(API_REST_CONTEXT_PATH +
                "/orders/search/findBySessionUuidIdAndStatusId?sessionUuid={sessionUuid}&status={status}", String.class, urlParams);
        assertThat(findExistingOrderRes.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testAddOrderEntryWithProductToOrder() {
        ResponseEntity<Session> createSessionRes = createNewSession();
        ResponseEntity<String> createOrderRes = createNewOrder(createSessionRes.getHeaders().getFirst(HttpHeaders.LOCATION), CART_STATUS_ID);

        Product chicken = productRepository.findByCode("chicken").get();
        JSONObject orderEntryReqJson = new JSONObject().put("amount", 42)
                .put("order", createOrderRes.getHeaders().getFirst(HttpHeaders.LOCATION))
                .put("product", restTemplate.getRestTemplate().getUriTemplateHandler().expand(API_REST_CONTEXT_PATH) + "/products/" + chicken.getId());
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
        assertThat(orderEntryResJson.getInt("amount")).isEqualTo(42);
        JSONObject productResJson = orderEntryResJson.getJSONObject("product");
        assertThat(productResJson.getLong("id")).isEqualTo(chicken.getId());
        assertThat(productResJson.getString("code")).isEqualTo(chicken.getCode());
    }

    // TODO: Test order confirmation logic

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
