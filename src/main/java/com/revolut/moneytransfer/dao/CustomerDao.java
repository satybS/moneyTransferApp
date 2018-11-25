package com.revolut.moneytransfer.dao;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Account> getAccounts(Long customerId);

    Optional<Customer> getCustomer(Long customerId);
}
