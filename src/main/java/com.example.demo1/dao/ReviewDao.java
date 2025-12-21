package com.example.demo1.dao;

import com.example.demo1.model.Review;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ReviewDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Review> getReviewsByProductId(int productId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT r.*, u.name AS user_name " +
                                "FROM reviews r " +
                                "JOIN users u ON r.user_id = u.id " +
                                "WHERE r.product_id = :pid " +
                                "ORDER BY r.created_at DESC")
                        .bind("pid", productId)
                        .mapToBean(Review.class)
                        .list()
        );
    }
}
