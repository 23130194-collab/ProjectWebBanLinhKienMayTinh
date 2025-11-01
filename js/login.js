//  Web tĩnh — chỉ xử lý thông báo và chuyển hướng
document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");

    loginForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Ngăn form reload

       // alert("Đăng nhập thành công!");
        setTimeout(() => {
            window.location.href = "index.html";
        }, 500);
    });
});
