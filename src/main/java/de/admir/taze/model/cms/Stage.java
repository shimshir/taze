package de.admir.taze.model.cms;

import de.admir.taze.model.IdentifiableEntity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stage extends IdentifiableEntity {
    private String header;
    private String image;
}
