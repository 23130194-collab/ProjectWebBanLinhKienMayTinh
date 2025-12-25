package com.example.demo1.model;

public class Product_Image {
    private int id;
    private int product_id;
    private String image;
    private int display_order;

    public Product_Image(int display_order, String image, int product_id, int id) {
        this.display_order = display_order;
        this.image = image;
        this.product_id = product_id;
        this.id = id;
    }

    public Product_Image() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }
}
