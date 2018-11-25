package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;

import java.math.BigDecimal;

public interface TransferService {

    boolean transfer(Long fromAccount, Long toAccount, BigDecimal amount);
}
