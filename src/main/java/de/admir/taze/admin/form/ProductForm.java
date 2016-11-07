package de.admir.taze.admin.form;

import de.admir.taze.model.product.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// TODO: Maybe use composition instead of inheritance
public class ProductForm extends Product {
    private String pdpImageData;
    private String listImageData;
}
