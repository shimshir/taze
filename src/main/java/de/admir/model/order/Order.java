package de.admir.model.order;

import de.admir.model.Customer;
import de.admir.model.Session;
import de.admir.model.TimestampedModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.PersistenceConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "taze_order")
public class Order extends TimestampedModel {
    @PersistenceConstructor
    public Order() {
    }

    public Order(Session session) {
        this.session = session;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "session_uuid", referencedColumnName = "uuid", nullable = false)
    private Session session;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries = new ArrayList<>();

    @Transient
    public BigDecimal getTotalPrice() {
        return entries.stream().map(OrderEntry::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
