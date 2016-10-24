package de.admir.model.cart;

import de.admir.model.IdentifiableModel;
import de.admir.model.Session;

import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Cart extends IdentifiableModel {
    @PersistenceConstructor
    public Cart() {
    }

    public Cart(Session session) {
        this.session = session;
    }

    @OneToOne
    @JoinColumn(name = "session_uuid", referencedColumnName = "uuid", unique = true)
    private Session session;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartEntry> entries = new ArrayList<>();

    @Transient
    public BigDecimal getTotalPrice() {
        return entries.stream().map(CartEntry::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
