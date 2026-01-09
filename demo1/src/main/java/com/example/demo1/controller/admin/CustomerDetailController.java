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
import java.util.List;

@WebServlet(name = "CustomerDetailController", value = "/admin/customer-detail")
public class CustomerDetailController extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (session.getAttribute("updateSuccess") != null) {
//            request.setAttribute("updateSuccess", "Cập nhật thông tin thành công!");
//            session.removeAttribute("updateSuccess");
//        }
//        if (session.getAttribute("updateError") != null) {
//            request.setAttribute("updateError", "Có lỗi xảy ra, không thể cập nhật thông tin. Vui lòng kiểm tra lại các trường hoặc liên hệ quản trị viên.");
//            session.removeAttribute("updateError");
//        }
//
//        String idStr = request.getParameter("id");
//        if (idStr != null) {
//            try {
//                int id = Integer.parseInt(idStr);
//                UserDao userDao = new UserDao();
//                User user = userDao.getUserById(id);
//                if (user != null) {
//                    OrderDao orderDao = new OrderDao();
//                    List<Order> orderList = orderDao.getOrdersByUserId(id);
//
//
//                    request.setAttribute("orderList", orderList);
//                    request.setAttribute("customer", user);
//                    request.getRequestDispatcher("/admin/detailsCustomers.jsp").forward(request, response);
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/admin/customers");
//                }
//            } catch (NumberFormatException e) {
//                response.sendRedirect(request.getContextPath() + "/admin/customers");
//            }
//        } else {
//            response.sendRedirect(request.getContextPath() + "/admin/customers");
//        }
//    }
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 1. Xử lý thông báo từ Session (giữ nguyên)
    HttpSession session = request.getSession();
    if (session.getAttribute("updateSuccess") != null) {
        request.setAttribute("updateSuccess", session.getAttribute("updateSuccess"));
        session.removeAttribute("updateSuccess");
    }
    if (session.getAttribute("updateError") != null) {
        request.setAttribute("updateError", session.getAttribute("updateError"));
        session.removeAttribute("updateError");
    }

    // 2. Lấy ID khách hàng từ URL
    String idStr = request.getParameter("id");
    if (idStr != null) {
        try {
            int userId = Integer.parseInt(idStr);
            UserDao userDao = new UserDao();
            User user = userDao.getUserById(userId);

            if (user != null) {
                // --- BẮT ĐẦU PHẦN XỬ LÝ PHÂN TRANG ĐƠN HÀNG ---

                // a. Xác định trang hiện tại (Mặc định là 1)
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    try {
                        page = Integer.parseInt(pageStr);
                    } catch (NumberFormatException e) {
                        page = 1;
                    }
                }

                // b. Cấu hình số lượng bản ghi trên 1 trang
                int pageSize = 5; // Hiển thị 5 đơn hàng mỗi trang

                // c. Tính toán tổng số trang
                OrderDao orderDao = new OrderDao();
                int totalOrders = orderDao.countOrdersByUserId(userId); // Cần có hàm này trong OrderDao
                int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

                // d. Tính vị trí bắt đầu (Offset)
                int offset = (page - 1) * pageSize;

                // e. Lấy danh sách đơn hàng theo trang
                // Cần có hàm này trong OrderDao
                List<Order> orderList = orderDao.getOrdersByUserIdPaging(userId, pageSize, offset);

                // f. Gửi dữ liệu phân trang sang JSP
                request.setAttribute("orderList", orderList);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);

                // --- KẾT THÚC PHẦN XỬ LÝ PHÂN TRANG ---

                // Gửi thông tin user sang JSP
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
        request.setCharacterEncoding("UTF-8");

        // 1. Kiểm tra ID ngay đầu vào
        String idStr = request.getParameter("id");
        int id = 0;

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new Exception("ID khách hàng không tồn tại.");
            }
            id = Integer.parseInt(idStr);

            // 2. Lấy dữ liệu từ Form
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");

            // --- QUAN TRỌNG: XỬ LÝ NGÀY THÁNG ---
            // Nếu ngày sinh rỗng (người dùng không chọn), phải chuyển thành NULL
            // MySQL không nhận chuỗi rỗng "" cho kiểu DATE
            if (birthday != null && birthday.trim().isEmpty()) {
                birthday = null;
            }

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            user.setGender(gender);
            user.setBirthday(birthday); // Giờ đây birthday có thể là null, SQL sẽ chấp nhận

            // 3. Gọi DAO update
            UserDao userDao = new UserDao();
            userDao.updateUser(user);

            // 4. Thông báo thành công
            HttpSession session = request.getSession();
            session.setAttribute("updateSuccess", "Cập nhật thông tin thành công!"); // Sửa lại lưu String message

            // Redirect để tránh resubmit form
            response.sendRedirect(request.getContextPath() + "/admin/customer-detail?id=" + id);

        } catch (Exception e) {
            // In lỗi chi tiết ra Console để bạn biết tại sao
            System.err.println("=== LỖI CẬP NHẬT USER ID: " + id + " ===");
            e.printStackTrace();

            HttpSession session = request.getSession();
            session.setAttribute("updateError", "Lỗi: " + e.getMessage()); // Lưu thông báo lỗi

            if (id > 0) {
                response.sendRedirect(request.getContextPath() + "/admin/customer-detail?id=" + id);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/customers");
            }
        }
    }
}
