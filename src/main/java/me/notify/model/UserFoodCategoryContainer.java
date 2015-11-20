package me.notify.model;

import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */
public class UserFoodCategoryContainer {

    private int userId;
    private List<FoodCategory> categories;

    public UserFoodCategoryContainer(int userId, List<FoodCategory> categories) {
        this.userId = userId;
        this.categories = categories;
    }

    public UserFoodCategoryContainer() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FoodCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FoodCategory> categories) {
        this.categories = categories;
    }
}
