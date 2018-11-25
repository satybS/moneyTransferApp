package com.revolut.moneytransfer.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages( "com.revolut.moneytransfer.web", "com.fasterxml.jackson.jaxrs.base" );
        property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
    }
}
