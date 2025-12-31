package com.example.demo1.controller.admin;

import com.example.demo1.model.Brand;
import com.example.demo1.model.BrandPage;
import com.example.demo1.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "AdminBrandController", value = "/admin/brands")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 15)
public class AdminBrandController extends HttpServlet {
    private final BrandService brandService = new BrandService();
    // Đường dẫn cố định bên ngoài project
    private static final String EXTERNAL_UPLOAD_DIR = System.getProperty("user.home") + File.separator + "web_uploads";
    private static final String DB_UPLOAD_DIR = "uploads"; // Đường dẫn ảo để lưu vào DB
    private static final int BRANDS_PER_PAGE = 10;

    @Override
    public void init() throws ServletException {
        super.init();
        File uploadDir = new File(EXTERNAL_UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteBrand(request, response);
                break;
            default:
                listBrands(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        saveBrand(request, response);
    }

    private void listBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String pageStr = request.getParameter("page");
        int currentPage = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);

        BrandPage brandPage = brandService.getPagedBrands(keyword, currentPage, BRANDS_PER_PAGE);
        int totalPages = (int) Math.ceil((double) brandPage.getTotalBrands() / BRANDS_PER_PAGE);

        request.setAttribute("brands", brandPage.getBrands());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/admin/adminBrands.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Brand brandToEdit = brandService.getBrandById(id);
            request.setAttribute("brandToEdit", brandToEdit);
        } catch (NumberFormatException e) {
            // Handle error
        }
        listBrands(request, response);
    }

    private void saveBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");

        Brand brand;
        if (idParam != null && !idParam.isEmpty()) {
            brand = brandService.getBrandById(Integer.parseInt(idParam));
        } else {
            brand = new Brand();
        }

        brand.setName(request.getParameter("name"));
        brand.setStatus(request.getParameter("status"));
        try {
            brand.setDisplayOrder(Integer.parseInt(request.getParameter("displayOrder")));
        } catch (NumberFormatException e) {
            brand.setDisplayOrder(0);
        }

        Part filePart = request.getPart("logoFile");
        String fileName = (filePart != null) ? Paths.get(filePart.getSubmittedFileName()).getFileName().toString() : "";

        if (!fileName.isEmpty()) {
            String filePath = EXTERNAL_UPLOAD_DIR + File.separator + fileName;
            filePart.write(filePath);
            brand.setLogo(DB_UPLOAD_DIR + "/" + fileName);
        } else if (brand.getId() == 0) {
            brand.setLogo(request.getParameter("logo"));
        }

        brandService.saveBrand(brand);
        response.sendRedirect("brands");
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (!brandService.deleteBrand(id)) {
                request.getSession().setAttribute("errorMessage", "Không thể xóa thương hiệu này vì vẫn còn sản phẩm liên quan.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID thương hiệu không hợp lệ.");
        }
        response.sendRedirect("brands");
    }
}
