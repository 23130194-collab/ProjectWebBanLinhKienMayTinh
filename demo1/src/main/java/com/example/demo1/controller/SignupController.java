package com.example.demo1.controller;

import com.example.demo1.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SignupController", value = "/signup")
public class SignupController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // 1. Kiểm tra mật khẩu khớp
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        AuthService service = new AuthService();

        // 2. Kiểm tra email tồn tại
        if (service.emailExists(email)) {
            request.setAttribute("error", "Email đã được đăng ký!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // 3. Đăng ký
        service.register(name, email, password);

        // 4. Thành công → login
        response.sendRedirect("login.jsp");
    }

}

