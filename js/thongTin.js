document.addEventListener("DOMContentLoaded", () => {
    const methods = document.querySelectorAll(".method");

    methods.forEach(method => {
        method.addEventListener("click", () => {
            // Xóa trạng thái active khỏi tất cả ô
            methods.forEach(m => m.classList.remove("active"));
            // Gán active cho ô vừa bấm
            method.classList.add("active");

            const text = method.textContent.trim();

            // Điều hướng sang trang tương ứng
            if (text === "Giao hàng tận nơi") {
                window.location.href = "giaoHangTanNha.html";
            } else if (text === "Nhận tại cửa hàng") {
                window.location.href = "thongTinNhanHang.html";
            }
        });
    });

    // Nút quay lại
    const backBtn = document.querySelector(".header-cart i.fa-arrow-left");
    if (backBtn) {
        backBtn.addEventListener("click", () => {
            window.location.href = "thongTinNhanHang.html";
        });
    }
});

