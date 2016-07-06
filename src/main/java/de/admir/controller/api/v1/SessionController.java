package de.admir.controller.api.v1;

import de.admir.model.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static de.admir.Constants.API_V1_BASE_PATH;

@RestController
@RequestMapping(path = API_V1_BASE_PATH)
public class SessionController {
    private final static Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @RequestMapping(path = "/session", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Session> getSession() {
        Session session = Session.createNew();
        LOG.info("Created session: {}", session);
        return ResponseEntity.ok(session);
    }
}
