package me.notify.rest;

import me.notify.rest.model.FoodItem;
import me.notify.rest.model.Place;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */
@Path("/fooditems/")
public class FoodItems {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodItem> getFoodItems(Place place) {
        List<FoodItem> items = new ArrayList<FoodItem>();
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT id, food_date, name, descprition FROM food_items WHERE place_id = ?");
            ps.setInt(1, place.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new FoodItem(rs.getInt(1), place, rs.getDate(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFoodItem(FoodItem item) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO food_item (place_id, food_date, name, description) VALUES (?, ?, ?, ?)");
            ps.setInt(1, item.getId());
            ps.setDate(2, new Date(item.getFoodDate().getTime()));
            ps.setString(3, item.getName());
            ps.setString(4, item.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DELETE
    public void deleteFoodItem(int id) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM food_item WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
