package me.notify.servlet;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by janne on 16.11.2015.
 */

@ApplicationPath("/*")
public class NotifyMeConfig extends ResourceConfig {

    public NotifyMeConfig() {
        packages("me.notify.rest");
    }

}
