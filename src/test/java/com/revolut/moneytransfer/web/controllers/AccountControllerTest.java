package com.revolut.moneytransfer.web.controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.ApplicationConstants;
import com.revolut.moneytransfer.config.JerseyConfig;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.setup.JerseyServerSupplier;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.facade.AccountFacade;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import io.logz.guice.jersey.configuration.JerseyConfigurationBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;


@RunWith(GuiceJUnit4Runner.class)
public class AccountControllerTest {

    @Inject
    private AccountFacade accountFacade;
    @Inject
    private Provider<EntityManager> em;
    private List<Customer> customers;
    private Account testAccount;
    JerseyConfigurationBuilder configurationBuilder;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        testAccount = customers.get(0).getAccounts().get(0);
        configurationBuilder = JerseyConfiguration.builder()
                .addPackage(AccountController.class.getPackage().toString())
                .withResourceConfig(new JerseyConfig());
    }


    @Test
    public void getAccount_OK() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            AccountDto response = target.path("account/" + testAccount.getId()).request().get().readEntity(AccountDto.class);
            assertEquals(accountFacade.getAccount(testAccount.getId()), response);
        });
    }

    @Test
    public void getAccount_Bad_Request_Not_Exists() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("account/" + new Random().nextLong()).request().get();
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }
}