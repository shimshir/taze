package de.admir.taze.service;


import de.admir.taze.model.Customer;
import de.admir.taze.model.PickupTypeEnum;
import de.admir.taze.model.Uuid;
import de.admir.taze.model.order.ConfirmationToken;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.repository.ConfirmationTokenRepository;
import de.admir.taze.repository.OrderRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.core.NestedRuntimeException;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
    private OrderService orderService = new OrderService();
    @Mock
    private OrderRepository orderRepositoryMock;
    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    private static final Long ORDER_ID = 1L;

    @Before
    public void initMocks() {
        orderService.setOrderRepository(orderRepositoryMock);
        orderService.setConfirmationTokenRepository(confirmationTokenRepository);
    }

    @Test
    public void testPatchPutNonExistingOrder() {
        assertThat(orderService.patchOrder(ORDER_ID, new Order(), StringUtils.EMPTY).isLeft()).isTrue();
        when(confirmationTokenRepository.findByValueId(anyString())).thenReturn(Optional.empty());
        assertThat(orderService.putOrder(ORDER_ID, new Order(), StringUtils.EMPTY).isLeft()).isTrue();
    }

    @Test
    public void testPatch() {
        Order requestOrder = new Order();
        Customer customer = new Customer();
        Date clientTime = new Date();

        requestOrder.setStatus(OrderStatusEnum.CART);
        requestOrder.setCustomer(customer);
        requestOrder.setPickupType(PickupTypeEnum.COLLECT);
        requestOrder.setClientTime(clientTime);

        Order persistedOrder = mock(Order.class);

        when(orderRepositoryMock.findOne(ORDER_ID)).thenReturn(persistedOrder);

        orderService.patchOrder(ORDER_ID, requestOrder, StringUtils.EMPTY);

        ArgumentCaptor<OrderStatusEnum> orderStatusArgumentCaptor = ArgumentCaptor.forClass(OrderStatusEnum.class);
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        ArgumentCaptor<PickupTypeEnum> pickUpTypeArgumentCaptor = ArgumentCaptor.forClass(PickupTypeEnum.class);
        ArgumentCaptor<Date> clientTimeArgumentCaptor = ArgumentCaptor.forClass(Date.class);

        verify(persistedOrder, times(1)).setStatus(orderStatusArgumentCaptor.capture());
        assertThat(orderStatusArgumentCaptor.getValue()).isEqualTo(OrderStatusEnum.CART);

        verify(persistedOrder, times(1)).setCustomer(customerArgumentCaptor.capture());
        assertThat(customerArgumentCaptor.getValue()).isEqualTo(customer);

        verify(persistedOrder, times(1)).setPickupType(pickUpTypeArgumentCaptor.capture());
        assertThat(pickUpTypeArgumentCaptor.getValue()).isEqualTo(PickupTypeEnum.COLLECT);

        verify(persistedOrder, times(1)).setClientTime(clientTimeArgumentCaptor.capture());
        assertThat(clientTimeArgumentCaptor.getValue()).isEqualTo(clientTime);
    }

    @Test
    public void testPatchToOrdered() {
        Order requestOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.ORDERED);
        Order persistedOrder = mock(Order.class);

        when(orderRepositoryMock.findOne(ORDER_ID)).thenReturn(persistedOrder);

        orderService.patchOrder(ORDER_ID, requestOrder, StringUtils.EMPTY);

        ArgumentCaptor<OrderStatusEnum> orderStatusArgumentCaptor = ArgumentCaptor.forClass(OrderStatusEnum.class);
        verify(persistedOrder, times(1)).setStatus(orderStatusArgumentCaptor.capture());
        assertThat(orderStatusArgumentCaptor.getValue()).isEqualTo(OrderStatusEnum.ORDERED);
    }

    @Test
    public void testPatchToConfirmed() {
        Order requestOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.CONFIRMED);
        Order persistedOrder = mock(Order.class);

        when(orderRepositoryMock.findOne(ORDER_ID)).thenReturn(persistedOrder);

        String uuidString = UUID.randomUUID().toString();

        ConfirmationToken mockToken = mock(ConfirmationToken.class);
        Uuid mockUuid = mock(Uuid.class);

        when(persistedOrder.getToken()).thenReturn(mockToken);
        when(mockToken.getValue()).thenReturn(mockUuid);
        when(mockUuid.getId()).thenReturn(uuidString);

        orderService.patchOrder(ORDER_ID, requestOrder, uuidString);

        ArgumentCaptor<OrderStatusEnum> orderArgumentCaptor = ArgumentCaptor.forClass(OrderStatusEnum.class);
        verify(persistedOrder, times(1)).setStatus(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue()).isEqualTo(OrderStatusEnum.CONFIRMED);
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

        Xor<Error, Order> result = orderService.patchOrder(ORDER_ID, requestOrder, StringUtils.EMPTY);
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft().getMessage()).isEqualTo(testErrorMessage);
    }

    @Test
    public void testCheckIfValidStatusChangeFromNullToNull() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
    }

    @Test
    public void testCheckIfValidStatusChangeFromNullToCart() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.CART);

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
    }

    @Test
    public void testCheckIfValidStatusChangeFromCartToOrdered() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.ORDERED);
        persistedOrder.setStatus(OrderStatusEnum.CART);

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isRight()).isTrue();
    }

    @Test
    public void testCheckIfValidStatusChangeToConfirmedInvalidToken() {
        Order requestOrder = new Order();
        Order persistedOrder = new Order();
        requestOrder.setStatus(OrderStatusEnum.CONFIRMED);

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, StringUtils.EMPTY);
        assertThat(statusChangeResult.isLeft()).isTrue();
        assertThat(statusChangeResult.getLeft().getMessage()).isEqualTo(OrderService.INVALID_TOKEN_MESSAGE);
    }

    @Test
    public void testCheckIfValidStatusChangeToConfirmedUsedToken() {
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

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, uuidString);
        assertThat(statusChangeResult.isLeft()).isTrue();
        assertThat(statusChangeResult.getLeft().getMessage()).isEqualTo(OrderService.USED_TOKEN_MESSAGE);
    }

    @Test
    public void testCheckIfValidStatusChangeToConfirmedValidToken() {
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

        Xor<Error, Void> statusChangeResult = OrderService.checkIfValidStatusChange(requestOrder, persistedOrder, uuidString);
        assertThat(statusChangeResult.isRight()).isTrue();
    }
}
