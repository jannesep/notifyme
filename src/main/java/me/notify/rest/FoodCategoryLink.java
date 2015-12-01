package me.notify.rest;

import me.notify.controller.FoodCategoryController;
import me.notify.model.FoodCategory;
import me.notify.model.FoodItem;
import me.notify.model.FoodItemCategoryContainer;
import me.notify.model.FoodItemCategoryList;
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

@Path("/foodcategorylink/")
public class FoodCategoryLink {

    @GET
    @Path("/{foodid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodCategory> getCategoryLinks(@PathParam("foodid") int item) {
        List<FoodCategory> categories = new ArrayList<FoodCategory>();
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT category_id FROM food_category_link WHERE food_id = ?");
            ps.setInt(1, item);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new FoodCategory(FoodCategoryController.getFoodCategory(rs.getInt(1))));
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
        return categories;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createLink(FoodItemCategoryContainer container) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO food_category_link (food_id, category_id) VALUES (?,?)");
            ps.setInt(1, container.getFoodId());
            ps.setInt(2, container.getCategId());
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createMultipleLinks(FoodItemCategoryList items) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = null;
            for (Integer catId : items.getCategoryIds()) {
                ps = conn.prepareStatement("INSERT INTO food_category_link (food_id, category_id) VALUES (?,?)");
                ps.setInt(1, items.getFoodId());
                ps.setInt(2, catId);
                ps.executeUpdate();
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
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteLink(FoodItemCategoryContainer container) {
        Connection conn = null;
        try {
            conn = DBManager.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM food_category_link WHERE food_id = ? AND category_id = ?");
            ps.setInt(1, container.getFoodId());
            ps.setInt(2, container.getCategId());
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
