package com.revolut.moneytransfer.web.controllers;

import com.revolut.moneytransfer.App;
import com.revolut.moneytransfer.ApplicationConstants;
import com.revolut.moneytransfer.service.TransferService;
import com.revolut.moneytransfer.web.dto.TransferRequest;
import org.eclipse.jetty.http.HttpStatus;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApplicationConstants.Controllers.TransferController.PATH)
public class TransferController {

    private final TransferService transferService;


    public TransferController() {
        this.transferService = App.injector().getInstance(TransferService.class);
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response transferMoney(@Valid TransferRequest request) {
        boolean isSuccess = transferService.transfer(request.getFromAccount(), request.getToAccount(), request.getAmount());
        if (isSuccess) {
            return Response.status(HttpStatus.CREATED_201).build();
        }
        return Response.status(HttpStatus.CONFLICT_409).entity("Couldn't process transaction").build();
    }
}
