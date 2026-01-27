package com.example.demo1.controller.admin;

import com.example.demo1.dao.OrderDao;
import com.example.demo1.dao.UserDao;
import com.example.demo1.model.Order;
import com.example.demo1.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CustomerDetailController", value = "/admin/customer-detail")
public class CustomerDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("updateSuccess") != null) {
            request.setAttribute("updateSuccess", session.getAttribute("updateSuccess"));
            session.removeAttribute("updateSuccess");
        }
        if (session.getAttribute("updateError") != null) {
            request.setAttribute("updateError", session.getAttribute("updateError"));
            session.removeAttribute("updateError");
        }

        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int userId = Integer.parseInt(idStr);
                UserDao userDao = new UserDao();
                User user = userDao.getUserById(userId);

                if (user != null) {
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
                    OrderDao orderDao = new OrderDao();
                    int totalOrders = orderDao.countOrdersByUserId(userId);
                    int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
                    int offset = (page - 1) * pageSize;
                    List<Order> orderList = orderDao.getOrdersByUserIdPaging(userId, pageSize, offset);

                    request.setAttribute("orderList", orderList);
                    request.setAttribute("currentPage", page);
                    request.setAttribute("totalPages", totalPages);
                    request.setAttribute("customer", user);

                    request.getRequestDispatcher("/admin/detailsCustomers.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/customers");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/customers");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/customers");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
