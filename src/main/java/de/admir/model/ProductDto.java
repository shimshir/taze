package de.admir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDto {
    private String code;
    private String name;
    private String listImage;
    private String pdpImage;
    @JsonIgnore
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

    @JsonProperty
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    @JsonIgnore
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
