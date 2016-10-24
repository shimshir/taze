package de.admir.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class TimestampedModel extends IdentifiableModel {

    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = created;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
