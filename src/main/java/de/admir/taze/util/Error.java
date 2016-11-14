package de.admir.taze.util;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

@ToString
public class Error {
    public Error(String message) {
        this.message = message;
    }

    public Error(Exception e) {
        this.message = e.getMessage();
    }

    public Error(String message, List<Error> nestedErrors) {
        this.message = message;
        this.nestedErrors = nestedErrors;
    }

    private String message;
    private List<Error> nestedErrors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Error> getNestedErrors() {
        return nestedErrors;
    }

    public Error addNestedError(Error nestedError) {
        if (nestedErrors == null)
            nestedErrors = new ArrayList<>();
        nestedErrors.add(nestedError);
        return this;
    }
}
