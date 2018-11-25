package com.revolut.moneytransfer;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class AppInitializer {

    @Inject
    public AppInitializer(PersistService persistService) {
        persistService.start();
    }
}
