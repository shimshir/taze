package de.admir.taze.util;

public class TazeUtils {

    public static void assertNotNull(Object object) throws NullPointerException {
        if (object == null)
            throw new NullPointerException();
    }

    public static void assertNotNull(Object object, String message) throws NullPointerException {
        if (object == null)
            throw new NullPointerException(message);
    }
}
