package com.example.demo1.dao;

import com.example.demo1.model.User;
import org.jdbi.v3.core.Jdbi;

public class UserDao {
    private Jdbi jdbi = DatabaseDao.get();

    public User getUserByEmail(String email) {
        return jdbi.withHandle( h -> h.createQuery( "select * from users where email = :email").bind("email", email)
                .mapToBean(User.class).stream().findFirst().orElse(null));
    }

    public void insertUser(String name, String email, String password) {
        jdbi.useHandle(h ->
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
        jdbi.useHandle(h ->
                h.createUpdate("UPDATE users SET password = :password WHERE email = :email")
                        .bind("password", password)
                        .bind("email", email)
                        .execute()
        );
    }
}
