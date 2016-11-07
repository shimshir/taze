package de.admir.taze.admin.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.admir.taze.model.product.Product;
import java.math.BigDecimal;

public class ProductForm {
    @JsonIgnore
    private Product product = new Product();

    private String pdpImageData;
    private String listImageData;

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public String getCode() {
        return product.getCode();
    }

    public void setCode(String code) {
        product.setCode(code);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }

    public String getListImage() {
        return product.getListImage();
    }

    public void setListImage(String listImage) {
        product.setListImage(listImage);
    }

    public String getPdpImage() {
        return product.getPdpImage();
    }

    public void setPdpImage(String pdpImage) {
        product.setPdpImage(pdpImage);
    }

    public BigDecimal getPricePerUnit() {
        return product.getPricePerUnit();
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        product.setPricePerUnit(pricePerUnit);
    }

    public String getUnitCode() {
        return product.getUnitCode();
    }

    public void setUnitCode(String unitCode) {
        product.setUnitCode(unitCode);
    }

    public String getFootnote() {
        return product.getFootnote();
    }

    public void setFootnote(String footnote) {
        product.setFootnote(footnote);
    }

    public String getPdpImageData() {
        return pdpImageData;
    }

    public void setPdpImageData(String pdpImageData) {
        this.pdpImageData = pdpImageData;
    }

    public String getListImageData() {
        return listImageData;
    }

    public void setListImageData(String listImageData) {
        this.listImageData = listImageData;
    }
}
