package com.revolut.moneytransfer.web.controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.config.JerseyConfig;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.setup.JerseyServerSupplier;
import com.revolut.moneytransfer.web.dto.CustomerDto;
import com.revolut.moneytransfer.web.dto.TransferRequest;
import com.revolut.moneytransfer.web.facade.CustomerFacade;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import io.logz.guice.jersey.configuration.JerseyConfigurationBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(GuiceJUnit4Runner.class)
public class TransferControllerTest {

    @Inject
    private Provider<EntityManager> em;

    private List<Customer> customers;

    private Account from;

    private Account to;

    JerseyConfigurationBuilder configurationBuilder;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        from = customers.get(0).getAccounts().get(0);
        to = customers.get(1).getAccounts().get(0);
        configurationBuilder = JerseyConfiguration.builder()
                .addPackage(CustomerController.class.getPackage().toString())
                .withResourceConfig(new JerseyConfig());
    }

    @Test
    public void transfer_Zero_Amount_Fail_Bad_Request() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("transfer/").request()
                    .post(Entity.entity(new TransferRequest(from.getId(), to.getId(), BigDecimal.ZERO), MediaType.APPLICATION_JSON_TYPE));
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }

    @Test
    public void transfer_Negative_Amount_Fail_Bad_Request() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("transfer/").request()
                    .post(Entity.entity(new TransferRequest(from.getId(), to.getId(), BigDecimal.ONE.negate()), MediaType.APPLICATION_JSON_TYPE));
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }


    @Test
    public void transferMoney_OK() throws Exception {
        BigDecimal fromBalance = from.getBalance();
        BigDecimal toBalance = to.getBalance();
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("transfer/").request()
                    .post(Entity.entity(new TransferRequest(from.getId(), to.getId(), BigDecimal.TEN), MediaType.APPLICATION_JSON_TYPE));
            assertEquals(HttpStatus.CREATED_201, response.getStatus());
            em.get().refresh(from);
            em.get().refresh(to);
            assertEquals(fromBalance.subtract(BigDecimal.TEN), from.getBalance());
            assertEquals(toBalance.add(BigDecimal.TEN), to.getBalance());
        });
    }
}