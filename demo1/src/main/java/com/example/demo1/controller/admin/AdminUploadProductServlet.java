package com.example.demo1.controller.admin;

import com.example.demo1.dao.Product_SpecDao;
import com.example.demo1.model.Brand;
import com.example.demo1.model.Category;
import com.example.demo1.model.Product;
import com.example.demo1.model.Product_Spec;
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

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        CategoryService categoryService = new CategoryService();
//        BrandService brandService = new BrandService();
//        ProductService productService = new ProductService();
//
//        // 1. Load danh mục và thương hiệu (Luôn cần thiết cho dropdown)
//        request.setAttribute("categoryList", categoryService.getAllCategories());
//        request.setAttribute("brandList", brandService.getAllBrands());
//
//        // 2. Kiểm tra xem có phải là chế độ SỬA (Edit) không?
//        String idStr = request.getParameter("id");
//
//        if (idStr != null && !idStr.isEmpty()) {
//            try {
//                int productId = Integer.parseInt(idStr);
//
//                // A. Lấy thông tin sản phẩm
//                Product product = productService.getProduct(productId);
//
//                if (product != null) {
//                    request.setAttribute("product", product);
//                    String desc = product.getDescription();
//                    System.out.println("=== DESCRIPTION FROM DB ===");
//                    System.out.println(desc);
//                    System.out.println("=== END ===");
//
//                    // Xử lý danh sách ảnh (tránh null)
//                    List<Product_Image> images = productService.getProductImages(productId);
//                    if (images == null) images = new ArrayList<>(); // Tạo list rỗng nếu null
//                    request.setAttribute("images", images);
//
//                    // Xử lý danh sách thuộc tính (tránh null)
//                    List<Product_Spec> specs = productService.getProductSpecs(productId);
//                    if (specs == null) specs = new ArrayList<>(); // Tạo list rỗng nếu null
//                    request.setAttribute("specs", specs);
//                }
//            } catch (NumberFormatException e) {
//                // ID lỗi thì coi như thêm mới
//            }
//        }
//
//        request.getRequestDispatcher("/admin/adminUploadProduct.jsp").forward(request, response);
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try { // ✅ THÊM TRY-CATCH ĐỂ BẮT LỖI
            CategoryService categoryService = new CategoryService();
            BrandService brandService = new BrandService();
            ProductService productService = new ProductService();

            request.setAttribute("categoryList", categoryService.getAllCategories());
            request.setAttribute("brandList", brandService.getAllBrands());

            String idStr = request.getParameter("id");

            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int productId = Integer.parseInt(idStr);
                    Product product = productService.getProduct(productId);

                    if (product != null) {
                        request.setAttribute("product", product);

                        // 1. Lấy danh sách ảnh chi tiết từ DB
                        List<Product_Image> images = productService.getProductImages(productId);
                        if (images == null) images = new ArrayList<>();

                        // 2. TẠO DANH SÁCH TỔNG HỢP (Gộp ảnh đại diện vào đầu danh sách)
                        List<Product_Image> allImages = new ArrayList<>();

                        // Thêm ảnh đại diện (từ bảng products) làm phần tử đầu tiên
                        if (product.getImage() != null && !product.getImage().isEmpty()) {
                            Product_Image mainImg = new Product_Image();
                            mainImg.setImage(product.getImage());
                            mainImg.setDisplayOrder(0); // Luôn để thứ tự nhỏ nhất cho ảnh chính
                            allImages.add(mainImg);
                        }

                        // Thêm các ảnh chi tiết vào sau
                        allImages.addAll(images);

                        // Gửi danh sách đã gộp sang JSP
                        request.setAttribute("images", allImages);

                        List<Product_Spec> specs = productService.getProductSpecs(productId);
                        if (specs == null) specs = new ArrayList<>();
                        request.setAttribute("specs", specs);

                        System.out.println("Load product thành công! Images: " + images.size() + ", Specs: " + specs.size());
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi parse productId: " + e.getMessage());
                }
            }

            request.getRequestDispatcher("/admin/adminUploadProduct.jsp").forward(request, response);

        } catch (Exception e) { // ✅ BẮT TẤT CẢ LỖI
            System.err.println("Xảy ra lỗi ở doGet");
            e.printStackTrace();

            // Hiển thị lỗi cho user
            response.setStatus(500);
            response.getWriter().write("Lỗi server: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            // Lấy productId để xác định là thêm mới hay cập nhật
            String productIdStr = request.getParameter("productId");
            int productId = parseIntSafe(productIdStr);

            // 1. Lấy dữ liệu cơ bản
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int categoryId = parseIntSafe(request.getParameter("categoryId"));
            int brandId = parseIntSafe(request.getParameter("brandId"));
            int stock = parseIntSafe(request.getParameter("stock"));
            double oldPrice = parseDoubleSafe(request.getParameter("oldPrice"));
            String status = request.getParameter("status");


            // 2. Lấy dữ liệu giảm giá
            String discountValueStr = request.getParameter("discountValue");
            Double discountValue = null;
            if (discountValueStr != null && !discountValueStr.trim().isEmpty()) {
                discountValue = Double.parseDouble(discountValueStr);
            }

            // 3. Lấy dữ liệu ngày tháng
            Timestamp discountStart = parseTimestamp(request.getParameter("discountStart"));
            Timestamp discountEnd = parseTimestamp(request.getParameter("discountEnd"));

            // 4. Lấy danh sách ảnh
            String[] imageUrls = request.getParameterValues("imageUrls");
            String[] imageOrders = request.getParameterValues("imageOrders");

            List<Product_Image> images = new ArrayList<>();
            if (imageUrls != null && imageOrders != null) {
                for (int i = 0; i < imageUrls.length; i++) {
                    if (imageUrls[i] != null && !imageUrls[i].trim().isEmpty()) {
                        Product_Image img = new Product_Image();
                        img.setImage(imageUrls[i].trim());
                        img.setDisplayOrder(parseIntSafe(imageOrders[i]));
                        images.add(img);
                    }
                }
            }
            images.sort(Comparator.comparingInt(Product_Image::getDisplayOrder));

            // 5. Validate cơ bản
            if (categoryId == 0 || brandId == 0) {
                throw new Exception("Vui lòng chọn Danh mục và Thương hiệu.");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Tên sản phẩm không được để trống.");
            }
            if (oldPrice <= 0) {
                throw new Exception("Giá sản phẩm phải lớn hơn 0.");
            }
            if (request.getParameter("stock") == null || request.getParameter("stock").isEmpty()) {
                throw new Exception("Vui lòng nhập số lượng tồn kho.");
            }

            ProductService productService = new ProductService();
            int currentProductId;

            if (productId > 0) { // Cập nhật sản phẩm
                productService.updateProduct(productId, name, categoryId, brandId, description, oldPrice, stock, status, discountValue, discountStart, discountEnd, images);
                currentProductId = productId;
            } else { // Thêm sản phẩm mới
                currentProductId = productService.createProduct(name, categoryId, brandId, description, oldPrice, stock, status, discountValue, discountStart, discountEnd, images);
            }


            // 7. XỬ LÝ LƯU THUỘC TÍNH (SPECIFICATIONS)
            String[] specIds = request.getParameterValues("specIds");
            String[] specValues = request.getParameterValues("specValues");

            if (currentProductId > 0 && specIds != null && specValues != null && specIds.length == specValues.length) {
                Product_SpecDao specDao = new Product_SpecDao();
                specDao.deleteProductSpecs(currentProductId); // Xóa spec cũ trước khi thêm mới

                for (int i = 0; i < specIds.length; i++) {
                    String val = specValues[i];
                    if (val != null && !val.trim().isEmpty()) {
                        try {
                            int attrId = Integer.parseInt(specIds[i]);
                            specDao.insertProductSpec(currentProductId, attrId, val.trim());
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

            // Thành công -> Chuyển hướng
            response.sendRedirect(request.getContextPath() + "/admin-product-list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi: " + e.getMessage());

            // Load lại dữ liệu dropdown để form không bị trống trơn khi báo lỗi
            CategoryService categoryService = new CategoryService();
            BrandService brandService = new BrandService();
            request.setAttribute("categoryList", categoryService.getAllCategories());
            request.setAttribute("brandList", brandService.getAllBrands());

            request.getRequestDispatcher("/admin/adminUploadProduct.jsp").forward(request, response);
        }
    }

    // Hàm parse ngày giờ chuẩn cho input type="datetime-local"
    private Timestamp parseTimestamp(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            // Định dạng của HTML5 datetime-local là "yyyy-MM-dd'T'HH:mm"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date parsedDate = dateFormat.parse(dateStr);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm phụ trợ parse số nguyên an toàn
    private int parseIntSafe(String s) {
        try {
            return (s != null && !s.isEmpty()) ? Integer.parseInt(s.trim()) : 0;
        } catch (NumberFormatException e) { return 0; }
    }

    // Hàm phụ trợ parse số thực an toàn
    private double parseDoubleSafe(String s) {
        try {
            return (s != null && !s.isEmpty()) ? Double.parseDouble(s.trim()) : 0.0;
        } catch (NumberFormatException e) { return 0.0; }
    }
}
