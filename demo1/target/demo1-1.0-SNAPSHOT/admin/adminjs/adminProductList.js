document.addEventListener('DOMContentLoaded', function () {

    // ==================================================
    // 1. XỬ LÝ CHUYỂN TAB (TẤT CẢ, ĐANG BÁN, HẾT HÀNG...)
    // ==================================================
    const tabs = document.querySelectorAll('.product-tabs .tab');

    tabs.forEach(tab => {
        tab.addEventListener('click', function () {
            // Xóa class 'active' ở tất cả các tab
            tabs.forEach(t => t.classList.remove('active'));

            // Thêm class 'active' vào tab vừa click
            this.classList.add('active');

            // Log ra console để kiểm tra (Sau này bạn sẽ gọi API lọc dữ liệu ở đây)
            console.log('Đã chọn tab:', this.innerText);
        });
    });

    // ==================================================
    // 2. XỬ LÝ PHÂN TRANG (PAGINATION)
    // ==================================================
    const pageNumbers = document.querySelectorAll('.page-number');
    const prevBtn = document.querySelector('.pagination-btn:first-child'); // Nút Previous
    const nextBtn = document.querySelector('.pagination-btn:last-child');  // Nút Next

    // Xử lý khi click vào số trang (1, 2, 5...)
    pageNumbers.forEach(page => {
        page.addEventListener('click', function (e) {
            e.preventDefault(); // Ngăn chặn thẻ a load lại trang

            // Xóa active cũ
            const currentActive = document.querySelector('.page-number.active');
            if (currentActive) {
                currentActive.classList.remove('active');
            }

            // Active trang vừa click
            this.classList.add('active');
            console.log('Chuyển đến trang:', this.innerText);
        });
    });

    // Xử lý nút Previous (Minh họa logic đơn giản)
    if (prevBtn) {
        prevBtn.addEventListener('click', function () {
            console.log('Click nút Previous');
            // Logic lùi trang sẽ viết ở đây
        });
    }

    // Xử lý nút Next (Minh họa logic đơn giản)
    if (nextBtn) {
        nextBtn.addEventListener('click', function () {
            console.log('Click nút Next');
            // Logic tiến trang sẽ viết ở đây
        });
    }

    // ==================================================
    // 3. XỬ LÝ CHECKBOX "CHỌN TẤT CẢ"
    // ==================================================
    const selectAllCheckbox = document.getElementById('selectAllProducts');
    const rowCheckboxes = document.querySelectorAll('.row-check');

    if (selectAllCheckbox) {
        selectAllCheckbox.addEventListener('change', function () {
            const isChecked = this.checked;

            // Duyệt qua tất cả checkbox ở các dòng và set trạng thái theo checkbox tổng
            rowCheckboxes.forEach(checkbox => {
                checkbox.checked = isChecked;
            });
        });
    }

    // (Tùy chọn) Nếu bỏ chọn 1 dòng thì bỏ chọn checkbox tổng
    rowCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            if (!this.checked) {
                selectAllCheckbox.checked = false;
            } else {
                // Kiểm tra xem tất cả có đang được check không
                const allChecked = Array.from(rowCheckboxes).every(cb => cb.checked);
                selectAllCheckbox.checked = allChecked;
            }
        });
    });

    // ==================================================
    // 4. XỬ LÝ CÁC NÚT THAO TÁC (SỬA, XEM, XÓA)
    // ==================================================

    // Nút Sửa
    const editBtns = document.querySelectorAll('.action-btn.edit');
    editBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            // Lấy tên sản phẩm của dòng đó để demo
            const productName = this.closest('tr').querySelector('.product-name').innerText;
            console.log('Đang sửa sản phẩm:', productName);
            // window.location.href = 'editProduct.html'; // Chuyển trang nếu cần
        });
    });

    // Nút Xem
    const viewBtns = document.querySelectorAll('.action-btn.view');
    viewBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const productName = this.closest('tr').querySelector('.product-name').innerText;
            console.log('Đang xem chi tiết:', productName);
        });
    });

    // Nút Xóa
    const deleteBtns = document.querySelectorAll('.action-btn.delete');
    deleteBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const productName = this.closest('tr').querySelector('.product-name').innerText;
            if(confirm(`Bạn có chắc muốn xóa sản phẩm: "${productName}" không?`)) {
                console.log('Đã xác nhận xóa:', productName);
                // Code xóa dòng khỏi bảng (Giao diện)
                this.closest('tr').remove();
            }
        });
    });

    // ==================================================
    // 5. TÌM KIẾM
    // ==================================================
    const searchInput = document.querySelector('.search-input-product');
    if (searchInput) {
        searchInput.addEventListener('keyup', function(e) {
            if (e.key === 'Enter') {
                console.log('Đang tìm kiếm:', this.value);
                // Gọi hàm search tại đây
            }
        });
    }
});