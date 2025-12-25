package com.example.demo1.model;

public class Product_Spec {
    private int id;
    private int product_id;
    private int attribute_id;
    private String attribute_name;
    private String spec_value;

    public Product_Spec(int id, int product_id, int attribute_id, String spec_value) {
        this.id = id;
        this.product_id = product_id;
        this.attribute_id = attribute_id;
        this.spec_value = spec_value;
    }

    public Product_Spec() {
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

    public int getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getSpec_value() {
        return spec_value;
    }

    public void setSpec_value(String spec_value) {
        this.spec_value = spec_value;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }
}
