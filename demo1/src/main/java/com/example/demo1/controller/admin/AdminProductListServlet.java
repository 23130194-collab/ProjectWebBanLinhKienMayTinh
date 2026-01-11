package com.example.demo1.controller.admin;

import com.example.demo1.model.Category;
import com.example.demo1.model.ProductPage;
import com.example.demo1.service.CategoryService;
import com.example.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "AdminProductListServlet", value = "/admin-product-list")
public class AdminProductListServlet extends HttpServlet {

    private static final int PRODUCTS_PER_PAGE = 2; // Để 15 khi chạy thật, để 3 hoặc 5 để test

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        CategoryService cs = new CategoryService();

        try {
            // Lấy danh sách tất cả các danh mục để hiển thị trong bộ lọc
            List<Category> allCategories = cs.getAllCategories();

            // Lấy các tham số từ request
            String categoryIdStr = request.getParameter("categoryId");
            String status = request.getParameter("status");
            String keyword = request.getParameter("keyword");
            String pageStr = request.getParameter("page");
            String sortOrder = request.getParameter("sort");

            // Xử lý giá trị mặc định
            Integer categoryId = null;
            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                try {
                    categoryId = Integer.parseInt(categoryIdStr);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid category ID format: " + categoryIdStr);
                }
            }

            int currentPage = 1;
            if (pageStr != null && !pageStr.isEmpty()) {
                try {
                    currentPage = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    currentPage = 1;
                }
            }

            // Xử lý sort mặc định nếu null
            if (sortOrder == null || sortOrder.isEmpty()) {
                sortOrder = "popular";
            }

            ProductPage productPage = ps.filterAndSortProducts(
                    categoryId, status, keyword, null, Collections.emptyMap(),
                    sortOrder, currentPage, PRODUCTS_PER_PAGE
            );

            // Tính toán tổng số trang
            int totalPages = (int) Math.ceil((double) productPage.getTotalProducts() / PRODUCTS_PER_PAGE);

            // Đặt các thuộc tính cho JSP
            request.setAttribute("productList", productPage.getProducts());
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("categories", allCategories);
            request.setAttribute("itemsPerPage", PRODUCTS_PER_PAGE);
            request.setAttribute("selectedCategoryId", categoryId);
            request.setAttribute("selectedStatus", status);
            request.setAttribute("selectedKeyword", keyword);
            request.setAttribute("selectedSort", sortOrder);

            request.getRequestDispatcher("/admin/adminProductList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi tải danh sách sản phẩm: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
