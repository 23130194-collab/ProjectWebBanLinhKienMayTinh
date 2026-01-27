<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/login.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Thông tin tài khoản | TechNova</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="top-card" role="region" aria-label="thông tin tài khoản">
        <div class="profile">
            <div class="summary-card">
                <div class="summary-left">
                    <div class="reviewer-avatar">${fn:substring(sessionScope.user.name, 0, 1)}</div>
                    <div class="summary-info">
                        <div class="summary-name">${sessionScope.user.name}</div>
                        <div class="summary-phone">${sessionScope.user.phone}</div>
                    </div>
                </div>
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
                <a href="user.html" class="menu-item" data-target="orders">
                    <i class="fa-solid fa-list icon"></i>
                    <span class="label">Đơn hàng của tôi</span>
                </a>
                <a href="sanPhamYeuThich.html" class="menu-item" data-target="favorites">
                    <i class="fa-regular fa-heart icon"></i>
                    <span class="label">Sản phẩm yêu thích</span>
                </a>
                <a href="thongTinTaiKhoan.jsp" class="menu-item active" data-target="account">
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
            <div class="section active" id="account">
                <h2>Thông tin tài khoản</h2>

                <c:if test="${not empty sessionScope.updateProfileSuccess}">
                    <div class="success-message">${sessionScope.updateProfileSuccess}</div>
                    <c:remove var="updateProfileSuccess" scope="session"/>
                </c:if>
                <c:if test="${not empty sessionScope.updateProfileError}">
                    <div class="error-message-form">${sessionScope.updateProfileError}</div>
                    <c:remove var="updateProfileError" scope="session"/>
                </c:if>

                <div class="info-section">
                    <div class="card" style="grid-column: span 2;">
                        <!-- Khung Thông tin cá nhân -->
                        <div class="info-card" id="infoView">
                            <div class="info-header">
                                <h2>Thông tin cá nhân</h2>
                                <button id="editBtn" class="update-btn">Cập nhật</button>
                            </div>
                            <div class="info-body">
                                <div class="info-row">
                                    <span>Họ và tên:</span>
                                    <p id="name">${sessionScope.user.name}</p>
                                    <span>Số điện thoại:</span>
                                    <p id="phone">${sessionScope.user.phone}</p>
                                </div>
                                <div class="info-row">
                                    <span>Giới tính:</span>
                                    <p id="gender">${sessionScope.user.gender}</p>
                                    <span>Email:</span>
                                    <p id="email">${sessionScope.user.email}</p>
                                </div>
                                <div class="info-row">
                                    <span>Ngày sinh:</span>
                                    <p id="dob"><fmt:formatDate value="${sessionScope.user.birthday}" pattern="dd/MM/yyyy" /></p>
                                    <span>Địa chỉ:</span>
                                    <p id="address">${sessionScope.user.address}</p>
                                </div>
                            </div>
                        </div>

                        <div class="info-card hidden" id="infoForm">
                            <div class="info-header">
                                <h2>Cập nhật thông tin</h2>
                            </div>
                            <form action="${pageContext.request.contextPath}/update-profile" method="post">
                                <div class="info-body">
                                    <div class="info-row">
                                        <span>Họ và tên:</span>
                                        <input type="text" name="name" value="${sessionScope.user.name}">
                                        <span>Số điện thoại:</span>
                                        <input type="text" name="phone" value="${sessionScope.user.phone}">
                                    </div>
                                    <div class="info-row">
                                        <span>Giới tính:</span>
                                        <div class="custom-select-wrapper">
                                            <select name="gender">
                                                <option value="Nam" ${sessionScope.user.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                                <option value="Nữ" ${sessionScope.user.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                                <option value="Khác" ${sessionScope.user.gender == 'Khác' ? 'selected' : ''}>Khác</option>
                                            </select>
                                        </div>
                                        <span>Email:</span>
                                        <input type="email" name="email" value="${sessionScope.user.email}" readonly>
                                    </div>
                                    <div class="info-row">
                                        <span>Ngày sinh:</span>
                                        <input type="date" name="birthday" value="<fmt:formatDate value='${sessionScope.user.birthday}' pattern='yyyy-MM-dd' />">
                                        <span>Địa chỉ:</span>
                                        <input type="text" name="address" value="${sessionScope.user.address}">
                                    </div>
                                </div>
                                <div class="info-actions">
                                    <button type="submit" class="save-btn">Lưu</button>
                                    <button type="button" id="cancelBtn" class="cancel-btn">Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="card">
                        <h3>
                            Mật khẩu
                            <a href="${pageContext.request.contextPath}/thayDoiMK.jsp" class="update-btn-ud">Thay đổi</a>
                        </h3>
                        <div class="info-item">Cập nhật lần cuối:
                            <c:if test="${not empty sessionScope.user.passwordUpdatedAt}">
                                <fmt:formatDate value="${sessionScope.user.passwordUpdatedAt}" pattern="dd/MM/yyyy HH:mm" timeZone="Asia/Ho_Chi_Minh"/>
                            </c:if>
                            <c:if test="${empty sessionScope.user.passwordUpdatedAt}">
                                Chưa có thông tin
                            </c:if>
                        </div>
                    </div>

                    <div class="card">
                        <h3>Tài khoản liên kết</h3>
                        <div class="linked"><i class="fa-brands fa-google" style="color: #ff7b00;"></i><span>Google</span><span class="linked-status">Liên kết</span></div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/thongTinTaiKhoan.js"></script>

</body>
</html>
