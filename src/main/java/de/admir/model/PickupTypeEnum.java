package de.admir.model;

public enum PickupTypeEnum {
    COLLECT("collect", "Preuzet cu ja"),
    DELIVERY("delivery", "Dostavite mi na adresu");

    private String value;
    private String text;

    PickupTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
