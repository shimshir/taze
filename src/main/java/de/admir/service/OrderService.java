package de.admir.service;

import de.admir.event.OrderEventHandler;
import de.admir.model.order.ConfirmationToken;
import de.admir.model.order.Order;
import de.admir.repository.ConfirmationTokenRepository;
import de.admir.repository.OrderRepository;
import de.admir.util.Error;
import de.admir.util.Xor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private OrderEventHandler orderEventHandler;

    public Xor<Error, Order> patchOrder(Long orderId, Order requestOrder, String token, boolean handleSaveEvents) {
        Order persistedOrder = orderRepository.findOne(orderId);
        Xor<Error, Order> patchResult = (persistedOrder == null ? Xor.left(new Error("Order does not exist!")) : Xor.right(persistedOrder));

        return patchResult
                .flatMapRight(order -> requestOrder.getStatus() != null ? handleStatusChange(requestOrder, order, token) : Xor.right(order))
                .mapRight(order -> {
                    if (requestOrder.getCustomer() != null)
                        order.setCustomer(requestOrder.getCustomer());
                    return order;
                })
                .mapRight(order -> {
                    if (requestOrder.getPickupType() != null)
                        order.setPickupType(requestOrder.getPickupType());
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

    public Xor<Error, Order> putOrder(Long orderId, Order requestOrder, String token, boolean handleSaveEvents) {
        requestOrder.setId(orderId);
        Xor<Error, Order> putResult = Xor.right(requestOrder);

        return putResult
                .flatMapRight(order -> requestOrder.getStatus() != null ? handleStatusChange(requestOrder, order, token) : Xor.right(order))
                .mapRight(order -> {
                    if (handleSaveEvents)
                        orderEventHandler.handleOrderBeforeSave(order);
                    return order;
                })
                .flatMapRight(order -> {
                    try {
                        orderRepository.save(order);
                        return Xor.right(order);
                    } catch (TransactionSystemException | JpaObjectRetrievalFailureException e) {
                        return Xor.left(new Error(e.getMostSpecificCause().getMessage()));
                    }
                })
                .mapRight(order -> {
                    if (handleSaveEvents)
                        orderEventHandler.handleOrderAfterSave(order);
                    return order;
                });
    }

    private Xor<Error, Order> handleStatusChange(Order requestOrder, Order persistedOrder, String token) {
        ConfirmationToken persistedToken = persistedOrder.getToken();

        boolean isSetToConfirmed = "confirmed".equalsIgnoreCase(requestOrder.getStatus().getId());
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
