package com.example.demo1.controller;

import com.example.demo1.dao.ReviewDao;
import com.example.demo1.model.Review;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewApiServlet", value = "/api/reviews")
public class ReviewApiServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy các tham số từ request
            int productId = Integer.parseInt(request.getParameter("productId"));
            int ratingFilter = Integer.parseInt(request.getParameter("filter")); // 0 for all
            int offset = Integer.parseInt(request.getParameter("offset"));
            int limit = 5; // Luôn lấy 5 đánh giá mỗi lần

            // Gọi DAO để lấy dữ liệu
            ReviewDao reviewDao = new ReviewDao();
            List<Review> reviews = reviewDao.getReviewsWithFilterAndPagination(productId, ratingFilter, limit, offset);

            // Chuyển danh sách thành chuỗi JSON và gửi về client
            String jsonResponse = this.gson.toJson(reviews);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
            e.printStackTrace();
        }
    }
}
