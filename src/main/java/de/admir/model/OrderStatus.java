package de.admir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderStatus {
    public OrderStatus() {
    }

    public OrderStatus(String value) {
        this.value = value;
    }

    @Id
    @Column(nullable = false)
    @NotNull
    private String value;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatus)) return false;

        OrderStatus orderStatus = (OrderStatus) o;

        return getValue().equals(orderStatus.getValue());

    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
