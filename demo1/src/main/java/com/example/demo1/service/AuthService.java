package com.example.demo1.service;

import com.example.demo1.dao.AuthDao;
import com.example.demo1.model.User;

public class AuthService {
    UserDao authDao = new UserDao();

    public User checkLogin(String email, String password) {
        User u = authDao.getUserByEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            u.setPassword(null);
            return u;
        }
        return null;
    }

    public boolean emailExists(String email) {
        return authDao.getUserByEmail(email) != null;
    }

    public void register(String name, String email, String password) {
        authDao.insertUser(name, email, password);
    }
}
