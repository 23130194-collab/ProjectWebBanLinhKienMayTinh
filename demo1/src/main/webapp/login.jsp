<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Đăng nhập | TechNova</title>

    <link rel="stylesheet" href="css/form.css"/>
    <link rel="stylesheet" href="https://site-assets.fontawesome.com/releases/v6.6.0/css/all.css">
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@600;700&display=swap" rel="stylesheet">
</head>

<body>
<div class="overlay">
    <div class="login-modal">
        <%
            String error = (String) request.getAttribute("error");
            if (error == null) error = "";
            String email = request.getParameter("email");
            if (email == null) email = "";
        %>
        <h2>Chào mừng đến với TechNova!</h2>

        <!-- LOGIN THƯỜNG -->
        <form action="login" method="post" onsubmit="return validateLogin()">
            <div class="input-group">
                <input type="email"
                       name="email"
                       placeholder="Nhập email"
                       value="<%=email%>"
                       required/>
            </div>

            <div class="input-group">
                <input type="password"
                       name="password"
                       placeholder="Nhập mật khẩu"
                       required/>
            </div>

            <c:if test="${not empty error}">
                <span style="color: red; font-size: 12px;">${error}</span>
            </c:if>

            <div class="remember">
                <a href="forgot.jsp">Quên mật khẩu?</a>
            </div>


            <button type="submit" class="login-btn">
                Đăng nhập
            </button>
        </form>


        <!-- PHÂN CÁCH -->
        <div class="divider">
            <span>Hoặc đăng nhập bằng</span>
        </div>

        <!-- SOCIAL LOGIN -->
        <div class="social-login">

            <%--            <!-- GOOGLE LOGIN -->--%>
            <%--            <form action="login-google" method="get">--%>
            <%--                <button type="submit" class="google">--%>
            <%--                    <i class="fa-brands fa-google"></i>--%>
            <%--                    Google--%>
            <%--                </button>--%>
            <%--            </form>--%>

            <%--            <!-- FACEBOOK LOGIN -->--%>
            <%--            <form action="login-facebook" method="get">--%>
            <%--                <button type="submit" class="facebook">--%>
            <%--                    <i class="fa-brands fa-facebook-f"></i>--%>
            <%--                    Facebook--%>
            <%--                </button>--%>
            <%--            </form>--%>
            <button type="button" class="google">
                <img src="https://i.postimg.cc/52XY45D7/z7179766768017-0600811c9c5ce7a039bb0715af80295b.jpg"
                     alt="Google logo">
                Google
            </button>
            <button type="button" class="facebook">
                <img src="https://i.postimg.cc/rsBv3Xyx/facebook.png" alt="Facebook logo">
                Facebook
            </button>
        </div>

        <div class="signup">
            <p>Chưa có tài khoản?
                <a href="signup.jsp">Đăng ký ngay</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>
