package com.example.demo1.dao;

import com.example.demo1.model.Review;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.List;
import java.util.Map;
import java.util.*;

public class ReviewDao {
    private Jdbi jdbi = DatabaseDao.get();

    /**
     * Lấy tất cả đánh giá cho một sản phẩm.
     */
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

    /**
     * Phương thức: Lấy đánh giá có phân trang và bộ lọc.
     *
     * @param productId    ID sản phẩm.
     * @param ratingFilter Số sao để lọc (1-5), hoặc 0 để lấy tất cả.
     * @param limit        Số lượng đánh giá tối đa cần lấy.
     * @param offset       Vị trí bắt đầu lấy.
     * @return Danh sách các bài đánh giá.
     */
    public List<Review> getReviewsWithFilterAndPagination(int productId, int ratingFilter, int limit, int offset) {
        String baseQuery = "SELECT r.*, u.name AS user_name FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.product_id = :productId ";
        
        // Thêm điều kiện lọc nếu có
        if (ratingFilter > 0) {
            baseQuery += "AND r.rating = :ratingFilter ";
        }
        
        // Thêm sắp xếp và phân trang
        String finalQuery = baseQuery + "ORDER BY r.created_at DESC LIMIT :limit OFFSET :offset";

        return jdbi.withHandle(handle -> {
            Query query = handle.createQuery(finalQuery)
                    .bind("productId", productId)
                    .bind("limit", limit)
                    .bind("offset", offset);

            if (ratingFilter > 0) {
                query.bind("ratingFilter", ratingFilter);
            }

            return query.mapToBean(Review.class).list();
        });
    }

    public Map<Integer, Integer> getRawStarCounts(int productId) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT rating, COUNT(*) as cnt FROM reviews WHERE product_id = :pid GROUP BY rating")
                    .bind("pid", productId)
                    .mapToMap()
                    .reduce(new HashMap<Integer, Integer>(), (accumulator, row) -> {
                        accumulator.put((Integer) row.get("rating"), ((Long) row.get("cnt")).intValue());
                        return accumulator;
                    });
        });
    }

    public void addReview(int productId, int userId, int rating, String comment) {
        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO reviews (product_id, user_id, rating, comment, created_at) VALUES (:productId, :userId, :rating, :comment, NOW())")
                        .bind("productId", productId)
                        .bind("userId", userId)
                        .bind("rating", rating)
                        .bind("comment", comment)
                        .execute()
        );
    }
}
