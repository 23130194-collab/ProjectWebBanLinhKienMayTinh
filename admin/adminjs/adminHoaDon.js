
    document.addEventListener('DOMContentLoaded', () => {
    // Lấy các phần tử DOM
    const orderListView = document.getElementById('order-list-view');
    const orderDetailView = document.getElementById('order-detail-view');
    const backBtn = document.getElementById('back-to-list-btn');


    const orderLinks = orderListView.querySelectorAll('tbody td a');

    orderLinks.forEach(link => {
    link.addEventListener('click', (event) => {

    event.preventDefault();

    orderListView.style.display = 'none';


    orderDetailView.style.display = 'block';

});
});

    backBtn.addEventListener('click', () => {

    orderDetailView.style.display = 'none';

    orderListView.style.display = 'block';
});
});
