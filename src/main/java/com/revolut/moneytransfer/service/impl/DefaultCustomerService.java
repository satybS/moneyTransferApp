package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.revolut.moneytransfer.dao.CustomerDao;
import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class DefaultCustomerService implements CustomerService {

    private static Logger logger = LoggerFactory.getLogger(DefaultCustomerService.class);

    private final CustomerDao customerDao;

    @Inject
    public DefaultCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Returns customer accounts that have same primary key(userId). Empty list if customer doesn't have any accounts
     * or there is no customer with this id.
     *
     * @param customerId id of the customer.
     */

    @Override
    public List<Account> getAccounts(Long customerId) {
        return customerDao.getAccounts(customerId);
    }

    /**
     * Returns customer for provided primary key(userId).
     *
     * @param userId id of the requested customer entity.
     *
     * @throws ResourceNotFoundException if customer is not exists.
     */

    @Override
    public Customer getCustomer(Long userId) {
        Optional<Customer> customer = customerDao.getCustomer(userId);
        if (!customer.isPresent()) {
            logger.error("Customer with this id {} doesn't exist", userId);
        }
        return customer.orElseThrow(ResourceNotFoundException::new);
    }
}
