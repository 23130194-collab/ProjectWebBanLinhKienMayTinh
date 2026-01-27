package com.example.demo1.controller.admin;

import com.example.demo1.dao.UserDao;
import com.example.demo1.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List; // QUAN TRỌNG: Phải import List từ java.util

@WebServlet(name = "AdminCustomerListController", value = "/admin/customers")
public class AdminCustomerListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        String status = request.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            status = "all";
        }

        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }

        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int pageSize = 5;

        int totalUsers = userDao.countCustomersByFilter(status, keyword);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        int offset = (page - 1) * pageSize;

        List<User> list = userDao.getCustomersPaging(pageSize, offset, status, keyword);

        request.setAttribute("customerList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.setAttribute("keyword", keyword);
        request.setAttribute("contextPath", request.getContextPath());

        request.setAttribute("currentStatus", status);

        request.getRequestDispatcher("/admin/customersList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}