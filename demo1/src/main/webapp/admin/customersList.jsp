<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Danh sách khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/customersList.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
        <li class="nav-item"><a href="${contextPath}/admin/customers" class="nav-link active"><span class="nav-icon"><i class="fa-solid fa-users"></i></span>Khách hàng</a></li>
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
        <div class="page-header">
            <h1 class="page-title">Danh sách khách hàng</h1>
            <a href="${pageContext.request.contextPath}/admin/adminThemKhachHang.jsp" class="add-customer-btn">
                <i class="fa-solid fa-user-plus"></i> Thêm khách hàng
            </a>
        </div>

        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp" class="breadcrumb-link">Trang chủ</a>
            <span class="breadcrumb-separator">/</span>
            <span class="breadcrumb-current">Danh sách khách hàng</span>
        </div>

        <div class="filter-section" style="margin-top: 15px; margin-bottom: 15px;">
            <form action="${contextPath}/admin/customers" method="get">
<%--                <label for="statusSelect" style="font-weight: bold; margin-right: 10px;">Lọc theo trạng thái:</label>--%>

                <select name="status" id="statusSelect" onchange="this.form.submit()"
                        style="padding: 8px 12px; border-radius: 5px; border: 1px solid #ccc; font-size: 14px; min-width: 150px;">

                    <option value="all" ${param.status == 'all' || empty param.status ? 'selected' : ''}>
                        -- Tất cả --
                    </option>

                    <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>
                        Đang hoạt động
                    </option>

                    <option value="Locked" ${param.status == 'Locked' ? 'selected' : ''}>
                        Đã khóa
                    </option>
                </select>

                <%-- Giữ lại từ khóa tìm kiếm nếu đang tìm --%>
                <input type="hidden" name="keyword" value="${param.keyword}">
            </form>
        </div>



        <div class="customer-list">
            <table class="customer-table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Đơn hàng</th>
                    <th>Tham gia</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody id="customerTableBody">
                <%-- Kiểm tra nếu danh sách rỗng thì hiện thông báo --%>
                <c:if test="${empty customerList}">
                    <tr>
                        <td colspan="6" style="text-align: center;">Không có dữ liệu khách hàng nào trong Database.</td>
                    </tr>
                </c:if>

                <%-- Vòng lặp duyệt danh sách customerList được gửi từ Controller --%>
                <c:forEach var="u" items="${customerList}" varStatus="status">
                    <tr>
                        <td style="text-align: center;">${status.index + 1}</td>

                        <td>
                            <a href="${pageContext.request.contextPath}/admin/customer-detail?id=${u.id}" class="customer-link">
                                <div class="reviewer-avatar">
                                        ${u.name != null ? u.name.substring(0, 1).toUpperCase() : "?"}
                                </div>
                                    ${u.name}
                            </a>
                        </td>

                        <td>${u.email}</td>

                        <td style="text-align: center;">${u.orderCount}</td>

                        <td>
                            <fmt:formatDate value="${u.created_at}" pattern="dd/MM/yyyy"/>
                        </td>

<%--                        <td>--%>
<%--                            <div class="action-buttons">--%>
<%--                                <a href="${pageContext.request.contextPath}/admin/customer-detail?id=${u.id}" class="action-btn edit" title="Sửa">--%>
<%--                                    <i class="fa-solid fa-pen"></i>--%>
<%--                                </a>--%>
<%--                                <a href="#" class="action-btn delete" title="Khóa" onclick="return confirm('Bạn có chắc muốn khóa user này?')">--%>
<%--                                    <i class="fa-solid fa-lock"></i>--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                        </td>--%>
                        <td>
                            <div class="action-buttons">
                                <a href="${contextPath}/admin/customer-detail?id=${u.id}" class="action-btn edit" title="Sửa">
                                    <i class="fa-solid fa-pen"></i>
                                </a>

                                <a href="${contextPath}/admin/lock-customer?id=${u.id}"
                                   class="action-btn delete"
                                   style="background-color: ${u.status == 'Locked' ? '#fee2e2' : '#e0f2fe'};
                                           color: ${u.status == 'Locked' ? '#b91c1c' : '#0284c7'};"
                                   title="${u.status == 'Locked' ? 'Mở khóa' : 'Khóa tài khoản'}"
                                   onclick="return confirm('Bạn có chắc muốn ${u.status == 'Locked' ? 'mở khóa' : 'khóa'} tài khoản này?')">

                                    <c:choose>
                                        <c:when test="${u.status == 'Locked'}">
                                            <i class="fa-solid fa-lock"></i> </c:when>
                                        <c:otherwise>
                                            <i class="fa-solid fa-lock-open"></i> </c:otherwise>
                                    </c:choose>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:if test="${totalPages > 1}">
                <div class="pagination-container">

                        <%-- Nút Previous --%>
                    <c:if test="${currentPage > 1}">
                        <a href="${contextPath}/admin/customers?page=${currentPage - 1}&keyword=${keyword}&status=${param.status}" class="pagination-btn">
                            <i class="fa-solid fa-chevron-left"></i>
                        </a>
                    </c:if>

                        <%-- Các số trang --%>
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="${contextPath}/admin/customers?page=${i}&keyword=${keyword}&status=${param.status}"
                           class="page-number ${currentPage == i ? 'active' : ''}">
                                ${i}
                        </a>
                    </c:forEach>

                        <%-- Nút Next --%>
                    <c:if test="${currentPage < totalPages}">
                        <a href="${contextPath}/admin/customers?page=${currentPage + 1}&keyword=${keyword}&status=${param.status}" class="pagination-btn">
                            <i class="fa-solid fa-chevron-right"></i>
                        </a>
                    </c:if>

                </div> </c:if>
        </div>
    </div>
</main>
</body>
<script src="${pageContext.request.contextPath}/admin/adminjs/adminNotification.js"></script>
</html>