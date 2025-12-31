package com.example.demo1.controller;

import com.example.demo1.model.Category;
import com.example.demo1.model.Product;
import com.example.demo1.service.CategoryService;
import com.example.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductListServlet", value = "/admin-product-list")
public class AdminProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        CategoryService cs = new CategoryService();

        try {

            List<Category> allCategories = cs.getAllCategories();


            String categoryIdStr = request.getParameter("categoryId");
            String status = request.getParameter("status");


            Integer categoryId = null;
            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                try {
                    categoryId = Integer.parseInt(categoryIdStr);
                } catch (NumberFormatException e) {

                    System.err.println("Invalid category ID format: " + categoryIdStr);
                }
            }


            List<Product> productList = ps.getFilteredProducts(categoryId, status);


            request.setAttribute("productList", productList);
            request.setAttribute("categories", allCategories);
            request.setAttribute("selectedCategoryId", categoryId);
            request.setAttribute("selectedStatus", status);


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
