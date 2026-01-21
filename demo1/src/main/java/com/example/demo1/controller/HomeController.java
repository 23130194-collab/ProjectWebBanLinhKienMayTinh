package com.example.demo1.controller;

import com.example.demo1.dao.BannerDao;
import com.example.demo1.dao.NotificationDao;
import com.example.demo1.model.*;
import com.example.demo1.service.CategoryService;
import com.example.demo1.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", value = "/home")
public class HomeController extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    BannerDao bannerDao = new BannerDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            NotificationDao notiDao = new NotificationDao();
            List<Notification> notiList = notiDao.getByUser(user.getId());
            request.setAttribute("notificationList", notiList);
        }

        try {

            List<Category> categoryList = categoryService.getAllCategories();
            request.setAttribute("categoryList", categoryList);

            List<Banner> bannerList = bannerDao.getBannersByPosition("Trang chủ");
            request.setAttribute("bannerList", bannerList);

            ProductPage popularPage = productService.filterAndSortProducts(null, "active", null, null, null, "popular", 1, 10);
            List<Product> flashSaleList = popularPage.getProducts();

            for (Product p : flashSaleList) {
                p.setAvgRating(productService.getReviewSummary(p.getId()).getAverageRating());
                if (p.getOldPrice() > p.getPrice()) {
                    p.setDiscountValue(((p.getOldPrice() - p.getPrice()) / p.getOldPrice()) * 100);
                }
            }
            request.setAttribute("flashSaleList", flashSaleList);

            List<Product> randomProducts = productService.getRandomProducts(8);
            request.setAttribute("suggestedProducts", randomProducts);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    }
