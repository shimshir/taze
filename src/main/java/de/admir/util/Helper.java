package de.admir.util;

public class Helper {
    public static boolean isSafeRequest(String requestMethod) {
        return !(requestMethod.equalsIgnoreCase("post") ||
                requestMethod.equalsIgnoreCase("put") ||
                requestMethod.equalsIgnoreCase("patch") ||
                requestMethod.equalsIgnoreCase("delete"));
    }
}
