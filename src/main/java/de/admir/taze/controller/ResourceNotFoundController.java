package de.admir.taze.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceNotFoundController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundController.class);
    private static final Gson gson = new GsonBuilder().create();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public void handleNotFound(HttpServletRequest request, ResourceNotFoundException e) {
        JsonObject logJson = new JsonObject();
        logJson.addProperty("requestUri", request.getRequestURI());
        logJson.add("urlParameters", gson.toJsonTree(request.getParameterMap()));
        logger.warn(gson.toJson(logJson));
    }
}
