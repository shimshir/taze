package de.admir.service;

import com.spencerwi.either.Either;
import de.admir.event.OrderEventHandler;
import de.admir.model.order.ConfirmationToken;
import de.admir.model.order.Order;
import de.admir.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.admir.util.Error;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEventHandler orderEventHandler;

    public Either<Error, Order> updateOrder(Long orderId, Order requestOrder, String token) {
        Order persistedOrder = orderRepository.findOne(orderId);

        if (requestOrder.getStatus() != null) {
            ConfirmationToken persistedToken = persistedOrder.getToken();

            boolean isSetToConfirmed = "confirmed".equalsIgnoreCase(requestOrder.getStatus().getCode());
            boolean isTokenAlreadyUsed = persistedToken != null && persistedToken.isUsed();
            boolean hasValidToken = token.equals(persistedToken == null ? null : persistedOrder.getToken().getValue());

            if (isSetToConfirmed && !isTokenAlreadyUsed && !hasValidToken) {
                return Either.left(new Error("Invalid confirmation token!"));
            } else if (isSetToConfirmed && isTokenAlreadyUsed) {
                return Either.left(new Error("Order has already been confirmed!"));
            } else {
                persistedOrder.setStatus(requestOrder.getStatus());
            }
        }

        if (requestOrder.getCustomer() != null)
            persistedOrder.setCustomer(requestOrder.getCustomer());

        orderEventHandler.handleOrderBeforeSave(persistedOrder);
        orderRepository.save(persistedOrder);
        orderEventHandler.handleOrderAfterSave(persistedOrder);

        return Either.right(persistedOrder);
    }
}
