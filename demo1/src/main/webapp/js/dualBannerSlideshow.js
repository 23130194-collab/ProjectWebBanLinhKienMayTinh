document.addEventListener('DOMContentLoaded', function () {

    // Hàm chung xử lý logic cho MỘT banner
    function setupSlider(sliderId) {
        const sliderElement = document.getElementById(sliderId);
        if (!sliderElement) return;

        const wrapper = sliderElement.querySelector('.slides-wrapper');
        const slides = wrapper.querySelectorAll('.banner-img');
        const prevBtn = sliderElement.querySelector('.prev-btn');
        const nextBtn = sliderElement.querySelector('.next-btn');
        const dotsContainer = sliderElement.querySelector('.dots-container');

        let currentSlideIndex = 0;
        let slideWidth = slides[0].clientWidth;
        let autoSlideInterval;

        // Chuyển slide

        // Phương thức  chuyển slide
        function goToSlide(index) {
            if (index < 0) {
                index = slides.length - 1;
            } else if (index >= slides.length) {
                index = 0;
            }
            currentSlideIndex = index;

            // Tính  khoảng dịch chuyển và dùng transform
            const offset = -currentSlideIndex * slideWidth;
            wrapper.style.transform = `translateX(${offset}px)`;

            // Cập nhật dots
            updateDots();
        }

        // Phương thức chuyển slide tiếp theo
        function nextSlide() {
            goToSlide(currentSlideIndex + 1);
        }

        // Phương thức tạo tự động chuyển động
        function startAutoSlideshow() {
            // Xóa interval cũ nếu có
            if (autoSlideInterval) {
                clearInterval(autoSlideInterval);
            }
            // Thiết lập interval mới: cứ mỗi 4 giây thì chuyển silde một lần
            autoSlideInterval = setInterval(nextSlide, 4000);
        }

        // Phương thức xử lý khi cửa sổ thay đổi kích thước
        function handleResize() {
            slideWidth = slides[0].clientWidth;
            goToSlide(currentSlideIndex); // Dịch chuyển lại đến slide hiện tại
        }

        // Cái dấu thể hiện đang ở slide nào
        function createDots() {
            dotsContainer.innerHTML = '';
            slides.forEach((_, index) => {
                const dot = document.createElement('span');
                dot.classList.add('dot');
                if (index === 0) dot.classList.add('active');

                dot.addEventListener('click', () => {
                    goToSlide(index);
                    startAutoSlideshow(); // Reset tự động chuyển
                });
                dotsContainer.appendChild(dot);
            });
        }

        function updateDots() {
            dotsContainer.querySelectorAll('.dot').forEach((dot, index) => {
                dot.classList.toggle('active', index === currentSlideIndex);
            });
        }

        // Có thể nắm kéo các slide
        let isDragging = false;
        let startPos = 0;
        let currentTranslate = 0;
        let prevTranslate = 0;
        let animationID = 0;

        // Bắt đầu kéo
        function dragStart(event) {
            // Ngừng tự động chuyển động khi bắt đầu kéo
            clearInterval(autoSlideInterval);

            isDragging = true;

            // Xác định vị trí bắt đầu (dùng clientX cho cả mouse và touch)
            startPos = event.type.includes('mouse') ? event.clientX : event.touches[0].clientX;

            // Xóa transition để kéo mượt hơn
            wrapper.style.transition = 'none';
        }

        // Đang kéo
        function drag(event) {
            if (!isDragging) return;

            const currentPos = event.type.includes('mouse') ? event.clientX : event.touches[0].clientX;

            // Tính khoảng cách dịch chuyển
            currentTranslate = prevTranslate + currentPos - startPos;

            // Dùng để chuyển trực tiếp
            wrapper.style.transform = `translateX(${currentTranslate}px)`;
        }

        // Kết thúc kéo
        function dragEnd() {
            if (!isDragging) return;
            isDragging = false;

            // Cho xuất hiện transition lại
            wrapper.style.transition = 'transform 0.5s ease-in-out';

            const movedBy = currentTranslate - prevTranslate;
            const threshold = slideWidth / 4; // Cái khoảng nắm kéo tới một khoảng giới haạn

            if (movedBy < -threshold) {
                // Kéo sang trái (Next Slide)
                goToSlide(currentSlideIndex + 1);
            } else if (movedBy > threshold) {
                // Kéo sang phải (Previous Slide)
                goToSlide(currentSlideIndex - 1);
            } else {
                // Dịch chuyển quá ít, quay lại slide hiện tại
                goToSlide(currentSlideIndex);
            }

            // Cập nhật vị trí dịch chuyển trước đó
            prevTranslate = -currentSlideIndex * slideWidth;

            // Khởi động lại tự động chuyển động
            startAutoSlideshow();
        }

        // Bắt đầu thực hiện

        // Cần lấy width của slide đầu tiên sau khi CSS được render
        slideWidth = slides[0].clientWidth;
        prevTranslate = 0; // Vị trí ban đầu là 0

        createDots(); // Tạo dấu chấm
        startAutoSlideshow(); // Bắt đầu tự động

        // Cho slide tự chuyển động
        prevBtn.addEventListener('click', () => {
            goToSlide(currentSlideIndex - 1);
            startAutoSlideshow(); // Reset tự động chuyển
        });
        nextBtn.addEventListener('click', () => {
            goToSlide(currentSlideIndex + 1);
            startAutoSlideshow(); // Reset tự động chuyển
        });

        // Kéo/thả slide
        wrapper.addEventListener('mousedown', dragStart);
        window.addEventListener('mousemove', drag);
        window.addEventListener('mouseup', dragEnd);
        // Ngăn chặn kéo ảnh mặc định
        wrapper.addEventListener('mouseleave', dragEnd);
        wrapper.addEventListener('dragstart', e => e.preventDefault());

        // Lướt slide
        wrapper.addEventListener('touchstart', dragStart);
        wrapper.addEventListener('touchmove', drag);
        wrapper.addEventListener('touchend', dragEnd);

        // Resize
        window.addEventListener('resize', handleResize);
    }

    // Tạo slideshow cho cả hai banner
    setupSlider('banner-left');
    setupSlider('banner-right');
});