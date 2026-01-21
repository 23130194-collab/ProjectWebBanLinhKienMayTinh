package com.example.demo1.model;
import java.sql.Timestamp;

public class Notification {
    private int id;
    private int userId;
    private String content;
    private String link;
    private Timestamp createdAt;

    public Notification() {}
    public Notification(int userId, String content, String link) {
        this.userId = userId;
        this.content = content;
        this.link = link;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}