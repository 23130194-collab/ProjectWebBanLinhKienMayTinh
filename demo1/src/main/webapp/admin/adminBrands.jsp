<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Thương hiệu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="admincss/adminBrands.css">
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
            <a href="adminBrands.html" class="nav-link active">
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
            <a href="adminProductList.jsp" class="nav-link">
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

<!--Main content-->
<main class="main-content">
    <div class="content-area">
        <div class="page-header">
            <div>
                <h1 class="page-title">Thương hiệu</h1>
                <div class="breadcrumb">
                    <a href="#" class="breadcrumb-link">Trang chủ</a>
                    <span>/</span>
                    <span class="breadcrumb-current">Thương hiệu</span>
                </div>
            </div>


        </div>

        <!-- FORM NHẬP THÔNG TIN -->
        <div class="brand-form">

            <div class="form-group">
                <label class="form-label">Tên thương hiệu mới</label>
                <input type="text" placeholder="Nhập tên thương hiệu">
            </div>

            <div class="form-group">
                <label class="form-label">Thứ tự hiển thị</label>
                <input type="number" placeholder="Nhập thứ tự">
            </div>

        </div>

        <!-- HÌNH ẢNH -->
        <div class="image-section">
            <label class="form-label">Hình ảnh thương hiệu</label>
            <div class="image-upload-box">
                <i class="fa-solid fa-image"></i>
            </div>
        </div>

        <!-- Nút + Tìm kiếm -->
        <div class="form-search-row">

            <button class="add-brand-submit">Thêm thương hiệu</button>

            <div class="search-wrapper">
                <i class="fa-solid fa-magnifying-glass search-icon"></i>
                <input type="text" class="search-input-brand" placeholder="Tìm kiếm thương hiệu">
            </div>

        </div>

        <!-- Danh sách -->
        <div class="brand-table-container">
            <table class="brand-table">
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>Hình ảnh</th>
                    <th>Tên thương hiệu</th>
                    <th>Thứ tự</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>

                <tbody>

                <tr>
                    <td><input type="checkbox"></td>
                    <td>
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Intel_logo_2023.svg/330px-Intel_logo_2023.svg.png" width="55">
                    </td>
                    <td>Intel</td>
                    <td>1</td>
                    <td><span class="status status-active">Hoạt động</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="action-btn edit"><i class="fa-solid fa-pen"></i></button>
                            <button class="action-btn view"><i class="fa-solid fa-eye"></i></button>
                            <button class="action-btn delete"><i class="fa-solid fa-trash"></i></button>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td><input type="checkbox"></td>
                    <td>
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/AMD_Logo.svg/500px-AMD_Logo.svg.png" width="55">
                    </td>
                    <td>AMD</td>
                    <td>2</td>
                    <td><span class="status status-hidden">Ẩn</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="action-btn edit"><i class="fa-solid fa-pen"></i></button>
                            <button class="action-btn view"><i class="fa-solid fa-eye"></i></button>
                            <button class="action-btn delete"><i class="fa-solid fa-trash"></i></button>
                        </div>
                    </td>
                </tr>

                </tbody>

            </table>
        </div>

        <!-- Phân trang -->
        <div class="pagination-container">
            <button class="pagination-btn"><i class="fa-solid fa-chevron-left"></i></button>
            <div class="page-number active">1</div>
            <div class="page-number">2</div>
            <div class="page-number">...</div>
            <div class="page-number">5</div>
            <button class="pagination-btn"><i class="fa-solid fa-chevron-right"></i></button>
        </div>
    </div>
</main>
</body>
<script src="adminjs/adminNotification.js"></script>
<script src="adminjs/adminProductList.js"></script>
</html>