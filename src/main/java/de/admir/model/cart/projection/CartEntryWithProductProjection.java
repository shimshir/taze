package de.admir.model.cart.projection;

import de.admir.model.product.Product;
import de.admir.model.cart.CartEntry;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;


@Projection(name = "with-product", types = CartEntry.class)
public interface CartEntryWithProductProjection {
    Long getId();
    int getAmount();
    Product getProduct();
    BigDecimal getTotalPrice();
}
