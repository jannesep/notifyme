package me.notify.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by janne on 16.11.2015.
 */

@Path("/hello/")
public class Hello {

    @GET
    @Produces("application/json")
    public String getHello() {
        return "Terve";
    }
}
