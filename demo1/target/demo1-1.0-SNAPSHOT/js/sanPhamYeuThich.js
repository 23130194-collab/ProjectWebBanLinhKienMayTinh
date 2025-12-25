document.addEventListener("DOMContentLoaded", function () {
    // Lấy tất cả các nút trái tim trong danh sách yêu thích
    const hearts = document.querySelectorAll('.product-fav-heart');

    // Lấy container chứa danh sách để kiểm tra rỗng sau khi xóa
    const container = document.querySelector('.favorite-grid');

    // Duyệt qua từng nút trái tim để gán sự kiện click
    hearts.forEach(function (heart) {
        heart.addEventListener('click', function () {

            // Bước 1: Hỏi xác nhận (Có thể bỏ bước này nếu muốn xóa ngay)
            const confirmDelete = confirm("Bạn có chắc muốn bỏ sản phẩm này khỏi danh sách yêu thích?");

            if (confirmDelete) {
                // Bước 2: Tìm thẻ cha chứa sản phẩm (product-card-fav)
                const productCard = this.closest('.product-card-fav');

                if (productCard) {
                    // Bước 3: Xóa sản phẩm khỏi DOM
                    productCard.remove();

                    // Bước 4: Kiểm tra xem còn sản phẩm nào không
                    // Nếu không còn thẻ product-card-fav nào thì hiện thông báo trống
                    const remainingProducts = container.querySelectorAll('.product-card-fav');
                    if (remainingProducts.length === 0) {
                        container.innerHTML = '<p style="grid-column: span 2; text-align: center; color: #999; margin-top: 20px;">Bạn chưa có sản phẩm yêu thích nào.</p>';
                    }
                }
            }
        });
    });
});