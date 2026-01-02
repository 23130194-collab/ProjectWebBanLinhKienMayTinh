package com.example.demo1.controller.admin;

import com.example.demo1.model.Banner;
import com.example.demo1.service.BannerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBannerController", value = "/admin/banners")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AdminBannerController extends HttpServlet {

    private final BannerService bannerService = new BannerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        }

        if ("edit".equals(action)) {
            handleEdit(request, response);
        }

        listBanners(request, response);
    }

    private void listBanners(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int page = 1;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int limit = 10;
        int offset = (page - 1) * limit;

        List<Banner> banners = bannerService.getBanners(keyword, limit, offset);
        int totalBanners = bannerService.countBanners(keyword);
        int totalPages = (int) Math.ceil((double) totalBanners / limit);

        request.setAttribute("banners", banners);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/admin/adminBanners.jsp").forward(request, response);
    }

    private void handleEdit(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Banner banner = bannerService.getBannerById(id);
            request.setAttribute("bannerToEdit", banner);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            bannerService.deleteBanner(id);
            request.getSession().setAttribute("message", "Đã xóa banner thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Lỗi khi xóa banner!");
        }
        response.sendRedirect(request.getContextPath() + "/admin/banners");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String name = request.getParameter("name");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String position = request.getParameter("position");
        String imageLink = request.getParameter("image");
        Part filePart = request.getPart("imageFile");

        int displayOrder = 1;
        try {
            displayOrder = Integer.parseInt(request.getParameter("display_order"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if ("create".equals(action)) {
            bannerService.createBanner(name, startTime, endTime, position, displayOrder, filePart, imageLink);
            request.getSession().setAttribute("message", "Thêm banner thành công!");

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            bannerService.updateBanner(id, name, startTime, endTime, position, displayOrder, filePart, imageLink);
            request.getSession().setAttribute("message", "Cập nhật banner thành công!");
        }

        response.sendRedirect(request.getContextPath() + "/admin/banners");
    }
}