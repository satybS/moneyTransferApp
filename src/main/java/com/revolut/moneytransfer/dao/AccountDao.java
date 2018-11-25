package com.revolut.moneytransfer.dao;

import com.google.inject.persist.Transactional;
import com.revolut.moneytransfer.model.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> getAccount(Long accountId);
}
