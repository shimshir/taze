package de.admir.converter.cart;

import de.admir.converter.ProductConverter;
import de.admir.model.cart.CartEntry;
import de.admir.model.cart.CartEntryDto;
import de.admir.service.CartService;
import de.admir.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CartEntryConverter {

    public static List<CartEntryDto> convertToDto(List<CartEntry> entries) {
        return entries.stream().map(cartEntry -> {
            CartEntryDto cartEntryDto = new CartEntryDto();
            cartEntryDto.setId(UUID.fromString(cartEntry.getUuid()));
            cartEntryDto.setProduct(ProductConverter.convertToDto(cartEntry.getProduct()));
            cartEntryDto.setAmount(cartEntry.getAmount());
            return cartEntryDto;
        }).collect(Collectors.toList());
    }

    public static CartEntryDto convertToDto(CartEntry cartEntry) {
        return convertToDto(Collections.singletonList(cartEntry)).get(0);
    }

    public static CartEntry convertFromDto(CartEntryDto cartEntryDto, String cartId) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setProduct(ProductService.getProductByCode(cartEntryDto.getProduct().getCode()));
        cartEntry.setAmount(cartEntryDto.getAmount());
        cartEntry.setCart(CartService.getCartByUuid(cartId));
        return cartEntry;
    }
}

