package me.notify.rest;

import me.notify.controller.FoodCategoryController;
import me.notify.model.FoodCategory;
import me.notify.model.UserFoodCategoryContainer;
import me.notify.servlet.DBManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */

@Path("/cannotconsume/")
public class CannotConsume {

    @GET
    public List<FoodCategory> getCannotConsume(int userId) {
        List<FoodCategory> categories = new ArrayList<FoodCategory>();

        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement(
                    "SELECT category_id FROM cannot_consume WHERE user_id = ?");
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
            PreparedStatement ps = DBManager.getConnection().prepareStatement("DELETE FROM cannot_consume WHERE user_id = ?");
            ps.setInt(1, container.getUserId());
            ps.executeUpdate();

            for (FoodCategory categ : container.getCategories()) {
                ps = DBManager.getConnection().prepareStatement("INSERT INTO cannot_consume (user_id, category_id) VALUES (?,?)");
                ps.setInt(1, container.getUserId());
                ps.setInt(2, categ.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
