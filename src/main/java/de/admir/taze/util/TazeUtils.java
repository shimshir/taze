package de.admir.taze.util;

import org.apache.commons.lang3.StringUtils;

public class TazeUtils {

    public static void assertNotNull(Object object) throws NullPointerException {
        if (object == null)
            throw new NullPointerException();
    }

    public static void assertNotNull(Object object, String message) throws NullPointerException {
        if (object == null)
            throw new NullPointerException(message);
    }

    public static String removeTemplateVariables(String uri) {
        return uri.replaceAll("\\{\\?.*\\}", StringUtils.EMPTY);
    }
}
