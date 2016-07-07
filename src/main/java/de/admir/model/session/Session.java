package de.admir.model.session;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Session {
    private String uuid;
    private Date created;

    public Session(UUID uuid) {
        this.uuid = uuid.toString();
        this.created = new Date(System.currentTimeMillis());
    }

    @JsonProperty("id")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Session{" +
                "uuid='" + uuid + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        return uuid.equals(session.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
