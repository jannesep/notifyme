package me.notify.rest;

import me.notify.model.FoodCategory;
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

@Path("/foodcategories/")
public class FoodCategories {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodCategory> getCategories() {
        List<FoodCategory> categories = new ArrayList<FoodCategory>();
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT id, name FROM food_category");
            while(rs.next()) {
                categories.add(new FoodCategory(rs.getInt(1), rs.getString(2)));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addFoodCategory(FoodCategory category) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO food_category SET name = ?");
            ps.setString(1, category.getName());
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

    @Path("/{id}")
    @DELETE
    public void deleteFoodCategory(@PathParam("id") int id) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM food_category WHERE id = ?");
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
