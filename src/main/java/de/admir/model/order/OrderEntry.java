package de.admir.model.order;

import de.admir.model.Entry;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderEntry extends Entry {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
