package de.admir.model.cart;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.admir.model.ProductDto;

import java.util.UUID;

public class CartEntryDto {
    @JsonIgnore
    private UUID id;
    private ProductDto product;
    private int amount;

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public void setId(UUID id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartEntryDto{" +
                "id=" + id +
                ", productCode=" + product.getCode() +
                ", amount=" + amount +
                '}';
    }
}
