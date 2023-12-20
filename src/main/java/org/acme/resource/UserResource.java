package org.acme.resource;

import java.util.List;

import org.acme.domain.User;
import org.acme.service.UserService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@RequestScoped
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> result = userService.users();

        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userService.createUser(user);

        return Response.created(null).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) {
        User user = userService.get(id);

        return Response.ok(user).build();
    }

    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_PLAIN)
    public Response query(@QueryParam("name") String name) {
        return Response.ok("Hello "+name).build();
    }
    

}
