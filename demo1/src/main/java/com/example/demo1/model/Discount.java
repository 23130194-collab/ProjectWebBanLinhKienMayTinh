package com.example.demo1.model;

public class Discount {
    private int id;
    private double discount_value;
    private String start_time;
    private String end_time;

    public Discount(int id, double discount_value, String start_time, String end_time) {
        this.id = id;
        this.discount_value = discount_value;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Discount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(double discount_value) {
        this.discount_value = discount_value;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
