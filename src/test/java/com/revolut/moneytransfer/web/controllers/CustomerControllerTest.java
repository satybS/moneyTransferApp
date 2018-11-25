package com.revolut.moneytransfer.web.controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.config.JerseyConfig;
import com.revolut.moneytransfer.dao.CustomerDao;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.setup.JerseyServerSupplier;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.dto.CustomerDto;
import com.revolut.moneytransfer.web.facade.CustomerFacade;
import io.logz.guice.jersey.configuration.JerseyConfiguration;
import io.logz.guice.jersey.configuration.JerseyConfigurationBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(GuiceJUnit4Runner.class)
public class CustomerControllerTest {

    @Inject
    private CustomerFacade customerFacade;
    @Inject
    private Provider<EntityManager> em;

    private List<Customer> customers;

    private Customer testCustomer;

    JerseyConfigurationBuilder configurationBuilder;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        testCustomer = customers.get(0);
        configurationBuilder = JerseyConfiguration.builder()
                .addPackage(CustomerController.class.getPackage().toString())
                .withResourceConfig(new JerseyConfig());
    }

    @Test
    public void getCustomer_OK() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            CustomerDto response = target.path("customer/" + testCustomer.getId()).request().get().readEntity(CustomerDto.class);
            assertEquals(customerFacade.getCustomer(testCustomer.getId()), response);
        });
    }

    @Test
    public void getCustomer_Fail_Bad_Request_Customer_Not_Exists() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("customer/" + new Random().nextLong()).request().get();
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }

    @Test
    public void getCustomer_Fail_Bad_Request_Validation_Error() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("customer/" + "somevalue").request().get();
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }

    @Test
    public void getCustomer_Fail_Bad_Request_Negative_Number() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("customer/-1").request().get();
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }

    @Test
    public void getAccounts_OK() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            List<AccountDto> response = target.path("customer/" + testCustomer.getId() + "/accounts").request().get()
                    .readEntity(new GenericType<List<AccountDto>>() {});
            assertEquals(customerFacade.getAccounts(testCustomer.getId()), response);
        });
    }

    @Test
    public void getAccounts_Fail_Bad_Request_Validation_Error() throws Exception {
        JerseyServerSupplier.createServerAndTest(configurationBuilder, target -> {
            Response response = target.path("customer/aa/accounts").request().get();
            assertEquals(HttpStatus.BAD_REQUEST_400, response.getStatus());
        });
    }
}