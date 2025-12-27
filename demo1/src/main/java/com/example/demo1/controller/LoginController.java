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
            HttpSession session = request.getSession();
            session.setAttribute("auth", u);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Email hoặc Password không hợp lệ!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
