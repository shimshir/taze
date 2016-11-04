package de.admir.taze.model.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class OrderStatus implements Identifiable<String> {
    public OrderStatus() {
    }

    public OrderStatus(String id) {
        this.id = id;
    }

    @Id
    @Column(nullable = false)
    @NotNull
    private String id;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatus)) return false;

        OrderStatus orderStatus = (OrderStatus) o;

        return getId().equals(orderStatus.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
