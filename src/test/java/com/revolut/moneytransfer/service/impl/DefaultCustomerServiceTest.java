package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.CustomerService;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GuiceJUnit4Runner.class)
public class DefaultCustomerServiceTest {

    @Inject
    private CustomerService customerService;
    @Inject
    private Provider<EntityManager> em;

    List<Customer> customers;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
    }

    @Test
    public void getAccounts_Success() {
        Customer customer = customers.get(0);
        List<Account> accounts = customerService.getAccounts(customer.getId());
        assertEquals(customer.getAccounts().size(), accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            assertEquals(customer.getAccounts().get(i), accounts.get(i));
        }
    }

    @Test
    public void getAccounts_Empty() {
        List<Account> accounts = customerService.getAccounts(new Random().nextLong());
        assertTrue(accounts.isEmpty());
    }

    @Test
    public void getCustomer_Success() {
        Customer customer = customers.get(0);
        Customer result = customerService.getCustomer(customer.getId());
        assertEquals(customer, result);
    }
}