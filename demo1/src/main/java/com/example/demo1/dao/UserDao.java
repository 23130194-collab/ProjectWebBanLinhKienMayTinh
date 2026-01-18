package com.example.demo1.dao;

import com.example.demo1.model.User;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

public class UserDao {
    private Jdbi jdbi = DatabaseDao.get();

    public User getUserByEmail(String email) {
        return jdbi.withHandle(h -> h.createQuery("SELECT * FROM users WHERE email = :email")
                .bind("email", email)
                .mapToBean(User.class)
                .stream().findFirst().orElse(null));
    }

    public void insertUser(String name, String email, String password, String token) {
        jdbi.useHandle(h ->
                h.createUpdate("INSERT INTO users(name, email, password, role, status, verification_token) VALUES (:name, :email, :password, :role, :status, :token)")
                        .bind("name", name)
                        .bind("email", email)
                        .bind("password", password)
                        .bind("role", 0)
                        .bind("status", "unverified")
                        .bind("token", token)
                        .execute()
        );
    }
    
    public Optional<User> findUserByToken(String token) {
        return jdbi.withHandle(h -> h.createQuery("SELECT * FROM users WHERE verification_token = :token")
                .bind("token", token)
                .mapToBean(User.class)
                .findFirst());
    }

    public void activateUser(int userId) {
        jdbi.useHandle(h ->
                h.createUpdate("UPDATE users SET status = 'active', verification_token = NULL WHERE id = :id")
                        .bind("id", userId)
                        .execute()
        );
    }

    public void updatePassword(String email, String password) {
        jdbi.useHandle(h ->
                h.createUpdate("UPDATE users SET password = :password WHERE email = :email")
                        .bind("password", password)
                        .bind("email", email)
                        .execute()
        );
    }

    public List<User> getAllUsers() {
        String sql = "SELECT u.*, COUNT(o.id) AS orderCount " +
                "FROM users u " +
                "LEFT JOIN orders o ON u.id = o.user_id " +
                "GROUP BY u.id " +
                "ORDER BY u.created_at DESC";
        try {
            return jdbi.withHandle(h ->
                    h.createQuery(sql)
                            .mapToBean(User.class)
                            .list()
            );
        } catch (Exception e) {
            return jdbi.withHandle(h ->
                    h.createQuery("SELECT * FROM users ORDER BY created_at DESC")
                            .mapToBean(User.class)
                            .list()
            );
        }
    }

    public List<User> getAllCustomers() {
        return jdbi.withHandle(h ->
                h.createQuery("SELECT * FROM users WHERE role = 0 ORDER BY created_at DESC")
                        .mapToBean(User.class)
                        .list()
        );
    }

    public User getUserById(int id) {
        return jdbi.withHandle(h ->
                h.createQuery("SELECT * FROM users WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .stream()
                        .findFirst()
                        .orElse(null)
        );
    }

    public void updateUser(User user) {
        jdbi.useHandle(h ->
                h.createUpdate("UPDATE users SET name = :name, email = :email, phone = :phone, address = :address, gender = :gender, birthday = :birthday WHERE id = :id")
                        .bindBean(user)
                        .execute()
        );
    }

    public int countAllUsers() {
        return jdbi.withHandle(h ->
                h.createQuery("SELECT COUNT(*) FROM users")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<User> getUsersPaging(int limit, int offset) {
        return jdbi.withHandle(h ->
                h.createQuery("SELECT * FROM users ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(User.class)
                        .list()
        );
    }

    public void updateUserStatus(int userId, String newStatus) {
        jdbi.useHandle(h ->
                h.createUpdate("UPDATE users SET status = :status WHERE id = :id")
                        .bind("status", newStatus)
                        .bind("id", userId)
                        .execute()
        );
    }

    public String getUserStatus(int userId) {
        return jdbi.withHandle(h ->
                h.createQuery("SELECT status FROM users WHERE id = :id")
                        .bind("id", userId)
                        .mapTo(String.class)
                        .one()
        );
    }

    public int countUsersByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM users";
        boolean hasFilter = status != null && !status.equals("all");

        if (hasFilter) {
            sql += " WHERE status = :status";
        }

        String finalSql = sql;
        return jdbi.withHandle(h -> {
            var q = h.createQuery(finalSql);
            if (hasFilter) q.bind("status", status);
            return q.mapTo(Integer.class).one();
        });
    }

    public List<User> getUsersPaging(int limit, int offset, String status) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT u.*, COUNT(o.id) as orderCount ");
        sql.append("FROM users u ");
        sql.append("LEFT JOIN orders o ON u.id = o.user_id ");

        boolean hasFilter = status != null && !status.equals("all");
        if (hasFilter) {
            sql.append("WHERE u.status = :status ");
        }

        sql.append("GROUP BY u.id ");
        sql.append("ORDER BY u.created_at DESC LIMIT :limit OFFSET :offset");

        return jdbi.withHandle(h -> {
            var query = h.createQuery(sql.toString())
                    .bind("limit", limit)
                    .bind("offset", offset);

            if (hasFilter) {
                query.bind("status", status);
            }

            return query.mapToBean(User.class).list();
        });
    }
}
