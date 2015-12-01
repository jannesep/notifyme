package me.notify.model;

import java.util.List;

/**
 * Created by janne on 1.12.2015.
 */
public class FoodItemCategoryList {

    private int foodId;
    private List<Integer> categoryIds;

    public FoodItemCategoryList(int foodId, List<Integer> categoryIds) {
        this.foodId = foodId;
        this.categoryIds = categoryIds;
    }

    public FoodItemCategoryList() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
