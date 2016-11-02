package de.admir.controller;

import de.admir.model.order.Order;
import de.admir.repository.OrderRepository;

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

@RepositoryRestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(path = "/orders/{id}", method = PATCH)
    public ResponseEntity<?> patchOrder(@PathVariable("id") Long orderId,
                                        @RequestBody Resource<Order> requestOrderResource,
                                        @RequestHeader(value = "X-Confirmation-Token", required = false, defaultValue = StringUtils.EMPTY) String token) {
        Order requestOrder = requestOrderResource.getContent();
        Order persistedOrder = orderRepository.findOne(orderId);

        // TODO: Check if the request has a valid confirmation token

        if (requestOrder.getCustomer() != null)
            persistedOrder.setCustomer(requestOrder.getCustomer());
        if (requestOrder.getStatus() != null && !"confirmed".equalsIgnoreCase(requestOrder.getStatus().getCode()))
            persistedOrder.setStatus(requestOrder.getStatus());

        orderRepository.save(persistedOrder);
        return ResponseEntity.noContent().build();
    }
}
