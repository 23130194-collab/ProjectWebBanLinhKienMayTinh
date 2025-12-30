<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Danh sách hóa đơn</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="admincss/adminHoaDon.css">
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
            <a href="adminProductList.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>
                Sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="adminHoaDon.html" class="nav-link active">
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
                <a href="adminAllNotification.jsp" class="see-all-link">Xem tất cả thông báo</a>
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
        <h1 class="page-title">Đơn hàng</h1>
        <div class="breadcrumb"><a href="adminDashboard.jsp">Trang chủ</a> / <span>Đơn hàng</span></div>

        <div class="order-tools">
            <div>
                <input type="text" placeholder="Tìm kiếm..."/>
                <button>Tìm kiếm</button>
            </div>
        </div>

        <table>
            <thead>
            <tr>
                <th class="checkbox-col" aria-hidden="true"></th>
                <th>Mã đơn</th>
                <th>Khách hàng</th>
                <th>Ngày đặt</th>
                <th>Trạng thái</th>
                <th>Thành tiền</th>
                <th>Thanh toán</th>
                <th>Đơn hàng</th>
                <th>Hoạt động</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td><input type="checkbox"/></td>
                <td><a href="adminChiTietHoaDon.jsp">#1988001</a></td>
                <td>HuongLan</td>
                <td>09/11/2025<br/>16:48</td>
                <td><span class="badge paid">Đã thanh toán</span></td>
                <td>9.891.000đ</td>
                <td>Tiền mặt</td>
                <td><span class="badge shipped">Đã vận chuyển</span></td>
                <td>
                    <div class="action-buttons">
                        <button class="action-btn edit"><i class="fa-solid fa-pen"></i></button>
                        <button class="action-btn delete"><i class="fa-solid fa-trash"></i></button>
                    </div>
                </td>
            </tr>

            <tr>
                <td><input type="checkbox"/></td>
                <td><a href="adminChiTietHoaDon.jsp">#1988001</a></td>
                <td>HuongLan</td>
                <td>09/11/2025<br/>16:48</td>
                <td><span class="badge paid">Đã thanh toán</span></td>
                <td>9.891.000đ</td>
                <td>Tiền mặt</td>
                <td><span class="badge shipped">Đã vận chuyển</span></td>
                <td>
                    <div class="action-buttons">
                        <button class="action-btn edit"><i class="fa-solid fa-pen"></i></button>
                        <button class="action-btn delete"><i class="fa-solid fa-trash"></i></button>
                    </div>
                </td>
            </tr>
            <!-- Thêm các đơn hàng khác nếu cần -->
            </tbody>
        </table>

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
<script src="adminjs/adminNotification.js"></script>
</body>
</html>