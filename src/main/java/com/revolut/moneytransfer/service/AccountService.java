package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;

public interface AccountService {

    Account getAccount(Long accountId);
}
