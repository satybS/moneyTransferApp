package com.revolut.moneytransfer.web.controllers.exceptionhandlers;

import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionHandler implements ExceptionMapper<ResourceNotFoundException> {

    private static Logger logger = LoggerFactory.getLogger(ResourceNotFoundExceptionHandler.class);


    @Override
    public Response toResponse(ResourceNotFoundException e) {
        logger.error("Resource not found", e);
        return Response.status(HttpStatus.BAD_REQUEST_400).entity("Resource with this id doesn't exist").build();
    }
}
