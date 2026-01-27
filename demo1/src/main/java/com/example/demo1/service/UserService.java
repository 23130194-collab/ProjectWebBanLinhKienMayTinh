package com.example.demo1.service;

import com.example.demo1.dao.DatabaseDao;
import com.example.demo1.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private final Jdbi jdbi;

    public UserService() {

        this.jdbi = DatabaseDao.get();
    }

    public static class UserMapper implements RowMapper<User> {
        @Override
        public User map(ResultSet rs, StatementContext ctx) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setRole(rs.getInt("role"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setAddress(rs.getString("address"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            user.setStatus(rs.getString("status"));
            user.setCreated_at(rs.getTimestamp("created_at"));
            user.setOtpCode(rs.getString("otp_code"));
            user.setOtpExpiry(rs.getTimestamp("otp_expiry"));
            user.setPasswordUpdatedAt(rs.getTimestamp("password_updated_at"));
            user.setSocialId(rs.getString("social_id"));
            return user;
        }
    }

    public User getUserByEmail(String email) {
        return jdbi.withHandle(handle -> {
            Optional<User> userOptional = handle.createQuery("SELECT * FROM users WHERE email = :email")
                    .bind("email", email)
                    .map(new UserMapper())
                    .findOne();
            return userOptional.orElse(null);
        });
    }

    public void createUser(User user) {
        jdbi.useHandle(handle -> {
            int defaultRole = 2;
            String defaultStatus = "active";

            handle.createUpdate("INSERT INTO users (role, email, password, name, status, social_id) " +
                                 "VALUES (:role, :email, NULL, :name, :status, :social_id)")
                    .bind("role", defaultRole)
                    .bind("email", user.getEmail())
                    .bind("name", user.getName())
                    .bind("status", defaultStatus)
                    .bind("social_id", user.getSocialId())
                    .execute();

        });
    }
}
