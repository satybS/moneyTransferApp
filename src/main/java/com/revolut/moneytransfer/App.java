package com.revolut.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.revolut.moneytransfer.config.JerseyConfig;
import com.revolut.moneytransfer.utils.DbInitUtils;
import io.logz.guice.jersey.JerseyModule;
import io.logz.guice.jersey.JerseyServer;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import org.glassfish.jersey.jackson.JacksonFeature;

public class App {

    private static Injector injector;

    public static Injector injector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        App.injector = injector;
    }

    public static void main(String[] args) throws Exception {
        DbInitUtils.migrateWithFlyway();
        JerseyConfiguration configuration = JerseyConfiguration.builder()
                .addPackage("com/revolut/moneytransfer/web")
                .withResourceConfig(new JerseyConfig())
                .addPort(8080)
                .registerClasses(JacksonFeature.class)
                .withContextPath("/api/*")
                .build();

        injector = Guice.createInjector(new AppModule(), new JpaPersistModule(ApplicationConstants.Persistence.VALUE), new JerseyModule(configuration));
        injector().getInstance(AppInitializer.class);
        injector().getInstance(JerseyServer.class).start();
    }

}
