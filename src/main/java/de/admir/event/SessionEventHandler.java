package de.admir.event;

import de.admir.filter.ClientIpFilter;
import de.admir.model.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class SessionEventHandler {

    private static final Logger LOG = Logger.getLogger(SessionEventHandler.class);

    @Autowired
    private ClientIpFilter clientIpFilter;

    @HandleBeforeCreate(Session.class)
    public void handleSessionBeforeCreate(Session session) {
        Optional<String> clientIpAddress = clientIpFilter.getClientIpAddress();
        if (clientIpAddress.isPresent()) {
            session.setIpAddress(clientIpAddress.get());
        }
    }
}
