package com.example.demo1.dao;

import com.example.demo1.model.Product;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ProductDao {
    private Jdbi jdbi = DatabaseDao.get();

    // Định nghĩa các trường cần lấy, bao gồm cả việc tính toán giá động
    private final String DYNAMIC_PRICE_SQL = 
        "FLOOR( (CASE WHEN d.discount_value > 0 THEN p.old_price * (1 - d.discount_value / 100) ELSE p.old_price END) / 10000 ) * 10000";

    private final String SELECT_PRODUCT_FIELDS =
            "p.id, p.category_id, p.brand_id, p.name, p.discount_id, p.description, p.stock, p.image, " +
            "p.old_price, " + // Giữ nguyên old_price là giá gốc
            "d.discount_value, " +
            "IFNULL(ROUND(AVG(r.rating), 1), 0) AS avg_rating, " +
            // Tính toán giá bán cuối cùng (price) dựa trên old_price và discount
            DYNAMIC_PRICE_SQL + " AS price ";

    public List<Product> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "GROUP BY p.id")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getByCategoryId(int categoryId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.category_id = :cid " +
                                "GROUP BY p.id")
                        .bind("cid", categoryId)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public Product getById(int productId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.id = :id " +
                                "GROUP BY p.id")
                        .bind("id", productId)
                        .mapToBean(Product.class)
                        .findOne()
        ).orElse(null);
    }

    public List<Product> getRelatedCandidateProducts(Product currentProduct, double priceRange) {
        double minPrice = currentProduct.getPrice() - priceRange;
        double maxPrice = currentProduct.getPrice() + priceRange;

        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.category_id = :categoryId " +
                                "AND p.id != :currentId " +
                                "AND (" + DYNAMIC_PRICE_SQL + ") BETWEEN :minPrice AND :maxPrice " +
                                "GROUP BY p.id")
                        .bind("categoryId", currentProduct.getCategory_id())
                        .bind("currentId", currentProduct.getId())
                        .bind("minPrice", minPrice)
                        .bind("maxPrice", maxPrice)
                        .mapToBean(Product.class)
                        .list()
        );
    }
}
