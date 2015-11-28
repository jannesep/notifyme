package me.notify.rest;

import me.notify.model.LunchTimePreference;
import me.notify.model.User;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by janne on 20.11.2015.
 */
@Path("/preferredlunchtime/")
public class LunchTimePreferences {

    @GET
    @Path("/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public LunchTimePreference getLunchTimePreference(@PathParam("userid") int userId) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT monday, tuesday, wednesday, thursday, friday, saturday, sunday FROM preferred_lunch_time WHERE user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                return new LunchTimePreference(userId, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new LunchTimePreference();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void replacePreferences(LunchTimePreference pref) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM preferred_lunch_time WHERE user_id = ?");
            ps.setInt(1, pref.getUserId());
            ps.executeUpdate();

            ps = conn.prepareStatement(
                    "INSERT INTO preferred_lunch_time (user_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, pref.getUserId());
            ps.setString(2, pref.getMonday());
            ps.setString(3, pref.getTuesday());
            ps.setString(4, pref.getWednesday());
            ps.setString(5, pref.getThursday());
            ps.setString(6, pref.getFriday());
            ps.setString(7, pref.getSaturday());
            ps.setString(8, pref.getSunday());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
