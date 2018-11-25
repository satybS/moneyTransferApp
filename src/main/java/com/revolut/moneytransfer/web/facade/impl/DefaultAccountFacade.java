package com.revolut.moneytransfer.web.facade.impl;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.facade.AccountFacade;
import com.revolut.moneytransfer.web.facade.convertors.Converter;

import com.google.inject.Inject;

public class DefaultAccountFacade implements AccountFacade {

    private final AccountService accountService;

    private final Converter<Account, AccountDto> accountConverter;

    @Inject
    public DefaultAccountFacade(AccountService accountService, Converter<Account, AccountDto> accountConverter) {
        this.accountService = accountService;
        this.accountConverter = accountConverter;
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        return accountConverter.convert(accountService.getAccount(accountId));
    }
}
