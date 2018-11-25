package com.revolut.moneytransfer.web.facade.convertors;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.web.dto.CustomerDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerConverter implements Converter<Customer, CustomerDto> {


    @Override
    public CustomerDto convert(Customer source) {
        CustomerDto target = new CustomerDto(source.getId(), source.getFirstName(), source.getLastName());
        List<Long> accountsIds = source.getAccounts().stream().map(Account::getId).collect(Collectors.toList());
        target.setAccountIds(accountsIds);
        return target;
    }
}
