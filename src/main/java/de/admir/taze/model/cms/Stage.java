package de.admir.taze.model.cms;

import de.admir.taze.model.IdentifiableModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stage extends IdentifiableModel {
    private String header;
    private String image;
}
