<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Chi tiết khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/customersList.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/detailsCustomers.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/adminNotification.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/headerAndSidebar.css">

</head>

<body>
<!-- Sidebar -->
<aside class="sidebar">
    <div class="logo">
        <a href="${contextPath}/admin/dashboard">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
        </a>
        <a href="${contextPath}/admin/dashboard" style="text-decoration: none;">
            <span class="logo-text">TechNova</span>
        </a>
    </div>
    <ul class="nav-menu">
        <li class="nav-item"><a href="${contextPath}/admin/dashboard" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>Dashboard</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/customers active" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-users"></i></span>Khách hàng</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/categories" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-list"></i></span>Mục sản phẩm</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/brands" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>Thương hiệu</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/attributes" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>Thuộc tính</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/banners" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-images"></i></span>Banner</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/products" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>Sản phẩm</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/orders" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>Đơn hàng</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/reviews" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-star"></i></span>Đánh giá</a></li>

    </ul>
    <div class="logout-section"><a href="${contextPath}/logout" class="nav-link logout-link"><span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>Đăng xuất</a></div>
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
        <h1 class="page-title">Chi tiết khách hàng</h1>
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">Trang chủ</a> / <a href="${pageContext.request.contextPath}/admin/customers">Danh sách khách hàng</a> / <span>Chi tiết khách hàng</span>
        </div>

        <%-- Thông báo thành công --%>
        <c:if test="${not empty updateSuccess}">
            <div class="alert-box success-message">
                <span>${updateSuccess}</span>
                <span class="close-btn">&times;</span> </div>
        </c:if>

        <%-- Thông báo lỗi --%>
        <c:if test="${not empty updateError}">
            <div class="alert-box error-message">
                <span>${updateError}</span>
                <span class="close-btn">&times;</span> </div>
        </c:if>

        <c:if test="${not empty customer}">
            <!-- Khung Thông tin cá nhân -->
            <section class="personal-info">
                <!-- Khung hiển thị -->
                <div class="info-card" id="infoView">
                    <div class="info-header">
                        <h2>Thông tin cá nhân</h2>
                        <button id="editBtn" class="update-btn">Cập nhật</button>
                    </div>

                    <div class="info-body">
                        <div class="info-row">
                            <span>Họ và tên:</span>
                            <p id="name">${customer.name}</p>
                            <span>Số điện thoại:</span>
                            <p id="phone">${customer.phone}</p>
                        </div>
                        <div class="info-row">
                            <span>Giới tính:</span>
                            <p id="gender">${customer.gender}</p>
                            <span>Email:</span>
                            <p id="email">${customer.email}</p>
                        </div>
                        <div class="info-row">
                            <span>Ngày sinh:</span>
                            <p id="dob">${customer.birthday}</p>
                            <span>Địa chỉ:</span>
                            <p id="address">${customer.address}</p>
                        </div>
                    </div>
                </div>

                <!-- Khung chỉnh sửa (ẩn lúc đầu) -->
                <form action="${pageContext.request.contextPath}/admin/customer-detail" method="post" class="info-card hidden" id="infoForm">
                    <input type="hidden" name="id" value="${customer.id}">
                    <div class="info-header">
                        <h2>Cập nhật thông tin</h2>
                    </div>

                    <div class="info-body">
                        <div class="info-row">
                            <span>Họ và tên:</span>
                            <input type="text" id="inputName" name="name" value="${customer.name}">
                            <span>Số điện thoại:</span>
                            <input type="text" id="inputPhone" name="phone" value="${customer.phone}">
                        </div>
                        <div class="info-row">
                            <span>Giới tính:</span>
                            <select id="inputGender" name="gender">
                                <option value="Nam" ${customer.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                <option value="Nữ" ${customer.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                <option value="Khác" ${customer.gender == 'Khác' ? 'selected' : ''}>Khác</option>
                            </select>
                            <span>Email:</span>
                            <input type="email" id="inputEmail" name="email" value="${customer.email}">
                        </div>
                        <div class="info-row">
                            <span>Ngày sinh:</span>
                            <input type="date" id="inputDob" name="birthday" value="${customer.birthday}">
                            <span>Địa chỉ:</span>
                            <input type="text" id="inputAddress" name="address" value="${customer.address}">
                        </div>
                    </div>

                    <div class="info-actions">
                        <button type="submit" id="saveBtn" class="save-btn">Lưu</button>
                        <button type="button" id="cancelBtn" class="cancel-btn">Hủy</button>
                    </div>
                </form>
            </section>

            <div class="customer-detail">
                <div class="orders-section">
                    <div class="orders-header">
                        <h3>Lịch sử đơn hàng</h3>
                    </div>

                    <table class="orders-table">
                        <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Ngày đặt hàng</th>
                            <th>Trạng thái</th>
                            <th>Tổng tiền</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty orderList}">
                            <tr>
                                <td colspan="4" style="text-align: center;">Khách hàng chưa có đơn hàng nào.</td>
                            </tr>
                        </c:if>

                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/orders?action=view&id=${order.id}">
                                        #${order.orderCode}
                                    </a>
                                </td>

                                <td>
                                    <fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${order.orderStatus == 'Hoàn thành'}">
                                            <span class="status completed">${order.orderStatus}</span>
                                        </c:when>
                                        <c:when test="${order.orderStatus == 'Đã hủy'}">
                                            <span class="status canceled">${order.orderStatus}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="status pending">${order.orderStatus}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="đ"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:if test="${totalPages > 1}">
                    <div class="pagination-container">
                            <%-- Nút Previous --%>
                        <c:if test="${currentPage > 1}">
                            <a href="${pageContext.request.contextPath}/admin/customer-detail?id=${customer.id}&page=${currentPage - 1}" class="pagination-btn">
                                <i class="fa-solid fa-chevron-left"></i>
                            </a>
                        </c:if>

                            <%-- Vòng lặp số trang --%>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="${pageContext.request.contextPath}/admin/customer-detail?id=${customer.id}&page=${i}"
                               class="page-number ${i == currentPage ? 'active' : ''}">
                                    ${i}
                            </a>
                        </c:forEach>

                            <%-- Nút Next --%>
                        <c:if test="${currentPage < totalPages}">
                            <a href="${pageContext.request.contextPath}/admin/customer-detail?id=${customer.id}&page=${currentPage + 1}" class="pagination-btn">
                                <i class="fa-solid fa-chevron-right"></i>
                            </a>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </c:if>
        <c:if test="${empty customer}">
            <p>Không tìm thấy khách hàng.</p>
        </c:if>


    </div>
</main>

</body>
<script src="${pageContext.request.contextPath}/admin/adminjs/adminHoaDon.js"></script>
<script src="${pageContext.request.contextPath}/admin/adminjs/adminNotification.js"></script>
<script src="${pageContext.request.contextPath}/admin/adminjs/adminUpdateCustomer.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 1. Lấy tất cả các thông báo (cả success và error)
        var alerts = document.querySelectorAll(".alert-box");

        alerts.forEach(function(alertBox) {
            // --- LOGIC 1: TỰ ĐỘNG TẮT SAU 5 GIÂY ---
            var autoCloseTimer = setTimeout(function() {
                closeAlert(alertBox);
            }, 5000);

            // --- LOGIC 2: BẤM NÚT X ĐỂ TẮT NGAY ---
            var closeBtn = alertBox.querySelector(".close-btn");
            if (closeBtn) {
                closeBtn.addEventListener("click", function() {
                    // Xóa hẹn giờ tự động (để tránh conflict)
                    clearTimeout(autoCloseTimer);
                    // Đóng ngay lập tức
                    closeAlert(alertBox);
                });
            }
        });

        // Hàm dùng chung để làm mờ và xóa element
        function closeAlert(box) {
            if (box.style.display !== 'none') {
                box.style.transition = "opacity 0.5s ease";
                box.style.opacity = "0"; // Mờ dần

                // Đợi 0.5s (500ms) cho mờ hẳn rồi mới xóa khỏi DOM
                setTimeout(function() {
                    box.remove();
                }, 500);
            }
        }
    });
</script>

</html>