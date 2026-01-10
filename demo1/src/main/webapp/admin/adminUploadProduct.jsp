<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Upload Product</title>
    <link rel="stylesheet" href="admincss/adminUploadProduct.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="admincss/adminNotification.css">
    <link rel="stylesheet" href="admincss/headerAndSidebar.css">
</head>
<body>

<!-- Sidebar -->
<aside class="sidebar">
    <div class="logo">
        <a href="adminDashboard.jsp">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
        </a>
        <a href="adminDashboard.jsp" style="text-decoration: none;">
            <span class="logo-text">TechNova</span>
        </a></div>

    <ul class="nav-menu">
        <li class="nav-item">
            <a href="adminDashboard.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>
                Dashboard
            </a>
        </li>

        <li class="nav-item">
            <a href="customersList.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-users"></i></span>
                Khách hàng
            </a>
        </li>

        <li class="nav-item">
            <a href="adminCategories.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-list"></i></span>
                Mục sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="adminBrands.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>
                Thương hiệu
            </a>
        </li>

        <li class="nav-item">
            <a href="adminAttributes.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>
                Thuộc tính
            </a>
        </li>

        <li class="nav-item">
            <a href="adminProductList.jsp" class="nav-link active">
                <span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>
                Sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="adminOrders.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>
                Đơn hàng
            </a>
        </li>
    </ul>

    <!-- Logout Section -->
    <div class="logout-section">
        <a href="../login.html" class="nav-link logout-link">
            <span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>
            Đăng xuất
        </a>
    </div>
</aside>

<!-- Header -->
<header class="header">
    <div class="search-box">
        <span class="search-icon nav-icon"><i class="fa-solid fa-magnifying-glass"></i></span>
        <input type="text" class="search-input" placeholder="Tìm kiếm">
    </div>

    <div class="header-actions">
        <button class="notification-btn" id="notificationBtn">
            <i class="fa-solid fa-bell"></i>
            <span class="notification-badge">3</span>
        </button>

        <!-- Thông báo -->
        <div class="notification-dropdown" id="notificationDropdown">
            <div class="notification-header">
                <h3>Thông báo</h3>
            </div>

            <div class="notification-list">
                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;">
                        <i class="fa-solid fa-box-open"></i>
                    </div>
                    <div class="notification-content">
                        <p class="notification-text">Đã thêm sản phẩm vào hệ thống <strong>thành công!</strong></p>
                        <span class="notification-time">20 giây trước</span>
                    </div>
                </div>

                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;">
                        <i class="fa-solid fa-users"></i>
                    </div>
                    <div class="notification-content">
                        <p class="notification-text">Đã thêm tài khoản khách hàng <strong>thành công!</strong></p>
                        <span class="notification-time">20 phút trước</span>
                    </div>
                </div>

                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;">
                        <i class="fa-solid fa-file-invoice"></i>
                    </div>
                    <div class="notification-content">
                        <p class="notification-text">Đã cập nhật hóa đơn #1988001 <strong>thành công!</strong></p>
                        <span class="notification-time">5 giờ trước</span>
                    </div>
                </div>

                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;">
                        <i class="fa-solid fa-box-open"></i>
                    </div>
                    <div class="notification-content">
                        <p class="notification-text">Đã thêm sản phẩm vào hệ thống <strong>thành công!</strong></p>
                        <span class="notification-time">12 giờ trước</span>
                    </div>
                </div>
            </div>

            <div class="notification-footer">
                <a href="#" class="see-all-link">Xem tất cả thông báo</a>
            </div>
        </div>
        <div class="user-profile">
            <img src="https://www.shutterstock.com/image-vector/admin-icon-strategy-collection-thin-600nw-2307398667.jpg"
                 alt="User Profile">
        </div>
    </div>
</header>

<!-- Main Content -->
<main class="main-content">
    <div class="content-area">
        <h1 class="page-title">Thêm sản phẩm</h1>
        <div class="breadcrumb">
            <a href="adminDashboard.jsp">Trang chủ</a> / <a href="adminProductList.jsp">Danh sách sản phẩm</a> / <span>Thêm sản phẩm</span>
        </div>

        <div class="upload-product-container">
            <div class="form-section">
                <div class="form-group">
                    <h3>Tên sản phẩm</h3>
                    <input type="text" placeholder="Nhập tên sản phẩm" class="form-input">
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <h3>Danh mục</h3>
                        <select class="form-select">
                            <option>Chọn danh mục</option>
                            <option>CPU</option>
                            <option>Mainboard</option>
                            <option>RAM</option>
                            <option>Ổ cứng</option>
                            <option>Card màn hình</option>
                            <option>Nguồn máy tính</option>
                            <option>Tản nhiệt</option>
                            <option>Case máy tính</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <h3>Thương hiệu</h3>
                        <select class="form-select">
                            <option>Chọn thương hiệu</option>
                            <option>Asus</option>
                            <option>MSI</option>
                            <option>Gigabyte</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <h3>Giới thiệu sản phẩm:</h3>
                <div class="rich-text-editor">
                    <div class="rte-toolbar">
                        <div class="rte-group">
                            <select class="rte-select"><option>Paragraph</option></select>
                            <button><i class="fa-solid fa-bold"></i></button>
                            <button><i class="fa-solid fa-italic"></i></button>
                            <button><i class="fa-solid fa-underline"></i></button>
                            <button><i class="fa-solid fa-strikethrough"></i></button>
                            <button><i class="fa-solid fa-quote-right"></i></button>
                            <button><i class="fa-solid fa-align-left"></i></button>
                            <button><i class="fa-solid fa-align-center"></i></button>
                            <button><i class="fa-solid fa-align-right"></i></button>
                            <button><i class="fa-solid fa-link"></i></button>
                            <button><i class="fa-solid fa-image"></i></button>
                        </div>
                        <button class="rte-close"><i class="fa-solid fa-xmark"></i></button>
                    </div>
                    <div class="rte-content" contenteditable="true">
                    </div>
                    <div class="rte-footer">
                        <span>Word count: 0</span>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <div class="attribute-header">
                    <h3>Thuộc tính</h3>
                    <select class="form-select attribute-select">
                        <option>Chọn thuộc tính</option>
                        <option>Dung lượng</option>
                        <option>Loại RAM</option>
                        <option>Bus RAM</option>
                    </select>
                </div>

                <div class="attribute-item">
                    <div class="attr-top">
                        <span class="attr-name">Thuộc tính 1:</span>
                        <span class="attr-delete">Xóa <i class="fa-solid fa-chevron-down"></i></span>
                    </div>
                    <div class="attr-body">
                        <div class="attr-row">
                            <span class="attr-label">Tên:</span>
                            <span class="attr-static-val">Giá trị</span>
                        </div>
                        <div class="attr-row">
                            <span class="attr-label">Thuộc tính 1:</span>
                            <input type="text" class="form-input" value="Giá trị">
                        </div>
                    </div>
                </div>

                <div class="attribute-item">
                    <div class="attr-top">
                        <span class="attr-name">Thuộc tính 2:</span>
                        <span class="attr-delete">Xóa <i class="fa-solid fa-chevron-left"></i></span>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <h3>Giá sản phẩm:</h3>
                <div class="form-row">
                    <div class="form-group">
                        <label>Giá gốc (VND)</label>
                        <input type="text" placeholder="Nhập giá sản phẩm" class="form-input">
                    </div>
                    <div class="form-group">
                        <label>Giảm giá (%)</label>
                        <input type="text" placeholder="Nhập giá trị giảm giá" class="form-input">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label>Ngày bắt đầu giảm giá</label>
                        <input type="text" placeholder="Từ DD-MM-YYYY" class="form-input">
                    </div>
                    <div class="form-group">
                        <label>Ngày kết thúc giảm giá</label>
                        <input type="text" placeholder="Đến DD-MM-YYYY" class="form-input">
                    </div>
                </div>
                <div class="form-group">
                    <label>Số lượng tồn kho</label>
                    <input type="text" placeholder="Nhập số lượng sản phẩm" class="form-input">
                </div>
            </div>

            <div class="form-section">
                <h3>Hình ảnh sản phẩm:</h3>
                <div class="image-uploader-wrapper">
                    <div class="image-upload-box">
                        <i class="fa-solid fa-image"></i>
                    </div>

                    <div class="image-inputs">
                        <div class="form-group" style="margin-bottom: 0;">
                            <label style="font-size: 13px; color: #4b5563; margin-bottom: 6px; display: block;">Thứ tự hiển thị</label>
                            <input type="number" placeholder="Nhập thứ tự hiển thị" class="form-input" style="width: 220px;">
                        </div>
                        <button class="btn-add-img">Thêm hình ảnh</button>
                    </div>
                </div>

                <div class="image-thumbnails">
                    <div class="thumb-item">
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_265_1_.png" alt="img">
                    </div>
                    <div class="thumb-item">
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/d/_/d_p.png" alt="img">
                        <span class="remove-thumb"><i class="fa-solid fa-circle-xmark"></i></span>
                    </div>
                    <div class="thumb-item">
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/x/x/xxong.png" alt="img">
                        <span class="remove-thumb"><i class="fa-solid fa-circle-xmark"></i></span>
                    </div>
                </div>
            </div>

            <div class="form-actions-footer">
                <a href="adminProductList.jsp"><button class="btn-cancel">Hủy</button></a>
                <a href="adminProductList.jsp"><button class="btn-complete">Hoàn thành</button></a>
            </div>
        </div>
    </div>
</main>
<script src="adminjs/adminNotification.js"></script>
</body>
</html>