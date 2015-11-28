package me.notify.model;

import java.util.List;

/**
 * Created by janne on 20.11.2015.
 */
public class UserFoodCategoryContainer {

    private int userId;
    private List<Integer> categories;

    public UserFoodCategoryContainer(int userId, List<Integer> categories) {
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

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
