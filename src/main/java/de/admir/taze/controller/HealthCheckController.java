package de.admir.taze.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HealthCheckController {

    @RequestMapping(path = "/health", method = GET)
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok("OK");
    }
}
