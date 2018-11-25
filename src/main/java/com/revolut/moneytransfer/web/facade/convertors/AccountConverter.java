package com.revolut.moneytransfer.web.facade.convertors;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.web.dto.AccountDto;

public class AccountConverter implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account source) {
        return new AccountDto(source.getId(), source.getBalance(), source.getCustomer().getId());
    }
}
