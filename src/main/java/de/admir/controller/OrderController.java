package de.admir.controller;

import com.spencerwi.either.Either;
import de.admir.model.order.Order;

import de.admir.service.OrderService;
import de.admir.util.Error;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RepositoryRestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "/orders/{id}", method = PATCH)
    public ResponseEntity<?> patchOrder(@PathVariable("id") Long orderId,
                                        @RequestBody Resource<Order> requestOrderResource,
                                        @RequestHeader(value = "X-Confirmation-Token", required = false, defaultValue = StringUtils.EMPTY) String token) {
        return orderService.updateOrder(orderId, requestOrderResource.getContent(), token).fold(
                error -> ResponseEntity.badRequest().body(error),
                order -> ResponseEntity.noContent().build());
    }
}
