package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(GuiceJUnit4Runner.class)
public class DefaultAccountServiceTest {

    @Inject
    private AccountService accountService;
    @Inject
    private Provider<EntityManager> em;

    List<Customer> customers;
    Account testAccount;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        testAccount = customers.get(0).getAccounts().get(0);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void getAccount_notFound() {
        accountService.getAccount(new Random().nextLong());
    }

    @Test
    public void getAccount_Success() {
        Account account = accountService.getAccount(testAccount.getId());
        assertEquals(testAccount, account);
    }
}