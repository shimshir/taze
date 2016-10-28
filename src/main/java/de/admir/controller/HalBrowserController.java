package de.admir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static de.admir.Constants.API_REST_BASE_PATH;


@Controller
public class HalBrowserController {
    @RequestMapping(value = "/hal-browser", method = RequestMethod.GET)
    public String getHalBrowser() {
        return String.format("redirect:/hal-browser/browser.html#%s", API_REST_BASE_PATH);
    }
}
