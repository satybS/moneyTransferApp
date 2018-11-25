package com.revolut.moneytransfer.web.facade;

import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.dto.CustomerDto;

import java.util.List;

public interface CustomerFacade {

    CustomerDto getCustomer(Long customerId);

    List<AccountDto> getAccounts(Long customerId);


}
