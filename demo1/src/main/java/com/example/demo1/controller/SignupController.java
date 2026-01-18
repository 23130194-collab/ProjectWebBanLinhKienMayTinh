package com.example.demo1.controller;

import com.example.demo1.service.AuthService;
import com.example.demo1.util.MD5;
import com.example.demo1.util.PasswordValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // 1. Kiểm tra độ phức tạp của mật khẩu
        if (!PasswordValidator.isValid(password)) {
            request.setAttribute("error", "Mật khẩu phải dài ít nhất 8 ký tự, bao gồm chữ hoa, chữ số và ký tự đặc biệt.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // 2. Kiểm tra mật khẩu khớp
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        AuthService service = new AuthService();

        // 3. Kiểm tra email tồn tại
        if (service.emailExists(email)) {
            request.setAttribute("error", "Email đã được đăng ký!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // 4. Hash mật khẩu và đăng ký
        String hashedPassword = MD5.hash(password);
        service.register(name, email, hashedPassword);

        // 5. Thành công → login
        request.getSession().setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
