
  const editBtn = document.getElementById('editBtn');
  const infoView = document.getElementById('infoView');
  const infoForm = document.getElementById('infoForm');
  const saveBtn = document.getElementById('saveBtn');
  const cancelBtn = document.getElementById('cancelBtn');

  editBtn.addEventListener('click', () => {
    infoView.classList.add('hidden');
    infoForm.classList.remove('hidden');
  });

  cancelBtn.addEventListener('click', () => {
    infoForm.classList.add('hidden');
    infoView.classList.remove('hidden');
  });

  saveBtn.addEventListener('click', () => {
    // Lấy dữ liệu từ input
    document.getElementById('name').textContent = document.getElementById('inputName').value;
    document.getElementById('phone').textContent = document.getElementById('inputPhone').value;
    document.getElementById('gender').textContent = document.getElementById('inputGender').value;
    document.getElementById('email').textContent = document.getElementById('inputEmail').value;
    document.getElementById('dob').textContent = document.getElementById('inputDob').value;
    document.getElementById('address').textContent = document.getElementById('inputAddress').value;

    // Ẩn form, hiện lại khung thông tin
    infoForm.classList.add('hidden');
    infoView.classList.remove('hidden');
  });

