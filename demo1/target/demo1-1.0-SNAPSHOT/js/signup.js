// 
document.addEventListener("DOMContentLoaded", () => {
    const signupForm = document.getElementById("signup-form");

    signupForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Ngăn form reload

        //alert("Đăng ký thành công!");
        setTimeout(() => {
            window.location.href = "login.jsp";
        }, 500);
    });
});
