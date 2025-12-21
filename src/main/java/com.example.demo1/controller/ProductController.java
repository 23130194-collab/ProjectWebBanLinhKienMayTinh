package com.example.demo1.controller;

import com.example.demo1.model.Product;
import com.example.demo1.model.Product_Image;
import com.example.demo1.service.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-detail")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductService ps = new ProductService();
        Product p = ps.getProduct(id);

        request.setAttribute("p", p);
        request.setAttribute("brand", ps.getBrand(id));
        request.setAttribute("specs", ps.getProductSpecs(id));
        request.setAttribute("images", ps.getProductImages(id));
        request.setAttribute("reviews", ps.getProductReviews(id));
        request.getRequestDispatcher("sanPham.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}