package de.admir.taze.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends IdentifiableEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", referencedColumnName = "id")
    private Uuid uuid;
    private String ipAddress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        uuid = new Uuid();
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

        return this.getId().equals(session.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
}
