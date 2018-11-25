package com.revolut.moneytransfer.web.controllers.exceptionhandlers;

import com.revolut.moneytransfer.exceptions.ResourceNotFoundException;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    private static Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    /**
     * General exception handler for internal exceptions.
     *
     * @param e Exception occurred.
     *
     */

    @Override
    public Response toResponse(Exception e) {
        logger.error("General exception occurred", e);
        return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity("Internal server error").build();
    }
}
