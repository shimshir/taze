package de.admir.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Profile("production")
public class CustomErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String error() {
        return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
