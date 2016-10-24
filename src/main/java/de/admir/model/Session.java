package de.admir.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Session extends TimestampedModel {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", unique = true, nullable = false)
    private TazeUuid uuid = new TazeUuid();

    public String getUuid() {
        return uuid.getValue();
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
