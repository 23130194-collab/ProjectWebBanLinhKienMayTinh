package com.example.demo1.model;

public class Brand {
    private int id;
    private String name;
    private String logo;
    private int display_order;

    public Brand(int id, String name, String logo, int display_order) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.display_order = display_order;
    }

    public Brand() {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }
}
