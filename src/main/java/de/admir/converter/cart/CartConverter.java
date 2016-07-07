package de.admir.converter.cart;

import de.admir.model.cart.Cart;
import de.admir.model.cart.CartDto;

import java.util.UUID;

public class CartConverter {

    public static CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(UUID.fromString(cart.getUuid()));
        cartDto.setEntries(CartEntryConverter.convertToDto(cart.getEntries()));
        return cartDto;
    }
}
