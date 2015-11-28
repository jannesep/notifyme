package me.notify.rest;

import me.notify.model.Feedback;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */

@Path("/feedbacks/")
public class Feedbacks {

    @GET
    @Path("/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Feedback> getFeedbacks(@PathParam("date") long date) {
        List<Feedback> feedbacks = new ArrayList<Feedback>();
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id, notification_id, time, title, answer FROM feedback WHERE time > ?");
            ps.setDate(1, new java.sql.Date(date));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                feedbacks.add(new Feedback(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
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
        return feedbacks;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFeedback(Feedback feedback) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO feedback (notification_id, time, title, answer) VALUES (?,?,?,?)");
            ps.setInt(1, feedback.getNotificationId());
            ps.setDate(2, new java.sql.Date(feedback.getTime().getTime()));
            ps.setString(3, feedback.getTitle());
            ps.setString(4, feedback.getAnswer());
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateFeedback(Feedback feedback) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE feedback SET notification_id = ?, time = ?, title = ?, answer = ? WHERE id = ?");
            ps.setInt(1, feedback.getNotificationId());
            ps.setDate(2, new java.sql.Date(feedback.getTime().getTime()));
            ps.setString(3, feedback.getTitle());
            ps.setString(4, feedback.getAnswer());
            ps.setInt(5, feedback.getId());
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
