package com.example.demo1.controller;

import com.example.demo1.model.ProductPage;
import com.example.demo1.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchController", value = "/search")
public class SearchController extends HttpServlet {
    private static final int PRODUCTS_PER_PAGE = 20;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();

        String keyword = request.getParameter("keyword");
        String sortOrder = request.getParameter("sort");
        if (sortOrder == null || sortOrder.isEmpty()) sortOrder = "popular";

        String pageStr = request.getParameter("page");
        int currentPage = 1;
        if (pageStr != null && !pageStr.isEmpty()) currentPage = Integer.parseInt(pageStr);

        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect("home.jsp");
            return;
        }

        ProductPage productPage = ps.filterAndSortProducts(
                null, "active", keyword, null, null, sortOrder, currentPage, PRODUCTS_PER_PAGE
        );

        request.setAttribute("productList", productPage.getProducts());
        request.setAttribute("selectedKeyword", keyword);
        request.setAttribute("selectedSortOrder", sortOrder);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", (int) Math.ceil((double) productPage.getTotalProducts() / PRODUCTS_PER_PAGE));

        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}