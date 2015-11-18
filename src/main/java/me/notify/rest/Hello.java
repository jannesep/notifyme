package me.notify.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 16.11.2015.
 */

@Path("/hello/")
public class Hello {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getUser() {
        List users = new ArrayList();
        users.add(new User());

        return users;
    }
}
