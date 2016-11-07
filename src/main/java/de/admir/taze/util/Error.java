package de.admir.taze.util;

public class Error {
    public Error(String message) {
        this.message = message;
    }

    public Error(Exception e) {
        this.message = e.getMessage();
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
