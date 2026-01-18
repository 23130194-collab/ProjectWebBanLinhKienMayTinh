package com.example.demo1.service;

import com.example.demo1.dao.UserDao;
import com.example.demo1.model.User;
import com.example.demo1.util.MD5;

public class AuthService {
    UserDao authDao = new UserDao();

    public User checkLogin(String email, String password) {
        User u = authDao.getUserByEmail(email);
        if (u != null && u.getPassword().equals(MD5.hash(password))) {
            // Chỉ cho đăng nhập nếu tài khoản đã active
            if ("active".equalsIgnoreCase(u.getStatus())) {
                u.setPassword(null);
                return u;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return authDao.getUserByEmail(email);
    }

    public boolean emailExists(String email) {
        return authDao.getUserByEmail(email) != null;
    }

    public void register(String name, String email, String hashedPassword, String token) {
        authDao.insertUser(name, email, hashedPassword, token);
    }
}
