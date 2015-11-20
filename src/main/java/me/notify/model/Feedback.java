package me.notify.model;

import java.util.Date;

/**
 * Created by janne on 20.11.2015.
 */
public class Feedback {

    private int id;
    private int notificationId;
    private Date time;
    private String title;
    private String answer;

    public Feedback(int id, int notificationId, Date time, String title, String answer) {
        this.id = id;
        this.notificationId = notificationId;
        this.time = time;
        this.title = title;
        this.answer = answer;
    }

    public Feedback() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
