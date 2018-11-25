package com.revolut.moneytransfer.web.controllers;

import com.revolut.moneytransfer.App;
import com.revolut.moneytransfer.ApplicationConstants;
import com.revolut.moneytransfer.web.facade.AccountFacade;

import javax.validation.constraints.DecimalMin;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.revolut.moneytransfer.web.dto.AccountDto;

@Path(ApplicationConstants.Controllers.AccountController.PATH)
public class AccountController {


    private final AccountFacade accountFacade;

    public AccountController() {
         accountFacade = App.injector().getInstance(AccountFacade.class);
    }

    @GET
    @Path(ApplicationConstants.Controllers.AccountController.Account.PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDto getAccount(@PathParam("accountId") @DecimalMin(value= "0", inclusive = false,
            message = "it is not valid id") String accountId) {
        return accountFacade.getAccount(Long.valueOf(accountId));
    }

}
