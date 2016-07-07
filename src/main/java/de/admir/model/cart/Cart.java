package de.admir.model.cart;

import de.admir.model.session.Session;

import java.util.List;

public class Cart {
    private String uuid;
    private Session session;
    private List<CartEntry> entries;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
