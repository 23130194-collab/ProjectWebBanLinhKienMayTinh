package com.example.demo1.controller.admin;

import com.example.demo1.model.Category;
import com.example.demo1.model.Product;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        CategoryService cs = new CategoryService();

        try {
            // Lấy danh sách tất cả các danh mục để hiển thị trong bộ lọc
            List<Category> allCategories = cs.getAllCategories();

            // Lấy các tham số từ request
            String categoryIdStr = request.getParameter("categoryId");
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
            
            if (sortOrder == null || sortOrder.isEmpty()) {
                sortOrder = "popular"; // Sắp xếp mặc định
            }

            // ĐÃ SỬA: Gọi đúng phương thức filterAndSortProducts
            // Vì trang admin không có bộ lọc theo spec và brand, ta truyền vào giá trị null hoặc rỗng
            ProductPage productPage = ps.filterAndSortProducts(
                categoryId != null ? categoryId : allCategories.get(0).getId(), // Lấy category đầu tiên nếu không có category nào được chọn
                null,          // brandId (không dùng ở admin)
                Collections.emptyMap(), // specFilters (không dùng ở admin)
                sortOrder,
                currentPage,
                15 // Hiển thị 15 sản phẩm mỗi trang
            );

            // Đặt các thuộc tính cho JSP
            request.setAttribute("productList", productPage.getProducts());
            request.setAttribute("totalPages", (int) Math.ceil((double) productPage.getTotalProducts() / 15));
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("categories", allCategories);
            request.setAttribute("selectedCategoryId", categoryId);
            request.setAttribute("selectedSort", sortOrder);

            request.getRequestDispatcher("/admin/adminProductList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi tải danh sách sản phẩm: " + e.getMessage());
            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
