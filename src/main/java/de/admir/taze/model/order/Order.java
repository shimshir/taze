package de.admir.taze.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.admir.taze.model.Customer;
import de.admir.taze.model.IdentifiableEntity;
import de.admir.taze.model.PickupTypeEnum;
import de.admir.taze.model.Session;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"entries", "token"})
@Table(name = "taze_order")
@ToString(exclude = "entries")
public class Order extends IdentifiableEntity {
    @Enumerated(EnumType.STRING)
    OrderStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Session session;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    @JsonIgnore
    private ConfirmationToken token;
    @Enumerated(EnumType.STRING)
    private PickupTypeEnum pickupType;
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
