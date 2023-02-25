package com.epam.donetc.restaurant.util;

import java.io.IOException;
import java.util.Properties;

/**
 * This class retrieves data from the application.properties file
 *
 * @author Stanislav Donetc
 * @version 1.0
 */

public class PropertiesUtil {
    private PropertiesUtil() {
    }

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (java.io.InputStream inputStream =
                     PropertiesUtil
                             .class
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
