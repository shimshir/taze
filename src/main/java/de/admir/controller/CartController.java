package de.admir.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spencerwi.either.Either;

import de.admir.model.cart.Cart;
import de.admir.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.admir.util.Error;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static de.admir.Constants.API_CUSTOM_BASE_PATH;
import static de.admir.Constants.API_REST_BASE_PATH;

@Controller
@RequestMapping(path = API_CUSTOM_BASE_PATH + "/carts")
class CartController {
    private final static Logger LOG = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createCart(@RequestParam("sessionUuid") String sessionUuid, HttpServletRequest request, HttpServletResponse response) {

        Either<Error, Cart> cartEth = cartService.createNewCart(sessionUuid);
        return cartEth.fold(error -> {
            LOG.error(error.getMessage());
            response.setStatus(HttpStatus.NOT_FOUND.value());
            try {
                response.getWriter().write(objectMapper.writeValueAsString(error));
                response.setContentType("application/json");
            } catch (IOException e) {
                LOG.error("Could not write error to response.", e);
            }
            return null;
        }, cart -> {
            request.getParameterMap().forEach(request::setAttribute);
            return "forward:" + API_REST_BASE_PATH + "/carts/search/findBySessionUuidValue";
        });
    }
}
