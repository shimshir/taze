package de.admir.taze.model.order;

public enum OrderStatusEnum {
    CART("cart", "Narudzba je u korpi"),
    ORDERED("ordered", "Narudzba je u zatrazena"),
    CONFIRMED("confirmed", "Narudzba je potvrdjena"),
    PROCESSING("processing", "Narudzba je u obradi"),
    COMPLETED("completed", "Narudzba je uspjesno obradjena"),
    FAILED("failed", "Narudzba je bila neuspjesna"),
    CANCELLED("cancelled", "Narudzba je otkazana");

    private String code;
    private String description;

    OrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
