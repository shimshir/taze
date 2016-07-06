package de.admir.model.session;

import java.util.UUID;

public final class Session {
    private UUID id;

    private Session() {}
    private Session(UUID id) {
        this.id = id;
    }

    public Session(String uuid) {
        this.id = UUID.fromString(uuid);
    }

    public static Session createNew() {
        return new Session(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        return id.equals(session.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
