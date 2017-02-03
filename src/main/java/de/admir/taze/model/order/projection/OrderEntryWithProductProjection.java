package de.admir.taze.model.order.projection;

import de.admir.taze.model.order.OrderEntry;
import de.admir.taze.model.product.Product;

import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;


@Projection(name = "with-product", types = OrderEntry.class)
public interface OrderEntryWithProductProjection {
    Long getId();
    int getAmount();
    Product getProduct();
    BigDecimal getTotalPrice();
    Long getOrderId();
}
