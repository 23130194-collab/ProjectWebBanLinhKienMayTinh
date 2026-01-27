<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>

<%@ page import="java.util.Map" %>
<%@ page import="com.example.demo1.model.CartItem" %>


<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng | TechNova</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/cart.css">
</head>

<body>
<header class="header">
    <div class="header-container">
        <a href="home.html" class="logo">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="logo">
            <span class="brand-name">TechNova</span>
        </a>
        <nav class="nav-links">
            <a href="home.html" class="active">Trang chủ</a>
            <a href="gioiThieu.html">Giới thiệu</a>
            <a href="#" id="category-toggle">Danh mục</a>
            <a href="lienHe.html">Liên hệ</a>
        </nav>
        <div class="search-box">
            <input type="text" placeholder="Bạn muốn mua gì hôm nay?">
            <button><i class="fas fa-search"></i></button>
        </div>
        <div class="header-actions">

            <%
                int totalQuantity = 0;
                Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

                if (cart != null) {
                    totalQuantity = cart.size();
                }
            %>

            <a href="${pageContext.request.contextPath}/AddCart?action=view" class="icon-btn cart-btn-wrapper" title="Giỏ hàng">
                <i class="fas fa-shopping-cart"></i>

                <% if (totalQuantity > 0) { %>
                <span class="cart-badge"><%= totalQuantity %></span>
                <% } %>
            </a>

            <a href="user.html" class="icon-btn" title="Tài khoản của bạn">
                <i class="fas fa-user"></i>
            </a>
        </div>

        <!-- Danh mục -->
        <div class="category-box" id="categoryBox">
            <a href="cpu.html" class="category-item"><i class="fa-solid fa-microchip"></i> CPU <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="mainboard.html" class="category-item"><i class="fa-solid fa-diagram-project"></i> Mainboard <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="ram.html" class="category-item"><i class="fa-solid fa-memory"></i> RAM <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="oCung.html" class="category-item"><i class="fa-solid fa-hard-drive"></i> Ổ cứng <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="cardManHinh.html" class="category-item"><i class="fa-solid fa-gauge-high"></i> Card màn hình <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="psu.html" class="category-item"><i class="fa-solid fa-plug"></i> Nguồn máy tính <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="tanNhiet.html" class="category-item"><i class="fa-solid fa-fan"></i> Tản nhiệt <i
                    class="fa-solid fa-chevron-right"></i></a>
            <a href="case.html" class="category-item"><i class="fa-solid fa-computer"></i> Case máy tính <i
                    class="fa-solid fa-chevron-right"></i></a>
        </div>
    </div>
</header>
<!-- Overlay nền mờ -->
<div class="overlay" id="overlay"></div>

<div class="app-container">
    <div class="header-cart">
        <span></span>
    </div>

    <div class="cart-content">

    <c:forEach items="${sessionScope.cart}" var="entry">
        <c:set var="item" value="${entry.value}"/>
        <div class="product-item">
            <img src="${item.product.image}" alt="${item.product.name}">
            <div class="info">
                <div class="info-line">
                    <span>${item.product.name}</span>
                    <div style="display:flex;flex-direction:column;align-items:flex-end;gap:6px;">
                        <a href="AddCart?action=delete&id=${item.product.id}" class="delete-icon">
                            <i class="fa fa-trash"></i>
                        </a>

                        <div class="qty">
                            <a href="AddCart?action=update&id=${item.product.id}&num=-1" style="text-decoration:none;">
                                <button>-</button>
                            </a>

                            <input type="text" value="${item.quantity}" readonly>

                            <a href="AddCart?action=update&id=${item.product.id}&num=1" style="text-decoration:none;">
                                <button>+</button>
                            </a>
                        </div>
                    </div>
                </div>
                <div>
                <span class="current-price">
                    <fmt:formatNumber value="${item.product.price}" pattern="#,###"/>₫
                </span>
                    <c:if test="${item.product.oldPrice > item.product.price}">
                    <span class="old-price">
                        <fmt:formatNumber value="${item.product.oldPrice}" pattern="#,###"/>₫
                    </span>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>

<div class="footer-bar">
    <span class="total-amount">Tạm tính: <fmt:formatNumber value="${totalAmount}" pattern="#,###"/>₫</span>
    <a href="AddCart?action=checkout" class="btn-buy-link">Mua ngay</a>
</div>
<script src="js/header.js"></script>
</body>

</html>