package com.example.demo1.dao;

import com.example.demo1.model.Order;
import java.util.List;

public class OrderDao extends DatabaseDao {

    public List<Order> getOrdersByUserId(int userId) {
        // Query lấy dữ liệu theo user_id
        String sql = "SELECT * FROM orders WHERE user_id = :userId ORDER BY created_at DESC";

        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Order.class) // Tự động map order_code -> orderCode, total_amount -> totalAmount
                        .list()
        );
    }
    // 1. Hàm đếm tổng số đơn hàng của user (để tính số trang)
    public int countOrdersByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = :userId";
        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapTo(Integer.class)
                        .one()
        );
    }

    // 2. Hàm lấy danh sách đơn hàng có Phân trang (Limit, Offset)
    public List<Order> getOrdersByUserIdPaging(int userId, int limit, int offset) {
        String sql = "SELECT * FROM orders WHERE user_id = :userId ORDER BY created_at DESC LIMIT :limit OFFSET :offset";

        return get().withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Order.class)
                        .list()
        );
    }
}