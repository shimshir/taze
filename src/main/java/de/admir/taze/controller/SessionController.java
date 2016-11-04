package de.admir.taze.controller;

import de.admir.taze.model.Session;
import de.admir.taze.repository.SessionRepository;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RepositoryRestController
public class SessionController {
    @Autowired
    private RepositoryEntityLinks entityLinks;
    @Autowired
    private SessionRepository sessionRepository;

    @RequestMapping(path = "/sessions*", method = POST)
    public ResponseEntity<?> postSession(@RequestBody Resource<Session> requestSessionResource, HttpServletRequest req) {
        Session session = requestSessionResource.getContent();
        String clientIpAddress = req.getHeader("X-FORWARDED-FOR") == null ?
                req.getRemoteAddr() : req.getRemoteAddr() + ";" + req.getHeader("X-FORWARDED-FOR");
        session.setIpAddress(clientIpAddress);
        sessionRepository.save(session);
        Resource<Session> responseSessionResource = new Resource<>(session);
        Link sessionEntityLink = entityLinks.linkToSingleResource(session);
        responseSessionResource.add(sessionEntityLink.withSelfRel());
        responseSessionResource.add(sessionEntityLink);
        return ResponseEntity.created(URI.create(sessionEntityLink.getHref())).body(responseSessionResource);
    }
}
