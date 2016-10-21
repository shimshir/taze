package de.admir.model.product;

import de.admir.model.Card;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCard extends Card {
    @OneToOne(optional = false)
    private Product product;
}
