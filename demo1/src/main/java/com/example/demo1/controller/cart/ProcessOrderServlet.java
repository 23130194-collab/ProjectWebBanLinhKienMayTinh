package com.example.demo1.controller.cart;

import com.example.demo1.dao.NotificationDao;
import com.example.demo1.dao.OrderDao;
import com.example.demo1.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ProcessOrderServlet", value = "/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("thanhToan.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("home.jsp");
            return;
        }

        String fullName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String addressDetail = request.getParameter("address");

        String phoneRegex = "^(03|05|07|08|09)[0-9]{8}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (phone == null || !phone.matches(phoneRegex) || email == null || !email.matches(emailRegex)) {
            response.sendRedirect("thanhToan.jsp?error=invalid_format");
            return;
        }

        Order order = new Order();
        double total = 0;
        double subprice = 0;
        for (CartItem item : cart.values()) {
            double price = item.getProduct().getPrice();
            double oldPrice = item.getProduct().getOldPrice();

            if (oldPrice == 0) {
                oldPrice = price;
            }

            total += price * item.getQuantity();
            subprice += oldPrice * item.getQuantity();
        }

        double discountAmount = subprice - total;
        double shippingFee = 0;

        order.setUserId(user.getId());
        order.setOrderCode("TN-" + System.currentTimeMillis());
        order.setOrderStatus("Chờ xác nhận");
        order.setSubprice(subprice);
        order.setDiscountAmount(discountAmount);
        order.setShippingFee(shippingFee);
        order.setTotalAmount(total);

        RecipientInfo recipient = new RecipientInfo();
        recipient.setFullName(fullName);
        recipient.setPhone(phone);
        recipient.setEmail(email);
        recipient.setProvince(province);
        recipient.setDistrict(district);
        recipient.setAddress(addressDetail);

        boolean success = orderDao.createOrder(order, recipient, cart);

        if (success) {
            try {
                NotificationDao notiDao = new NotificationDao();
                String content = "Đơn hàng " + order.getOrderCode() + " đặt thành công. Cảm ơn bạn!";
                String link = "user";

                Notification noti = new Notification(user.getId(), content, link);
                notiDao.insert(noti);

            } catch (Exception e) {
                e.printStackTrace();
            }
            session.removeAttribute("cart");
            response.sendRedirect("thankyouNotification.jsp");
        } else {
            response.sendRedirect("thanhToan.jsp?error=db");
        }
    }
}