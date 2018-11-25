package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Account> getAccounts(Long customerId);

    Customer getCustomer(Long customerId);
}
