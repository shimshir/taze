package de.admir.taze.service;

import de.admir.taze.event.OrderEventHandler;
import de.admir.taze.model.order.ConfirmationToken;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.repository.OrderRepository;
import de.admir.taze.util.Error;
import de.admir.taze.util.Xor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    static final String INVALID_TOKEN_MESSAGE = "Invalid confirmation token!";
    static final String USED_TOKEN_MESSAGE = "Order has already been confirmed!";
    //setter inject
    private OrderRepository orderRepository;
    //setter inject
    private OrderEventHandler orderEventHandler;

    public Xor<Error, Order> patchOrder(Long orderId, Order requestOrder, String tokenString, boolean handleSaveEvents) {
        Order persistedOrder = orderRepository.findOne(orderId);
        Xor<Error, Order> patchResult = (persistedOrder == null ? Xor.left(new Error("Order does not exist!")) : Xor.right(persistedOrder));

        return patchResult
                .flatMapRight(order -> requestOrder.getStatus() != null ? handleStatusChange(requestOrder, persistedOrder, tokenString) : Xor.right(order))
                .mapRight(order -> {
                    if (requestOrder.getCustomer() != null)
                        order.setCustomer(requestOrder.getCustomer());
                    if (requestOrder.getPickupType() != null)
                        order.setPickupType(requestOrder.getPickupType());
                    return order;
                })
                .flatMapRight(order -> saveOrder(order, handleSaveEvents));
    }

    public Xor<Error, Order> putOrder(Long orderId, Order requestOrder, String tokenString, boolean handleSaveEvents) {
        requestOrder.setId(orderId);
        Order persistedOrder = orderRepository.findOne(orderId);
        Xor<Error, Order> putResult = (persistedOrder == null ? Xor.left(new Error("Order does not exist!")) : Xor.right(requestOrder));

        return putResult
                .flatMapRight(order -> requestOrder.getStatus() != null ? handleStatusChange(requestOrder, persistedOrder, tokenString) : Xor.right(order))
                .flatMapRight(order -> saveOrder(order, handleSaveEvents));
    }

    Xor<Error, Order> saveOrder(Order order, boolean handleSaveEvents) {
        return Xor.catchNonFatal(() -> {
            if (handleSaveEvents)
                orderEventHandler.handleOrderBeforeSave(order);
            Order savedOrder = orderRepository.save(order);
            if (handleSaveEvents)
                orderEventHandler.handleOrderAfterSave(savedOrder);
            return savedOrder;
        }).mapLeft(e ->
                NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                        new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                        new Error(e)
        );
    }

    static Xor<Error, Order> handleStatusChange(Order requestOrder, Order persistedOrder, String tokenString) {
        ConfirmationToken persistedToken = persistedOrder.getToken();

        boolean isSetToConfirmed = OrderStatusEnum.CONFIRMED.equals(requestOrder.getStatus());
        boolean isTokenAlreadyUsed = persistedToken != null && persistedToken.isUsed();
        boolean hasValidToken = tokenString.equals(persistedToken == null ? null : persistedOrder.getToken().getValue().getId());

        if (isSetToConfirmed && !isTokenAlreadyUsed && !hasValidToken) {
            return Xor.left(new Error(INVALID_TOKEN_MESSAGE));
        } else if (isSetToConfirmed && isTokenAlreadyUsed) {
            return Xor.left(new Error(USED_TOKEN_MESSAGE));
        } else {
            persistedOrder.setStatus(requestOrder.getStatus());
            return Xor.right(persistedOrder);
        }
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderEventHandler(OrderEventHandler orderEventHandler) {
        this.orderEventHandler = orderEventHandler;
    }
}
