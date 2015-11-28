package me.notify.rest;

import me.notify.model.Place;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */

@Path("/places/")
public class Places {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<Place>();
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, name, latitude, longtitude, opening_time, closing_time FROM places");
            while (rs.next()) {
                places.add(new Place(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return places;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPlace(Place place) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO places (name, latitude, longtitude, opening_time, closing_time) VALUES (?,?,?,?,?)");
            ps.setString(1, place.getName());
            ps.setString(2, place.getLatitude());
            ps.setString(3, place.getLongtitude());
            ps.setString(4, place.getOpeningTime());
            ps.setString(5, place.getClosingTime());
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

    @DELETE
    public void deletePlace(int id) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM places WHERE id = ?");
            ps.setInt(1, id);
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
