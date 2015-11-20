package me.notify.model;

import java.util.Date;

/**
 * Created by janne on 20.11.2015.
 */
public class Notification {

    private int id;
    private int userId;
    private int placeId;
    private Date time;
    private String title;
    private String message;

    public Notification(int id, int userId, int placeId, Date time, String title, String message) {
        this.id = id;
        this.userId = userId;
        this.placeId = placeId;
        this.time = time;
        this.title = title;
        this.message = message;
    }

    public Notification() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
