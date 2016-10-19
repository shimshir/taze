package de.admir.model.cart;

import de.admir.model.IdentifiableModel;
import de.admir.model.Session;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    private List<CartEntry> entries;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<CartEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CartEntry> entries) {
        this.entries = entries;
    }
}
