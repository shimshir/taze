package de.admir.taze.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends IdentifiableEntity {
    private String firstName;
    private String lastName;
    private String address;
    @Column(nullable = false)
    @NotNull
    private String email;
}
