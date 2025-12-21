package com.example.demo1.model;

public class Category {
    private int id;
    private String name;
    private int display_order;

    public Category(int id, String name, int display_order) {
        this.id = id;
        this.name = name;
        this.display_order = display_order;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }
}
