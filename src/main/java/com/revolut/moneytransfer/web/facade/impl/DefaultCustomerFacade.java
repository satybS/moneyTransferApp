package com.revolut.moneytransfer.web.facade.impl;

import com.google.inject.Inject;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.CustomerService;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.dto.CustomerDto;
import com.revolut.moneytransfer.web.facade.CustomerFacade;
import com.revolut.moneytransfer.web.facade.convertors.Converter;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultCustomerFacade implements CustomerFacade {

    private final CustomerService customerService;
    private final Converter<Customer, CustomerDto> customerConverter;
    private final Converter<Account, AccountDto> accountConverter;

    @Inject
    public DefaultCustomerFacade(CustomerService customerService, Converter<Customer, CustomerDto> customerConverter, Converter<Account, AccountDto> accountConverter1) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
        this.accountConverter = accountConverter1;
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        return customerConverter.convert(customerService.getCustomer(customerId));
    }

    @Override
    public List<AccountDto> getAccounts(Long customerId) {
        return customerService.getAccounts(customerId).stream().map(accountConverter::convert).collect(Collectors.toList());
    }


}
