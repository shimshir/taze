package de.admir.model;

import de.admir.model.product.Product;

import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Entry extends IdentifiableModel {
    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    private int amount;

    @Transient
    public BigDecimal getTotalPrice() {
        return product.getPricePerUnit().multiply(BigDecimal.valueOf(amount));
    }
}
