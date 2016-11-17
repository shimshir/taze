package de.admir.taze.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

@MappedSuperclass
@Getter
@Setter
public abstract class IdentifiableEntity implements Identifiable<Long> {
    @Id
    @TableGenerator(name = "taze_id_generator", table = "taze_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "taze_id_generator")
    private Long id;
}
