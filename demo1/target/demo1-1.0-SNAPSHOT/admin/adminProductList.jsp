<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Danh sách sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="admincss/adminProductList.css">
    <link rel="stylesheet" href="admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="admincss/adminNotification.css">
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
            <a href="adminProductList.html" class="nav-link active">
                <span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>
                Sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="adminHoaDon.jsp" class="nav-link">
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

<header class="header">
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
                        <p class="notification-text">Đã thêm tài khoản khách hàng vào hệ thống <strong>thành
                            công!</strong></p>
                        <span class="notification-time">20 phút trước</span>
                    </div>
                </div>

                <div class="notification-item">
                    <div class="notification-icon" style="background: #5b86e5;">
                        <i class="fa-solid fa-file-invoice"></i>
                    </div>
                    <div class="notification-content">
                        <p class="notification-text">Đã cập nhật hóa đơn #1988001 vào hệ thống <strong>thành
                            công!</strong></p>
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
                <a href="adminAllNotification.jsp" class="see-all-link">Xem tất cả thông báo</a>
            </div>
        </div>
        <div class="user-profile">
            <img src="https://www.shutterstock.com/image-vector/admin-icon-strategy-collection-thin-600nw-2307398667.jpg"
                 alt="User Profile">
        </div>
    </div>
</header>

<main class="main-content">
    <div class="content-area">

        <div class="page-header">
            <div class="page-title-wrapper">
                <h1 class="page-title">Sản phẩm</h1>
                <div class="breadcrumb">
                    <a href="adminDashboard.jsp" class="breadcrumb-link">Trang chủ</a>
                    <span class="breadcrumb-separator">/</span>
                    <span class="breadcrumb-current">Sản phẩm</span>
                </div>
            </div>

            <a href="adminUploadProduct.jsp" class="add-product-btn" title="Thêm sản phẩm mới">
                <i class="fa-solid fa-plus"></i>
            </a>
        </div>

        <div class="filter-bar">
            <div class="product-tabs">
                <button class="tab active">Tất cả</button>
                <button class="tab">Đang bán</button>
                <button class="tab">Hết hàng</button>
                <button class="tab">Hết hàng</button>
                <button class="tab">Ngừng bán</button>
            </div>
            <div class="search-wrapper">
                <i class="fa-solid fa-magnifying-glass search-icon"></i>
                <input type="text" class="search-input-product" placeholder="Tìm sản phẩm...">
            </div>
        </div>

        <div class="product-table-container">
            <table class="product-table">
                <thead>
                <tr>
                    <th style="width: 50px;"><input type="checkbox" id="selectAllProducts"></th>
                    <th style="width: 100px;">Hình ảnh</th>
                    <th style="width: 170px">Sản phẩm</th>
                    <th style="width: 125px;">Giá bán</th>
                    <th style="width: 100px;">Tồn kho</th>
                    <th style="width: 120px;">Trạng thái</th>
                    <th style="width: 150px;">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox" class="row-check"></td>
                    <td class="td-image">
                        <div class="img-wrapper">
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/o/-/o-cung-di-dong-ssd-kingston-xs1000-usb-3-2-gen-2-den-1tb_2_.png"
                                 alt="SP">
                        </div>
                    </td>
                    <td class="td-name">
                                <span class="product-name" title="Ổ cứng di động SSD Kingston XS1000 USB 3.2 Gen 2 1TB">
                                    Ổ cứng di động SSD Kingston XS1000 USB 3.2 Gen 2 1TB
                                </span>
                    </td>
                    <td class="td-price">
                        <div class="price-group">
                            <span class="current-price">2.090.000đ</span>
                            <span class="old-price">2.390.000đ</span>
                        </div>
                    </td>
                    <td><span class="stock-text">100</span></td>
                    <td><span class="status status-selling">Đang bán</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="action-btn edit" title="Sửa"><i class="fa-solid fa-pen"></i></button>
                            <button class="action-btn view" title="Xem"><i class="fa-solid fa-eye"></i></button>
                            <button class="action-btn delete" title="Xóa"><i class="fa-solid fa-trash-can"></i></button>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><input type="checkbox" class="row-check"></td>
                    <td class="td-image">
                        <div class="img-wrapper">
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_251_3_.png"
                                 alt="SP">
                        </div>
                    </td>
                    <td class="td-name">
                        <span class="product-name">CPU Intel Core i5 12400F</span>
                    </td>
                    <td class="td-price">
                        <div class="price-group">
                            <span class="current-price">4.290.000đ</span>
                            <span class="old-price">5.390.000đ</span>
                        </div>
                    </td>
                    <td><span class="stock-text">5</span></td>
                    <td><span class="status status-stopped">Hết hàng</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="action-btn edit"><i class="fa-solid fa-pen"></i></button>
                            <button class="action-btn view"><i class="fa-solid fa-eye"></i></button>
                            <button class="action-btn delete"><i class="fa-solid fa-trash-can"></i></button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="pagination-container">
            <button class="pagination-btn"><i class="fa-solid fa-chevron-left"></i></button>
            <a href="#" class="page-number active">1</a>
            <a href="#" class="page-number">2</a>
            <span class="ellipsis">...</span>
            <a href="#" class="page-number">5</a>
            <button class="pagination-btn"><i class="fa-solid fa-chevron-right"></i></button>
        </div>

    </div>
</main>
</body>
<script src="adminjs/adminNotification.js"></script>
<script src="adminjs/adminProductList.js"></script>
</html>