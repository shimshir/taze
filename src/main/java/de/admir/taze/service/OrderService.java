package de.admir.taze.service;

import de.admir.taze.model.order.ConfirmationToken;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderStatusEnum;
import de.admir.taze.repository.ConfirmationTokenRepository;
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
    private ConfirmationTokenRepository confirmationTokenRepository;
    //setter inject
    private MailService mailService;

    public Xor<Error, Order> patchOrder(Long orderId, Order requestOrder, String tokenString) {
        Order persistedOrder = orderRepository.findOne(orderId);
        if (persistedOrder == null)
            return Xor.left(new Error("Order does not exist!"));

        if (requestOrder.getStatus() != null) {
            Xor<Error, Void> statusChangeValidationResult = checkIfValidStatusChange(requestOrder, persistedOrder, tokenString);
            if (statusChangeValidationResult.isLeft())
                return Xor.left(statusChangeValidationResult.getLeft());
            else
                persistedOrder.setStatus(requestOrder.getStatus());
        }

        if (requestOrder.getCustomer() != null)
            persistedOrder.setCustomer(requestOrder.getCustomer());
        if (requestOrder.getPickupType() != null)
            persistedOrder.setPickupType(requestOrder.getPickupType());
        if (requestOrder.getClientOrderTimeString() != null)
            persistedOrder.setClientOrderTimeString(requestOrder.getClientOrderTimeString());

        return saveOrder(persistedOrder);
    }

    public Xor<Error, Order> putOrder(Long orderId, Order requestOrder, String tokenString) {

        requestOrder.setId(orderId);
        ConfirmationToken persistedToken = confirmationTokenRepository.findByValueId(tokenString).orElseGet(() -> null);
        requestOrder.setToken(persistedToken);

        Order persistedOrder = orderRepository.findOne(orderId);

        if (persistedOrder == null) {
            return Xor.left(new Error("Order does not exist!"));
        }

        if (requestOrder.getStatus() != null) {
            Xor<Error, Void> statusChangeValidationResult = checkIfValidStatusChange(requestOrder, persistedOrder, tokenString);
            if (statusChangeValidationResult.isLeft())
                return Xor.left(statusChangeValidationResult.getLeft());
        }

        return saveOrder(requestOrder);
    }

    private Xor<Error, Order> saveOrder(Order order) {
        return Xor.catchNonFatal(() -> {
            if (OrderStatusEnum.ORDERED.equals(order.getStatus()))
                order.setToken(confirmationTokenRepository.save(new ConfirmationToken()));

            Order savedOrder = orderRepository.save(order);

            if (OrderStatusEnum.ORDERED.equals(savedOrder.getStatus())) {
                mailService.sendConfirmationEmail(savedOrder.getId());
            }
            if (OrderStatusEnum.CONFIRMED.equals(savedOrder.getStatus())) {
                ConfirmationToken token = savedOrder.getToken();
                token.setUsed(true);
                confirmationTokenRepository.save(token);
            }
            return savedOrder;
        }).mapLeft(e ->
                NestedRuntimeException.class.isAssignableFrom(e.getClass()) ?
                        new Error(((NestedRuntimeException) e).getMostSpecificCause().getMessage()) :
                        new Error(e)
        );
    }

    static Xor<Error, Void> checkIfValidStatusChange(Order requestOrder, Order persistedOrder, String tokenString) {
        ConfirmationToken persistedToken = persistedOrder.getToken();

        boolean isSetToConfirmed = OrderStatusEnum.CONFIRMED.equals(requestOrder.getStatus());
        boolean isTokenAlreadyUsed = persistedToken != null && persistedToken.isUsed();
        boolean hasValidToken = tokenString.equals(persistedToken == null ? null : persistedOrder.getToken().getValue().getId());

        if (isSetToConfirmed && !isTokenAlreadyUsed && !hasValidToken) {
            return Xor.left(new Error(INVALID_TOKEN_MESSAGE));
        } else if (isSetToConfirmed && isTokenAlreadyUsed) {
            return Xor.left(new Error(USED_TOKEN_MESSAGE));
        } else {
            return Xor.right(null);
        }
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
