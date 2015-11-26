package me.notify.rest;

import me.notify.controller.FoodCategoryController;
import me.notify.model.FoodCategory;
import me.notify.model.UserFoodCategoryContainer;
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

@Path("/categorypreferences/")
public class CategoryPreferences {

    @GET
    @Path("/{userid}")
    public List<FoodCategory> getCategoryPreferences(@PathParam("userid") int userId) {
        List<FoodCategory> categories = new ArrayList<FoodCategory>();

        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement(
                    "SELECT category_id FROM category_preferences WHERE user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new FoodCategory(FoodCategoryController.getFoodCategory(rs.getInt(1))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCannotConsume(UserFoodCategoryContainer container) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM category_preferences WHERE user_id = ?");
            ps.setInt(1, container.getUserId());
            ps.executeUpdate();

            for (FoodCategory categ : container.getCategories()) {
                ps = DBManager.getConnection().prepareStatement("INSERT INTO category_preferences (user_id, category_id) VALUES (?,?)");
                ps.setInt(1, container.getUserId());
                ps.setInt(2, categ.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
