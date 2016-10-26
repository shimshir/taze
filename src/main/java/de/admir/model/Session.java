package de.admir.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

@Entity
public class Session extends TimestampedModel {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", unique = true, nullable = false)
    private TazeUuid tazeUuid;

    public String getUuid() {
        return tazeUuid.getValue();
    }

    @PrePersist
    public void onCreate() {
        super.onCreate();
        this.tazeUuid = new TazeUuid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        return this.getUuid().equals(session.getUuid());
    }

    @Override
    public int hashCode() {
        return this.getUuid().hashCode();
    }
}
