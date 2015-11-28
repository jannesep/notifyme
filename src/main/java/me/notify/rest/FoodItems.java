package me.notify.rest;

import me.notify.model.FoodItem;
import me.notify.model.Place;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */
@Path("/fooditems/")
public class FoodItems {

    @GET
    @Path("/{placeid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodItem> getFoodItems(@PathParam("placeid") int placeId) {
        List<FoodItem> items = new ArrayList<FoodItem>();
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, food_date, name, description FROM food_item WHERE place_id = ?");
            ps.setInt(1, placeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new FoodItem(rs.getInt(1), placeId, rs.getDate(2), rs.getString(3), rs.getString(4)));
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
        return items;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFoodItem(FoodItem item) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO food_item (place_id, food_date, name, description) VALUES (?, ?, ?, ?)");
            ps.setInt(1, item.getId());
            ps.setDate(2, new Date(item.getFoodDate().getTime()));
            ps.setString(3, item.getName());
            ps.setString(4, item.getDescription());
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
    public void deleteFoodItem(int id) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM food_item WHERE id = ?");
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
