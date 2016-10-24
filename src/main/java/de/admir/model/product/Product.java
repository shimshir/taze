package de.admir.model.product;

import de.admir.model.IdentifiableModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends IdentifiableModel {
    @Column(nullable = false, unique = true)
    private String code;
    private String name;
    private String listImage;
    private String pdpImage;
    @Column(nullable = false)
    private BigDecimal pricePerUnit;
    @Column(nullable = false)
    private String unitCode;
    private String footnote;
}
