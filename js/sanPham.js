// Lấy các phần tử cần thiết
const specBtn = document.querySelector('.spec-btn');
const specModal = document.getElementById('spec-modal');
const closeBtn = document.getElementById('close-spec-modal');

// Thực hiện đóng mở bảng thông sô sản phẩm
if (specBtn && specModal && closeBtn) {
    // 1. Mở Modal khi click vào nút "Thông số"
    specBtn.addEventListener('click', () => {
        specModal.style.display = 'block';
        // Ngăn cuộn trang chính khi modal mở
        document.body.style.overflow = 'hidden';
    });

    // 2. Đóng Modal khi click vào nút "X"
    closeBtn.addEventListener('click', () => {
        specModal.style.display = 'none';
        document.body.style.overflow = 'auto'; // Bật lại cuộn trang
    });

    // 3. Đóng Modal khi click ra ngoài (trên vùng nền mờ)
    window.addEventListener('click', (event) => {
        if (event.target === specModal) {
            specModal.style.display = 'none';
            document.body.style.overflow = 'auto'; // Bật lại cuộn trang
        }
    });
}

// Thực hiện đóng mở khung viết đánh giá
const btnWriteReview = document.getElementById('btn-write-review');
const reviewModal = document.getElementById('review-modal');
const closeReviewModal = document.getElementById('close-review-modal');

if (btnWriteReview && reviewModal && closeReviewModal) {
    // Mở modal viết đánh giá
    btnWriteReview.addEventListener('click', () => {
        reviewModal.style.display = 'block';
        document.body.style.overflow = 'hidden';
    });

    // Đóng modal
    closeReviewModal.addEventListener('click', () => {
        reviewModal.style.display = 'none';
        document.body.style.overflow = 'auto';
    });

    // Đóng khi click ra ngoài
    window.addEventListener('click', (event) => {
        if (event.target === reviewModal) {
            reviewModal.style.display = 'none';
            document.body.style.overflow = 'auto';
        }
    });
}

// Nút lên đầu trang
const btnScrollTop = document.getElementById('btn-scroll-top');

window.addEventListener('scroll', () => {
    if (window.scrollY > 300) {
        btnScrollTop.classList.add('show');
    } else {
        btnScrollTop.classList.remove('show');
    }
});

btnScrollTop.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});