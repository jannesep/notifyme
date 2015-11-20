package me.notify.rest;

import me.notify.rest.model.FoodCategory;
import me.notify.servlet.DBManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */

@Path("/foodcategories/")
public class FoodCategories {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodCategory> getCategories() {
        List<FoodCategory> categories = new ArrayList<FoodCategory>();
        try {
            ResultSet rs = DBManager.getConnection().createStatement().executeQuery("SELECT id, name FROM food_category");
            while(rs.next()) {
                categories.add(new FoodCategory(rs.getInt(1), rs.getString(2)));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFoodCategory(FoodCategory category) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO food_category SET name = ?");
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DELETE
    public void deleteFoodCategory(int id) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM food_category WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
