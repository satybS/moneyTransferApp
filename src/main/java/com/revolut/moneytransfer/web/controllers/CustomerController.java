package com.revolut.moneytransfer.web.controllers;

import com.google.inject.Inject;
import com.revolut.moneytransfer.App;
import com.revolut.moneytransfer.ApplicationConstants;
import com.revolut.moneytransfer.web.dto.AccountDto;
import com.revolut.moneytransfer.web.dto.CustomerDto;
import com.revolut.moneytransfer.web.facade.CustomerFacade;

import javax.validation.constraints.DecimalMin;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(ApplicationConstants.Controllers.CustomerController.PATH)
public class CustomerController {

    private final CustomerFacade customerFacade;


    public CustomerController() {
        this.customerFacade = App.injector().getInstance(CustomerFacade.class);
    }

    @GET
    @Path(ApplicationConstants.Controllers.CustomerController.Customer.PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto getCustomer(@PathParam("customerId") @DecimalMin(value= "0", inclusive = false,
            message = "it is not valid id") String customerId) {
        return customerFacade.getCustomer(Long.valueOf(customerId));
    }

    @GET
    @Path(ApplicationConstants.Controllers.CustomerController.Accounts.PATH)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDto> getAccounts(@PathParam("customerId") @DecimalMin(value= "0", inclusive = false,
            message = "it is not valid id") String customerId) {
        return customerFacade.getAccounts(Long.valueOf(customerId));
    }

}
