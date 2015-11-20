package me.notify.rest;

import me.notify.model.Notification;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */

@Path("/notifications/")
public class Notifications {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notification> getSentNotifications(Date date) {
        List<Notification> notifications = new ArrayList<Notification>();
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT id, user_id, place_id, time, title, message FROM sent_notifications WHERE time > ?");
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                notifications.add(new Notification(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addSentNotification(Notification notification) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement(
                    "INSERT INTO sent_notification (user_id, place_id, time, title, message) VALUES (?,?,?,?,?)");
            ps.setInt(1, notification.getUserId());
            ps.setInt(2, notification.getPlaceId());
            ps.setDate(3, new java.sql.Date(notification.getTime().getTime()));
            ps.setString(4, notification.getTitle());
            ps.setString(5, notification.getMessage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
