package de.admir.model.cart;


import de.admir.model.Entry;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CartEntry extends Entry {
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
