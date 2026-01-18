package com.example.demo1.controller.admin;

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

@WebServlet(name = "UploadAPI", value = "/api/upload-image")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UploadAPI extends HttpServlet {

    // Đường dẫn lưu file - PHẢI KHỚP với ImageServlet của bạn
    // Đảm bảo thư mục này có quyền ghi (write permission)
    private static final String UPLOAD_DIR = System.getProperty("user.home") + File.separator + "web_uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // 1. Tạo thư mục lưu trữ nếu chưa có
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 2. Lấy file từ request (key là "file")
            Part filePart = request.getPart("file");
            if (filePart == null || filePart.getSize() == 0) {
                throw new Exception("Chưa chọn file hoặc file rỗng");
            }

            // 3. Tạo tên file duy nhất để tránh trùng lặp
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = "";
            int i = originalFileName.lastIndexOf('.');
            if (i > 0) {
                fileExtension = originalFileName.substring(i);
            }
            // Tên file = thời gian hiện tại + đuôi file (VD: 170999999.png)
            String uniqueFileName = System.currentTimeMillis() + fileExtension;

            // 4. Lưu file vào ổ cứng
            String fullSavePath = UPLOAD_DIR + File.separator + uniqueFileName;
            filePart.write(fullSavePath);

            // 5. Trả về đường dẫn tương đối (để ImageServlet phục vụ)
            // Ví dụ: uploads/170999999.png
            String fileUrl = "uploads/" + uniqueFileName;

            // Trả về JSON kết quả thành công
            response.getWriter().write("{\"url\": \"" + fileUrl + "\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}