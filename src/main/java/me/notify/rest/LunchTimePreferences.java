package me.notify.rest;

import me.notify.model.LunchTimePreference;
import me.notify.model.User;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by janne on 20.11.2015.
 */
@Path("/preferredlunchtime/")
public class LunchTimePreferences {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LunchTimePreference getLunchTimePreference(User user) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT monday, tuesday, wednesday, thursday, friday, saturday, sunday FROM preferred_lunch_time WHERE user_id = ?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                return new LunchTimePreference(user, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LunchTimePreference();
    }

    @POST
    public void replacePreferences(LunchTimePreference pref) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM preferred_lunch_time WHERE user_id = ?");
            ps.setInt(1, pref.getUser().getId());
            ps.executeUpdate();

            ps = DBManager.getConnection().prepareStatement(
                    "INSERT INTO preferred_lunch_time (user_id, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, pref.getUser().getId());
            ps.setString(2, pref.getMonday());
            ps.setString(3, pref.getTuesday());
            ps.setString(4, pref.getWednesday());
            ps.setString(5, pref.getThursday());
            ps.setString(6, pref.getFriday());
            ps.setString(7, pref.getSaturday());
            ps.setString(8, pref.getSunday());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
