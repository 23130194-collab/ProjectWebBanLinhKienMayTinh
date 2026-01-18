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
    <title>TechNova Admin - Danh sách sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminProductList.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminNotification.css">
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
            <a href="${contextPath}/admin/brands" class="nav-link">
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
            <a href="${contextPath}/admin-product-list" class="nav-link active">
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
        <a href="${contextPath}/logout" class="nav-link logout-link">
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

            <a href="${contextPath}/admin-upload-product" class="add-product-btn" title="Thêm sản phẩm mới">
                <i class="fa-solid fa-plus"></i>
            </a>
        </div>

        <div class="filter-bar">
            <form action="${contextPath}/admin-product-list" method="get" id="filterForm" class="filter-left">
                <div class="filter-item">
                    <div class="select-wrapper">
                        <select name="categoryId" id="category-select" onchange="this.form.submit()">
                            <option value="">Tất cả danh mục</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}" ${category.id == selectedCategoryId ? 'selected' : ''}>
                                        ${category.name}
                                </option>
                            </c:forEach>
                        </select>
                        <i class="fa-solid fa-chevron-down select-arrow"></i>
                    </div>
                </div>

                <div class="filter-item">
                    <div class="select-wrapper">
                        <select name="status" id="status-select" onchange="this.form.submit()">
                            <option value="">Tất cả trạng thái</option>
                            <option value="active" ${'active' == selectedStatus ? 'selected' : ''}>Hoạt động</option>
                            <option value="inactive" ${'inactive' == selectedStatus ? 'selected' : ''}>Ẩn</option>
                        </select>
                        <i class="fa-solid fa-chevron-down select-arrow"></i>
                    </div>
                </div>
                <div class="search-wrapper">
                    <input type="text" name="keyword" class="search-input-product" placeholder="Tìm kiếm sản phẩm..." value="${selectedKeyword}">
                    <button class="search-btn" type="submit">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </div>
            </form>

        </div>

        <div class="product-table-container">
            <table class="product-table">
                <thead>
                <tr>
                    <th style="width: 50px;">STT</th>
                    <th style="width: 100px;">Hình ảnh</th>
                    <th style="width: 170px">Sản phẩm</th>
                    <th style="width: 125px;">Giá bán</th>
                    <th style="width: 100px;">Tồn kho</th>
                    <th style="width: 120px;">Trạng thái</th>
                    <th style="width: 150px;">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productList}" varStatus="loop">
                    <tr>
                        <td>${(currentPage - 1) * itemsPerPage + loop.index + 1}</td>                        <td class="td-image">
                            <div class="img-wrapper">
                                <img src="${product.image}" alt="${product.name}" onerror="this.src='https.i.postimg.cc/Hn4Jc3yj/logo-2.png';">
                            </div>
                        </td>
                        <td class="td-name">
                            <span class="product-name" title="${product.name}">${product.name}</span>
                        </td>
                        <td class="td-price">
                            <div class="price-group">
                                <span class="current-price"><fmt:formatNumber value="${product.price}" type="number" pattern="#,##0" />đ</span>
                                <c:if test="${product.oldPrice > 0 && product.oldPrice > product.price}">
                                    <span class="old-price"><fmt:formatNumber value="${product.oldPrice}" type="number" pattern="#,##0" />đ</span>
                                </c:if>
                            </div>
                        </td>
                        <td><span class="stock-text">${product.stock}</span></td>
                        <td>
                            <c:choose>
                                <c:when test="${product.status == 'active'}">
                                    <span class="status status-active">Hoạt động</span>
                                </c:when>
                                <c:when test="${product.status == 'inactive'}">
                                    <span class="status status-hidden">Ẩn</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status status-other">${product.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="${contextPath}/admin-upload-product?id=${product.id}" class="action-btn edit" title="Sửa"><i class="fa-solid fa-pen"></i></a>
                                <a href="${contextPath}/admin-product-list?action=delete&id=${product.id}" class="action-btn delete" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');"><i class="fa-solid fa-trash-can"></i></a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%-- Phần Pagination --%>
        <c:if test="${totalPages > 1}">
            <div class="pagination-container">
                    <%-- Nút Trang Trước --%>
                <c:url var="prevUrl" value="/admin-product-list">
                    <c:param name="page" value="${currentPage - 1}" />
                    <c:if test="${not empty selectedCategoryId}"><c:param name="categoryId" value="${selectedCategoryId}" /></c:if>
                    <c:if test="${not empty selectedStatus}"><c:param name="status" value="${selectedStatus}" /></c:if>
                    <c:if test="${not empty selectedKeyword}"><c:param name="keyword" value="${selectedKeyword}" /></c:if>
                    <c:if test="${not empty selectedSort}"><c:param name="sort" value="${selectedSort}" /></c:if>
                </c:url>
                <a href="${currentPage > 1 ? prevUrl : '#'}" class="pagination-btn ${currentPage == 1 ? 'disabled' : ''}">
                    <i class="fa-solid fa-chevron-left"></i>
                </a>

                    <%-- Các Nút Số Trang --%>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <c:url var="pageUrl" value="/admin-product-list">
                        <c:param name="page" value="${i}" />
                        <c:if test="${not empty selectedCategoryId}"><c:param name="categoryId" value="${selectedCategoryId}" /></c:if>
                        <c:if test="${not empty selectedStatus}"><c:param name="status" value="${selectedStatus}" /></c:if>
                        <c:if test="${not empty selectedKeyword}"><c:param name="keyword" value="${selectedKeyword}" /></c:if>
                        <c:if test="${not empty selectedSort}"><c:param name="sort" value="${selectedSort}" /></c:if>
                    </c:url>
                    <a href="${pageUrl}" class="page-number ${currentPage == i ? 'active' : ''}">${i}</a>
                </c:forEach>

                    <%-- Nút Trang Sau (Làm tương tự nút trước) --%>
                <c:url var="nextUrl" value="/admin-product-list">
                    <c:param name="page" value="${currentPage + 1}" />
                    <c:if test="${not empty selectedCategoryId}"><c:param name="categoryId" value="${selectedCategoryId}" /></c:if>
                    <c:if test="${not empty selectedStatus}"><c:param name="status" value="${selectedStatus}" /></c:if>
                    <c:if test="${not empty selectedKeyword}"><c:param name="keyword" value="${selectedKeyword}" /></c:if>
                    <c:if test="${not empty selectedSort}"><c:param name="sort" value="${selectedSort}" /></c:if>
                </c:url>
                <a href="${currentPage < totalPages ? nextUrl : '#'}" class="pagination-btn ${currentPage == totalPages ? 'disabled' : ''}">
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
            </div>
        </c:if>

    </div>
</main>
</body>
<script src="${contextPath}/admin/adminjs/adminNotification.js"></script>
<script src="${contextPath}/admin/adminjs/adminProductList.js"></script>
</html>