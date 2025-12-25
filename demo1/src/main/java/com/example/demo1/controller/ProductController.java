package com.example.demo1.controller;

import com.example.demo1.dao.ReviewDao;
import com.example.demo1.model.Product;
import com.example.demo1.model.Review;
import com.example.demo1.model.ReviewSummary;
import com.example.demo1.model.User; // Import lớp User
import com.example.demo1.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Import lớp HttpSession

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-detail")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // =================================================================
        // == BẮT ĐẦU ĐOẠN CODE GIẢ LẬP ĐĂNG NHẬP (ĐỂ TEST) ==
        // =================================================================
        HttpSession session = request.getSession();
        // Tạo một đối tượng User giả
        User fakeUser = new User();
        fakeUser.setId(3); // ID của người dùng, bạn có thể thay đổi
        fakeUser.setName("Người dùng Test"); // Tên người dùng
        // Bạn có thể set thêm các thuộc tính khác nếu cần
        // Đặt người dùng giả vào session
        session.setAttribute("user", fakeUser);
        // =================================================================
        // == KẾT THÚC ĐOẠN CODE GIẢ LẬP ĐĂNG NHẬP ==
        // =================================================================

        int id = Integer.parseInt(request.getParameter("id"));
        ProductService ps = new ProductService();
        ReviewDao reviewDao = new ReviewDao(); // Tạo instance của ReviewDao

        Product p = ps.getProduct(id);
        ReviewSummary summary = ps.getReviewSummary(id);
        List<Review> initialReviews = reviewDao.getReviewsWithFilterAndPagination(id, 0, 5, 0); // Lấy 5 review đầu tiên
        
        // Lấy danh sách sản phẩm liên quan
        List<Product> relatedProducts = ps.getRelatedProducts(p);

        request.setAttribute("p", p);
        request.setAttribute("brand", ps.getBrand(id));
        request.setAttribute("specs", ps.getProductSpecs(id));
        request.setAttribute("images", ps.getProductImages(id));
        request.setAttribute("reviews", initialReviews);
        request.setAttribute("reviewSummary", summary);
        
        // Đưa danh sách sản phẩm liên quan vào request
        request.setAttribute("relatedProducts", relatedProducts);

        request.getRequestDispatcher("sanPham.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
