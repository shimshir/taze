package de.admir.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Entity
@Data
public class Session extends IdentifiableModel {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", unique = true)
    private TazeUuid uuid = new TazeUuid();
    private Date created;
    private Date updated;

    public String getUuid() {
        return uuid.getValue();
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = created;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        return getUuid().equals(session.getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }
}
