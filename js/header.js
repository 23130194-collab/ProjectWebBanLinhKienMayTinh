const categoryToggle = document.getElementById("category-toggle");
const categoryBox = document.getElementById("categoryBox");
const overlay = document.getElementById("overlay");

categoryToggle.addEventListener("click", (e) => {
  e.preventDefault();
  categoryBox.classList.toggle("show");
  overlay.classList.toggle("active");
});

overlay.addEventListener("click", () => {
  categoryBox.classList.remove("show");
  overlay.classList.remove("active");
});

//  Thêm chức năng chuyển trang cho nút giỏ hàng và đăng nhập
document.querySelector(".cart-btn").addEventListener("click", () => {
  window.location.href = "cart.html";
});

document.querySelector(".login-btn").addEventListener("click", () => {
  window.location.href = "login.html";
});