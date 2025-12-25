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

@WebServlet(name = "ListProductController", value = "/list-product")
public class ListProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        CategoryService cs = new CategoryService();

        try {
            // Lấy category id từ URL
            String idStr = request.getParameter("id");
            int categoryId;

            if (idStr == null || idStr.isEmpty()) {
                // Nếu không có id, lấy danh mục đầu tiên làm mặc định
                List<Category> allCategories = cs.getAllCategories();
                if (allCategories != null && !allCategories.isEmpty()) {
                    categoryId = allCategories.get(0).getId();
                } else {
                    // Xử lý trường hợp không có danh mục nào trong DB
                    request.setAttribute("errorMessage", "Không tìm thấy danh mục nào.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
            } else {
                categoryId = Integer.parseInt(idStr);
            }

            // Lấy danh sách sản phẩm theo category id
            List<Product> list = ps.getProductsByCategoryId(categoryId);

            // Lấy thông tin của category hiện tại
            Category category = cs.getCategoryById(categoryId);

            request.setAttribute("productList", list);
            request.setAttribute("category", category); // Đưa category vào request
            request.getRequestDispatcher("category.jsp").forward(request, response); // Chuyển đến trang category.jsp

        } catch (NumberFormatException e) {
            // Xử lý nếu id không phải là số
            response.sendRedirect("home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
