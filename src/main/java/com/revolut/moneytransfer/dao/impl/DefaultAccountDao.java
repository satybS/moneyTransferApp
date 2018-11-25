package com.revolut.moneytransfer.dao.impl;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import com.revolut.moneytransfer.model.Account;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.Optional;

public class DefaultAccountDao implements AccountDao {

    private final Provider<EntityManager> em;

    @Inject
    public DefaultAccountDao(Provider<EntityManager> em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Optional<Account> getAccount(Long accountId) {
        return Optional.ofNullable(em.get().find(Account.class, accountId));
    }

}
