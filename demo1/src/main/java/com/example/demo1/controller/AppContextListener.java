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
        ServletContext context = sce.getServletContext();
        CategoryService categoryService = new CategoryService();
        
        // ĐÃ SỬA: Chỉ lấy các danh mục đang hoạt động để hiển thị cho người dùng
        List<Category> categoryList = categoryService.getActiveCategories();

        context.setAttribute("categoryList", categoryList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Không cần làm gì ở đây
    }
}
