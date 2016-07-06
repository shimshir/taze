package de.admir.model.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CartDto {
    @JsonIgnore
    private UUID id;
    private List<CartEntryDto> entries = new LinkedList<>();

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public void setId(UUID id) {
        this.id = id;
    }

    public List<CartEntryDto> getEntries() {
        return entries;
    }

    public void setEntries(List<CartEntryDto> entries) {
        this.entries = entries;
    }
}
