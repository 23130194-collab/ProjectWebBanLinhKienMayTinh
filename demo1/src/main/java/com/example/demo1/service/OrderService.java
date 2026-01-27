package com.example.demo1.service;

import com.example.demo1.dao.OrderDao;
import com.example.demo1.model.Order;
import com.example.demo1.model.OrderDetail;
import com.example.demo1.model.OrderItem;
import com.example.demo1.model.OrderPage;

import java.util.List;
import java.util.Optional;

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
            recalculateAndSyncOrderTotals(orderId);
        }
        return success;
    }

    public void recalculateAndSyncOrderTotals(int orderId) {
        List<OrderItem> items = orderDao.getOrderItemsByOrderId(orderId);
        Order order = orderDao.getOrderById(orderId);

        if (order == null || items == null) {
            return;
        }

        double subprice = 0;
        double discountAmount = 0;

        for (OrderItem item : items) {
            subprice += item.getOriginalPrice() * item.getQuantity();
            discountAmount += (item.getOriginalPrice() - item.getUnitPrice()) * item.getQuantity();
        }

        double totalAmount = subprice - discountAmount + order.getShippingFee();

        orderDao.updateOrderTotals(orderId, subprice, discountAmount, totalAmount);
    }

    public boolean isOrderCodeExists(String orderCode) {
        return orderDao.isOrderCodeExists(orderCode);
    }

    public String generateNextOrderCode() {
        Optional<String> latestOrderCodeOpt = orderDao.findLatestOrderCode();
        if (latestOrderCodeOpt.isEmpty()) {
            return "#11110";
        }

        String latestOrderCode = latestOrderCodeOpt.get();
        String numberPart = latestOrderCode.substring(1);
        int nextNumber = Integer.parseInt(numberPart) + 1;
        
        return "#" + String.format("%05d", nextNumber);
    }
}
