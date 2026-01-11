<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Thêm sản phẩm</title>
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminUploadProduct.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminNotification.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/headerAndSidebar.css">
</head>
<body>

<!-- Sidebar -->
<aside class="sidebar">
    <div class="logo">
        <a href="${contextPath}/admin/adminDashboard.jsp">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
        </a>
        <a href="${contextPath}/admin/adminDashboard.jsp" style="text-decoration: none;">
            <span class="logo-text">TechNova</span>
        </a>
    </div>
    <ul class="nav-menu">
        <li class="nav-item">
            <a href="${contextPath}/admin/adminDashboard.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>
                Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin/customersList.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-users"></i></span>
                Khách hàng
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin/adminCategories.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-list"></i></span>
                Mục sản phẩm
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin/brands" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>
                Thương hiệu
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin/adminAttributes.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>
                Thuộc tính
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin-product-list" class="nav-link active">
                <span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>
                Sản phẩm
            </a>
        </li>
        <li class="nav-item">
            <a href="${contextPath}/admin/adminHoaDon.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>
                Đơn hàng
            </a>
        </li>
    </ul>
    <div class="logout-section">
        <a href="${contextPath}/logout" class="nav-link logout-link">
            <span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>
            Đăng xuất
        </a>
    </div>
</aside>

<!-- Header -->
<header class="header">
    <div class="header-actions">
        <button class="notification-btn" id="notificationBtn">
            <i class="fa-solid fa-bell"></i>
            <span class="notification-badge">3</span>
        </button>
        <div class="notification-dropdown" id="notificationDropdown">
            <div class="notification-header"><h3>Thông báo</h3></div>
            <div class="notification-list">
                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;"><i class="fa-solid fa-box-open"></i></div>
                    <div class="notification-content">
                        <p class="notification-text">Đã thêm sản phẩm vào hệ thống <strong>thành công!</strong></p>
                        <span class="notification-time">20 giây trước</span>
                    </div>
                </div>
            </div>
            <div class="notification-footer"><a href="#" class="see-all-link">Xem tất cả thông báo</a></div>
        </div>
        <div class="user-profile">
            <img src="https://www.shutterstock.com/image-vector/admin-icon-strategy-collection-thin-600nw-2307398667.jpg" alt="User Profile">
        </div>
    </div>
</header>

<!-- Main Content -->
<main class="main-content">
    <div class="content-area">
        <h1 class="page-title">Thêm sản phẩm</h1>
        <div class="breadcrumb">
            <a href="${contextPath}/admin/adminDashboard.jsp">Trang chủ</a> / <a href="${contextPath}/admin-product-list">Danh sách sản phẩm</a> / <span>Thêm sản phẩm</span>
        </div>

        <form action="${contextPath}/admin-upload-product" method="post" class="upload-product-container">
            <div class="form-section">
                <div class="form-group">
                    <h3>Tên sản phẩm</h3>
                    <input type="text" name="name" placeholder="Nhập tên sản phẩm" class="form-input" required>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <h3>Danh mục</h3>
                        <select class="form-select" name="categoryId" required>
                            <option value="" disabled selected>Chọn danh mục</option>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <h3>Thương hiệu</h3>
                        <select class="form-select" name="brandId" required>
                            <option value="" disabled selected>Chọn thương hiệu</option>
                            <c:forEach var="brand" items="${brandList}">
                                <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <h3>Giới thiệu sản phẩm:</h3>
                <div class="form-group">
                    <textarea id="product-description" name="description" rows="15"></textarea>
                </div>
            </div>

            <div class="form-section">
                <h3>Giá sản phẩm:</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>Giá gốc (VND)</label>
                        <input type="text" name="oldPrice" placeholder="Nhập giá sản phẩm" class="form-input">
                    </div>
                    <div class="form-group">
                        <label>Giảm giá (%)</label>
                        <input type="text" name="discountValue" placeholder="Nhập giá trị giảm giá" class="form-input">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Ngày bắt đầu giảm giá</label>
                        <input type="text" name="discountStart" placeholder="DD-MM-YYYY" class="form-input">
                    </div>
                    <div class="form-group">
                        <label>Ngày kết thúc giảm giá</label>
                        <input type="text" name="discountEnd" placeholder="DD-MM-YYYY" class="form-input">
                    </div>
                </div>
                <div class="form-group">
                    <label>Số lượng tồn kho</label>
                    <input type="text" name="stock" placeholder="Nhập số lượng sản phẩm" class="form-input">
                </div>
            </div>

            <div class="form-section">
                <h3>Hình ảnh sản phẩm</h3>
                <div class="image-uploader">
                    <div class="image-adder-group">
                        <div class="form-group">
                            <label for="image-url-input">URL hình ảnh</label>
                            <input type="text" id="image-url-input" placeholder="https://example.com/image.png" class="form-input">
                        </div>
                        <div class="form-group">
                            <label for="image-order-input">Thứ tự</label>
                            <input type="number" id="image-order-input" placeholder="1" class="form-input" style="width: 100px;">
                        </div>
                        <button type="button" id="btn-add-image" class="btn-add-img">Thêm</button>
                    </div>
                    <p><i>Lưu ý: Chức năng tải file từ máy tính sẽ được cập nhật sau. Vui lòng sử dụng đường dẫn URL.</i></p>

                    <div id="image-data-container" style="display: none;"></div>

                    <div class="image-thumbnails" id="image-preview-container">
                    </div>
                </div>
            </div>


            <div class="form-actions-footer">
                <a href="${contextPath}/admin-product-list" class="btn-cancel">Hủy</a>
                <button type="submit" class="btn-complete">Hoàn thành</button>
            </div>
        </form>
    </div>
</main>
<script src="${contextPath}/admin/adminjs/adminNotification.js"></script>

<script src="https://cdn.tiny.cloud/1/3ed7uep3wrojhgtffcu69d19t08h1k9sikr7x4myygwkmrju/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Khởi tạo TinyMCE (giữ nguyên)
        tinymce.init({
            selector: '#product-description',
            plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
            language: 'vi',
            height: 400,
            menubar: false,
            placeholder: 'Nhập mô tả chi tiết cho sản phẩm ở đây...'
        });

        // Image Uploader Logic (ĐÃ ĐƯỢC CẢI TIẾN)
        const btnAddImage = document.getElementById('btn-add-image');
        const imageUrlInput = document.getElementById('image-url-input');
        const imageOrderInput = document.getElementById('image-order-input');
        const previewContainer = document.getElementById('image-preview-container');
        const dataContainer = document.getElementById('image-data-container');

        btnAddImage.addEventListener('click', function() {
            const imageUrl = imageUrlInput.value.trim();
            const displayOrder = imageOrderInput.value.trim() || '0';

            if (!imageUrl) {
                alert('Vui lòng nhập URL hình ảnh.');
                return;
            }

            const imageId = 'img-' + Date.now();

            // 1. Tạo thẻ bao ngoài (wrapper)
            const thumbItem = document.createElement('div');
            thumbItem.classList.add('thumb-item');
            thumbItem.setAttribute('data-image-id', imageId);

            // 2. Tạo thẻ IMG bằng code (an toàn hơn dùng chuỗi HTML)
            const imgElement = document.createElement('img');
            imgElement.src = imageUrl;
            imgElement.alt = "Preview";

            // Xử lý khi ảnh lỗi (quan trọng)
            imgElement.onerror = function() {
                this.onerror = null; // Tránh lặp vô hạn
                this.src = 'https://placehold.co/100x100/e2e8f0/475569?text=Lỗi+Ảnh'; // Ảnh thay thế
                // Hoặc bạn có thể dùng ảnh có sẵn trong project:
                // this.src = '${contextPath}/assets/images/no-image.png';
            };

            // 3. Tạo nút xóa
            const removeBtn = document.createElement('span');
            removeBtn.className = 'remove-thumb';
            removeBtn.innerHTML = '&times;';
            removeBtn.title = 'Xóa ảnh này';

            // 4. Tạo hiển thị thứ tự
            const orderDiv = document.createElement('div');
            orderDiv.className = 'thumb-order';
            orderDiv.textContent = `Thứ tự: ${displayOrder}`;

            // 5. Ghép lại
            thumbItem.appendChild(imgElement);
            thumbItem.appendChild(removeBtn);
            thumbItem.appendChild(orderDiv);
            previewContainer.appendChild(thumbItem);

            // 6. Tạo hidden inputs để gửi về Server
            const urlHiddenInput = document.createElement('input');
            urlHiddenInput.type = 'hidden';
            urlHiddenInput.name = 'imageUrls';
            urlHiddenInput.value = imageUrl;
            urlHiddenInput.setAttribute('data-image-id', imageId);

            const orderHiddenInput = document.createElement('input');
            orderHiddenInput.type = 'hidden';
            orderHiddenInput.name = 'imageOrders';
            orderHiddenInput.value = displayOrder;
            orderHiddenInput.setAttribute('data-image-id', imageId);

            dataContainer.appendChild(urlHiddenInput);
            dataContainer.appendChild(orderHiddenInput);

            // 7. Reset form
            imageUrlInput.value = '';
            imageUrlInput.focus();
        });

        // Xử lý xóa ảnh
        previewContainer.addEventListener('click', function(e) {
            if (e.target && e.target.classList.contains('remove-thumb')) {
                const thumbItem = e.target.closest('.thumb-item');
                if (thumbItem) {
                    const imageId = thumbItem.getAttribute('data-image-id');
                    thumbItem.remove(); // Xóa giao diện

                    // Xóa dữ liệu ẩn
                    const inputsToRemove = dataContainer.querySelectorAll(`[data-image-id="${imageId}"]`);
                    inputsToRemove.forEach(input => input.remove());
                }
            }
        });
    });
</script>

</body>
</html>