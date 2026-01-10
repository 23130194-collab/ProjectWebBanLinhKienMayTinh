package com.example.demo1.controller.admin;

import com.example.demo1.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LockCustomerController", value = "/admin/lock-customer")
public class LockCustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                UserDao userDao = new UserDao();

                // 1. Kiểm tra trạng thái hiện tại
                String currentStatus = userDao.getUserStatus(id);

                // 2. Đảo ngược trạng thái
                String newStatus = "Active"; // Mặc định là mở
                String msg = "Đã mở khóa tài khoản!";

                if (currentStatus == null || currentStatus.equalsIgnoreCase("Active")) {
                    newStatus = "Locked"; // Nếu đang mở thì khóa lại
                    msg = "Đã khóa tài khoản thành công!";
                }

                // 3. Cập nhật xuống DB
                userDao.updateUserStatus(id, newStatus);

                // 4. Thông báo
                request.getSession().setAttribute("updateSuccess", msg);

            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("updateError", "Lỗi khi cập nhật trạng thái!");
            }
        }

        // Quay lại trang danh sách
        response.sendRedirect(request.getContextPath() + "/admin/customers");
    }
}