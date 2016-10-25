package de.admir.model.order.projection;

import de.admir.model.order.OrderEntry;
import de.admir.model.product.Product;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;


@Projection(name = "with-product", types = OrderEntry.class)
public interface OrderEntryWithProductProjection {
    Long getId();
    int getAmount();
    Product getProduct();
    BigDecimal getTotalPrice();
}
