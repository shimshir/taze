package de.admir.service;

import com.spencerwi.either.Either;

import de.admir.model.cart.Cart;
import de.admir.repository.rest.CartRepository;
import de.admir.repository.rest.SessionRepository;
import de.admir.util.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final static Logger LOG = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Either<Error, Cart> createNewCart(String sessionUuid) {
        Optional<Cart> cartOpt = sessionRepository.findByUuidValue(sessionUuid)
                .map(session -> cartRepository.save(new Cart(session)));
        if (cartOpt.isPresent()) {
            LOG.info("Created cart for sessionUuid: " + sessionUuid);
            return Either.right(cartOpt.get());
        }
        else
            return Either.left(new Error("Could not create cart for sessionUuid: " + sessionUuid));
    }
}
