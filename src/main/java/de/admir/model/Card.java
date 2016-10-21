package de.admir.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class Card extends IdentifiableModel {
    private String title;
    private String paragraph;
    private String small;
}
