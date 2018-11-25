package com.revolut.moneytransfer.web.facade;

import com.revolut.moneytransfer.web.dto.AccountDto;

public interface AccountFacade {

    AccountDto getAccount(Long accountId);
}
