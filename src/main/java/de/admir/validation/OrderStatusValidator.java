package de.admir.validation;

import de.admir.filter.TokenHeaderFilter;
import de.admir.model.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class OrderStatusValidator implements Validator {
    @Autowired
    private TokenHeaderFilter tokenHeaderFilter;

    @Override
    public boolean supports(Class<?> clazz) {
        return Order.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;
        Optional<String> tokenHeaderOpt = tokenHeaderFilter.getToken();
        boolean isStatusChangeToConfirmed = "confirmed".equalsIgnoreCase(order.getStatus().getCode());
        boolean hasValidConfirmationToken = tokenHeaderOpt.isPresent() &&
                order.getToken() != null &&
                !order.getToken().isUsed() &&
                tokenHeaderOpt.get().equals(order.getToken().getValue());

        if (isStatusChangeToConfirmed && tokenHeaderOpt.isPresent() && !hasValidConfirmationToken) {
            errors.rejectValue("status", null, "No valid confirmation token header present!");
        }
    }
}
