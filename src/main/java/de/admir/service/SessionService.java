package de.admir.service;

import de.admir.model.Session;
import de.admir.repository.rest.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session createNewSession() {
        return sessionRepository.save(new Session());
    }
}
