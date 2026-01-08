package com.example.demo1.controller.admin;

import com.example.demo1.model.Category;
import com.example.demo1.service.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", value = "/admin/categories")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AdminCategoryServlet extends HttpServlet {
    private static final String SERVLET_PATH = "/admin/categories";
    private static final String JSP_PATH = "/admin/adminCategories.jsp";
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    handleEditAction(request, response);
                    return;
                case "delete":
                    handleDeleteAction(request, response);
                    return;
            }
        }
        
        showCategoryList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String categoryIdStr = request.getParameter("categoryId");

        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            handleUpdatePost(request, response);
        } else {
            handleInsertPost(request, response);
        }
    }

    private void showCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKeyword = request.getParameter("searchKeyword");
        List<Category> categoryList;

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            // Trang admin luôn tìm kiếm trên TẤT CẢ danh mục
            categoryList = categoryService.searchCategories(searchKeyword);
            request.setAttribute("searchKeyword", searchKeyword);
        } else {
            // Trang admin luôn hiển thị TẤT CẢ danh mục
            categoryList = categoryService.getAllCategories();
        }

        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher(JSP_PATH).forward(request, response);
    }

    private void handleEditAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Category categoryToEdit = categoryService.getCategoryById(id);
            if (categoryToEdit != null) {
                request.setAttribute("categoryToEdit", categoryToEdit);
            }
        } catch (NumberFormatException e) {
            // Bỏ qua
        }
        showCategoryList(request, response);
    }

    private void handleDeleteAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean deleted = categoryService.deleteCategory(id);
            if (deleted) {
                request.getSession().setAttribute("successMessage", "Xóa danh mục thành công!");
                refreshApplicationScopeCategories();
            } else {
                String categoryName = "<strong>" + categoryService.getCategoryById(id).getName() + "</strong>";
                request.getSession().setAttribute("errorMessage", "Không thể xóa danh mục " + categoryName + " vì vẫn còn sản phẩm tồn tại.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID danh mục không hợp lệ.");
        }
        response.sendRedirect(request.getContextPath() + SERVLET_PATH);
    }

    private void handleInsertPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("categoryName");
        String displayOrderStr = request.getParameter("displayOrder");
        String status = request.getParameter("status");

        String imagePath = handleImageUpload(request);

        if (name == null || name.trim().isEmpty() || displayOrderStr == null || displayOrderStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tên danh mục và thứ tự không được để trống!");
            showCategoryList(request, response);
            return;
        }
        if (imagePath == null) {
            request.setAttribute("errorMessage", "Vui lòng cung cấp hình ảnh cho danh mục!");
            showCategoryList(request, response);
            return;
        }

        try {
            int displayOrder = Integer.parseInt(displayOrderStr);
            categoryService.addCategory(name, displayOrder, imagePath, status);
            request.getSession().setAttribute("successMessage", "Thêm danh mục mới thành công!");
            refreshApplicationScopeCategories();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi thêm vào cơ sở dữ liệu.");
            showCategoryList(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + SERVLET_PATH);
    }

    private void handleUpdatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String name = request.getParameter("categoryName");
        String displayOrderStr = request.getParameter("displayOrder");
        String status = request.getParameter("status");

        Category oldCategory = categoryService.getCategoryById(categoryId);
        String imagePath = handleImageUpload(request);
        if (imagePath == null) {
            imagePath = oldCategory.getImage();
        }

        if (name == null || name.trim().isEmpty() || displayOrderStr == null || displayOrderStr.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tên danh mục và thứ tự không được để trống!");
            showCategoryList(request, response);
            return;
        }

        try {
            int displayOrder = Integer.parseInt(displayOrderStr);
            Category updatedCategory = new Category(categoryId, imagePath, name, displayOrder, status);
            categoryService.updateCategory(updatedCategory);
            request.getSession().setAttribute("successMessage", "Cập nhật danh mục thành công!");
            refreshApplicationScopeCategories();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi cập nhật cơ sở dữ liệu.");
            showCategoryList(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + SERVLET_PATH);
    }

    private String handleImageUpload(HttpServletRequest request) throws IOException, ServletException {
        String imageUrl = request.getParameter("imageUrl");
        Part filePart = request.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        if (filePart != null && fileName != null && !fileName.isEmpty()) {
            String realPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(realPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            filePart.write(realPath + File.separator + uniqueFileName);
            return "uploads/" + uniqueFileName;
        } else if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            return imageUrl;
        }
        return null;
    }

    private void refreshApplicationScopeCategories() {
        // ĐÃ SỬA: Luôn làm mới applicationScope với danh sách các danh mục đang hoạt động
        List<Category> activeCategoryList = categoryService.getActiveCategories();
        getServletContext().setAttribute("categoryList", activeCategoryList);
    }
}
