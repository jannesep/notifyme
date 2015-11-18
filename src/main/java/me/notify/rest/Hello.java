package me.notify.rest;

import me.notify.servlet.DBManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            Connection conn = DBManager.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, name, imei FROM users");
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
