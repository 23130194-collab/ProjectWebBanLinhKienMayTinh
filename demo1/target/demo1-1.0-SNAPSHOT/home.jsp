<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ | TechNova</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/mucSanPham.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/thongBao.css">
</head>

<body>
    <!-- Header -->
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
                <a href="cpu.jsp" class="category-item"><i class="fa-solid fa-microchip"></i> CPU <i
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

    <main>
        <section class="home-layout">
            <!-- Cột trái: Danh mục sản phẩm -->
            <div class="home-left">
                <div class="content-category">
                    <h2>Sản phẩm</h2>
                    <a href="cpu.jsp" class="category-item"><i class="fa-solid fa-microchip"></i> CPU <i
                            class="fa-solid fa-chevron-right"></i></a>
                    <a href="mainboard.html" class="category-item"><i class="fa-solid fa-diagram-project"></i> Mainboard
                        <i class="fa-solid fa-chevron-right"></i></a>
                    <a href="ram.html" class="category-item"><i class="fa-solid fa-memory"></i> RAM <i
                            class="fa-solid fa-chevron-right"></i></a>
                    <a href="oCung.html" class="category-item"><i class="fa-solid fa-hard-drive"></i> Ổ cứng <i
                            class="fa-solid fa-chevron-right"></i></a>
                    <a href="cardManHinh.html" class="category-item"><i class="fa-solid fa-gauge-high"></i> Card màn
                        hình <i class="fa-solid fa-chevron-right"></i></a>
                    <a href="psu.html" class="category-item"><i class="fa-solid fa-plug"></i> Nguồn máy tính <i
                            class="fa-solid fa-chevron-right"></i></a>
                    <a href="tanNhiet.html" class="category-item"><i class="fa-solid fa-fan"></i> Tản nhiệt <i
                            class="fa-solid fa-chevron-right"></i></a>
                    <a href="case.html" class="category-item"><i class="fa-solid fa-computer"></i> Case máy tính <i
                            class="fa-solid fa-chevron-right"></i></a>
                </div>
            </div>

            <!-- Cột giữa: slideshow -->
            <div class="home-center">
                <div class="banners-container" id="banner-left">
                    <div class="slider-container">

                        <div class="slides-wrapper">
                            <a href="#"><img src="https://i.postimg.cc/QC0GXpKP/meg-ai1600pcie5-678x429.png"
                                    class="banner-img" alt="Banner AMD"></a>
                            <a href="#"><img
                                    src="https://bizweb.dktcdn.net/100/329/122/files/nguon-may-tinh-msi-mpg-a1000p-pcie5-nd.jpg?v=1669348348721"
                                    class="banner-img" alt="Banner Intel"></a>
                            <a href="#"><img src="https://i.postimg.cc/PxY3DjDc/Screenshot-2025-11-01-235443.png"
                                    class="banner-img" alt="Banner AMD"></a>
                        </div>

                        <button class="slider-nav prev-btn">❮</button>
                        <button class="slider-nav next-btn">❯</button>
                        <div class="dots-container"></div>
                    </div>
                </div>

            </div>


            <div class="home-right">
                <!-- ===== KHUNG 1: CHÀO MỪNG & LIÊN HỆ ===== -->
                <div class="suggest-box">
                    <div class="suggest-welcome">
                        <div class="suggest-text">
                            <h2>Chào mừng bạn đến với TechNova</h2>
                            <p>Tham gia với chúng tôi để không bỏ lỡ các ưu đãi</p>
                        </div>
                    </div>

                    <span id="openNotice" class="suggest-item" style="cursor:pointer;">
                        <i class="fa-solid fa-bell"></i> Thông báo của bạn
                        <i class="fa-solid fa-chevron-right"></i>
                    </span>

                    <!-- Overlay nền mờ -->
                    <div class="notice-overlay" id="noticeOverlay"></div>

                    <!-- Khung thông báo -->
                    <div class="notice-box" id="noticeBox">
                        <div class="notice-header">
                            <span>Thông báo</span>
                        </div>

                        <div class="notice-content">
                            <div class="notice-item">
                                <i class="fa-solid fa-truck icon"></i>
                                <div>
                                    <div>Đơn hàng <b>#01214S2510002030</b> vừa được giao thành công.</div>
                                    <div class="notice-meta">
                                        <span>1 tuần trước</span>
                                        <a class="notice-detail" href="user.jsp">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>

                            <div class="notice-item">
                                <i class="fa-solid fa-truck icon"></i>
                                <div>
                                    <div>Đơn hàng <b>#01214S2510002030</b> vừa được giao thành công.</div>
                                    <div class="notice-meta">
                                        <span>1 tuần trước</span>
                                        <a class="notice-detail" href="user.jsp">Xem chi tiết</a>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="closeNoticeBtn" class="close-btn">Đóng</div>
                    </div>


                    <a href="lienHe.html" class="suggest-item">
                        <i class="fa-solid fa-phone-volume"></i> Liên hệ với chúng tôi
                        <i class="fa-solid fa-chevron-right"></i>
                    </a>
                </div>


                <!-- ===== KHUNG 2: ƯU ĐÃI ===== -->
                <div class="suggest-box">
                    <div class="suggest-special">Ưu đãi khách hàng</div>


                    <a href="#" class="suggest-item">
                        <i class="fa-solid fa-arrow-trend-up"></i> Chính sách đổi trả
                        <i class="fa-solid fa-chevron-right"></i>
                    </a>

                    <a href="#" class="suggest-item">
                        <i class="fa-regular fa-clock"></i> Cam kết sản phẩm
                        <i class="fa-solid fa-chevron-right"></i>
                    </a>

                    <a href="#" class="suggest-item">
                        <i class="fa-solid fa-shield-halved"></i> Chính sách bảo hành
                        <i class="fa-solid fa-chevron-right"></i>
                    </a>
                </div>
            </div>

        </section>



        <section class="flash-sale">
            <div class="flash-sale-header">
                <h2>SẢN PHẨM BÁN CHẠY</h2>
            </div>

            <div class="slider-wrapper">
                <button class="arrow left"><i class="fa-solid fa-chevron-left"></i></button>
                <div class="slider" id="slider">

                    <a href="sanPham.jsp" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i5-12400.jpg"
                                class="product-image" alt="CPU Intel Core i5 12400">
                            <div class="product-title">CPU Intel Core i5 12400</div>
                            <div class="price">5.312.500đ <span class="old">6.250.000đ</span></div>
                            <div class="sold">Đã bán 10k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.jsp" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/g/r/group_301_7__3.png"
                                class="product-image" alt="Ổ cứng di động SSD Transcend ESD310C 1050MB/s 128GB">
                            <div class="product-title">Ổ cứng di động SSD Transcend ESD310C 1050MB/s 128GB</div>
                            <div class="price">671.500đ <span class="old">790.000đ</span></div>
                            <div class="sold">Đã bán 9k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i9-14900kf_2_.png"
                                class="product-image" alt="CPU Intel Core i9 14900KF">
                            <div class="product-title">CPU Intel Core i9 14900KF</div>
                            <div class="price">15.249.000đ <span class="old">17.940.000đ</span></div>
                            <div class="sold">Đã bán 8k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i7-12700f.jpg"
                                class="product-image" alt="CPU Intel Core i7 12700F">
                            <div class="product-title">CPU Intel Core i7 12700F</div>
                            <div class="price">8.160.000đ <span class="old">9.600.000đ</span></div>
                            <div class="sold">Đã bán 7k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/2/0/20_2_8.jpg"
                                class="product-image" alt="RAM PC Kingston Fury 8GB 3200MHz KF432C16BB/8">
                            <div class="product-title">RAM PC Kingston Fury 8GB 3200MHz KF432C16BB/8</div>
                            <div class="price">1.096.000đ <span class="old">1.290.000đ</span></div>
                            <div class="sold">Đã bán 6k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/1/9/192_1_1.png"
                                class="product-image" alt="Ổ cứng HDD SEAGATE SKYHAWK AI 3.5">
                            <div class="product-title">Ổ cứng HDD SEAGATE SKYHAWK AI 3.5 SATA 3 7200RPM 10TB</div>
                            <div class="price">7.301.500đ <span class="old">8.590.000đ</span></div>
                            <div class="sold">Đã bán 5k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_17__7_30_1_1_1_1_1.png"
                                class="product-image" alt="Ram PC Kingston Fury Beast RGB DDR5 5600MHz 64GB (2*32GB)">
                            <div class="product-title">Ram PC Kingston Fury Beast RGB DDR5 5600MHz 64GB (2*32GB)</div>
                            <div class="price">5.941.500đ <span class="old">6.990.000đ</span></div>
                            <div class="sold">Đã bán 4k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_19__4_49_1.png"
                                class="product-image" alt="Ổ cứng di động SSD Synology BeeDrive USB-C 3.2 Gen 2 2TB">
                            <div class="product-title">Ổ cứng di động SSD Synology BeeDrive USB-C 3.2 Gen 2 2TB</div>
                            <div class="price">6.196.500đ <span class="old">7.290.000đ</span></div>
                            <div class="sold">Đã bán 3k suất</div>
                        </div>
                    </a>

                    <a href="sanPham.html" class="slider-item">
                        <div class="product-card">
                            <span class="discount-badge">-15%</span>
                            <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i3-10105-sn_1.jpg"
                                class="product-image" alt="CPU Intel Core i3 10105 (SN)">
                            <div class="product-title">CPU Intel Core i3 10105 (SN)</div>
                            <div class="price">2.626.500đ <span class="old">3.090.000đ</span></div>
                            <div class="sold">Đã bán 2k suất</div>
                        </div>
                    </a>

                </div>
                <button class="arrow right"><i class="fa-solid fa-chevron-right"></i></button>
            </div>

        </section>

        <!-- SECTION GỢI Ý SẢN PHẨM -->
        <section class="suggest-products">
            <h2 class="suggest-title">GỢI Ý SẢN PHẨM CHO BẠN</h2>
            <div class="product-grid">
                <!-- 1 -->
                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-10%</span>
                        <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/1/9/192_1_1.png"
                            class="product-image" alt="Ổ cứng HDD SEAGATE SKYHAWK AI 3.5">
                        <div class="product-title">Ổ cứng HDD SEAGATE SKYHAWK AI 3.5 SATA 3 7200RPM 10TB</div>
                        <div class="price">7.731.000đ <span class="old">8.590.000đ</span></div>
                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-10%</span>
                        <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/t/e/text_ng_n_17__7_30_1_1_1_1_1.png"
                            class="product-image" alt="Ram PC Kingston Fury Beast RGB DDR5 5600MHz 64GB (2*32GB)">
                        <div class="product-title">Ram PC Kingston Fury Beast RGB DDR5 5600MHz 64GB (2*32GB)</div>
                        <div class="price">6.291.000đ <span class="old">6.990.000đ</span></div>

                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-25%</span>
                        <img src="https://cdn2.cellphones.com.vn/x/media/catalog/product/g/r/group_301_7__3.png"
                            class="product-image" alt="Ổ cứng di động SSD Transcend ESD310C 1050MB/s 128GB">
                        <div class="product-title">Ổ cứng di động SSD Transcend ESD310C 1050MB/s 128GB</div>
                        <div class="price">592.500đ <span class="old">790.000đ</span></div>

                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-10%</span>
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i3-10105-sn_1.jpg"
                            class="product-image" alt="CPU Intel Core i3 10105 (SN)">
                        <div class="product-title">CPU Intel Core i3 10105 (SN)</div>
                        <div class="price">2.781.000đ <span class="old">3.090.000đ</span></div>
                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-50%</span>
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i5-12400.jpg"
                            class="product-image" alt="CPU Intel Core i5 12400">
                        <div class="product-title">CPU Intel Core i5 12400</div>
                        <div class="price">3.125.500đ <span class="old">6.250.000đ</span></div>
                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-20%</span>
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i7-12700f.jpg"
                            class="product-image" alt="CPU Intel Core i7 12700F">
                        <div class="product-title">CPU Intel Core i7 12700F</div>
                        <div class="price">7.680.000đ <span class="old">9.600.000đ</span></div>

                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-15%</span>
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/2/0/20_2_8.jpg"
                            class="product-image" alt="RAM PC Kingston Fury 8GB 3200MHz KF432C16BB/8">
                        <div class="product-title">RAM PC Kingston Fury 8GB 3200MHz KF432C16BB/8</div>
                        <div class="price">1.096.000đ <span class="old">1.290.000đ</span></div>

                    </div>
                </a>

                <a href="sanPham.html" class="slider-item">
                    <div class="product-card">
                        <span class="discount-badge">-20%</span>
                        <img src="https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/c/p/cpu-intel-core-i9-14900kf_2_.png"
                            class="product-image" alt="CPU Intel Core i9 14900KF">
                        <div class="product-title">CPU Intel Core i9 14900KF</div>
                        <div class="price">14.352.000đ <span class="old">17.940.000đ</span></div>

                    </div>
                </a>
            </div>
        </section>


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
    <script src="js/thongBao.js"></script>
    <script src="js/flashSale.js"></script>
    <script src="js/dualBannerSlideshow.js"></script>




</body>

</html>