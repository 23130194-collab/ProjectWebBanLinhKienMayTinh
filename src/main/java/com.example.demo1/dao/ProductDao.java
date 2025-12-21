package com.example.demo1.dao;

import com.example.demo1.model.Product;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {
    private Jdbi jdbi = DatabaseDao.get();

    // Lấy toàn bộ sản phẩm từ Database
    public List<Product> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT p.*, d.discount_value, IFNULL(ROUND(AVG(r.rating), 1), 0) AS avg_rating " +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "GROUP BY p.id;")
                        .mapToBean(Product.class) // Tự động map cột DB vào fields của Model
                        .list()
        );
    }

    // Lấy sản phẩm theo Category
    public List<Product> getByCategoryId(int categoryId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM products WHERE category_id = :cid")
                        .bind("cid", categoryId)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // Lấy sản phẩm theo id
    public Product getById(int productId) {
        return (Product) jdbi.withHandle(handle ->
                handle.createQuery("SELECT p.*, d.discount_value, IFNULL(ROUND(AVG(r.rating), 1), 0) AS avg_rating " +
                                "FROM products  p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.id = :id " +
                                "GROUP BY p.id;")
                        .bind("id", productId)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null) // Lấy giá trị hoặc trả về null nếu không thấy
        );
    }
}
