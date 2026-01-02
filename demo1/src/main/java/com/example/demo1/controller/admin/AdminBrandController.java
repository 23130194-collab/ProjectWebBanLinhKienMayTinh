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
    private static final String EXTERNAL_UPLOAD_DIR = System.getProperty("user.home") + File.separator + "web_uploads";
    private static final String DB_UPLOAD_DIR = "uploads";
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
            if (brandToEdit == null) {
                request.getSession().setAttribute("errorMessage", "Thương hiệu không tồn tại.");
                response.sendRedirect(request.getContextPath() + "/admin/brands");
                return;
            }
            request.setAttribute("brandToEdit", brandToEdit);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID thương hiệu không hợp lệ.");
            response.sendRedirect(request.getContextPath() + "/admin/brands");
            return;
        }
        listBrands(request, response);
    }

    private void saveBrand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String displayOrderStr = request.getParameter("displayOrder");
        String logoUrl = request.getParameter("logo");
        Part filePart = request.getPart("logoFile");
        String fileName = (filePart != null) ? Paths.get(filePart.getSubmittedFileName()).getFileName().toString() : "";
        boolean isNew = (idParam == null || idParam.isEmpty());

        // --- VALIDATION ---
        boolean hasImage = !fileName.isEmpty() || (logoUrl != null && !logoUrl.trim().isEmpty());
        if (name == null || name.trim().isEmpty() || displayOrderStr == null || displayOrderStr.trim().isEmpty() || (isNew && !hasImage)) {
            request.setAttribute("errorMessage", "Thêm thất bại: Vui lòng điền đầy đủ tất cả các trường, bao gồm cả hình ảnh.");
            
            Brand brand = new Brand();
            if (!isNew) {
                brand.setId(Integer.parseInt(idParam));
            }
            brand.setName(name);
            brand.setStatus(request.getParameter("status"));
            brand.setLogo(logoUrl);
            try {
                brand.setDisplayOrder(Integer.parseInt(displayOrderStr));
            } catch (NumberFormatException e) {
                // Bỏ qua nếu nhập sai
            }
            request.setAttribute("brandToEdit", brand);
            
            listBrands(request, response);
            return;
        }
        // --- END VALIDATION ---

        Brand brand;
        if (!isNew) {
            brand = brandService.getBrandById(Integer.parseInt(idParam));
            if (brand == null) {
                request.getSession().setAttribute("errorMessage", "Lỗi: Thương hiệu bạn đang cố cập nhật không tồn tại hoặc đã bị xóa.");
                response.sendRedirect(request.getContextPath() + "/admin/brands");
                return;
            }
        } else {
            brand = new Brand();
        }

        brand.setName(name);
        brand.setStatus(request.getParameter("status"));
        brand.setDisplayOrder(Integer.parseInt(displayOrderStr));

        if (!fileName.isEmpty()) {
            String filePath = EXTERNAL_UPLOAD_DIR + File.separator + fileName;
            filePart.write(filePath);
            brand.setLogo(DB_UPLOAD_DIR + "/" + fileName);
        } else {
            brand.setLogo(logoUrl);
        }

        brandService.saveBrand(brand);
        request.getSession().setAttribute("successMessage", isNew ? "Thêm thương hiệu thành công!" : "Cập nhật thương hiệu thành công!");
        response.sendRedirect(request.getContextPath() + "/admin/brands");
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (brandService.deleteBrand(id)) {
                request.getSession().setAttribute("successMessage", "Xóa thương hiệu thành công!");
            } else {
                request.getSession().setAttribute("errorMessage", "Không thể xóa thương hiệu này vì vẫn còn sản phẩm liên quan.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID thương hiệu không hợp lệ.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/brands");
    }
}
