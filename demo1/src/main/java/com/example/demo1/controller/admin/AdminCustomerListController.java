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

        // 1. Lấy trạng thái từ URL (Dropdown)
        // Ví dụ: ?status=Locked
        String status = request.getParameter("status");
        if (status == null || status.trim().isEmpty()) {
            status = "all"; // Mặc định hiển thị tất cả nếu không chọn gì
        }

        // 2. Lấy từ khóa tìm kiếm (để giữ lại trong ô tìm kiếm nếu có)
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }

        // 3. Xử lý số trang hiện tại
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // 4. Cấu hình phân trang
        int pageSize = 5; // Số khách hàng mỗi trang

        // 5. Tính tổng số trang (QUAN TRỌNG: Phải đếm dựa theo status)
        // Nếu đang chọn "Locked", chỉ đếm user bị khóa để tính trang
        int totalUsers = userDao.countUsersByStatus(status);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // 6. Tính vị trí bắt đầu (Offset)
        int offset = (page - 1) * pageSize;

        // 7. Lấy danh sách dữ liệu (QUAN TRỌNG: Phải lọc theo status)
        List<User> list = userDao.getUsersPaging(pageSize, offset, status);

        // 8. Đẩy dữ liệu sang JSP
        request.setAttribute("customerList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Gửi lại các tham số để JSP dùng cho link phân trang và form
        request.setAttribute("keyword", keyword);
        request.setAttribute("contextPath", request.getContextPath());

        // Gửi status để dropdown biết đang chọn option nào (Active/Locked/All)
        request.setAttribute("currentStatus", status);

        // 9. Chuyển hướng
        request.getRequestDispatcher("/admin/customersList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý logic POST nếu có
    }

}