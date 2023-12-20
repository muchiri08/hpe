package org.acme.resource.filters;

import java.io.IOException;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

    @Inject
    UriInfo uriInfo;

    @Inject
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final String method = requestContext.getMethod();
        final String path = uriInfo.getPath();
        final String address = request.remoteAddress().toString();

        LOG.infof("Request %s %s from IP %s", method, path, address);

    }

}