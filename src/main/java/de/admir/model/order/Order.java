package de.admir.model.order;

import de.admir.model.Customer;
import de.admir.model.TimestampedModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "taze_order")
public class Order extends TimestampedModel {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries = new ArrayList<>();
}
