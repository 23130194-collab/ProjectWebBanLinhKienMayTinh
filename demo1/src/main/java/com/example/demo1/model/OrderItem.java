package com.example.demo1.model;

public class OrderItem {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private double originalPrice;
    private double discountPercentage; // Đã đổi từ unitPrice
    private String productName;

    public OrderItem() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Phương thức tiện ích để tính giá sau khi giảm
    public double getUnitPrice() {
        return originalPrice * (1 - discountPercentage / 100);
    }

    // Phương thức tiện ích để tính tổng tiền cho mục này
    public double getTotal() {
        return getUnitPrice() * quantity;
    }
}
