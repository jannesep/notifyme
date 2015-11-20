package me.notify.model;

/**
 * Created by janne on 18.11.2015.
 */
public class User {

    private int id;
    private String username;
    private String imei;

    public User() {

    }

    public User(int id, String username, String imei) {
        this.id = id;
        this.username = username;
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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




}
