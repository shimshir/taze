package de.admir.model.cart;

import de.admir.model.IdentifiableModel;
import de.admir.model.Session;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Cart extends IdentifiableModel {
    public Cart() {
    }

    public Cart(Session session) {
        this.session = session;
    }

    @OneToOne
    @JoinColumn(name = "session_uuid", referencedColumnName = "uuid", unique = true)
    private Session session;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_2_entries",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_id")
    )
    private Collection<CartEntry> entries;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Collection<CartEntry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<CartEntry> entries) {
        this.entries = entries;
    }
}
