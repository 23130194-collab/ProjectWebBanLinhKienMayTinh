<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechNova Admin - Banner</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminNotification.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/headerAndSidebar.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminBanners.css">
    <link rel="stylesheet" href="${contextPath}/admin/admincss/adminBrands.css">
</head>
<body>

<aside class="sidebar">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/admin/dashboard">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
        </a>
        <a href="${pageContext.request.contextPath}/admin/dashboard" style="text-decoration: none;">
            <span class="logo-text">TechNova</span>
        </a>
    </div>
    <ul class="nav-menu">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-border-all"></i></span>Dashboard</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/customers" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-users"></i></span>Khách hàng</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/categories" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-list"></i></span>Mục sản phẩm</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/brands" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-certificate"></i></span>Thương hiệu</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin-attributes" class="nav-link active"><span class="nav-icon"><i class="fa-solid fa-sliders"></i></span>Thuộc tính</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/products" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-box-open"></i></span>Sản phẩm</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin/orders" class="nav-link"><span class="nav-icon"><i class="fa-solid fa-clipboard-list"></i></span>Đơn hàng</a></li>
    </ul>
    <div class="logout-section"><a href="${pageContext.request.contextPath}/logout" class="nav-link logout-link"><span class="nav-icon"><i class="fa-solid fa-right-from-bracket"></i></span>Đăng xuất</a></div>
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
            <div>
                <h1 class="page-title">Quản Lý Banner</h1>
                <div class="breadcrumb">
                    <a href="${contextPath}/admin/dashboard" class="breadcrumb-link">Trang chủ</a>
                    <span>/</span>
                    <span class="breadcrumb-current">Banner</span>
                </div>
            </div>
        </div>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success" id="successAlert">
                <span>${sessionScope.message}</span>
                <span class="close-btn" onclick="this.parentElement.style.display='none';">&times;</span>
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <form action="${contextPath}/admin/banners" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="${not empty bannerToEdit ? 'update' : 'create'}">
            <c:if test="${not empty bannerToEdit}">
                <input type="hidden" name="id" value="${bannerToEdit.id}">
            </c:if>

            <div class="banner-form-grid">
                <div class="form-group col-span-1">
                    <label>Tên banner</label>
                    <input type="text" name="name" placeholder="Nhập tên banner" value="${bannerToEdit.name}" required>
                </div>

                <div class="form-group col-span-1">
                    <label>Thời gian bắt đầu</label>
                    <c:set var="startDate" value="${bannerToEdit.start_time}" />
                    <c:if test="${not empty startDate && startDate.length() > 10}">
                        <c:set var="startDate" value="${startDate.substring(0, 10)}" />
                    </c:if>
                    <input type="date" name="start_time" value="${startDate}" required>
                </div>

                <div class="form-group col-span-1">
                    <label>Thời gian kết thúc</label>
                    <c:set var="endDate" value="${bannerToEdit.end_time}" />
                    <c:if test="${not empty endDate && endDate.length() > 10}">
                        <c:set var="endDate" value="${endDate.substring(0, 10)}" />
                    </c:if>
                    <input type="date" name="end_time" value="${endDate}" required>
                </div>

                <div class="form-group col-span-2">
                    <label>Vị trí hiển thị</label>
                    <select name="position">
                        <option value="">-- Chọn vị trí --</option>
                        <option value="Trang chủ" ${bannerToEdit.position == 'Trang chủ' ? 'selected' : ''}>Trang chủ</option>
                        <option value="Slider Chính" ${bannerToEdit.position == 'Slider Chính' ? 'selected' : ''}>Slider Chính</option>
                        <option value="CPU" ${bannerToEdit.position == 'CPU' ? 'selected' : ''}>Danh mục CPU</option>
                        <option value="VGA" ${bannerToEdit.position == 'VGA' ? 'selected' : ''}>Danh mục VGA</option>
                        <option value="RAM" ${bannerToEdit.position == 'RAM' ? 'selected' : ''}>Danh mục RAM</option>
                        <option value="Sidebar" ${bannerToEdit.position == 'Sidebar' ? 'selected' : ''}>Sidebar (Cột bên)</option>
                    </select>
                </div>

                <div class="form-group col-span-1">
                    <label>Thứ tự hiển thị</label>
                    <input type="number"
                           name="display_order"
                           placeholder="Nhập thứ tự"
                           value="${not empty bannerToEdit ? bannerToEdit.display_order : ''}"
                           min="1" required>
                </div>

                <div class="form-group col-span-3">
                    <label>Thêm hình ảnh banner</label>
                    <input type="file" name="imageFile" accept="image/*" style="line-height: 20px;">

                    <c:if test="${not empty bannerToEdit.image}">
                        <div style="margin-top: 10px; border: 1px dashed #ccc; padding: 5px; display: inline-block;">
                            <span style="font-size: 12px; color: #666;">Ảnh hiện tại:</span><br>
                            <img src="${bannerToEdit.image}" height="60" alt="Current Banner" style="margin-top: 5px;">
                        </div>
                    </c:if>
                </div>

                <div class="form-action-row">
                    <div class="form-group link-group">
                        <input type="text" name="image" placeholder="Hoặc nhập link ảnh online..."
                               value="${bannerToEdit.image.startsWith('http') ? bannerToEdit.image : ''}">
                    </div>

                    <button type="submit" class="btn-add-banner">
                        <i ></i> ${not empty bannerToEdit ? 'Cập Nhật' : 'Thêm Banner mới'}
                    </button>

                    <c:if test="${not empty bannerToEdit}">
                        <a href="${contextPath}/admin/banners" class="btn-add-banner" style="background: #334155; text-decoration: none; display: flex; align-items: center; justify-content: center;">
                            <i class="fa-solid fa-xmark" style="margin-right: 7px;"></i> Hủy
                        </a>
                    </c:if>
                </div>
            </div>
        </form>

        <form action="${contextPath}/admin/banners" method="get">
            <div class="form-search-row">
                <div class="search-wrapper">
                    <i class="fa-solid fa-magnifying-glass search-icon"></i>
                    <input type="text" name="keyword" class="search-input" placeholder="Tìm kiếm banner..." value="${param.keyword}">
                </div>
            </div>
        </form>

        <div class="banner-table-container">
            <table class="banner-table">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Hình ảnh</th>
                    <th style="text-align: left;">Tên banner</th>
                    <th>Vị trí</th>
                    <th>Thứ tự</th>
                    <th>Thời gian</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="b" items="${banners}" varStatus="status">
                    <tr>
                        <td>${(currentPage - 1) * 10 + status.count}</td>
                        <td>
                            <img src="${b.image}" class="banner-img-preview" alt="Banner"
                                 onerror="this.src='https://via.placeholder.com/100x60?text=No+Image'">
                        </td>
                        <td style="text-align: left; font-weight: 600;">${b.name}</td>
                        <td><span class="status status-active" style="background: #f1f5f9; color: #334155;">${b.position}</span></td>
                        <td>${b.display_order}</td>
                        <td>
                            <div class="time-range">
                                <i class="fa-regular fa-calendar"></i> ${b.start_time}<br>
                                <i class="fa-solid fa-arrow-down-long" style="font-size: 10px; margin: 2px 0;"></i><br>
                                <i class="fa-regular fa-calendar-check"></i> ${b.end_time}
                            </div>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="${contextPath}/admin/banners?action=edit&id=${b.id}" class="action-btn edit"><i class="fa-solid fa-pen"></i></a>
                                <a href="${contextPath}/admin/banners?action=delete&id=${b.id}" class="action-btn delete"
                                   onclick="return confirm('Xóa banner này?')"><i class="fa-solid fa-trash"></i></a>
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
                    <a href="${contextPath}/admin/banners?page=${currentPage - 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-left"></i></a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${contextPath}/admin/banners?page=${i}&keyword=${keyword}" class="page-number ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${contextPath}/admin/banners?page=${currentPage + 1}&keyword=${keyword}" class="pagination-btn"><i class="fa-solid fa-chevron-right"></i></a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>
<script src="${contextPath}/admin/adminjs/adminNotification.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var alertBox = document.getElementById("successAlert");
        if (alertBox) {
            setTimeout(function() {
                if (alertBox.style.display !== 'none') {
                    // Hiệu ứng mờ dần
                    alertBox.style.transition = "opacity 0.5s ease";
                    alertBox.style.opacity = "0";
                    setTimeout(function() {
                        alertBox.remove();
                    }, 500);
                }
            }, 5000);
        }
    });
</script>
</body>
</html>