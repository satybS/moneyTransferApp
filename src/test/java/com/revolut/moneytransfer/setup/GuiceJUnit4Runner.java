package com.revolut.moneytransfer.setup;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.revolut.moneytransfer.App;
import com.revolut.moneytransfer.AppInitializer;
import com.revolut.moneytransfer.AppModule;
import com.revolut.moneytransfer.ApplicationConstants;
import com.revolut.moneytransfer.utils.DbInitUtils;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class GuiceJUnit4Runner extends BlockJUnit4ClassRunner {

    public static Injector injector;

    public GuiceJUnit4Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public Object createTest() throws Exception {
        Object object = super.createTest();
        DbInitUtils.migrateWithFlyway();
        Injector injector = Guice.createInjector(new AppModule(), new JpaPersistModule(ApplicationConstants.Persistence.VALUE));
        injector.getInstance(AppInitializer.class);
        injector.injectMembers(object);
        App.setInjector(injector);
        return object;
    }



}