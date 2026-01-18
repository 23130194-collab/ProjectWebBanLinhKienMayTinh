package com.example.demo1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UserLogoutServlet", value = "/user-logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới nếu không có
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate(); // Hủy toàn bộ session
        }
        // Chuyển hướng về trang chủ
        response.sendRedirect(request.getContextPath() + "/home.jsp");
    }
}
