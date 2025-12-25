const noticeToggle = document.getElementById("openNotice");
const noticeBox = document.getElementById("noticeBox");
const noticeOverlay = document.getElementById("noticeOverlay");
const closeNoticeBtn = document.getElementById("closeNoticeBtn");

noticeToggle.addEventListener("click", (e) => {
    e.preventDefault();
    noticeBox.style.display = "block";
    noticeOverlay.style.display = "block";
});

closeNoticeBtn.addEventListener("click", () => {
    noticeBox.style.display = "none";
    noticeOverlay.style.display = "none";
});

noticeOverlay.addEventListener("click", () => {
    noticeBox.style.display = "none";
    noticeOverlay.style.display = "none";
});
