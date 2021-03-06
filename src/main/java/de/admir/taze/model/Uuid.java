package de.admir.taze.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "taze_uuid")
@Getter
@Setter
public class Uuid implements Identifiable<String> {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uuid)) return false;

        Uuid uuid = (Uuid) o;

        return getId().equals(uuid.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
