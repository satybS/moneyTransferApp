package com.revolut.moneytransfer.web.controllers.exceptionhandlers;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    Logger logger = LoggerFactory.getLogger(JsonExceptionHandler.class);

    /**
     * Json validation exception handler.
     *
     * @param exception json validation exception.
     *
     */

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        logger.error("json constrain violation", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type("text/plain")
                .build();
    }
}
