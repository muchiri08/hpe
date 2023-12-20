package org.acme.resource.exceptions;

import org.acme.exceptions.NoRecordFoundException;
import org.acme.exceptions.UserNotExistException;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalHandler implements ExceptionMapper<Exception> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(Exception exception) {
        return mapExceptionToResponse(exception);
    }

    private Response mapExceptionToResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
            return Response.fromResponse(originalErrorResponse)
                    .entity("mine: " + exception.getMessage())
                    .build();
        } else if (exception instanceof NoRecordFoundException) {
            return Response.status(Response.Status.OK).entity("No record found!").build();
        } else if (exception instanceof UserNotExistException) {
            return Response.status(Response.Status.NOT_FOUND).entity("User with that ID not found!").build();
        } else {
            LOG.fatalf(exception,
                    "Failed to process request to:");
            // httpServerRequestProvider.get().absoluteURI());
            return Response.serverError().entity("Internal Server Error").build();
        }
    }

}
