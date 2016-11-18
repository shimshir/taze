package de.admir.taze.controller;

import de.admir.taze.model.order.Order;

import de.admir.taze.service.OrderService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RepositoryRestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "/orders/{id}", method = PATCH)
    public ResponseEntity<?> patchOrder(@PathVariable("id") Long orderId,
                                        @RequestBody Resource<Order> requestOrderResource,
                                        @RequestHeader(value = "X-Confirmation-Token", required = false, defaultValue = StringUtils.EMPTY) String tokenString) {
        return orderService.patchOrder(orderId, requestOrderResource.getContent(), tokenString, true).fold(
                error -> ResponseEntity.badRequest().body(error),
                order -> ResponseEntity.noContent().build());
    }

    @RequestMapping(path = "/orders/{id}", method = PUT)
    public ResponseEntity<?> putOrder(@PathVariable("id") Long orderId,
                                      @RequestBody Resource<Order> requestOrderResource,
                                      @RequestHeader(value = "X-Confirmation-Token", required = false, defaultValue = StringUtils.EMPTY) String tokenString) {
        return orderService.putOrder(orderId, requestOrderResource.getContent(), tokenString, true).fold(
                error -> ResponseEntity.badRequest().body(error),
                order -> ResponseEntity.noContent().build());
    }
}
