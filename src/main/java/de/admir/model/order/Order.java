package de.admir.model.order;

import de.admir.model.Customer;
import de.admir.model.IdentifiableModel;
import de.admir.model.Session;

import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"entries", "token"})
@Table(name = "taze_order")
@ToString(exclude = "entries")
public class Order extends IdentifiableModel {
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
    @OneToOne
    @JoinColumn(name = "token_value", referencedColumnName = "value")
    private ConfirmationToken token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = created;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Transient
    public BigDecimal getTotalPrice() {
        return CollectionUtils.isEmpty(entries) ? null : entries.stream().map(OrderEntry::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
