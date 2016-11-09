package de.admir.taze.model.product;

import de.admir.taze.model.Card;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCard extends Card {
    @OneToOne(optional = false)
    private Product product;

    @Transient
    public String getTransientProductCode() {
        return product.getCode();
    }
}
