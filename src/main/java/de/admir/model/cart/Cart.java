package de.admir.model.cart;

import de.admir.model.IdentifiableModel;
import de.admir.model.Session;

import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Collection;

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
    private Collection<CartEntry> entries;
}
