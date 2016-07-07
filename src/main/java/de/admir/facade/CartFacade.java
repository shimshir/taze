package de.admir.facade;

import de.admir.converter.cart.CartConverter;
import de.admir.converter.cart.CartEntryConverter;
import de.admir.model.cart.CartDto;
import de.admir.model.cart.CartEntry;
import de.admir.model.cart.CartEntryDto;
import de.admir.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CartFacade {
    private static final Logger LOG = LoggerFactory.getLogger(CartFacade.class);

    private CartFacade() {
    }

    public static void removeFromCart(String entryId) {
        CartService.removeFromCart(entryId);
    }

    public static void updateCartEntry(String cartId, String entryId, CartEntryDto cartEntryDto) {
        CartEntry cartEntry = CartEntryConverter.convertFromDto(cartEntryDto, cartId);
        cartEntry.setUuid(entryId);
        CartService.updateCartEntry(cartEntry);
    }

    public static CartDto getCartBySessionId(String sessionId) {
        return CartConverter.convertToDto(CartService.getCartBySessionUuid(sessionId));
    }

    public static CartEntryDto addToCart(String cartId, CartEntryDto cartEntryDto) {
        CartEntry cartEntry = CartEntryConverter.convertFromDto(cartEntryDto, cartId);
        return CartEntryConverter.convertToDto(CartService.createCartEntry(cartEntry));
    }
}
