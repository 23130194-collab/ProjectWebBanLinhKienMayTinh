document.addEventListener('DOMContentLoaded', function() {

    // ==============================================================
    // 1. CÁC XỬ LÝ GIAO DIỆN (UI) - KHÔNG CẦN SỬA KHI QUA JSP
    // ==============================================================

    // --- Xử lý Modal Thông số ---
    const specBtn = document.querySelector('.spec-btn');
    const specModal = document.getElementById('spec-modal');
    const closeSpecBtn = document.getElementById('close-spec-modal');

    if (specBtn && specModal && closeSpecBtn) {
        specBtn.addEventListener('click', () => {
            specModal.style.display = 'block';
            document.body.style.overflow = 'hidden';
        });
        closeSpecBtn.addEventListener('click', () => {
            specModal.style.display = 'none';
            document.body.style.overflow = 'auto';
        });
        window.addEventListener('click', (e) => {
            if (e.target === specModal) {
                specModal.style.display = 'none';
                document.body.style.overflow = 'auto';
            }
        });
    }

    // --- Xử lý Modal Viết Đánh Giá ---
    const btnWriteReview = document.getElementById('btn-write-review');
    const reviewModal = document.getElementById('review-modal');
    const closeReviewModal = document.getElementById('close-review-modal');

    if (btnWriteReview && reviewModal && closeReviewModal) {
        btnWriteReview.addEventListener('click', () => {
            reviewModal.style.display = 'block';
            document.body.style.overflow = 'hidden';
        });
        closeReviewModal.addEventListener('click', () => {
            reviewModal.style.display = 'none';
            document.body.style.overflow = 'auto';
        });
        window.addEventListener('click', (e) => {
            if (e.target === reviewModal) {
                reviewModal.style.display = 'none';
                document.body.style.overflow = 'auto';
            }
        });
    }

    // --- Nút lên đầu trang ---
    const btnScrollTop = document.getElementById('btn-scroll-top');
    if (btnScrollTop) {
        window.addEventListener('scroll', () => {
            if (window.scrollY > 300) btnScrollTop.classList.add('show');
            else btnScrollTop.classList.remove('show');
        });
        btnScrollTop.addEventListener('click', () => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    }

    // --- Hiệu ứng chọn Sao (Giao diện) ---
    const ratingInputs = document.querySelectorAll('.rating-input');
    const ratingOptions = document.querySelectorAll('.rating-option');
    const ratingGroup = document.querySelector('.rating-options');

    if (ratingGroup) {
        function highlightStars(count) {
            ratingOptions.forEach((option, index) => {
                if (index < count) option.classList.add('star-fill');
                else option.classList.remove('star-fill');
            });
        }

        function resetStars() {
            let checkedIndex = -1;
            ratingInputs.forEach((input, index) => {
                if (input.checked) checkedIndex = index;
            });
            highlightStars(checkedIndex + 1);
        }

        ratingOptions.forEach((option, index) => {
            option.addEventListener('mouseenter', () => highlightStars(index + 1));
        });
        ratingGroup.addEventListener('mouseleave', resetStars);
        ratingInputs.forEach((input) => {
            input.addEventListener('change', resetStars);
        });
        resetStars(); // Chạy lần đầu
    }

    // --- Chọn ảnh Thumbnail ---
    const mainImage = document.getElementById('main-product-img');
    const thumbnailImages = document.querySelectorAll('.thumbnails-wrapper img');

    if (mainImage && thumbnailImages.length > 0) {
        thumbnailImages.forEach(thumb => {
            thumb.addEventListener('click', function() {
                // Lấy ảnh gốc từ data attribute
                const newSrc = this.getAttribute('data-main-img');
                if (newSrc) mainImage.src = newSrc;

                // Active class
                thumbnailImages.forEach(t => t.classList.remove('active'));
                this.classList.add('active');
            });
        });
    }

    // --- Slider Thumbnail ---
    const sliderWrapper = document.querySelector('.thumbnails-wrapper');
    const btnPrev = document.querySelector('.thumb-nav.prev-btn');
    const btnNext = document.querySelector('.thumb-nav.next-btn');

    if (sliderWrapper && btnPrev && btnNext) {
        const scrollAmount = (75 + 10) * 2; // Kích thước ảnh + gap
        btnNext.addEventListener('click', () => {
            sliderWrapper.scrollBy({ left: scrollAmount, behavior: 'smooth' });
        });
        btnPrev.addEventListener('click', () => {
            sliderWrapper.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
        });
    }

    // --- Xử lý nút Gửi đánh giá (Chỉ lấy nội dung, không xử lý gì thêm) ---
    const btnSubmitReview = document.querySelector('.btn-submit-review');
    const txtReviewContent = document.querySelector('.review-comment-box textarea');

    if (btnSubmitReview && txtReviewContent) {
        btnSubmitReview.addEventListener('click', (e) => {
            e.preventDefault(); // Ngăn form submit theo cách truyền thống
            // 1. Lấy nội dung comment
            const content = txtReviewContent.value;

            // 2. Lấy số sao (dựa vào radio button đang được check)
            const ratingInput = document.querySelector('input[name="rating"]:checked');
            const star = ratingInput ? ratingInput.value : '5';

            if(content.trim() === "") {
                alert("Bạn chưa nhập nội dung đánh giá!");
            } else {
                document.getElementById('form-review-product').submit();
            }
        });
    }

    // ==============================================================
    // XỬ LÝ ĐÁNH GIÁ ĐỘNG VỚI AJAX (PHÂN TRANG + LỌC)
    // ==============================================================
    const reviewsList = document.querySelector('.reviews-list');
    const btnSeeMore = document.querySelector('.btn-see-more');
    const filterReviewInputs = document.querySelectorAll('.reviews-filter .filter-input');
    const productIdInput = document.querySelector('input[name="productId"]');

    if (reviewsList && btnSeeMore && filterReviewInputs.length > 0 && productIdInput) {
        const productId = productIdInput.value;

        let currentOffset = 5; // Bắt đầu lấy từ vị trí thứ 5 (vì 5 cái đầu đã được render sẵn)
        let currentFilter = 0; // 0 = tất cả
        let isLoading = false;

        // Hàm để tạo HTML cho một review
        function createReviewElement(review) {
            const reviewItem = document.createElement('div');
            reviewItem.className = 'review-item';
            reviewItem.setAttribute('data-rating', review.rating);

            const formattedDate = new Date(review.created_at).toLocaleDateString('vi-VN');
            
            let ratingLabel = 'Bình thường';
            if (review.rating >= 5) ratingLabel = 'Tuyệt vời';
            else if (review.rating >= 4) ratingLabel = 'Tốt';
            else if (review.rating < 3) ratingLabel = 'Tệ';

            let starsHtml = '';
            for (let i = 1; i <= 5; i++) {
                starsHtml += `<i class="fa-solid fa-star ${review.rating >= i ? 'star-active' : 'star-grey'}"></i>`;
            }

            reviewItem.innerHTML = `
                <div class="reviewer-avatar">${review.user_name.substring(0, 1)}</div>
                <div class="review-content">
                    <div class="reviewer-name">${review.user_name}</div>
                    <div class="review-rating">
                        ${starsHtml}
                        <span class="rating-label-text">${ratingLabel}</span>
                    </div>
                    <div class="review-text">${review.comment}</div>
                    <div class="review-time">
                        <i class="fa-regular fa-clock"></i>
                        Đánh giá đã đăng vào: ${formattedDate}
                    </div>
                </div>
            `;
            return reviewItem;
        }

        // Hàm chính để gọi API và render reviews
        async function fetchReviews() {
            if (isLoading) return;
            isLoading = true;
            btnSeeMore.textContent = 'Đang tải...';
            btnSeeMore.disabled = true;

            try {
                const response = await fetch(`api/reviews?productId=${productId}&filter=${currentFilter}&offset=${currentOffset}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const newReviews = await response.json();

                if (newReviews.length > 0) {
                    newReviews.forEach(review => {
                        const reviewElement = createReviewElement(review);
                        reviewsList.appendChild(reviewElement);
                    });
                    currentOffset += newReviews.length;
                    if (newReviews.length < 5) {
                         btnSeeMore.style.display = 'none'; // Ẩn nút nếu đã hết
                    }
                } else {
                    btnSeeMore.style.display = 'none'; // Ẩn nút nếu không còn review nào
                }

            } catch (error) {
                console.error('Fetch error:', error);
                btnSeeMore.textContent = 'Lỗi, thử lại?';
            } finally {
                isLoading = false;
                btnSeeMore.disabled = false;
                if(btnSeeMore.textContent === 'Đang tải...') {
                     btnSeeMore.textContent = 'Xem thêm đánh giá';
                }
            }
        }

        // Gắn sự kiện cho nút "Xem thêm"
        btnSeeMore.addEventListener('click', fetchReviews);

        // Gắn sự kiện cho các nút lọc
        filterReviewInputs.forEach(input => {
            input.addEventListener('change', function() {
                const newFilterValue = this.id.replace('filter-', '').replace('star','');
                currentFilter = (newFilterValue === 'all') ? 0 : parseInt(newFilterValue);
                
                // Reset
                currentOffset = 0;
                reviewsList.innerHTML = ''; // Xóa các review cũ
                btnSeeMore.style.display = 'flex'; // Hiện lại nút xem thêm
                
                // Tải lại từ đầu
                fetchReviews();
            });
        });
    }
});