package com.revolut.moneytransfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(GuiceJUnit4Runner.class)
public class DefaultAccountDaoTest {

    @Inject
    private AccountDao accountDao;
    @Inject
    private Provider<EntityManager> em;
    private List<Customer> customers;
    private Account testAccount;

    @Before
    public void setUp() {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        testAccount = customers.get(0).getAccounts().get(0);
    }


    @Test
    public void getAccount_notFound() {
        Optional<Account> account = accountDao.getAccount(new Random().nextLong());
        assertFalse(account.isPresent());
    }

    @Test
    public void getAccount_Success() {
        Account account = accountDao.getAccount(testAccount.getId()).get();
        assertEquals(account, testAccount);
    }

}
