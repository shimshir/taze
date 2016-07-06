package de.admir.model;

import java.math.BigDecimal;

public class Product {
    private String code;
    private String name;
    private String listImage;
    private String pdpImage;
    private BigDecimal pricePerUnit;
    private String unitCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    public String getPdpImage() {
        return pdpImage;
    }

    public void setPdpImage(String pdpImage) {
        this.pdpImage = pdpImage;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
