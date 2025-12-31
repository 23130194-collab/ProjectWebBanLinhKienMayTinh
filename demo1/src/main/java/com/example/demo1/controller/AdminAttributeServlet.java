package com.example.demo1.controller.admin;

import com.example.demo1.dao.CategoryDao;
import com.example.demo1.model.Attribute;
import com.example.demo1.model.Category;
import com.example.demo1.service.AttributeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminAttributeServlet", value = "/admin-attributes")
public class AdminAttributeServlet extends HttpServlet {
    private AttributeService attributeService;
    private CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.attributeService = new AttributeService();
        this.categoryDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Handle edit request
        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Attribute attributeToEdit = attributeService.getAttributeById(id);
                request.setAttribute("attributeToEdit", attributeToEdit);
            } catch (NumberFormatException e) {
                // Handle invalid ID
                response.sendRedirect(request.getContextPath() + "/admin-attributes?error=invalid_id");
                return;
            }
        }

        // Always load categories for the form
        List<Category> categories = categoryDao.getAll();
        request.setAttribute("categories", categories);

        // Always load the list of attributes for the table
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

        List<Attribute> attributes = attributeService.getAttributes(keyword, limit, offset);
        int totalAttributes = attributeService.countAttributes(keyword);
        int totalPages = (int) Math.ceil((double) totalAttributes / limit);

        request.setAttribute("attributes", attributes);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keyword", keyword);

        request.getRequestDispatcher("/admin/adminAttributes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin-attributes");
            return;
        }

        try {
            switch (action) {
                case "add":
                    addAttributeForCategory(request, response);
                    break;
                case "update":
                    updateAttribute(request, response);
                    break;
                case "delete":
                    deleteAttribute(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin-attributes");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin-attributes?error=true");
        }
    }

    private void addAttributeForCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        int displayOrder = Integer.parseInt(request.getParameter("display_order"));
        int isFilterable = (request.getParameter("is_filterable") != null) ? 1 : 0;

        // 1. KIỂM TRA TỒN TẠI
        if (attributeService.isAttributeExists(name, categoryId)) {
            // --- XỬ LÝ KHI BỊ TRÙNG ---

            // A. Báo lỗi
            request.setAttribute("errorMessage", "Thuộc tính '" + name + "' đã tồn tại trong danh mục này!");

            // B. Giữ lại giá trị cũ để người dùng không phải nhập lại
            request.setAttribute("oldName", name);
            request.setAttribute("oldStatus", status);
            request.setAttribute("oldCategoryId", categoryId);
            request.setAttribute("oldDisplayOrder", displayOrder);
            request.setAttribute("oldIsFilterable", isFilterable);

            // C. LOAD LẠI DỮ LIỆU CHO TRANG (Copy logic từ doGet)
            // Cần load lại Categories để đổ vào thẻ <select>
            List<Category> categories = categoryDao.getAll();
            request.setAttribute("categories", categories);

            // Cần load lại danh sách Attributes để hiện cái bảng bên dưới
            String keyword = request.getParameter("keyword");
            int page = 1; // Mặc định về trang 1 khi lỗi
            int limit = 10;
            int offset = (page - 1) * limit;

            List<Attribute> attributes = attributeService.getAttributes(keyword, limit, offset);
            int totalAttributes = attributeService.countAttributes(keyword);
            int totalPages = (int) Math.ceil((double) totalAttributes / limit);

            request.setAttribute("attributes", attributes);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", page);

            // D. Forward lại về trang JSP (Không dùng sendRedirect)
            request.getRequestDispatcher("/admin/adminAttributes.jsp").forward(request, response);
            return;
        }

        // 2. NẾU KHÔNG TRÙNG -> THÊM MỚI BÌNH THƯỜNG
        if (name != null && !name.trim().isEmpty()) {
            attributeService.createAttributeForCategory(name, status, categoryId, displayOrder, isFilterable);
            response.sendRedirect(request.getContextPath() + "/admin-attributes?success=add");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin-attributes?error=name_required");
        }
    }

    private void updateAttribute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        int displayOrder = Integer.parseInt(request.getParameter("display_order"));
        int isFilterable = (request.getParameter("is_filterable") != null) ? 1 : 0;

        if (name != null && !name.trim().isEmpty()) {
            attributeService.updateAttributeAndCategoryLink(id, name, status, categoryId, displayOrder, isFilterable);
            response.sendRedirect(request.getContextPath() + "/admin-attributes?success=update");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin-attributes?action=edit&id=" + id + "&error=name_required");
        }
    }

    private void deleteAttribute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            attributeService.deleteAttribute(id);
            response.sendRedirect(request.getContextPath() + "/admin-attributes?success=delete");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin-attributes?error=invalid_id");
        }
    }
}
