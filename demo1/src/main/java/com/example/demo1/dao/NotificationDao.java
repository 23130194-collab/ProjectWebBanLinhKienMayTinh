package com.example.demo1.dao;

import com.example.demo1.model.Notification;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class NotificationDao {

    // 1. Khởi tạo Jdbi theo phong cách của bạn
    private final Jdbi jdbi = DatabaseDao.get();

    /**
     * Thêm thông báo mới vào Database
     */
    public void insert(Notification n) {
        String sql = "INSERT INTO notifications (user_id, content, link) VALUES (?, ?, ?)";

        // Dùng useHandle cho các thao tác ghi (INSERT/UPDATE/DELETE) không cần trả về kết quả
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind(0, n.getUserId())
                        .bind(1, n.getContent())
                        .bind(2, n.getLink())
                        .execute()
        );
    }

    /**
     * Lấy danh sách thông báo của 1 user (Mới nhất lên đầu)
     */
    public List<Notification> getByUser(int userId) {
        String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";

        // Dùng withHandle cho các thao tác đọc (SELECT) cần trả về dữ liệu
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind(0, userId)
                        .mapToBean(Notification.class) // Tự động map cột db sang field của class
                        .list()
        );
    }
}