package de.admir.taze.model.product.projection;

import de.admir.taze.model.product.Product;
import de.admir.taze.model.product.ProductCard;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "with-product", types = ProductCard.class)
public interface ProductCardWithProduct {
    Long getId();
    String getTitle();
    String getParagraph();
    String getSmall();
    Product getProduct();
}
