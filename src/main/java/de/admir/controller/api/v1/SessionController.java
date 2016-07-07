package de.admir.controller.api.v1;

import de.admir.facade.SessionFacade;
import de.admir.model.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static de.admir.Constants.API_V1_BASE_PATH;

@RestController
@RequestMapping(path = API_V1_BASE_PATH)
public class SessionController {
    private final static Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @RequestMapping(path = "/session", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Session> createSession() {
        Session session = SessionFacade.createNewSession();
        LOG.info("Created session: {}", session);
        return ResponseEntity.ok(session);
    }

    @RequestMapping(path = "/session/{sessionId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity checkSession(@PathVariable String sessionId) {
        Session session = SessionFacade.getSessionById(sessionId);
        if (session != null) {
            return ResponseEntity.ok(session);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
