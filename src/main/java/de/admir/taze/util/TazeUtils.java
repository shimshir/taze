package de.admir.taze.util;

public class TazeUtils {
    public static boolean isSafeRequest(String requestMethod) {
        return !(requestMethod.equalsIgnoreCase("post") ||
                requestMethod.equalsIgnoreCase("put") ||
                requestMethod.equalsIgnoreCase("patch") ||
                requestMethod.equalsIgnoreCase("delete"));
    }
}
