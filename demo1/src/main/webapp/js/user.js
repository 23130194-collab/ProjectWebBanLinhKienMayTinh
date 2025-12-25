(function () {
    const tabs = document.querySelectorAll('.tab');
    const orders = document.querySelectorAll('.order');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            const key = tab.dataset.tab;

            orders.forEach(order => {
                if (key === 'all' || order.dataset.status === key) {
                    order.style.display = 'flex';
                } else {
                    order.style.display = 'none';
                }
            });
        });
    });
})();

// JS tĩnh cho chuyển đổi giữa các menu
const menuItems = document.querySelectorAll('.menu-item');
const sections = document.querySelectorAll('.section');

menuItems.forEach(item => {
    item.addEventListener('click', () => {
        menuItems.forEach(i => i.classList.remove('active'));
        item.classList.add('active');

        const target = item.dataset.target;
        sections.forEach(sec => {
            sec.classList.toggle('active', sec.id === target);
        });
    });
});

//chi tiet sp

(function () {
    //  Tabs lọc đơn hàng
    const tabs = document.querySelectorAll('.tab');
    const orders = document.querySelectorAll('.order');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            const key = tab.dataset.tab;

            orders.forEach(order => {
                order.style.display = (key === 'all' || order.dataset.status === key) ? 'flex' : 'none';
            });
        });
    });

    //  Các phần tử chính
    const menuItems = document.querySelectorAll('.menu-item');
    const sections = document.querySelectorAll('.section');
    const orderLinks = document.querySelectorAll('.small-link');
    const backToOrdersBtn = document.getElementById('backToOrders');

    //  Hàm chuyển section + active menu
    function showSection(targetId) {
        // Hiển thị section
        sections.forEach(sec => {
            sec.classList.toggle('active', sec.id === targetId);
        });

        // Active menu đúng
        menuItems.forEach(item => {
            const isOrdersMenu = item.dataset.target === 'orders';
            const shouldActive =
                (isOrdersMenu && (targetId === 'orders' || targetId === 'order-details')) ||
                item.dataset.target === targetId;

            item.classList.toggle('active', shouldActive);
        });
    }

    //  Click menu
    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            showSection(item.dataset.target);
        });
    });

    //  Xem chi tiết đơn hàng
    orderLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            const orderEl = e.currentTarget.closest('.order');
            const status = orderEl.dataset.status;
            const orderId = orderEl.querySelector('.details .meta strong').textContent;
            const orderDate = orderEl.querySelector('.details .meta').textContent.split('•')[1].trim();

            // Cập nhật thông tin
            document.getElementById('detail-order-id').textContent = orderId;
            document.getElementById('detail-order-date').textContent = orderDate;

            // Cập nhật tiến độ
            updateProgressView(status);

            // Chuyển sang chi tiết
            showSection('order-details');
        });
    });

    // . Nút quay lại
    backToOrdersBtn.addEventListener('click', () => {
        showSection('orders');
    });

    //  Hàm cập nhật tiến độ
    function updateProgressView(status) {
        const steps = {
            pending: document.getElementById('step-pending'),
            confirmed: document.getElementById('step-confirmed'),
            delivered: document.getElementById('step-delivered')
        };
        const statusText = document.getElementById('detail-order-status-text');
        const progress = document.getElementById('orderProgressSteps');
        const cancelled = document.getElementById('orderCancelledNotice');

        // Reset
        Object.values(steps).forEach(s => s?.classList.remove('active'));
        progress.style.display = 'flex';
        cancelled.style.display = 'none';

        if (status === 'pending') {
            steps.pending.classList.add('active');
            statusText.className = 'status-pill pending';
            statusText.textContent = 'Chờ xác nhận';
        } else if (status === 'confirmed' || status === 'shipping') {
            steps.pending.classList.add('active');
            steps.confirmed.classList.add('active');
            statusText.className = status === 'shipping' ? 'status-pill shipping' : 'status-pill confirmed';
            statusText.textContent = status === 'shipping' ? 'Đang vận chuyển' : 'Đã xác nhận';
        } else if (status === 'delivered') {
            Object.values(steps).forEach(s => s.classList.add('active'));
            statusText.className = 'status-pill delivered';
            statusText.textContent = 'Đã giao hàng';
        } else if (status === 'cancelled') {
            progress.style.display = 'none';
            cancelled.style.display = 'block';
            statusText.className = 'status-pill cancelled';
            statusText.textContent = 'Đã hủy';
        }
    }

    //  Khởi tạo: hiển thị Đơn hàng
    showSection('orders');
})();


// ĐĂNG XUẤT → CHUYỂN VỀ INDEX.HTML
document.querySelector('[data-target="logout"]').addEventListener('click', () => {
    window.location.href = 'index.html';
});

