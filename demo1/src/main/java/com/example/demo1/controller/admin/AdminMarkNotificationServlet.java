package com.example.demo1.controller.admin;

import com.example.demo1.dao.NotificationDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminMarkNotificationServlet", value = "/admin/mark-read")
public class AdminMarkNotificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String targetLink = request.getParameter("target");

            NotificationDao dao = new NotificationDao();
            dao.markAsRead(id);

            response.sendRedirect(request.getContextPath() + "/" + targetLink);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}