package com.example.demo1.controller;

import com.example.demo1.model.Category;
import com.example.demo1.service.CategoryService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Lấy ServletContext từ sự kiện
        ServletContext context = sce.getServletContext();

        // Tạo service và lấy danh sách tất cả các danh mục
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.getAllCategories();

        // Đặt danh sách danh mục vào application scope
        context.setAttribute("categoryList", categoryList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Phương thức này được gọi khi ứng dụng tắt, không cần làm gì ở đây
    }
}
