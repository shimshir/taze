package de.admir.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class IdentifiableModel implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
