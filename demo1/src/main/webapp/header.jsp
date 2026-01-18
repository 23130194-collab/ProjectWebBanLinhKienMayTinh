<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">
    <div class="header-container">
        <a href="${pageContext.request.contextPath}/home.jsp" class="logo">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
            <span class="brand-name">TechNova</span>
        </a>

        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/home.jsp" class="${pageContext.request.servletPath.endsWith('/home.jsp') ? 'active' : ''}">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/gioiThieu.jsp" class="${pageContext.request.servletPath.endsWith('/gioiThieu.jsp') ? 'active' : ''}">Giới thiệu</a>
            <a href="#" id="category-toggle">Danh mục</a>
            <a href="${pageContext.request.contextPath}/contact" class="${pageContext.request.servletPath.endsWith('/contact.jsp') ? 'active' : ''}">Liên hệ</a>
        </nav>

        <div class="search-box">
            <input type="text" placeholder="Bạn muốn mua gì hôm nay?">
            <button><i class="fas fa-search"></i></button>
        </div>

        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/cart.jsp" class="icon-btn" title="Giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
            </a>

            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/user" class="icon-btn" title="Tài khoản của bạn">
                        <i class="fas fa-user"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login.jsp" class="icon-btn" title="Đăng nhập">
                        <i class="fas fa-user"></i>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Danh mục -->
        <div class="category-box" id="categoryBox">
            <c:forEach var="cat" items="${applicationScope.categoryList}">
                <a href="${pageContext.request.contextPath}/list-product?categoryId=${cat.id}" class="category-item">
                    <i class="fa-solid fa-microchip"></i> ${cat.name} <i class="fa-solid fa-chevron-right"></i>
                </a>
            </c:forEach>
        </div>
    </div>
</header>
<!-- Overlay nền mờ -->
<div class="overlay" id="overlay"></div>
