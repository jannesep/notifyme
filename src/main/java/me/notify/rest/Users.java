package me.notify.rest;

import me.notify.model.User;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 16.11.2015.
 */

@Path("/users/")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUser() {
        List<User> users = new ArrayList<User>();
        try {
            ResultSet rs = DBManager.getConnection().createStatement().executeQuery("SELECT id, name, imei FROM users");
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO users (name, imei) VALUES (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getImei());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DELETE
    public void deleteUser(int id) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
