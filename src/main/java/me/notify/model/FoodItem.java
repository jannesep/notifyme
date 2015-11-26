package me.notify.model;

import java.util.Date;

/**
 * Created by janne on 20.11.2015.
 */
public class FoodItem {

    private int id;
    private int place;
    private Date foodDate;
    private String name;
    private String description;

    public FoodItem(int id, int place, Date foodDate, String name, String description) {
        this.id = id;
        this.place = place;
        this.foodDate = foodDate;
        this.name = name;
        this.description = description;
    }

    public FoodItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Date getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(Date foodDate) {
        this.foodDate = foodDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
