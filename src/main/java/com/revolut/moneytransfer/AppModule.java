package com.revolut.moneytransfer;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.dao.CustomerDao;
import com.revolut.moneytransfer.dao.impl.DefaultAccountDao;
import com.revolut.moneytransfer.dao.impl.DefaultCustomerDao;
import lombok.extern.slf4j.Slf4j;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Customer;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.CustomerService;
import com.revolut.moneytransfer.service.TransferService;
import com.revolut.moneytransfer.service.impl.DefaultAccountService;
import com.revolut.moneytransfer.service.impl.DefaultCustomerService;
import com.revolut.moneytransfer.service.impl.MoneyTransferService;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.dto.CustomerDto;
import com.revolut.moneytransfer.web.facade.AccountFacade;
import com.revolut.moneytransfer.web.facade.CustomerFacade;
import com.revolut.moneytransfer.web.facade.convertors.AccountConverter;
import com.revolut.moneytransfer.web.facade.convertors.Converter;
import com.revolut.moneytransfer.web.facade.convertors.CustomerConverter;
import com.revolut.moneytransfer.web.facade.impl.DefaultAccountFacade;
import com.revolut.moneytransfer.web.facade.impl.DefaultCustomerFacade;

@Slf4j
public class AppModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(AccountDao.class).to(DefaultAccountDao.class);
        bind(CustomerDao.class).to(DefaultCustomerDao.class);
        bind(AccountService.class).to(DefaultAccountService.class);
        bind(CustomerFacade.class).to(DefaultCustomerFacade.class);
        bind(AccountFacade.class).to(DefaultAccountFacade.class);
        bind(CustomerService.class).to(DefaultCustomerService.class);
        bind(TransferService.class).to(MoneyTransferService.class);
        bind(new TypeLiteral<Converter<Account, AccountDto>>(){}).to(AccountConverter.class);
        bind(new TypeLiteral<Converter<Customer, CustomerDto>>(){}).to(CustomerConverter.class);
    }
}
