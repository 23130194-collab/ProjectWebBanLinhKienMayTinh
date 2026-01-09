package com.example.demo1.controller;

import com.example.demo1.service.AuthService;
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

        AuthService as = new AuthService();
        User u = as.checkLogin(email, password);

        if (u != null) {
            // --- BẮT ĐẦU PHẦN THÊM MỚI: KIỂM TRA KHÓA ---

            // Giả sử quy ước trong Database: status = "Locked" là bị khóa
            // (Nếu trong DB bạn lưu kiểu int 0/1 thì sửa code check tương ứng)
            if ("Locked".equalsIgnoreCase(u.getStatus())) {
                // 1. Báo lỗi ra trang login
                request.setAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ Admin!");
                request.getRequestDispatcher("login.jsp").forward(request, response);

                // 2. QUAN TRỌNG: return ngay để không tạo Session bên dưới
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("auth", u);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Email hoặc Password không hợp lệ!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
