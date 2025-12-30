<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Danh mục sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/adminNotification.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admincss/adminCategories.css">
</head>

<body>

<!-- Sidebar -->
<aside class="sidebar">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
        </a>
        <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp" style="text-decoration: none;">
            <span class="logo-text">TechNova</span>
        </a></div>

    <ul class="nav-menu">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>
                Dashboard
            </a>
        </li>

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/customersList.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-users"></i></span>
                Khách hàng
            </a>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin-categories" class="nav-link active">
                <span class="nav-icon"><i class="fa-solid fa-list"></i></span>
                Mục sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/adminBrands.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>
                Thương hiệu
            </a>
        </li>

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/adminAttributes.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>
                Thuộc tính
            </a>
        </li>

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/adminProductList.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>
                Sản phẩm
            </a>
        </li>

        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/admin/adminHoaDon.jsp" class="nav-link">
                <span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>
                Đơn hàng
            </a>
        </li>
        <%-- Các mục nav khác --%>
    </ul>
    <div class="logout-section">
        <a href="${pageContext.request.contextPath}/logout" class="nav-link logout-link">
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

        <div class="notification-dropdown" id="notificationDropdown"></div>

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
                <h1 class="page-title">Mục sản phẩm</h1>
                <div class="breadcrumb">
                    <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp" class="breadcrumb-link">Trang chủ</a>
                    <span class="breadcrumb-separator">/</span>
                    <span class="breadcrumb-current">Danh mục</span>
                </div>
            </div>
        </div>

        <!-- Hiển thị thông báo (nếu có) -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success"><c:out value="${sessionScope.successMessage}" escapeXml="false"/></div>
            <c:remove var="successMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger"><c:out value="${sessionScope.errorMessage}" escapeXml="false"/></div>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>
        <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger"><c:out value="${requestScope.errorMessage}" escapeXml="false"/></div>
        </c:if>

        <!-- Form thêm/sửa danh mục -->
        <form action="${pageContext.request.contextPath}/admin-categories" method="post" enctype="multipart/form-data" class="category-form" id="addCategoryForm">
            <c:if test="${not empty categoryToEdit}">
                <input type="hidden" name="categoryId" value="${categoryToEdit.id}">
            </c:if>
            <div class="form-row">
                <div class="input-group flex-2">
                    <label class="input-label">Tên mục sản phẩm</label>
                    <input type="text" name="categoryName" class="input-field" placeholder="Tên mục sản phẩm" value="${categoryToEdit.name}">
                </div>
                <div class="input-group flex-1">
                    <label class="input-label">Thứ tự hiển thị</label>
                    <input type="number" name="displayOrder" class="input-field" placeholder="Thứ tự" value="${categoryToEdit.display_order}">
                </div>
                <div class="input-group flex-1">
                    <label class="input-label">Trạng thái</label>
                    <select name="status" class="input-field">
                        <option value="active" ${categoryToEdit.status == 'active' ? 'selected' : ''}>Hoạt động</option>
                        <option value="hidden" ${categoryToEdit.status == 'hidden' ? 'selected' : ''}>Ẩn</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="input-group flex-full">
                    <label class="input-label">Hình ảnh mục sản phẩm</label>
                    <input type="file" name="imageFile" class="input-field file-input">

                </div>
            </div>
            <div class="form-row" style="align-items: center;">

                <div class="input-group" style="flex: 1;">
                    <input type="text" name="imageUrl" class="input-field" placeholder="Hoặc dán đường dẫn hình ảnh mới tại đây" value="">
                </div>

                <div class="button-group" style="margin-left: 15px; width: auto;">
                    <button type="submit" class="add-category-btn">
                        <c:choose>
                            <c:when test="${not empty categoryToEdit}">Cập nhật</c:when>
                            <c:otherwise>Thêm danh mục</c:otherwise>
                        </c:choose>
                    </button>
                    <c:if test="${not empty categoryToEdit}">
                        <a href="${pageContext.request.contextPath}/admin-categories" class="cancel-btn">Hủy</a>
                    </c:if>
                </div>
            </div>
            <c:if test="${not empty categoryToEdit.image}">
                <div class="current-image-preview" style="margin-top: 10px; display: flex; align-items: center;">
                    <span style="font-size: 14px; color: #64748b; margin-right: 10px;">Ảnh hiện tại:</span>

                    <c:choose>
                        <%-- Trường hợp 1: Ảnh là link online (bắt đầu bằng http) --%>
                        <c:when test="${categoryToEdit.image.startsWith('http')}">
                            <img src="${categoryToEdit.image}" alt="Preview"
                                 style="height: 40px; border-radius: 4px; border: 1px solid #e2e8f0;">
                        </c:when>

                        <%-- Trường hợp 2: Ảnh lưu trong server (local) --%>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/${categoryToEdit.image}" alt="Preview"
                                 style="height: 40px; border-radius: 4px; border: 1px solid #e2e8f0;">
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </form>

        <!-- Form tìm kiếm -->
        <form action="${pageContext.request.contextPath}/admin-categories" method="get" class="form-search-row" id="searchForm">
            <div class="search-wrapper">
                <input type="text" name="searchKeyword" class="search-input-category" placeholder="Tìm kiếm mục sản phẩm..." value="${searchKeyword}">
                <a href="#" class="search-icon-btn" id="searchBtn"><i class="fa-solid fa-magnifying-glass"></i></a>
            </div>
        </form>

        <!-- Bảng danh mục -->
        <div class="category-table-container">
            <table class="category-table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Hình ảnh</th>
                    <th>Tên mục sản phẩm</th>
                    <th>Thứ tự</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="cat" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>
                            <c:set var="imageSrc" value="${cat.image}"/>
                            <c:choose>
                                <c:when test="${imageSrc.startsWith('http')}">
                                    <img src="${imageSrc}" alt="${cat.name}" class="table-image">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/${imageSrc}" alt="${cat.name}" class="table-image">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${cat.name}</td>
                        <td>${cat.display_order}</td>
                        <td>
                            <c:choose>
                                <c:when test="${cat.status.trim().equalsIgnoreCase('active')}">
                                    <span class="status status-active">Hoạt động</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="status status-hidden">Ẩn</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="${pageContext.request.contextPath}/admin-categories?action=edit&id=${cat.id}" class="action-btn edit" title="Sửa"><i class="fa-solid fa-pen"></i></a>
                                <a href="${pageContext.request.contextPath}/admin-categories?action=delete&id=${cat.id}" class="action-btn delete" title="Xóa" onclick="return confirmDelete();"><i class="fa-solid fa-trash-can"></i></a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>

<script>
    function confirmDelete() {
        return confirm("Bạn có chắc chắn muốn xóa danh mục này không?");
    }
    document.addEventListener('DOMContentLoaded', function() {
        // Tự động ẩn thông báo
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            setTimeout(function() {
                alert.style.display = 'none';
            }, 5000);
        });

        // Xử lý nút tìm kiếm
        const searchBtn = document.getElementById('searchBtn');
        if(searchBtn) {
            searchBtn.addEventListener('click', function(e) {
                e.preventDefault(); // Ngăn thẻ <a> chuyển trang
                document.getElementById('searchForm').submit(); // Submit form tìm kiếm
            });
        }
    });
</script>

</body>
</html>
