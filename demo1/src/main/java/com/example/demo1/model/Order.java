package com.example.demo1.model;

import java.sql.Timestamp;
import java.math.BigDecimal; // Dùng BigDecimal cho tiền tệ sẽ chuẩn hơn double

public class Order {
    private int id;
    private int userId;         // DB: user_id
    private String orderCode;   // DB: order_code
    private String orderStatus; // DB: order_status
    private double subprice;
    private double discountAmount; // DB: discount_amount
    private double shippingFee;    // DB: shipping_fee
    private double totalAmount;    // DB: total_amount
    private String notes;
    private Timestamp createdAt;       // DB: created_at
    private Timestamp updatedAt;       // DB: updated_at

    public Order() {}

    // Getter & Setter (Generate tự động trong IntelliJ: Alt+Insert -> Getter and Setter)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


}