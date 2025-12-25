//  Web tĩnh — chỉ xử lý thông báo gửi thành công
document.addEventListener("DOMContentLoaded", () => {
    const forgotForm = document.getElementById("forgot-password-form");

    forgotForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Ngăn form reload
        const email = document.getElementById("forgot-email").value.trim();

        // alert(` Hướng dẫn đặt lại mật khẩu đã được gửi đến ${email}`);
        setTimeout(() => {
            window.location.href = "login.jsp";
        }, 500);
    });
});
