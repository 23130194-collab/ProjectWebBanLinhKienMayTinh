package com.example.demo1.service;

import com.example.demo1.dao.OrderDao;
import com.example.demo1.model.Order;
import com.example.demo1.model.OrderDetail;
import com.example.demo1.model.OrderItem;
import com.example.demo1.model.OrderPage;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();

    public OrderPage getPagedOrders(String keyword, int currentPage, int ordersPerPage) {
        List<Order> orders;
        int totalOrders;

        if (keyword != null && !keyword.isEmpty()) {
            orders = orderDao.searchOrders(keyword, currentPage, ordersPerPage);
            totalOrders = orderDao.getSearchOrderCount(keyword);
        } else {
            orders = orderDao.getAllOrders(currentPage, ordersPerPage);
            totalOrders = orderDao.getTotalOrderCount();
        }
        return new OrderPage(orders, totalOrders);
    }

    public OrderDetail getOrderDetailById(int orderId) {
        return orderDao.getOrderDetailById(orderId);
    }

    public boolean updateOrderStatus(int orderId, String status) {
        boolean success = orderDao.updateOrderStatus(orderId, status);
        if (success) {
            // Sau khi cập nhật trạng thái, TỰ ĐỘNG TÍNH TOÁN VÀ ĐỒNG BỘ LẠI TỔNG TIỀN
            recalculateAndSyncOrderTotals(orderId);
        }
        return success;
    }

    // PHƯƠNG THỨC MỚI ĐỂ TÍNH TOÁN VÀ ĐỒNG BỘ
    public void recalculateAndSyncOrderTotals(int orderId) {
        List<OrderItem> items = orderDao.getOrderItemsByOrderId(orderId);
        Order order = orderDao.getOrderById(orderId);

        if (order == null || items == null) {
            return; // Không tìm thấy đơn hàng hoặc sản phẩm
        }

        double subprice = 0;
        double discountAmount = 0;

        for (OrderItem item : items) {
            subprice += item.getOriginalPrice() * item.getQuantity();
            discountAmount += (item.getOriginalPrice() - item.getUnitPrice()) * item.getQuantity();
        }

        double totalAmount = subprice - discountAmount + order.getShippingFee();

        // Gọi DAO để cập nhật vào database
        orderDao.updateOrderTotals(orderId, subprice, discountAmount, totalAmount);
    }

    public boolean deleteOrder(int orderId) {
        return orderDao.deleteOrder(orderId);
    }
}
