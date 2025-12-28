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
    private static final int RELATED_PRODUCTS_PER_PAGE = 5;

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

            // Lấy trang hiện tại cho sản phẩm liên quan
            String relatedPageStr = request.getParameter("relatedPage");
            int relatedCurrentPage = 1;
            if (relatedPageStr != null && !relatedPageStr.isEmpty()) {
                relatedCurrentPage = Integer.parseInt(relatedPageStr);
            }

            // Lấy sản phẩm liên quan đã phân trang
            ProductPage relatedProductPage = ps.getRelatedProductsPage(p, relatedCurrentPage, RELATED_PRODUCTS_PER_PAGE);
            List<Product> relatedProducts = relatedProductPage.getProducts();
            int relatedTotalPages = (int) Math.ceil((double) relatedProductPage.getTotalProducts() / RELATED_PRODUCTS_PER_PAGE);


            ReviewSummary summary = ps.getReviewSummary(id);
            List<Review> initialReviews = reviewDao.getReviewsWithFilterAndPagination(id, 0, 5, 0);

            request.setAttribute("p", p);
            request.setAttribute("brand", ps.getBrand(id));
            request.setAttribute("specs", ps.getProductSpecs(id));
            request.setAttribute("images", ps.getProductImages(id));
            request.setAttribute("reviews", initialReviews);
            request.setAttribute("reviewSummary", summary);
            
            // Thuộc tính sản phẩm liên quan và phân trang
            request.setAttribute("relatedProducts", relatedProducts);
            request.setAttribute("relatedCurrentPage", relatedCurrentPage);
            request.setAttribute("relatedTotalPages", relatedTotalPages);

            request.getRequestDispatcher("sanPham.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Để trống
    }
}
