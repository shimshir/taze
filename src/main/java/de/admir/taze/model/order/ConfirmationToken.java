package de.admir.taze.model.order;

import de.admir.taze.model.IdentifiableEntity;
import de.admir.taze.model.Uuid;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConfirmationToken extends IdentifiableEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value", referencedColumnName = "id")
    private Uuid value;
    private boolean used;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = created;
        value = new Uuid();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmationToken)) return false;

        ConfirmationToken that = (ConfirmationToken) o;

        return getValue().equals(that.getValue());

    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
