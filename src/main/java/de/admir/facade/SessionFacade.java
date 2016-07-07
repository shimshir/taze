package de.admir.facade;

import de.admir.model.session.Session;
import de.admir.service.SessionService;

public class SessionFacade {

    public static Session createNewSession() {
        return SessionService.createNewSession();
    }

    public static Session getSessionById(String sessionId) {
        return SessionService.getSessionById(sessionId);
    }
}
