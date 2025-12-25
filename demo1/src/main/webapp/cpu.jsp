<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CPU | TechNova</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/mucSanPham.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<header class="header">
    <div class="header-container">
        <a href="home.jsp" class="logo">
            <img src="https://i.postimg.cc/Hn4Jc3yj/logo-2.png" alt="TechNova Logo">
            <span class="brand-name">TechNova</span>
        </a>

        <nav class="nav-links">
            <a href="home.jsp" class="active">Trang chủ</a>
            <a href="gioiThieu.jsp">Giới thiệu</a>
            <a href="#" id="category-toggle">Danh mục</a>
            <a href="lienHe.jsp">Liên hệ</a>
        </nav>

        <div class="search-box">
            <input type="text" placeholder="Bạn muốn mua gì hôm nay?">
            <button><i class="fas fa-search"></i></button>
        </div>

        <div class="header-actions">
            <a href="cart.jsp" class="icon-btn" title="Giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
            </a>

            <a href="user.jsp" class="icon-btn" title="Tài khoản của bạn">
                <i class="fas fa-user"></i>
            </a>
        </div>

        <!-- Danh mục -->
        <div class="category-box" id="categoryBox">
            <c:forEach items="${applicationScope.categoryList}" var="cat">
                <a href="list-product?id=${cat.id}" class="category-item">
                    <img src="${cat.icon}" class="category-icon" alt="${cat.name}">
                    ${cat.name}
                    <i class="fa-solid fa-chevron-right"></i>
                </a>
            </c:forEach>
        </div>
    </div>
</header>
<!-- Overlay nền mờ -->
<div class="overlay" id="overlay"></div>
<!-- Breadcrumb & Banners -->
<div class="page-header-wrapper">
    <div class="container">
        <!-- Breadcrumb -->
        <div class="breadcrumb">
            <a href="index.html" class="path">Trang chủ</a>
            <span class="separator">/</span>
            <a href="cpu.html" class="path">CPU</a>
        </div>

        <!-- Banners -->
        <div class="banners-container">

            <div class="banner-link slider-container" id="banner-left">
                <button class="slider-nav prev-btn">❮</button>
                <button class="slider-nav next-btn">❯</button>

                <div class="slides-wrapper">
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/amd.png"
                            class="banner-img" alt="CPU AMD"></a>
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/cate-cpu-intel-28-08.png"
                            class="banner-img" alt="CPU Intel"></a>
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/amd.png"
                            class="banner-img" alt="CPU AMD"></a>
                </div>
                <div class="dots-container"></div>
            </div>

            <div class="banner-link slider-container" id="banner-right">
                <button class="slider-nav prev-btn">❮</button>
                <button class="slider-nav next-btn">❯</button>

                <div class="slides-wrapper">
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/cate-cpu-intel-28-08.png"
                            class="banner-img" alt="CPU Intel"></a>
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/amd.png"
                            class="banner-img" alt="CPU AMD"></a>
                    <a href="#"><img
                            src="https://cdn2.cellphones.com.vn/insecure/rs:fill:595:100/q:100/plain/https://dashboard.cellphones.com.vn/storage/cate-cpu-intel-28-08.png"
                            class="banner-img" alt="CPU Intel"></a>
                </div>
                <div class="dots-container"></div>
            </div>
        </div>
    </div>
</div>

<!-- Main Content -->
<main class="container">
    <h1 class="page-title">CPU</h1>

    <!-- Brand Filter -->
    <div class="brand-filter">
        <a href="#" class="brand-logo">
            <img src="https://blog.logomaster.ai/hs-fs/hubfs/intel-logo-1968.jpg?width=1680&height=1135&name=intel-logo-1968.jpg"
                 alt="CPU Intel">
        </a>
        <a href="#" class="brand-logo">
            <img src="https://logos-world.net/wp-content/uploads/2020/03/AMD-Logo.png" alt="CPU AMD">
        </a>
    </div>

    <!-- Filter Section -->
    <h1 class="page-title">Chọn theo tiêu chí</h1>

    <!-- Filter Toggle Button -->
    <div class="filter-toggle-container">
        <button class="filter-toggle-btn" id="filterToggleBtn">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                <path d="M6 10.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/>
            </svg>
            Bộ lọc
        </button>
    </div>

    <div class="filter-section">
        <div class="option-group">
            <h4 class="group-title">Dòng sản phẩm</h4>
            <div class="options-grid">
                <input type="checkbox" id="opt-product-1" name="product" value="Core i9">
                <label for="opt-product-1" class="option-label">Core i9</label>

                <input type="checkbox" id="opt-product-2" name="product" value="Core i7">
                <label for="opt-product-2" class="option-label">Core i7</label>

                <input type="checkbox" id="opt-product-3" name="product" value="Core i5">
                <label for="opt-product-3" class="option-label">Core i5</label>

                <input type="checkbox" id="opt-product-4" name="product" value="Core i3">
                <label for="opt-product-4" class="option-label">Core i3</label>

                <input type="checkbox" id="opt-product-5" name="product" value="Core Ultra 9">
                <label for="opt-product-5" class="option-label">Core Ultra 9</label>

                <input type="checkbox" id="opt-product-6" name="product" value="Core Ultra 7">
                <label for="opt-product-6" class="option-label">Core Ultra 7</label>

                <input type="checkbox" id="opt-product-7" name="product" value="Core Ultra 5">
                <label for="opt-product-7" class="option-label">Core Ultra 5</label>

                <input type="checkbox" id="opt-product-8" name="product" value="Ryzen 9">
                <label for="opt-product-8" class="option-label">Ryzen 9</label>

                <input type="checkbox" id="opt-product-9" name="product" value="Ryzen 7">
                <label for="opt-product-9" class="option-label">Ryzen 7</label>

                <input type="checkbox" id="opt-product-10" name="product" value="Ryzen 5">
                <label for="opt-product-10" class="option-label">Ryzen 5</label>

                <input type="checkbox" id="opt-product-11" name="product" value="Ryzen 3">
                <label for="opt-product-11" class="option-label">Ryzen 3</label>

                <input type="checkbox" id="opt-product-12" name="product" value="AMD Ryzen PRO">
                <label for="opt-product-12" class="option-label">AMD Ryzen PRO</label>

                <input type="checkbox" id="opt-product-13" name="product" value="AMD Athlon">
                <label for="opt-product-13" class="option-label">AMD Athlon</label>

            </div>
        </div>

        <div class="option-group">
            <h4 class="group-title">Thế hệ CPU</h4>
            <div class="options-grid">
                <input type="checkbox" id="opt-generation-1" name="generation" value="Intel thế hệ 14">
                <label for="opt-generation-1" class="option-label">Intel thế hệ 14</label>

                <input type="checkbox" id="opt-generation-2" name="generation" value="Intel thế hệ 14">
                <label for="opt-generation-2" class="option-label">Intel thế hệ 13</label>

                <input type="checkbox" id="opt-generation-3" name="generation" value="Intel thế hệ 14">
                <label for="opt-generation-3" class="option-label">Intel thế hệ 12</label>

                <input type="checkbox" id="opt-generation-4" name="generation" value="Intel thế hệ 14">
                <label for="opt-generation-4" class="option-label">Intel thế hệ 11</label>

                <input type="checkbox" id="opt-generation-5" name="generation" value="AMD Ryzen thế hệ thứ 9">
                <label for="opt-generation-5" class="option-label">AMD Ryzen thế hệ thứ 9</label>

                <input type="checkbox" id="opt-generation-6" name="generation" value="AMD Ryzen thế hệ thứ 8">
                <label for="opt-generation-6" class="option-label">AMD Ryzen thế hệ thứ 8</label>

                <input type="checkbox" id="opt-generation-7" name="generation" value="AMD Ryzen thế hệ thứ 7">
                <label for="opt-generation-7" class="option-label">AMD Ryzen thế hệ thứ 7</label>

                <input type="checkbox" id="opt-generation-8" name="generation" value="AMD Ryzen thế hệ thứ 5">
                <label for="opt-generation-8" class="option-label">AMD Ryzen thế hệ thứ 5</label>

                <input type="checkbox" id="opt-generation-9" name="generation" value="AMD Ryzen thế hệ thứ 4">
                <label for="opt-generation-9" class="option-label">AMD Ryzen thế hệ thứ 4</label>

                <input type="checkbox" id="opt-generation-10" name="generation" value="AMD Ryzen thế hệ thứ 3">
                <label for="opt-generation-10" class="option-label">AMD Ryzen thế hệ thứ 3</label>
            </div>
        </div>

        <div class="option-group">
            <h4 class="group-title">Socket</h4>
            <div class="options-grid">
                <input type="checkbox" id="opt-socket-1" name="socket" value="LGA 1700">
                <label for="opt-socket-1" class="option-label">LGA 1700</label>

                <input type="checkbox" id="opt-socket-2" name="socket" value="LGA 1200">
                <label for="opt-socket-2" class="option-label">LGA 1200</label>

                <input type="checkbox" id="opt-socket-3" name="socket" value="AM5">
                <label for="opt-socket-3" class="option-label">AM5</label>

                <input type="checkbox" id="opt-socket-4" name="socket" value="AM4">
                <label for="opt-socket-4" class="option-label">AM4</label>

                <input type="checkbox" id="opt-socket-5" name="socket" value="sWRX8">
                <label for="opt-socket-5" class="option-label">sWRX8</label>

                <input type="checkbox" id="opt-socket-6" name="socket" value="FCLGA1851">
                <label for="opt-socket-6" class="option-label">FCLGA1851</label>

                <input type="checkbox" id="opt-socket-7" name="socket" value="FCLGA1700">
                <label for="opt-socket-7" class="option-label">FCLGA1700</label>
            </div>
        </div>

        <div class="option-group">
            <h4 class="group-title">Số nhân</h4>
            <div class="options-grid">
                <input type="checkbox" id="opt-core-1" name="core" value="64">
                <label for="opt-core-1" class="option-label">64</label>

                <input type="checkbox" id="opt-core-2" name="core" value="32">
                <label for="opt-core-2" class="option-label">32</label>

                <input type="checkbox" id="opt-core-3" name="core" value="24">
                <label for="opt-core-3" class="option-label">24</label>

                <input type="checkbox" id="opt-core-4" name="core" value="20">
                <label for="opt-core-4" class="option-label">20</label>

                <input type="checkbox" id="opt-core-5" name="core" value="16">
                <label for="opt-core-5" class="option-label">16</label>

                <input type="checkbox" id="opt-core-6" name="core" value="14">
                <label for="opt-core-6" class="option-label">14</label>

                <input type="checkbox" id="opt-core-7" name="core" value="12">
                <label for="opt-core-7" class="option-label">12</label>

                <input type="checkbox" id="opt-core-8" name="core" value="10">
                <label for="opt-core-8" class="option-label">10</label>

                <input type="checkbox" id="opt-core-9" name="core" value="8">
                <label for="opt-core-9" class="option-label">8</label>

                <input type="checkbox" id="opt-core-10" name="core" value="6">
                <label for="opt-core-10" class="option-label">6</label>

                <input type="checkbox" id="opt-core-11" name="core" value="4">
                <label for="opt-core-11" class="option-label">4</label>

                <input type="checkbox" id="opt-core-12" name="core" value="2">
                <label for="opt-core-12" class="option-label">2</label>
            </div>
        </div>


        <div class="action-bar">
            <button class="cancel-button">Hủy</button>
            <button class="confirm-button">Xác nhận</button>
        </div>
    </div>

    <div class="applied-filters" id="applied-filters"></div>

    <div class="sort-wrapper">
        <h1 class="page-title">Sắp xếp theo</h1>

        <div class="sort-buttons">
            <input type="radio" id="sort-popular" name="sort" checked>
            <label for="sort-popular" class="sort-btn">Phổ biến</label>

            <input type="radio" id="sort-low-high" name="sort">
            <label for="sort-low-high" class="sort-btn">Giá Thấp - Cao</label>

            <input type="radio" id="sort-high-low" name="sort">
            <label for="sort-high-low" class="sort-btn">Giá Cao - Thấp</label>
        </div>
    </div>

    <!-- Product Grid -->
    <div class="product-grid">
        <c:forEach items="${productList}" var="p">
            <!-- Sản phẩm -->
            <div class="product-card" data-product="Core i5" data-generation="Intel thế hệ 12" data-socket="LGA 1700"
                 data-core="6">
                <div class="discount-tag">
                    <span class="discount-percent">-<fmt:formatNumber value="${p.discount_value}" pattern="#"/>%</span>
                </div>
                <a href="product-detail?id=${p.id}" class="product-link">
                    <img src="${p.image}" alt="${p.name}" class="product-image">
                    <h3 class="product-title">${p.name}</h3>
                    <div class="price-section">
                        <div class="current-price"><fmt:formatNumber value="${p.price}" pattern="#,###"/>đ</div>
                        <c:if test="${p.discount_value > 0}">
                            <div class="original-price"><fmt:formatNumber value="${p.old_price}" pattern="#,###"/>đ</div>
                        </c:if>
                    </div>
                </a>

                <div class="product-footer-interaction">
                    <div class="action-item rating">
                        <div class="stars-container">
                            <div class="stars-outer">
                                <div class="stars-inner" style="width: ${ (p.avg_rating * 1.0 / 5) * 100 }%;"></div>
                            </div>
                        </div>
                        <span class="rating-value"><fmt:formatNumber value="${p.avg_rating}" pattern="0.0"/></span>
                    </div>

                    <!-- Yêu thích -->
                    <button class="action-item like-btn">
                        <i class="fa-regular fa-heart"></i>
                    </button>

                </div>
            </div>
        </c:forEach>
    </div>
    <!--   Chuyển trang    -->
    <div class="pagination-container">
        <button class="pagination-btn disabled">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
            </svg>
        </button>

        <a href="#" class="page-number active">1</a>
        <a href="#" class="page-number">2</a>
        <span class="ellipsis">...</span>
        <a href="#" class="page-number">5</a>


        <button class="pagination-btn">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
            </svg>
        </button>
    </div>
</main>

<footer>
    <div class="footer-container">
        <div class="footer-main-content">

            <div class="footer-col col-1">
                <h4>Tổng đài hỗ trợ miễn phí</h4>
                <ul>
                    <li>Mua hàng - bảo hành 1800.2097 (7h30 - 18h30)</li>
                    <li>Khiếu nại 1800.2063 (8h00 - 21h30)</li>
                </ul>

                <h4>Phương thức thanh toán</h4>
                <div class="payment-methods">
                    <img src="https://i.postimg.cc/FsJvZGsX/apple-Pay.png" alt="Apple Pay">
                    <img src="https://i.postimg.cc/pTTbnJ10/bidv.png" alt="BIDV">
                    <img src="https://i.postimg.cc/L6fXXmPn/momo.jpg" alt="MoMo">
                    <img src="https://i.postimg.cc/bYn803wR/Zalo-Pay.png" alt="Zalo Pay">
                </div>
            </div>

            <div class="footer-col col-2">
                <h4>Thông tin về chính sách</h4>
                <ul>
                    <li>Mua hàng và thanh toán online</li>
                    <li>Mua hàng trả góp online</li>
                    <li>Mua hàng trả góp bằng thẻ tín dụng</li>
                    <li>Chính sách giao hàng</li>
                    <li>Chính sách đổi trả</li>
                    <li>Đổi điểm</li>
                    <li>Xem ưu đãi</li>
                    <li>Tra cứu hóa đơn điện tử</li>
                    <li>Thông tin hóa đơn mua hàng</li>
                    <li>Trung tâm bảo hành chính hãng</li>
                    <li>Quy định về việc sao lưu dữ liệu</li>
                    <li>Thuế VAT</li>
                </ul>
            </div>

            <div class="footer-col col-3">
                <h4>Dịch vụ và thông tin khác</h4>
                <ul>
                    <li>Khách hàng doanh nghiệp</li>
                    <li>Ưu đãi thanh toán</li>
                    <li>Quy chế hoạt động</li>
                    <li>Chính sách bảo mật thông tin cá nhân</li>
                    <li>Chính sách bảo hành</li>
                    <li>Liên hệ hợp tác kinh doanh</li>
                    <li>Tuyển dụng</li>
                    <li>Dịch vụ bảo hành</li>
                </ul>
            </div>

            <div class="footer-col col-4">
                <h4>Kết nối với TechNova</h4>
                <div class="connect-methods">
                    <img src="https://i.postimg.cc/CLjh0my7/youtube.png" alt="Youtube">
                    <img src="https://i.postimg.cc/rsBv3Xyx/facebook.png" alt="Facebook">
                    <img src="https://i.postimg.cc/vBkYYKHS/tiktok.png" alt="TikTok">
                    <img src="https://i.postimg.cc/k55qxC26/Zalo.png" alt="Zalo">
                </div>
            </div>

        </div>
        <div class="footer-subscription"></div>
    </div>
</footer>


<script src="js/header.js"></script>
<script src="js/boLoc.js"></script>
<script src="js/dualBannerSlideshow.js"></script>
</body>
</html>
