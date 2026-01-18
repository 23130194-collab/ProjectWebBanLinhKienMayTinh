package com.example.demo1.controller;

import com.example.demo1.service.AuthService;
import com.example.demo1.util.PasswordValidator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import com.example.demo1.model.User;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Kiểm tra định dạng mật khẩu trước khi truy vấn DB
        if (!PasswordValidator.isValid(password)) {
            request.setAttribute("error", "Email hoặc mật khẩu không hợp lệ!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AuthService as = new AuthService();
        User user = as.checkLogin(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home.jsp");
        } else {
            User existingUser = as.getUserByEmail(email);
            if (existingUser != null && "Locked".equalsIgnoreCase(existingUser.getStatus())) {
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ Admin!");
            } else {
                request.setAttribute("error", "Email hoặc mật khẩu không hợp lệ!");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
