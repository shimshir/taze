package de.admir.taze.model.product;

import de.admir.taze.model.IdentifiableEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCard extends IdentifiableEntity {
    @OneToOne(optional = false)
    private Product product;
    private String title;
    private String paragraph;
    private String small;
    private String image;

    @Transient
    public String getTransientProductCode() {
        return product.getCode();
    }
}
