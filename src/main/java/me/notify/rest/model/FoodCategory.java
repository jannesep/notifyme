package me.notify.rest.model;

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
