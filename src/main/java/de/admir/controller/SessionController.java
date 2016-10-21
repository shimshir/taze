package de.admir.controller;

import de.admir.service.SessionService;
import de.admir.model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static de.admir.Constants.API_CUSTOM_BASE_PATH;
import static de.admir.Constants.API_REST_BASE_PATH;

@Controller
@RequestMapping(path = API_CUSTOM_BASE_PATH + "/sessions")
class SessionController {
    private final static Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createSession() {
        Session session = sessionService.createNewSession();
        LOG.info("Created session: {}", session);
        return "forward:" + API_REST_BASE_PATH + "/sessions/search/findByUuidValue?uuid=" + session.getUuid();
    }
}
