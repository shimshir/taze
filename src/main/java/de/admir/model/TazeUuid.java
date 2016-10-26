package de.admir.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@Deprecated
// TODO: Try without this
public class TazeUuid {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TazeUuid)) return false;

        TazeUuid tazeUuid = (TazeUuid) o;

        return value.equals(tazeUuid.getValue());

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
