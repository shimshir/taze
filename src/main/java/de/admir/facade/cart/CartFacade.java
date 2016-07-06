package de.admir.facade.cart;

import de.admir.model.cart.CartDto;
import de.admir.model.cart.CartEntryDto;
import de.admir.model.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

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

    public static CartEntryDto addToCart(String cartId, CartEntryDto entry) {
        entry.setId(UUID.randomUUID());
        Optional<Boolean> result = getCartById(cartId)
                .map(cart -> cart.getEntries().add(entry));
        if (result.orElse(false))
            LOG.info("Cart entry {} added to cart.", entry);
        else
            LOG.error("Cart entry {} was not added!", entry);
        return entry;
    }

    public static void removeFromCart(String cartId, String entryId) {
        Optional<CartDto> cartDtoOpt = getCartById(cartId);
        if (cartDtoOpt.isPresent()) {
            cartDtoOpt.get().setEntries(
                    cartDtoOpt.get().getEntries().stream()
                            .filter(entry -> !UUID.fromString(entryId).equals(entry.getId()))
                            .collect(Collectors.toList())
            );
        }
    }

    private static Optional<CartDto> getCartById(String cartId) {
        List<CartDto> filteredCarts = cartMap.values().stream()
                .filter(cart -> UUID.fromString(cartId).equals(cart.getId()))
                .collect(Collectors.toList());
        if (filteredCarts.size() > 1)
            throw new IllegalArgumentException("CartId must be unique!");
        else
            return filteredCarts.size() != 0 ? Optional.of(filteredCarts.get(0)) : Optional.empty();
    }
}
