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
        <li class="nav-item"><a href="adminDashboard.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>Dashboard</a></li>
        <li class="nav-item"><a href="customersList.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-users"></i></span>Khách hàng</a></li>
        <li class="nav-item"><a href="adminCategories.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-list"></i></span>Mục sản phẩm</a></li>
        <li class="nav-item"><a href="brands" class="nav-link active"><span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>Thương hiệu</a></li>
        <li class="nav-item"><a href="adminAttributes.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>Thuộc tính</a></li>
        <li class="nav-item"><a href="adminProductList.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>Sản phẩm</a></li>
        <li class="nav-item"><a href="adminHoaDon.jsp" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>Đơn hàng</a></li>
    </ul>

    <div class="logout-section">
        <a href="../logout" class="nav-link logout-link"><span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>Đăng xuất</a>
    </div>
</aside>

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
            <div class="notification-footer"><a href="adminAllNotification.jsp" class="see-all-link">Xem tất cả thông báo</a></div>
        </div>
        <div class="user-profile">
            <img src="https://www.shutterstock.com/image-vector/admin-icon-strategy-collection-thin-600nw-2307398667.jpg" alt="User Profile">
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
                    <a href="adminDashboard.jsp" class="breadcrumb-link">Trang chủ</a>
                    <span>/</span>
                    <span class="breadcrumb-current">Thương hiệu</span>
                </div>
            </div>
        </div>

        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>

        <form action="brands" method="post" id="brandForm" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${brandToEdit.id}">

            <div class="brand-form">
                <div class="form-group">
                    <label class="form-label">Tên thương hiệu</label>
                    <input type="text" name="name" placeholder="Nhập tên thương hiệu" value="${brandToEdit.name}" required>
                </div>
                <div class="form-group">
                    <label class="form-label">Thứ tự hiển thị</label>
                    <input type="number" name="displayOrder" placeholder="Nhập thứ tự" value="${brandToEdit != null ? brandToEdit.displayOrder : ''}" required min="1">
                </div>
                <div class="form-group">
                    <label class="form-label">Trạng thái hoạt động</label>
                    <div class="custom-select-wrapper">
                        <select name="status">
                            <option value="Hoạt động" ${brandToEdit.status == 'Hoạt động' ? 'selected' : ''}>Hoạt động</option>
                            <option value="Ẩn" ${brandToEdit.status == 'Ẩn' ? 'selected' : ''}>Ẩn</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="image-section">
                <label class="form-label">Hình ảnh thương hiệu</label>
                <div class="image-input-group">
                    <input type="file" id="image-upload" name="logoFile" class="image-input-box">
                </div>
                <div class="image-input-group">
                    <div class="url-input-group">
                        <input type="text" id="image-url" name="logo" class="image-input-box" placeholder="https://example.com/image.png" value="${brandToEdit.logo}">
                        <button type="submit" class="add-brand-submit">
                            <c:choose>
                                <c:when test="${brandToEdit != null}">Cập nhật</c:when>
                                <c:otherwise>Thêm thương hiệu</c:otherwise>
                            </c:choose>
                        </button>
                    </div>

                </div>
            </div>
            <img id="image-preview"
                 src="${not empty brandToEdit.logo ? pageContext.request.contextPath.concat('/').concat(brandToEdit.logo) : '#'}"
                 alt="Image Preview"
                 style="max-width: 200px; max-height: 200px; margin-top: 10px; display: ${not empty brandToEdit.logo ? 'block' : 'none'};"/>
            <!-- Tìm kiếm -->
            <div class="form-search-row">
                <div class="search-wrapper">
                    <i class="fa-solid fa-magnifying-glass search-icon"></i>
                    <input type="text" id="searchInput" class="search-input-brand" placeholder="Tìm kiếm thương hiệu" value="${keyword}">
                </div>
            </div>
        </form>

        <!-- Danh sách -->
        <div class="brand-table-container">
            <table class="brand-table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Hình ảnh</th>
                    <th>Tên thương hiệu</th>
                    <th>Thứ tự hiển thị</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="brand" items="${brands}" varStatus="loop">
                    <tr>
                        <td>${(currentPage - 1) * 10 + loop.count}</td>
                        <td>
                            <c:set var="logoUrl" value="${brand.logo}" />
                            <c:choose>
                                <c:when test="${logoUrl.startsWith('http')}">
                                    <img src="${logoUrl}" width="55" alt="${brand.name}" onerror="this.style.display='none'">
                                </c:when>
                                <c:when test="${not empty logoUrl}">
                                    <img src="${pageContext.request.contextPath}/${logoUrl}" width="55" alt="${brand.name}" onerror="this.style.display='none'">
                                </c:when>
                            </c:choose>
                        </td>
                        <td><c:out value="${brand.name}" /></td>
                        <td><c:out value="${brand.displayOrder}" /></td>
                        <td>
                                <span class="status ${brand.status == 'Hoạt động' ? 'status-active' : 'status-hidden'}">
                                    <c:out value="${brand.status}" />
                                </span>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="brands?action=edit&id=${brand.id}" class="action-btn edit"><i class="fa-solid fa-pen"></i></a>
                                <a href="brands?action=delete&id=${brand.id}" class="action-btn delete" onclick="return confirm('Bạn có chắc chắn muốn xóa thương hiệu này không?')">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Phân trang -->
        <c:if test="${totalPages > 1}">
            <div class="pagination-container">
                <c:if test="${currentPage > 1}">
                    <a href="brands?page=${currentPage - 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-left"></i></a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="brands?page=${i}&keyword=${keyword}" class="page-number ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="brands?page=${currentPage + 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-right"></i></a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>
</body>
<script src="../adminjs/adminNotification.js"></script>
<script>
    document.getElementById('searchInput').addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            var keyword = this.value;
            window.location.href = 'brands?keyword=' + encodeURIComponent(keyword);
        }
    });
</script>
</html>