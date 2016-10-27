package de.admir.model.order;

import de.admir.model.Customer;
import de.admir.model.OrderStatus;
import de.admir.model.OrderStatusEnum;
import de.admir.model.Session;
import de.admir.model.TimestampedModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "taze_order")
@ToString(exclude = "entries")
public class Order extends TimestampedModel {
    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    @NotNull
    OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "session_uuid", referencedColumnName = "uuid", nullable = false)
    @NotNull
    private Session session;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries = new ArrayList<>();

    @Transient
    public BigDecimal getTotalPrice() {
        return entries.stream().map(OrderEntry::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
