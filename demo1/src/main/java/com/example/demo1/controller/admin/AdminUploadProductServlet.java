package com.example.demo1.controller.admin;

import com.example.demo1.model.Brand;
import com.example.demo1.model.Category;
import com.example.demo1.model.Product_Image;
import com.example.demo1.service.BrandService;
import com.example.demo1.service.CategoryService;
import com.example.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "AdminUploadProductServlet", value = "/admin-upload-product")
public class AdminUploadProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        BrandService brandService = new BrandService();

        List<Category> categoryList = categoryService.getAllCategories();
        List<Brand> brandList = brandService.getAllBrands();

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("brandList", brandList);

        request.getRequestDispatcher("/admin/adminUploadProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy dữ liệu sản phẩm chính
            String name = request.getParameter("name");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            int brandId = Integer.parseInt(request.getParameter("brandId"));
            String description = request.getParameter("description");
            double oldPrice = Double.parseDouble(request.getParameter("oldPrice"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            String discountValueStr = request.getParameter("discountValue");
            Double discountValue = (discountValueStr != null && !discountValueStr.isEmpty()) ? Double.parseDouble(discountValueStr) : null;

            String discountStartStr = request.getParameter("discountStart");
            Timestamp discountStart = parseTimestamp(discountStartStr);

            String discountEndStr = request.getParameter("discountEnd");
            Timestamp discountEnd = parseTimestamp(discountEndStr);

            // 1. Lấy mảng dữ liệu ảnh phụ từ JSP
            String[] imageUrls = request.getParameterValues("imageUrls");
            String[] imageOrders = request.getParameterValues("imageOrders");

            // 2. Tạo danh sách các đối tượng Product_Image
            List<Product_Image> images = new ArrayList<>();
            if (imageUrls != null && imageOrders != null && imageUrls.length == imageOrders.length) {
                for (int i = 0; i < imageUrls.length; i++) {
                    if (imageUrls[i] != null && !imageUrls[i].trim().isEmpty()) {
                        Product_Image img = new Product_Image();
                        img.setImage(imageUrls[i]);
                        try {
                            img.setDisplayOrder(Integer.parseInt(imageOrders[i]));
                        } catch (NumberFormatException e) {
                            img.setDisplayOrder(i); // Mặc định thứ tự nếu có lỗi
                        }
                        images.add(img);
                    }
                }
            }
            // Sắp xếp hình ảnh theo thứ tự
            images.sort(Comparator.comparingInt(Product_Image::getDisplayOrder));

            // 3. Gọi service để tạo sản phẩm
            ProductService productService = new ProductService();
            productService.createProduct(name, categoryId, brandId, description, oldPrice, stock, discountValue, discountStart, discountEnd, images);

            response.sendRedirect(request.getContextPath() + "/admin-product-list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi thêm sản phẩm: " + e.getMessage());
//            request.getRequestDispatcher("/admin/error.jsp").forward(request, response);
        }
    }

    private Timestamp parseTimestamp(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = dateFormat.parse(dateStr);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
