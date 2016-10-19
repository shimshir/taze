package de.admir.model.cart;


import de.admir.model.IdentifiableModel;
import de.admir.model.Product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CartEntry extends IdentifiableModel {
    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    private int amount;
    @ManyToOne
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
}
