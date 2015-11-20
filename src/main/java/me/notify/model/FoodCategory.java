package me.notify.model;

/**
 * Created by janne on 20.11.2015.
 */
public class FoodCategory {

    private int id;
    private String name;

    public FoodCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FoodCategory(FoodCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public FoodCategory() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
