
console.log("Header JS running...");

// =======================
// Xử lý Danh mục
// =======================
const toggleMenu = document.getElementById("toggleMenu");
const overlay = document.getElementById("overlay");
const categoryBox = document.getElementById("categoryBox");

if (!toggleMenu || !overlay || !categoryBox) {
  console.warn("⚠️ Không tìm thấy phần tử danh mục (toggleMenu / overlay / categoryBox)");
} else {
  console.log("Danh mục đã sẵn sàng");

  const openMenu = () => {
    categoryBox.classList.add("show");
    overlay.classList.add("active");
  };

  const closeMenu = () => {
    categoryBox.classList.remove("show");
    overlay.classList.remove("active");
  };

  toggleMenu.addEventListener("click", (e) => {
    e.preventDefault();
    categoryBox.classList.contains("show") ? closeMenu() : openMenu();
  });

  overlay.addEventListener("click", closeMenu);

  // Ẩn danh mục khi click ra ngoài vùng menu
  document.addEventListener("click", (e) => {
    if (!categoryBox.contains(e.target) && !toggleMenu.contains(e.target)) {
      closeMenu();
    }
  });
}

// =======================
//Xử lý Menu tài khoản
// =======================
const accountBtn = document.getElementById("accountBtn");
const accountMenu = document.getElementById("accountMenu");
const logoutBtn = document.getElementById("logoutBtn");

if (!accountBtn || !accountMenu) {
  console.warn("Không tìm thấy phần tử tài khoản (accountBtn / accountMenu)");
} else {
  console.log("Menu tài khoản đã sẵn sàng");

  // Hiện / ẩn menu tài khoản khi bấm nút
  accountBtn.addEventListener("click", (e) => {
    e.preventDefault();
    accountMenu.classList.toggle("show");
  });

  // Ẩn menu khi click ra ngoài
  document.addEventListener("click", (e) => {
    if (!accountBtn.contains(e.target) && !accountMenu.contains(e.target)) {
      accountMenu.classList.remove("show");
    }
  });

  // Xử lý nút Đăng xuất
  if (logoutBtn) {
    logoutBtn.addEventListener("click", (e) => {
      e.preventDefault();
      alert("Bạn đã đăng xuất!");
      accountMenu.classList.remove("show");
    });
  }
}
