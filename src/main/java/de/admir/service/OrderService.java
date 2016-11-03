package de.admir.service;

import de.admir.event.OrderEventHandler;
import de.admir.model.order.ConfirmationToken;
import de.admir.model.order.Order;
import de.admir.repository.ConfirmationTokenRepository;
import de.admir.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.admir.util.Error;
import de.admir.util.Xor;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private OrderEventHandler orderEventHandler;

    public Xor<Error, Order> updateOrder(Long orderId, Order requestOrder, String token, boolean handleSaveEvents) {
        Order persistedOrder = orderRepository.findOne(orderId);
        Xor<Error, Order> updateResult = (persistedOrder == null ? Xor.left(new Error("Order does not exist!")) : Xor.right(persistedOrder));

        return updateResult
                .flatMapRight(order -> requestOrder.getStatus() != null ? handleStatusChange(requestOrder, order, token) : Xor.right(order))
                .mapRight(order -> {
                    if (requestOrder.getCustomer() != null)
                        order.setCustomer(requestOrder.getCustomer());
                    return order;
                })
                .mapRight(order -> {
                    if (handleSaveEvents)
                        orderEventHandler.handleOrderBeforeSave(order);
                    return order;
                })
                .mapRight(order -> {
                    orderRepository.save(order);
                    return order;
                })
                .mapRight(order -> {
                    if (handleSaveEvents)
                        orderEventHandler.handleOrderAfterSave(order);
                    return order;
                });
    }

    private Xor<Error, Order> handleStatusChange(Order requestOrder, Order persistedOrder, String token) {
        ConfirmationToken persistedToken = persistedOrder.getToken();

        boolean isSetToConfirmed = "confirmed".equalsIgnoreCase(requestOrder.getStatus().getCode());
        boolean isTokenAlreadyUsed = persistedToken != null && persistedToken.isUsed();
        boolean hasValidToken = token.equals(persistedToken == null ? null : persistedOrder.getToken().getValue());

        if (isSetToConfirmed && !isTokenAlreadyUsed && !hasValidToken) {
            return Xor.left(new Error("Invalid confirmation token!"));
        } else if (isSetToConfirmed && isTokenAlreadyUsed) {
            return Xor.left(new Error("Order has already been confirmed!"));
        } else {
            persistedOrder.setStatus(requestOrder.getStatus());
            return Xor.right(persistedOrder);
        }
    }
}
