package com.example.demo1.dao;

import com.example.demo1.model.Order;
import com.example.demo1.model.OrderDetail;
import com.example.demo1.model.OrderItem;
import com.example.demo1.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.util.List;

public class OrderDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Order> getAllOrders(int page, int pageSize) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("limit", pageSize)
                        .bind("offset", (page - 1) * pageSize)
                        .mapToBean(Order.class)
                        .list()
        );
    }

    public int getTotalOrderCount() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM orders")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public OrderDetail getOrderDetailById(int orderId) {
        Order order = getOrderById(orderId);
        if (order == null) {
            return null;
        }
        User customer = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = :userId")
                        .bind("userId", order.getUserId())
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null)
        );
        List<OrderItem> items = getOrderItemsByOrderId(orderId);
        return new OrderDetail(order, customer, items);
    }

    public Order getOrderById(int orderId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE id = :orderId")
                        .bind("orderId", orderId)
                        .mapToBean(Order.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        String query = "SELECT od.id, od.order_id, od.product_id, od.quantity, od.original_price, od.discount_percentage, p.name as productName " +
                       "FROM order_details od JOIN products p ON od.product_id = p.id " +
                       "WHERE od.order_id = :orderId";
        return jdbi.withHandle(handle -> {
            handle.registerRowMapper(BeanMapper.factory(OrderItem.class));
            return handle.createQuery(query)
                    .bind("orderId", orderId)
                    .mapTo(OrderItem.class)
                    .list();
        });
    }

    public boolean updateOrderStatus(int orderId, String status) {
        int updatedRows = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE orders SET order_status = :status, updated_at = NOW() WHERE id = :orderId")
                        .bind("status", status)
                        .bind("orderId", orderId)
                        .execute()
        );
        return updatedRows > 0;
    }

    public void updateOrderTotals(int orderId, double subprice, double discountAmount, double totalAmount) {
        jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE orders SET subprice = :subprice, discount_amount = :discountAmount, total_amount = :totalAmount, updated_at = NOW() WHERE id = :orderId")
                        .bind("orderId", orderId)
                        .bind("subprice", subprice)
                        .bind("discountAmount", discountAmount)
                        .bind("totalAmount", totalAmount)
                        .execute()
        );
    }

    public boolean deleteOrder(int orderId) {
        return jdbi.withHandle(handle -> {
            handle.createUpdate("DELETE FROM order_details WHERE order_id = :orderId")
                    .bind("orderId", orderId)
                    .execute();
            int updatedRows = handle.createUpdate("DELETE FROM orders WHERE id = :orderId")
                    .bind("orderId", orderId)
                    .execute();
            return updatedRows > 0;
        });
    }

    public List<Order> searchOrders(String keyword, int page, int pageSize) {
        String searchKeyword = "%" + keyword + "%";
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders WHERE order_code LIKE :keyword ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("keyword", searchKeyword)
                        .bind("limit", pageSize)
                        .bind("offset", (page - 1) * pageSize)
                        .mapToBean(Order.class)
                        .list()
        );
    }

    public int getSearchOrderCount(String keyword) {
        String searchKeyword = "%" + keyword + "%";
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM orders WHERE order_code LIKE :keyword")
                        .bind("keyword", searchKeyword)
                        .mapTo(Integer.class)
                        .one()
        );
    }
    public List<Order> getOrdersByUserId(int userId) {
        // Query lấy dữ liệu theo user_id
        String sql = "SELECT * FROM orders WHERE user_id = :userId ORDER BY created_at DESC";

        return jdbi.withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Order.class) // Tự động map order_code -> orderCode, total_amount -> totalAmount
                        .list()
        );
    }
    // 1. Hàm đếm tổng số đơn hàng của user (để tính số trang)
    public int countOrdersByUserId(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = :userId";
        return jdbi.withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .mapTo(Integer.class)
                        .one()
        );
    }

    // 2. Hàm lấy danh sách đơn hàng có Phân trang (Limit, Offset)
    public List<Order> getOrdersByUserIdPaging(int userId, int limit, int offset) {
        String sql = "SELECT * FROM orders WHERE user_id = :userId ORDER BY created_at DESC LIMIT :limit OFFSET :offset";

        return jdbi.withHandle(h ->
                h.createQuery(sql)
                        .bind("userId", userId)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Order.class)
                        .list()
        );
    }
}
