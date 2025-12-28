package com.example.demo1.controller;

import com.example.demo1.dao.ReviewDao;
import com.example.demo1.model.*;
import com.example.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-detail")
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProductService ps = new ProductService();
            ReviewDao reviewDao = new ReviewDao();

            Product p = ps.getProduct(id);

            if (p == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            // Lấy danh sách sản phẩm liên quan (tối đa 3 sản phẩm, không phân trang)
            List<Product> relatedProducts = ps.getRelatedProducts(p);

            ReviewSummary summary = ps.getReviewSummary(id);
            List<Review> initialReviews = reviewDao.getReviewsWithFilterAndPagination(id, 0, 5, 0);

            request.setAttribute("p", p);
            request.setAttribute("brand", ps.getBrand(id));
            request.setAttribute("specs", ps.getProductSpecs(id));
            request.setAttribute("images", ps.getProductImages(id));
            request.setAttribute("reviews", initialReviews);
            request.setAttribute("reviewSummary", summary);
            
            // Thuộc tính sản phẩm liên quan
            request.setAttribute("relatedProducts", relatedProducts);

            request.getRequestDispatcher("sanPham.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Để trống
    }
}
