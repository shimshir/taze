package de.admir.model.order;

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

    public OrderStatus(String code) {
        this.code = code;
    }

    @Id
    @Column(nullable = false)
    @NotNull
    private String code;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatus)) return false;

        OrderStatus orderStatus = (OrderStatus) o;

        return getCode().equals(orderStatus.getCode());

    }

    @Override
    public int hashCode() {
        return getCode().hashCode();
    }
}
