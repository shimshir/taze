package de.admir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends IdentifiableModel {
    private String firstName;
    private String lastName;
    private String address;
    @Column(nullable = false)
    private String email;
}
