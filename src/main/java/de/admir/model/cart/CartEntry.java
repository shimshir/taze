package de.admir.model.cart;


import de.admir.model.IdentifiableModel;
import de.admir.model.product.Product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CartEntry extends IdentifiableModel {
    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Transient
    public BigDecimal getTotalPrice() {
        return product.getPricePerUnit().multiply(BigDecimal.valueOf(amount));
    }
}
