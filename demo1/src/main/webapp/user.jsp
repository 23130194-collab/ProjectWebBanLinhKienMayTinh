<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Đơn hàng của tôi | TechNova</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">

</head>
<body>
    <header class="header">
        <div class="header-container">
            <a href="${pageContext.request.contextPath}/home.jsp" class="logo">
                <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
                <span class="brand-name">TechNova</span>
            </a>

            <nav class="nav-links">
                <a href="${pageContext.request.contextPath}/home.jsp">Trang chủ</a>
                <a href="${pageContext.request.contextPath}/gioiThieu.jsp">Giới thiệu</a>
                <a href="#" id="category-toggle">Danh mục</a>
                <a href="${pageContext.request.contextPath}/contact">Liên hệ</a>
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
                        <a href="${pageContext.request.contextPath}/user" class="icon-btn active" title="Tài khoản của bạn">
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
<div class="overlay" id="overlay"></div>
<div class="container">
    <div class="top-card" role="region" aria-label="thông tin tài khoản">
        <div class="profile">

            <div class="summary-card">
                <c:if test="${not empty sessionScope.user}">
                    <div class="summary-left">
                        <div class="reviewer-avatar">${sessionScope.user.name.substring(0,1)}</div>
                        <div class="summary-info">
                            <div class="summary-name">${sessionScope.user.name}</div>
                            <div class="summary-phone">${sessionScope.user.phone}</div>
                        </div>
                    </div>
                </c:if>

                <div class="summary-divider"></div>

                <div class="summary-item">
                    <div class="summary-icon">
                        <i class="fa-solid fa-cart-shopping" style="color: #ff0000;"></i>
                    </div>
                    <div class="summary-text">
                        <div class="summary-count">5</div>
                        <div class="summary-label">Tổng số đơn hàng đã mua</div>
                    </div>
                </div>

                <div class="summary-divider"></div>

                <div class="summary-item">
                    <div class="summary-icon">
                        <i class="fa-solid fa-sack-dollar" style="color: #74C0FC;"></i>
                    </div>
                    <div class="summary-text">
                        <div class="summary-count">2.265.000₫</div>
                        <div class="summary-small">Tổng tiền tích lũy</div>
                    </div>
                </div>

            </div>
        </div>

    </div>


    <div class="main">

        <aside class="side" aria-label="menu">
            <nav class="menu" aria-label="menu chính">
                <a href="${pageContext.request.contextPath}/user" class="menu-item active" data-target="orders">
                    <i class="fa-solid fa-list icon"></i>
                    <span class="label">Đơn hàng của tôi</span>
                </a>

                <a href="#" class="menu-item" data-target="favorites">
                    <i class="fa-regular fa-heart icon"></i>
                    <span class="label">Sản phẩm yêu thích</span>
                </a>

                <a href="#" class="menu-item" data-target="account">
                    <i class="fa-regular fa-user icon"></i>
                    <span class="label">Thông tin tài khoản</span>
                </a>

                <a href="${pageContext.request.contextPath}/logout" class="menu-item" data-target="account">
                    <i class="fa-solid fa-right-from-bracket icon"></i>
                    <span class="label">Đăng xuất</span>
                </a>
            </nav>
        </aside>
        <section class="content">

            <div class="section active" id="orders">
                <div class="tabs" role="tablist" aria-label="lọc đơn hàng">
                    <a href="user.jsp">
                        <div class="tab active">Tất cả</div>
                    </a>
                    <a href="choXacNhan.jsp">
                        <div class="tab">Chờ xác nhận</div>
                    </a>
                    <a href="daXacNhan.jsp">
                        <div class="tab">Đã xác nhận</div>
                    </a>
                    <a href="dangVanChuyen.jsp">
                        <div class="tab">Đang vận chuyển</div>
                    </a>
                    <a href="daGiaoHang.jsp">
                        <div class="tab">Đã giao hàng</div>
                    </a>
                    <a href="daHuy.jsp">
                        <div class="tab">Đã huỷ</div>
                    </a>
                </div>


                <div class="orders" id="orders-list">

                    <article class="order" data-status="pending">
                        <div class="thumb"><img
                                src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_303_1_4.png"
                                alt=""></div>
                        <div class="details">
                            <div class="meta">Đơn hàng: <strong>#001</strong> • Ngày đặt hàng: 01/11/2025</div>
                            <div class="title">Mainboard MSI B760M Gaming WIFI DDR5</div>
                            <div class="price">3.190.000₫</div>
                        </div>
                        <div class="right">
                            <div class="status-pill pending">Chờ xác nhận</div>
                            <div class="muted">Tổng thanh toán:</div>
                            <div class="total">2.990.000₫</div>
                            <a class="small-link" href="chiTietDonHang.jsp">Xem chi tiết ></a>

                        </div>
                    </article>

                    <article class="order" data-status="confirmed">
                        <div class="thumb"><img
                                src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_303_1_4.png"
                                alt=""></div>
                        <div class="details">
                            <div class="meta">Đơn hàng: <strong>#002</strong> • Ngày đặt hàng: 29/10/2025</div>
                            <div class="title">Mainboard MSI B760M Gaming WIFI DDR5</div>
                            <div class="price">3.190.000₫</div>
                        </div>
                        <div class="right">
                            <div class="status-pill confirmed">Đã xác nhận</div>
                            <div class="muted">Tổng thanh toán:</div>
                            <div class="total">2.990.000₫</div>
                            <a class="small-link" href="chiTietDonHang.jsp">Xem chi tiết ></a>

                        </div>
                    </article>

                    <article class="order" data-status="shipping">
                        <div class="thumb"><img
                                src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_303_1_4.png"
                                alt=""></div>
                        <div class="details">
                            <div class="meta">Đơn hàng: <strong>#003</strong> • Ngày đặt hàng: 28/10/2025</div>
                            <div class="title">Mainboard MSI B760M Gaming WIFI DDR5</div>
                            <div class="price">3.190.000₫</div>
                        </div>
                        <div class="right">
                            <div class="status-pill shipping">Đang vận chuyển</div>
                            <div class="muted">Tổng thanh toán:</div>
                            <div class="total">2.990.000₫</div>
                            <a class="small-link" href="chiTietDonHang.jsp">Xem chi tiết ></a>

                        </div>
                    </article>

                    <article class="order" data-status="delivered">
                        <div class="thumb"><img
                                src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_303_1_4.png"
                                alt=""></div>
                        <div class="details">
                            <div class="meta">Đơn hàng: <strong>#004</strong> • Ngày đặt hàng: 27/10/2025</div>
                            <div class="title">Mainboard MSI B760M Gaming WIFI DDR5</div>
                            <div class="price">3.190.000₫</div>
                        </div>
                        <div class="right">
                            <div class="status-pill delivered">Đã giao hàng</div>
                            <div class="muted">Tổng thanh toán:</div>
                            <div class="total">2.990.000₫</div>
                            <a class="small-link" href="chiTietDonHang.jsp">Xem chi tiết ></a>

                        </div>
                    </article>

                    <article class="order" data-status="cancelled">
                        <div class="thumb"><img
                                src="https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/g/r/group_303_1_4.png"
                                alt=""></div>
                        <div class="details">
                            <div class="meta">Đơn hàng: <strong>#005</strong> • Ngày đặt hàng: 25/10/2025</div>
                            <div class="title">Mainboard MSI B760M Gaming WIFI DDR5</div>
                            <div class="price">3.190.000₫</div>
                        </div>
                        <div class="right">
                            <div class="status-pill cancelled">Đã hủy</div>
                            <div class="muted">Tổng thanh toán:</div>
                            <div class="total">2.990.000₫</div>
                            <a class="small-link" href="chiTietDonHang.jsp">Xem chi tiết ></a>

                        </div>
                    </article>

                </div>
            </div>

        </section>
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/header.js"></script>
</body>
</html>
