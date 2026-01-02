package com.example.demo1.service;

import com.example.demo1.dao.ReviewDao;
import com.example.demo1.model.Review;

import java.util.List;
import java.util.Map;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    /**
     * Lấy tất cả đánh giá cho trang Admin, có thể lọc theo keyword và status.
     */
    public List<Review> getAllReviewsForAdmin(String keyword, String status) {
        return reviewDao.getAllReviewsForAdmin(keyword, status);
    }

    /**
     * Lấy một đánh giá bằng ID.
     */
    public Review getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }

    /**
     * Cập nhật trạng thái của một đánh giá.
     */
    public void updateReviewStatus(int reviewId, String status) {
        reviewDao.updateReviewStatus(reviewId, status);
    }

    /**
     * Lấy số lượng đánh giá theo sao cho trang người dùng.
     */
    public Map<Integer, Integer> getReviewSummary(int productId) {
        return reviewDao.getRawStarCounts(productId);
    }

    /**
     * Lấy đánh giá cho trang người dùng (chỉ các review 'active').
     */
    public List<Review> getReviewsForUser(int productId, int ratingFilter, int limit, int offset) {
        return reviewDao.getReviewsWithFilterAndPagination(productId, ratingFilter, limit, offset, false);
    }

    /**
     * Lấy đánh giá cho trang admin (tất cả các status).
     */
    public List<Review> getReviewsForAdmin(int productId, int ratingFilter, int limit, int offset) {
        return reviewDao.getReviewsWithFilterAndPagination(productId, ratingFilter, limit, offset, true);
    }

    /**
     * Thêm một đánh giá mới (sẽ ở trạng thái 'active').
     */
    public void addReview(int productId, int userId, int rating, String comment) {
        reviewDao.addReview(productId, userId, rating, comment);
    }

    /* --- METHODS FOR PAGINATION --- */
    public List<Review> getReviewsByPage(int currentPage, int reviewsPerPage, String keyword, String status) {
        int offset = (currentPage - 1) * reviewsPerPage;
        return reviewDao.getReviewsByPage(offset, reviewsPerPage, keyword, status);
    }

    public int getTotalReviewCount(String keyword, String status) {
        return reviewDao.getTotalReviewCount(keyword, status);
    }
}
