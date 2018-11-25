package com.revolut.moneytransfer.service.impl;

import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DefaultAccountService implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(DefaultAccountService.class);


    private final AccountDao accountDao;

    @Inject
    public DefaultAccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * Returns account for provided primary key(accountId).
     *
     * @param accountId id of the requested account entity.
     *
     * @throws ResourceNotFoundException if account is not exists.
     */

    @Override
    public Account getAccount(Long accountId) {
        Optional<Account> account = accountDao.getAccount(accountId);
        if (!account.isPresent()){
            logger.error("Account with id: {} doesn't exists", accountId);
        }
        return account.orElseThrow(ResourceNotFoundException::new);
    }
}
