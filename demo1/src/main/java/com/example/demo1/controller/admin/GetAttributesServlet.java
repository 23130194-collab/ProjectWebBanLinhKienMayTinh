package com.example.demo1.controller.admin;

import com.example.demo1.model.Attribute;
import com.example.demo1.service.CategoryAttributeService;
import com.google.gson.Gson; // Nếu chưa có GSON, tôi sẽ dùng cách nối chuỗi thủ công bên dưới để bạn đỡ phải tải thư viện

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAttributesServlet", value = "/api/get-attributes")
public class GetAttributesServlet extends HttpServlet {

    private CategoryAttributeService categoryAttributeService = new CategoryAttributeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            // Lấy danh sách thuộc tính dựa trên Category ID
            List<Attribute> attributes = categoryAttributeService.getAllAttributesByCategoryId(categoryId);

            // Chuyển List thành JSON thủ công (để không phụ thuộc thư viện ngoài)
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < attributes.size(); i++) {
                Attribute attr = attributes.get(i);
                json.append("{")
                        .append("\"id\":").append(attr.getId()).append(",")
                        .append("\"name\":\"").append(attr.getName()).append("\"")
                        .append("}");
                if (i < attributes.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            response.getWriter().write(json.toString());

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("[]");
        }
    }
}
