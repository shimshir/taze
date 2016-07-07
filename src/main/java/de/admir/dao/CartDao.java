package de.admir.dao;

import de.admir.model.cart.Cart;
import de.admir.model.cart.CartEntry;
import de.admir.model.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CartDao {
    private static final List<Cart> carts = new ArrayList<>();

    public static Optional<Cart> findCartBySession(Session session) {
        return carts.stream()
                .filter(cart -> session.equals(cart.getSession()))
                .findFirst();
    }

    public static Cart createCart(Cart cart) {
        cart.setUuid(UUID.randomUUID().toString());
        carts.add(cart);
        return cart;
    }

    public static Optional<Cart> findCartByUuid(String cartUuid) {
        return carts.stream()
                .filter(cart -> cartUuid.equals(cart.getUuid()))
                .findFirst();
    }

    public static CartEntry createCartEntry(CartEntry cartEntry) {
        cartEntry.setUuid(UUID.randomUUID().toString());
        findCartByUuid(cartEntry.getCart().getUuid())
                .orElseThrow(() -> new IllegalArgumentException("No cart found for cartEntry"))
                .getEntries().add(cartEntry);
        return cartEntry;
    }

    public static void updateCartEntry(CartEntry cartEntry) {
        findCartByUuid(cartEntry.getCart().getUuid())
                .orElseThrow(() -> new IllegalArgumentException("No cart found for cartEntry"))
                .getEntries()
                .replaceAll(entry -> entry.getUuid().equals(cartEntry.getUuid()) ? cartEntry : entry);
    }

    public static void deleteCartEntry(String entryId) {
        for (Cart cart : carts) {
            if (cart.getEntries().removeIf(cartEntry -> entryId.equals(cartEntry.getUuid())))
                break;
        }
    }
}
