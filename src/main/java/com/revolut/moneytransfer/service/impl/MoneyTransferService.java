package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransferService;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;

public class MoneyTransferService implements TransferService {

    private static Logger logger = LoggerFactory.getLogger(MoneyTransferService.class);

    private final AccountService accountService;

    private final Provider<EntityManager> em;

    @Inject
    public MoneyTransferService(AccountService accountService, Provider<EntityManager> em) {
        this.em = em;
        this.accountService = accountService;
    }

    /**
     * This method transfer money from one account to another. Concurrent access to account is managed by keeping current
     * version of the account entity. Optimistic lock is a current locking mechanism, which will throw exception if
     * entity was changed before transaction was committed. This could be changed to Pessimistic lock witch will lock
     * data record in the database for all operations.
     * or there is no customer with this id.
     *
     * @param fromAccount id of the account from which money will be withdrawn.
     * @param toAccount id of the account to which money will be deposited.
     * @param amount transfer amount
     *
     * @throws OptimisticLockException if entity was changed during transaction by different thread.
     */
    @Override
    public boolean transfer(Long fromAccount, Long toAccount, BigDecimal amount) {
        try {
            boolean updated = false;
            em.get().getTransaction().begin();
            Account from = accountService.getAccount(fromAccount);
            Account to = accountService.getAccount(toAccount);
            if (fromAccount.compareTo(toAccount) != 0 && amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(from.getBalance()) < 0) {
                update(from, amount.negate());
                update(to, amount);
                updated = true;
            }
            em.get().getTransaction().commit();
            return updated;
        } catch (Exception e) {
            logger.error("Transaction was not complete for accounts: {}, {}, amount: {}", fromAccount, toAccount, amount, e);
            em.get().getTransaction().rollback();
        }

        return false;
    }

    private void update(Account account, BigDecimal amount) {
        em.get().lock(account, LockModeType.OPTIMISTIC);
        account.setBalance(account.getBalance().add(amount));
        em.get().persist(account);
    }
}
