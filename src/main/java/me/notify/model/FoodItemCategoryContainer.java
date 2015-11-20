package me.notify.model;

/**
 * Created by janne on 20.11.2015.
 */
public class FoodItemCategoryContainer {

    private int foodId;
    private int categId;

    public FoodItemCategoryContainer(int foodId, int categId) {
        this.foodId = foodId;
        this.categId = categId;
    }

    public FoodItemCategoryContainer() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getCategId() {
        return categId;
    }

    public void setCategId(int categId) {
        this.categId = categId;
    }
}
