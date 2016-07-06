package de.admir.facade.cart;

import de.admir.model.cart.CartDto;
import de.admir.model.cart.CartEntryDto;
import de.admir.model.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CartFacade {
    private static final Logger LOG = LoggerFactory.getLogger(CartFacade.class);
    private static Map<Session, CartDto> cartMap = new HashMap<>();

    private CartFacade() {}

    public static CartDto getCartBySession(Session session) {
        if (cartMap.containsKey(session))
            return cartMap.get(session);
        else {
            CartDto cart = new CartDto();
            cart.setId(UUID.randomUUID());
            cartMap.put(session, cart);
            return cart;
        }
    }

    public static void addToCart(String cartUuid, CartEntryDto entry) {
        Optional<Boolean> result = cartMap.values().stream()
                .filter(cart -> UUID.fromString(cartUuid).equals(cart.getId()))
                .findFirst()
                .map(cart -> cart.getEntries().add(entry));
        if (result.orElse(false))
            LOG.info("Entry added to cart.");
        else
            LOG.error("Cart entry was not added!");
    }
}
