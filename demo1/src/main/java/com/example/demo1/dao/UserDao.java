package com.example.demo1.dao;

import com.example.demo1.model.User;

import java.util.List;

public class UserDao extends DatabaseDao {
    public User getUserByEmail(String email) {
        return get().withHandle( h -> h.createQuery( "select * from users where email = :email").bind("email", email)
                .mapToBean(User.class).stream().findFirst().orElse(null));
    }

    public void insertUser(String name, String email, String password) {
        get().useHandle(h ->
                h.createUpdate(
                                "insert into users(name, email, password, role) " +
                                        "values (:name, :email, :password, :role)"
                        )
                        .bind("name", name)
                        .bind("email", email)
                        .bind("password", password)
                        .bind("role", 0)
                        .execute()
        );
    }

    public void updatePassword(String email, String password) {
        get().useHandle(h ->
                h.createUpdate("UPDATE users SET password = :password WHERE email = :email")
                        .bind("password", password)
                        .bind("email", email)
                        .execute()
        );
    }

    // Thêm hàm này để lấy danh sách user
    public List<User> getAllUsers() {
        // This query assumes you have an 'orders' table with a 'user_id' column.
        // If your table names are different, you will need to update this query.
        String sql = "SELECT u.*, COUNT(o.id) AS orderCount " +
                "FROM users u " +
                "LEFT JOIN orders o ON u.id = o.user_id " +
                "GROUP BY u.id " +
                "ORDER BY u.created_at DESC";
        try {
            return get().withHandle(h ->
                    h.createQuery(sql)
                            .mapToBean(User.class)
                            .list()
            );
        } catch (Exception e) {
            // Fallback query if the 'orders' table doesn't exist or causes an error
            return get().withHandle(h ->
                    h.createQuery("SELECT * FROM users ORDER BY created_at DESC")
                            .mapToBean(User.class)
                            .list()
            );
        }
    }

    public List<User> getAllCustomers() {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM users WHERE role = 0 ORDER BY created_at DESC")
                        .mapToBean(User.class)
                        .list()
        );
    }

    public User getUserById(int id) {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .stream()
                        .findFirst()
                        .orElse(null)
        );
    }

    public void updateUser(User user) {
        get().useHandle(h ->
                h.createUpdate("UPDATE users SET name = :name, email = :email, phone = :phone, address = :address, gender = :gender, birthday = :birthday WHERE id = :id")
                        .bindBean(user)
                        .execute()
        );
    }

    // 1. Hàm đếm tổng số khách hàng (để tính Total Pages)
    public int countAllUsers() {
        return get().withHandle(h ->
                h.createQuery("SELECT COUNT(*) FROM users") // Hoặc WHERE role = 0 nếu chỉ đếm khách hàng
                        .mapTo(Integer.class)
                        .one()
        );
    }

    // 2. Hàm lấy danh sách có phân trang (LIMIT, OFFSET)
    public List<User> getUsersPaging(int limit, int offset) {
        return get().withHandle(h ->
                h.createQuery("SELECT * FROM users ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(User.class)
                        .list()
        );
    }

    //Cập nhật để mở/khóa
    public void updateUserStatus(int userId, String newStatus) {
        get().useHandle(h ->
                h.createUpdate("UPDATE users SET status = :status WHERE id = :id")
                        .bind("status", newStatus)
                        .bind("id", userId)
                        .execute()
        );
    }

    // Hàm lấy status hiện tại (để biết đang khóa hay đang mở mà đảo ngược lại)
    public String getUserStatus(int userId) {
        return get().withHandle(h ->
                h.createQuery("SELECT status FROM users WHERE id = :id")
                        .bind("id", userId)
                        .mapTo(String.class)
                        .one()
        );
    }

    // 1. Hàm đếm có lọc status
    public int countUsersByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM users";
        boolean hasFilter = status != null && !status.equals("all");

        if (hasFilter) {
            sql += " WHERE status = :status";
        }

        String finalSql = sql;
        return get().withHandle(h -> {
            var q = h.createQuery(finalSql);
            if (hasFilter) q.bind("status", status);
            return q.mapTo(Integer.class).one();
        });
    }

    // Trong file UserDao.java

    public List<User> getUsersPaging(int limit, int offset, String status) {
        // 1. Xây dựng câu SQL có JOIN để đếm đơn hàng
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT u.*, COUNT(o.id) as orderCount "); // Đếm số đơn hàng và gán vào alias orderCount
        sql.append("FROM users u ");
        sql.append("LEFT JOIN orders o ON u.id = o.user_id "); // Kết nối bảng users với orders (LEFT JOIN để lấy cả user chưa có đơn)

        // 2. Xử lý bộ lọc trạng thái
        boolean hasFilter = status != null && !status.equals("all");
        if (hasFilter) {
            // Lưu ý: Dùng u.status vì bảng users được đặt tên giả là 'u'
            sql.append("WHERE u.status = :status ");
        }

        // 3. Gom nhóm và Sắp xếp
        sql.append("GROUP BY u.id "); // Bắt buộc phải Group By ID thì mới đếm đúng từng người
        sql.append("ORDER BY u.created_at DESC LIMIT :limit OFFSET :offset");

        // 4. Thực thi
        return get().withHandle(h -> {
            var query = h.createQuery(sql.toString())
                    .bind("limit", limit)
                    .bind("offset", offset);

            if (hasFilter) {
                query.bind("status", status);
            }

            // JDBI sẽ tự động map cột 'orderCount' trong SQL vào thuộc tính 'orderCount' trong Model User
            return query.mapToBean(User.class).list();
        });
    }
}
