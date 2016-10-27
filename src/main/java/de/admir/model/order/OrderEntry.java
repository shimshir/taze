package de.admir.model.order;

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
public class OrderEntry extends IdentifiableModel {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @OneToOne
    @JoinColumn(nullable = false)
    private Product product;
    private int amount;

    @Transient
    public BigDecimal getTotalPrice() {
        return product == null ? BigDecimal.ZERO : product.getPricePerUnit().multiply(BigDecimal.valueOf(amount));
    }
}
