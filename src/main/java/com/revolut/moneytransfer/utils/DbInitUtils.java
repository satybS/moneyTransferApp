package com.revolut.moneytransfer.utils;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.util.Properties;

public class DbInitUtils {

    public static void migrateWithFlyway() throws IOException {
        Properties properties = PropertiesUtils.readProperties("db/db.properties");
        String url = properties.getProperty("jdbc.url");

        Flyway flyway = new Flyway();
        flyway.setDataSource(url, "", "");
        flyway.setLocations("db/migration");
        flyway.clean();
        flyway.migrate();
    }
}
