package de.admir.model.cart;

import java.util.List;
import java.util.UUID;

public class Cart {
    private UUID id;
    private List<CartEntry> entries;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CartEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CartEntry> entries) {
        this.entries = entries;
    }
}
