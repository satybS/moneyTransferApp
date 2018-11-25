package com.revolut.moneytransfer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties readProperties(String location) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
            if (input == null) {
                new RuntimeException("Can't load properties file");
            }
            prop.load(input);
            return prop;

        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
