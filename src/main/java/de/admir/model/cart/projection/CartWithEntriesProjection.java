package de.admir.model.cart.projection;

import de.admir.model.cart.Cart;
import de.admir.model.cart.CartEntry;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;

@Projection(name = "with-entries", types = Cart.class)
public interface CartWithEntriesProjection {
    Long getId();
    Collection<CartEntry> getEntries();
}
