// Toggle Filter
const filterBtn = document.getElementById("filterToggleBtn");
const filterSection = document.querySelector(".filter-section");

// Lắng nghe sự kiện click nút Bộ lọc
filterBtn.addEventListener("click", () => {
    filterSection.classList.toggle("active");
});


// Nút Hủy
const cancelBtn = document.querySelector(".cancel-button");
cancelBtn.addEventListener("click", () => {
    filterSection.classList.remove("active");
});

// Nút Xác nhận
const confirmBtn = document.querySelector(".confirm-button");
confirmBtn.addEventListener("click", () => {
    // alert("Áp dụng bộ lọc thành công!");
    filterSection.classList.remove("active");
});