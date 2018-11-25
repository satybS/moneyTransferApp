package com.revolut.moneytransfer.dao.impl;

import com.google.inject.Provider;
import com.revolut.moneytransfer.dao.CustomerDao;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;

import com.google.inject.Inject;;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DefaultCustomerDao implements CustomerDao {

    private final Provider<EntityManager> em;

    @Inject
    public DefaultCustomerDao(Provider<EntityManager> em) {
        this.em = em;
    }


    @Override
    public List<Account> getAccounts(Long customerId) {
        return em.get().createQuery("SELECT a FROM Account a WHERE a.customer.id = :customerId", Account.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public Optional<Customer> getCustomer(Long customerId) {
        return Optional.ofNullable(em.get().find(Customer.class, customerId));
    }
}
