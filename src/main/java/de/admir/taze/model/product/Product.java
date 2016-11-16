package de.admir.taze.model.product;

import de.admir.taze.model.IdentifiableModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends IdentifiableModel {
    @Column(nullable = false, unique = true)
    @NotNull
    private String code;
    @Column(nullable = false)
    @NotNull
    private String name;
    private String listImage;
    private String pdpImage;
    @Column(nullable = false)
    @NotNull
    private BigDecimal pricePerUnit;
    @Column(nullable = false)
    @NotNull
    private String unitCode;
    private String footnote;
    @OneToOne(mappedBy = "product")
    private ProductCard productCard;
}
