package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.CustomerService;
import com.revolut.moneytransfer.service.TransferService;
import com.revolut.moneytransfer.setup.GuiceJUnit4Runner;
import com.revolut.moneytransfer.utils.DbInitUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(GuiceJUnit4Runner.class)
public class MoneyTransferServiceTest {

    @Inject
    private TransferService transferService;
    @Inject
    private Provider<EntityManager> em;

    List<Customer> customers;
    Account from;
    Account to;

    @Before
    public void setUp() throws IOException {
        customers = em.get().createQuery("SELECT c FROM Customer c").getResultList();
        from = customers.get(0).getAccounts().get(0);
        to = customers.get(1).getAccounts().get(0);
    }

    @Test
    public void transfer_Zero_Amount_Fail() {
        assertFalse(transferService.transfer(from.getId(), to.getId(), BigDecimal.ZERO));
    }

    @Test
    public void transfer_Negative_Amount_Fail() {
        assertFalse(transferService.transfer(from.getId(), to.getId(), BigDecimal.ONE.negate()));
    }

    @Test
    public void transfer_To_Same_Accounts_Fail() {
        assertFalse(transferService.transfer(from.getId(), from.getId(), BigDecimal.ONE));
    }

    @Test
    public void transfer_Larger_Amount_Fail() {
        BigDecimal fromBalance = from.getBalance();
        BigDecimal toBalance = to.getBalance();
        assertFalse(transferService.transfer(from.getId(), to.getId(), from.getBalance().add(BigDecimal.TEN)));
        assertEquals(fromBalance, from.getBalance());
        assertEquals(toBalance, to.getBalance());
    }

    @Test
    public void transfer_Success() {
        BigDecimal fromBalance = from.getBalance();
        BigDecimal toBalance = to.getBalance();
        assertTrue(transferService.transfer(from.getId(), to.getId(), BigDecimal.TEN));
        assertEquals(fromBalance.subtract(BigDecimal.TEN), from.getBalance());
        assertEquals(toBalance.add(BigDecimal.TEN), to.getBalance());
    }
}