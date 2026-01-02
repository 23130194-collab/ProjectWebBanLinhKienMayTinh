package com.example.demo1.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private int category_id;
    private int brand_id;
    private String name;
    private int discount_id;
    private String description;
    private double price;
    private double old_price;
    private int stock;
    private String image;
    private String status; // Thêm trường status
    private double discount_value;
    private double avg_rating;

    public Product(int id, int category_id, int brand_id, String name, int discount_id, String description, double price, double old_price, int stock, String image, String status) {
        this.id = id;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.name = name;
        this.discount_id = discount_id;
        this.description = description;
        this.price = price;
        this.old_price = old_price;
        this.stock = stock;
        this.image = image;
        this.status = status;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(double discount_value) {
        this.discount_value = discount_value;
    }

    public double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(double avg_rating) {
        this.avg_rating = avg_rating;
    }
}
