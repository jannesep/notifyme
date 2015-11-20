package me.notify.controller;

import me.notify.model.FoodCategory;
import me.notify.servlet.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by janne on 20.11.2015.
 */
public class FoodCategoryController {

    public static FoodCategory getFoodCategory(int id) {
        try {
            PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT id, name FROM food_category WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                return new FoodCategory(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
