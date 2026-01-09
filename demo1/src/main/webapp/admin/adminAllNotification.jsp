<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="admincss/adminNotification.css">
    <link rel="stylesheet" href="admincss/adminAllNotification.css">
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
            <a href="adminDashboard.jsp" class="nav-link active">
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
                Danh mục sản phẩm
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
            <a href="adminProductList.jsp" class="nav-link">
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
                <a href="adminAllNotification.html" class="see-all-link">Xem tất cả thông báo</a>
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
        <div class="notification-container">
            <!-- Thông báo 1 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-box-open"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã thêm thông tin sản phẩm #RAM111 vào hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">20 phút trước</span>
                </div>
            </div>

            <!-- Thông báo 2 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-box-open"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã cập nhật thông tin sản phẩm #CPU222 trên hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">3 giờ trước</span>
                </div>
            </div>

            <!-- Thông báo 3 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-users"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã thêm tài khoản khách hàng #USER333 vào hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">5 giờ trước</span>
                </div>
            </div>

            <!-- Thông báo 4 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-file-invoice"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã cập nhật trạng thái hóa đơn #1988001 vào hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">6 giờ trước</span>
                </div>
            </div>

            <!-- Thông báo 5 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-file-invoice"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã thêm hóa đơn #1988001 vào hệ thống <strong>thành công!</strong></p>
                    <span class="notification-time">10 giờ trước</span>
                </div>
            </div>

            <!-- Thông báo 6 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-box-open"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã ẩn thông tin sản phẩm #CPU123 trên hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">1 ngày trước</span>
                </div>
            </div>

            <!-- Notification 7 -->
            <div class="notification-item">
                <div class="notification-icon-circle">
                    <i class="fa-solid fa-box-open"></i>
                </div>
                <div class="notification-content">
                    <p class="notification-text">Đã thêm thông tin sản phẩm #CPU123 vào hệ thống <strong>thành
                        công!</strong></p>
                    <span class="notification-time">2 ngày trước</span>
                </div>
            </div>

            <!-- View All Button -->
            <button class="view-all-btn">Xem thêm thông báo</button>
        </div>
    </div>
</main>
<script src="adminjs/adminNotification.js"></script>
</body>
</html>