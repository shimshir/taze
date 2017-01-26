package de.admir.taze.controller;

import de.admir.taze.model.order.Order;
import de.admir.taze.service.OrderService;
import de.admir.taze.util.Xor;
import de.admir.taze.util.Error;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderControllerTest {
    private OrderController orderController = new OrderController();
    @Mock
    private OrderService orderServiceMock;
    @Mock
    private Resource<Order> requestOrderResourceMock;

    @Before
    public void injectMocks() {
        orderController.setOrderService(orderServiceMock);
    }

    @Test
    public void testPatchOrderSuccessfulRequest() {
        Order order = new Order();
        when(orderServiceMock.patchOrder(any(), any(), any())).thenReturn(Xor.right(new Order()));
        when(requestOrderResourceMock.getContent()).thenReturn(order);
        assertThat(orderController.patchOrder(1L, requestOrderResourceMock, StringUtils.EMPTY).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testPatchOrderBadRequest() {
        Order order = new Order();
        Error error = new Error("test error");
        when(orderServiceMock.patchOrder(any(), any(), any())).thenReturn(Xor.left(error));
        when(requestOrderResourceMock.getContent()).thenReturn(order);
        ResponseEntity result = orderController.patchOrder(1L, requestOrderResourceMock, StringUtils.EMPTY);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody()).isEqualTo(error);
    }

    @Test
    public void testPutOrderSuccessfulRequest() {
        Order order = new Order();
        when(orderServiceMock.putOrder(any(), any(), any())).thenReturn(Xor.right(new Order()));
        when(requestOrderResourceMock.getContent()).thenReturn(order);
        assertThat(orderController.putOrder(1L, requestOrderResourceMock, StringUtils.EMPTY).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testPutOrderBadRequest() {
        Order order = new Order();
        Error error = new Error("test error");
        when(orderServiceMock.putOrder(any(), any(), any())).thenReturn(Xor.left(error));
        when(requestOrderResourceMock.getContent()).thenReturn(order);
        ResponseEntity result = orderController.putOrder(1L, requestOrderResourceMock, StringUtils.EMPTY);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getBody()).isEqualTo(error);
    }
}
