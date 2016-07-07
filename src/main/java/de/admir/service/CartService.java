package de.admir.service;

import de.admir.dao.CartDao;
import de.admir.dao.SessionDao;
import de.admir.model.cart.Cart;
import de.admir.model.cart.CartEntry;
import de.admir.model.session.Session;

import java.util.ArrayList;

public class CartService {
    public static Cart getCartBySessionUuid(String sessionUuid) {
        Session session = SessionDao.findSessionByUuid(sessionUuid).orElse(SessionDao.createSession());
        return CartDao.findCartBySession(session).orElseGet(() -> {
            Cart cart = new Cart();
            cart.setSession(session);
            cart.setEntries(new ArrayList<>());
            CartDao.createCart(cart);
            return cart;
        });
    }

    public static CartEntry createCartEntry(CartEntry cartEntry) {
        return CartDao.createCartEntry(cartEntry);
    }

    public static Cart getCartByUuid(String cartId) {
        return CartDao.findCartByUuid(cartId).orElseThrow(() -> new IllegalArgumentException("Could not find cart for cartId: " + cartId));
    }

    public static void updateCartEntry(CartEntry cartEntry) {
        CartDao.updateCartEntry(cartEntry);
    }

    public static void removeFromCart(String entryId) {
        CartDao.deleteCartEntry(entryId);
    }
}
