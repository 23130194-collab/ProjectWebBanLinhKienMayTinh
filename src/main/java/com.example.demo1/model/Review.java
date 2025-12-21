package com.example.demo1.model;

public class Review {
    private int id;
    private int product_id;
    private int user_id;
    private String user_name;
    private int rating;
    private String comment;
    private String create_at;

    public Review(int id, int product_id, int user_id, int rating, String comment, String create_at) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.rating = rating;
        this.comment = comment;
        this.create_at = create_at;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
