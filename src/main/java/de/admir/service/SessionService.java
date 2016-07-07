package de.admir.service;

import de.admir.dao.SessionDao;
import de.admir.model.session.Session;

public class SessionService {
    public static Session createNewSession() {
        return SessionDao.createSession();
    }

    public static Session getSessionById(String sessionId) {
        return SessionDao.findSessionByUuid(sessionId).orElse(null);
    }
}
