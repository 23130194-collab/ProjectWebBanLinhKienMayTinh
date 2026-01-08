<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Thuộc tính</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminAttributes.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminNotification.css">

</head>

<body>

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
        <li class="nav-item"><a href="${contextPath}/admin/customers" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-users"></i></span>Khách hàng</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/categories" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-list"></i></span>Mục sản phẩm</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/brands" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>Thương hiệu</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/attributes" class="nav-link active"><span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>Thuộc tính</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/banners" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-images"></i></span>Banner</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/products" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>Sản phẩm</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/orders" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>Đơn hàng</a></li>
        <li class="nav-item"><a href="${contextPath}/admin/reviews" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-star"></i></span>Đánh giá</a></li>

    </ul>
    <div class="logout-section"><a href="${contextPath}/logout" class="nav-link logout-link"><span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>Đăng xuất</a></div>
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
                <a href="${contextPath}/admin/notifications" class="see-all-link">Xem tất cả thông báo</a>
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
        <div class="page-title">Thuộc tính</div>
        <div class="breadcrumb">
            <a href="${contextPath}/admin/dashboard" class="breadcrumb-link">Trang chủ</a>
            <span class="breadcrumb-separator">/</span>
            <span class="breadcrumb-current">Thuộc tính</span>
        </div>

        <%--HIỂN THỊ THÔNG BÁO --%>
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                <span class="close-btn" onclick="this.parentElement.style.display='none';">&times;</span>
                <c:choose>
                    <c:when test="${param.success == 'add'}">Thêm thuộc tính mới thành công!</c:when>
                    <c:when test="${param.success == 'update'}">Cập nhật thuộc tính thành công!</c:when>
                    <c:when test="${param.success == 'delete'}">Xóa thuộc tính thành công!</c:when>
                </c:choose>
            </div>
        </c:if>
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                <span class="close-btn" onclick="this.parentElement.style.display='none';">&times;</span>
                <c:choose>
                    <c:when test="${param.error == 'name_required'}">Lỗi: Tên thuộc tính không được để trống.</c:when>
                    <c:when test="${param.error == 'invalid_id'}">Lỗi: ID không hợp lệ.</c:when>
                    <c:otherwise>Đã có lỗi xảy ra. Vui lòng thử lại.</c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                <span class="close-btn" onclick="this.parentElement.style.display='none';">&times;</span>
                    ${errorMessage}
            </div>
        </c:if>
        <%-- KẾT THÚC KHỐI THÔNG BÁO --%>


        <!-- FORM SECTION -->
        <div class="form-section">
            <form method="post" action="${contextPath}/admin/attributes">
                <input type="hidden" name="action" value="${not empty attributeToEdit ? 'update' : 'add'}">
                <input type="hidden" name="id" value="${attributeToEdit.id}">

                <div class="form-row">
                    <div class="form-item">
                        <label class="form-label">Tên thuộc tính</label>
                        <input type="text" name="name" placeholder="Nhập tên thuộc tính"
                               value="${not empty oldName ? oldName : attributeToEdit.name}" required>
                    </div>

                    <div class="form-item">
                        <label class="form-label">Danh mục</label>
                        <select name="category_id" required>
                            <option value="">Chọn danh mục</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}"
                                    ${(not empty oldCategoryId && category.id == oldCategoryId) || (category.id == attributeToEdit.categoryId) ? 'selected' : ''}>
                                        ${category.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row second-row">
                    <div class="form-item">
                        <label class="form-label">Thứ tự hiển thị</label>
                        <input type="number" name="display_order" placeholder="Nhập thứ tự" value="${not empty oldDisplayOrder ? oldDisplayOrder : (not empty attributeToEdit ? attributeToEdit.displayOrder : '')}"
                               min="1" required>
                    </div>

                    <div class="form-item">
                        <label class="form-label">Bộ lọc</label>
                        <input type="checkbox" class="checkbox-round" name="is_filterable" value="1"
                        ${(not empty oldIsFilterable && oldIsFilterable == 1) || (attributeToEdit.isFilterable == 1) ? 'checked' : ''}>
                    </div>

                    <div class="form-item">
                        <label class="form-label">Trạng thái</label>
                        <select name="status" required>
                            <option value="active" ${attributeToEdit.status == 'active' ? 'selected' : ''}>Hoạt động</option>
                            <option value="inactive" ${attributeToEdit.status == 'inactive' ? 'selected' : ''}>Ẩn</option>
                        </select>
                    </div>


                    <div class="form-item buttons-row" style="display: flex; flex-direction: row; gap: 8px; margin-left: auto;">
                        <c:choose>
                            <c:when test="${not empty attributeToEdit}">
                                <button type="submit" class="btn-update">
                                    <i class="fa-solid fa-save"></i> Cập nhật
                                </button>
                                <a href="${contextPath}/admin/attributes" class="btn-cancel">Hủy</a>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn-add">
                                    <i class="fa-solid fa-plus"></i> Thêm thuộc tính
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </form>
        </div>

        <!-- SEARCH -->
        <div class="top-actions">
             <form action="${contextPath}/admin/attributes" method="get" class="search-wrapper">
                 <i class="fa-solid fa-magnifying-glass search-icon"></i>
                <input type="text" name="keyword" class="search-input-brand" placeholder="Tìm kiếm thuộc tính" value="${keyword}">
                <button type="submit" style="display:none;"></button>
            </form>
        </div>

        <!-- TABLE -->
        <div class="table-container">
            <table class="table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên thuộc tính</th>
                    <th>Danh mục</th>
                    <th>Thứ tự hiển thị</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="attr" items="${attributes}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1 + (currentPage - 1) * 10}</td>
                        <td><c:out value="${attr.name}"/></td>
                        <td>
                            <c:forEach var="cat" items="${categories}">
                                <c:if test="${cat.id == attr.categoryId}">
                                    ${cat.name}
                                </c:if>
                            </c:forEach>
                        </td>

                        <td>${attr.displayOrder}</td>
                        <td>
                            <span class="badge ${attr.status == 'active' ? 'active-status' : 'inactive-status'}">
                                ${attr.status == 'active' ? 'Hoạt động' : 'Ẩn'}
                            </span>
                        </td>
                        <td class="actions">
                            <a href="${contextPath}/admin/attributes?action=edit&id=${attr.id}" class="btn-edit"><i class="fa-solid fa-pen"></i></a>
                            <form action="${contextPath}/admin/attributes" method="post" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa thuộc tính này không?');">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${attr.id}">
                                <button type="submit" class="btn-delete"><i class="fa-solid fa-trash"></i></button>
                            </form>
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
                    <a href="${contextPath}/admin/attributes?page=${currentPage - 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-left"></i></a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${contextPath}/admin/attributes?page=${i}&keyword=${keyword}" class="page-number ${currentPage == i ? 'active' : ''}">${i}</a>

                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${contextPath}/admin/attributes?page=${currentPage + 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-right"></i></a>
                </c:if>
            </div>
        </c:if>

    </div>
</main>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(function(alert) {
            setTimeout(function() {
                alert.style.opacity = '0';
                setTimeout(function() {
                    alert.style.display = 'none';
                }, 500);
            }, 5000);
        });
    });
</script>

</body>
</html>