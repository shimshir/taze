package de.admir.taze.service;


import de.admir.taze.event.OrderEventHandler;
import de.admir.taze.model.Customer;
import de.admir.taze.model.PickupTypeEnum;
import de.admir.taze.model.Uuid;
import de.admir.taze.model.order.ConfirmationToken;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.repository.OrderRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.core.NestedRuntimeException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
    private OrderService orderService = new OrderService();
    @Mock
    private OrderEventHandler orderEventHandlerMock;
    @Mock
    private OrderRepository orderRepositoryMock;

    private static final Long ORDER_ID = 1L;

    @Before
    public void initMocks() {
        orderService.setOrderRepository(orderRepositoryMock);
        orderService.setOrderEventHandler(orderEventHandlerMock);
    }

    @Test
    public void testPatchPutNonExistingOrder() {
        assertThat(orderService.patchOrder(ORDER_ID, new Order(), StringUtils.EMPTY, true).isLeft()).isTrue();
        assertThat(orderService.putOrder(ORDER_ID, new Order(), StringUtils.EMPTY, true).isLeft()).isTrue();
    }

    @Test
    public void testPatch() {
        Order requestOrder = new Order();
        requestOrder.setCustomer(new Customer());
        requestOrder.setPickupType(PickupTypeEnum.COLLECT);
        Order persistedOrder = new Order();

        when(orderRepositoryMock.findOne(ORDER_ID)).thenReturn(persistedOrder);
        when(orderRepositoryMock.save(persistedOrder)).thenReturn(requestOrder);

        Xor<Error, Order> result = orderService.patchOrder(ORDER_ID, requestOrder, StringUtils.EMPTY, true);
        assertThat(result.isRight()).isTrue();
        assertThat(result.getRight()).isEqualTo(requestOrder);
    }

    @Test
    public void testPatchError() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();

        String testErrorMessage = "test error message";

        NestedRuntimeException nestedExcMock = mock(NestedRuntimeException.class);
        Exception specExcMock = mock(Exception.class);
        when(nestedExcMock.getMostSpecificCause()).thenReturn(specExcMock);
        when(specExcMock.getMessage()).thenReturn(testErrorMessage);

        when(orderRepositoryMock.findOne(ORDER_ID)).thenReturn(persistedOrder);
        when(orderRepositoryMock.save(persistedOrder)).thenThrow(nestedExcMock);

        Xor<Error, Order> result = orderService.patchOrder(ORDER_ID, requestOrder, StringUtils.EMPTY, true);
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft().getMessage()).isEqualTo(testErrorMessage);
    }

    @Test
    public void testHandleStatusChangeFromNullToNull() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
        assertThat(statusChangeResult.getRight()).isEqualTo(persistedOrder);
    }

    @Test
    public void testHandleStatusChangeFromNullToCart() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.CART);

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
        assertThat(statusChangeResult.getRight().getStatus()).isEqualTo(OrderStatusEnum.CART);
    }

    @Test
    public void testHandleStatusChangeFromCartToOrdered() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.ORDERED);
        persistedOrder.setStatus(OrderStatusEnum.CART);

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
        assertThat(statusChangeResult.getRight().getStatus()).isEqualTo(OrderStatusEnum.ORDERED);
    }

    @Test
    public void testHandleStatusChangeToConfirmedInvalidToken() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.CONFIRMED);

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isLeft()).isTrue();
        assertThat(statusChangeResult.getLeft().getMessage()).isEqualTo(OrderService.INVALID_TOKEN_MESSAGE);
    }

    @Test
    public void testHandleStatusChangeToConfirmedUsedToken() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();

        requestOrder.setStatus(OrderStatusEnum.CONFIRMED);

        ConfirmationToken token = new ConfirmationToken();
        token.setUsed(true);
        Uuid uuid = new Uuid();
        String uuidString = UUID.randomUUID().toString();
        uuid.setId(uuidString);
        token.setValue(uuid);
        persistedOrder.setToken(token);

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, uuidString);
        assertThat(statusChangeResult.isLeft()).isTrue();
        assertThat(statusChangeResult.getLeft().getMessage()).isEqualTo(OrderService.USED_TOKEN_MESSAGE);
    }

    @Test
    public void testHandleStatusChangeToConfirmedValidToken() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();

        requestOrder.setStatus(OrderStatusEnum.CONFIRMED);

        ConfirmationToken token = new ConfirmationToken();
        token.setUsed(false);
        Uuid uuid = new Uuid();
        String uuidString = UUID.randomUUID().toString();
        uuid.setId(uuidString);
        token.setValue(uuid);
        persistedOrder.setToken(token);

        Xor<Error, Order> statusChangeResult = OrderService.handleStatusChange(requestOrder, persistedOrder, uuidString);
        assertThat(statusChangeResult.isRight()).isTrue();
        assertThat(statusChangeResult.getRight().getStatus()).isEqualTo(OrderStatusEnum.CONFIRMED);
    }
}
