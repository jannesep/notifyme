package me.notify.rest;

import java.io.Serializable;

/**
 * Created by janne on 18.11.2015.
 */
public class User implements Serializable {

    private int id;
    private String username;
    private String fullName;

    public User() {
        id = 123;
        username = "testuser";
        fullName = "Test User";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
