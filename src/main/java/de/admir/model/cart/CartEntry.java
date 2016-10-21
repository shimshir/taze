package de.admir.model.cart;


import de.admir.model.IdentifiableModel;
import de.admir.model.Product;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class CartEntry extends IdentifiableModel {
    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Transient
    public BigDecimal getTotalPrice() {
        return product.getPricePerUnit().multiply(BigDecimal.valueOf(amount));
    }
}
