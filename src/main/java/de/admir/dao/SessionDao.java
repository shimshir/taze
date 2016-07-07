package de.admir.dao;

import de.admir.model.session.Session;

import java.util.*;

public class SessionDao {
    private static final List<Session> sessions = new LinkedList<>();

    public static Session createSession() {
        Session session = new Session(UUID.randomUUID());
        sessions.add(session);
        return session;
    }

    public static Optional<Session> findSessionByUuid(String sessionUuid) {
        return sessions.stream().filter(session -> sessionUuid.equals(session.getUuid())).findFirst();
    }
}
