package de.admir.taze.model;

import java.math.BigDecimal;

public enum PickupTypeEnum {
    COLLECT("collect", "Preuzet cu ja", BigDecimal.ZERO),
    DELIVERY("delivery", "Dostavite mi na adresu", new BigDecimal("15"));

    private String value;
    private String text;
    private BigDecimal price;

    PickupTypeEnum(String value, String text, BigDecimal price) {
        this.value = value;
        this.text = text;
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
