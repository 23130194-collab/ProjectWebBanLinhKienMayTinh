document.addEventListener('DOMContentLoaded', function () {
    function initTinyMCE() {
        if (typeof tinymce !== 'undefined') {
            tinymce.init({
                selector: '#product-description',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                language: 'vi',
                height: 400,
                menubar: false,
                placeholder: 'Nhập mô tả chi tiết cho sản phẩm ở đây...',
                entity_encoding: 'raw',
                verify_html: false,
                forced_root_block: 'p',
                remove_script_host: false,
                convert_urls: false,
                setup: function(editor) {
                    editor.on('init', function() {
                        console.log('TinyMCE đã khởi tạo thành công!');
                        const textarea = document.getElementById('product-description');
                        if (textarea && textarea.value.trim()) {
                            editor.setContent(textarea.value);
                            console.log('Đã load nội dung vào TinyMCE');
                        }
                    });
                }
            });
        } else {
            console.error('TinyMCE chưa được load!');
            setTimeout(initTinyMCE, 500);
        }
    }

    initTinyMCE();

    const btnAddImage = document.getElementById('btn-add-image');
    const imageUrlInput = document.getElementById('image-url-input');
    const imageOrderInput = document.getElementById('image-order-input');
    const previewContainer = document.getElementById('image-preview-container');
    const dataContainer = document.getElementById('image-data-container');

    function loadExistingImages() {
        if (!dataContainer || !previewContainer) return;

        const urlInputs = dataContainer.querySelectorAll('input[name="imageUrls"]');
        const orderInputs = dataContainer.querySelectorAll('input[name="imageOrders"]');

        urlInputs.forEach((urlInput, index) => {
            let imageUrl = urlInput.value;
            const displayOrder = orderInputs[index] ? orderInputs[index].value : '0';
            const imageId = 'img-existing-' + index;

            urlInput.setAttribute('data-image-id', imageId);
            if (orderInputs[index]) {
                orderInputs[index].setAttribute('data-image-id', imageId);
            }

            let finalSrc = imageUrl;
            if (imageUrl && !imageUrl.startsWith('http') && !imageUrl.startsWith('/')) {
                finalSrc = globalContextPath + '/' + imageUrl;
            }

            const thumbItem = document.createElement('div');
            thumbItem.classList.add('thumb-item');
            thumbItem.setAttribute('data-image-id', imageId);

            thumbItem.innerHTML = `
            <img src="${finalSrc}" alt="Preview" onerror="this.src='https://placehold.co/100x100?text=No+Image'">
            <span class="remove-thumb" title="Xóa ảnh này">&times;</span>
            <div class="thumb-order">Thứ tự: ${displayOrder}</div>
        `;

            previewContainer.appendChild(thumbItem);
        });
    }

    loadExistingImages();

    const btnBrowseFile = document.getElementById('btn-browse-file');
    const fileInput = document.getElementById('file-upload-input');
    const uploadStatus = document.getElementById('upload-status');

    if (btnBrowseFile && fileInput) {
        btnBrowseFile.addEventListener('click', function() {
            fileInput.click();
        });

        fileInput.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const file = this.files[0];
                uploadFileToServer(file);
            }
        });
    }

    function uploadFileToServer(file) {
        if(uploadStatus) uploadStatus.style.display = 'block';
        if(btnBrowseFile) btnBrowseFile.disabled = true;
        if(btnAddImage) btnAddImage.disabled = true;

        const formData = new FormData();
        formData.append('file', file);

        const contextPath = (typeof globalContextPath !== 'undefined') ? globalContextPath : '';
        const apiUrl = contextPath + '/api/upload-image';

        fetch(apiUrl, {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.url) {
                    const fullUrl = contextPath + "/" + data.url;
                    imageUrlInput.value = fullUrl;
                    btnAddImage.click();
                } else {
                    alert('Lỗi upload: ' + (data.error || 'Không rõ nguyên nhân'));
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Lỗi kết nối đến server.');
            })
            .finally(() => {
                if(uploadStatus) uploadStatus.style.display = 'none';
                if(btnBrowseFile) btnBrowseFile.disabled = false;
                if(btnAddImage) btnAddImage.disabled = false;
                fileInput.value = '';
            });
    }

    if (btnAddImage) {
        btnAddImage.addEventListener('click', function() {
            const imageUrl = imageUrlInput.value.trim();
            const displayOrder = imageOrderInput.value.trim() || '0';

            if (!imageUrl) {
                alert('Vui lòng nhập URL hoặc tải ảnh lên.');
                return;
            }

            const imageId = 'img-' + Date.now();

            const thumbItem = document.createElement('div');
            thumbItem.classList.add('thumb-item');
            thumbItem.setAttribute('data-image-id', imageId);

            const imgElement = document.createElement('img');
            imgElement.src = imageUrl;
            imgElement.alt = "Preview";
            imgElement.onerror = function() {
                this.onerror = null;
                this.src = 'https://placehold.co/100x100/e2e8f0/475569?text=Lỗi+Ảnh';
            };

            const removeBtn = document.createElement('span');
            removeBtn.className = 'remove-thumb';
            removeBtn.innerHTML = '&times;';
            removeBtn.title = 'Xóa ảnh này';

            const orderDiv = document.createElement('div');
            orderDiv.className = 'thumb-order';
            orderDiv.textContent = `Thứ tự: ${displayOrder}`;

            thumbItem.appendChild(imgElement);
            thumbItem.appendChild(removeBtn);
            thumbItem.appendChild(orderDiv);
            previewContainer.appendChild(thumbItem);

            const urlHiddenInput = document.createElement('input');
            urlHiddenInput.type = 'hidden';
            urlHiddenInput.name = 'imageUrls';
            urlHiddenInput.value = imageUrl;
            urlHiddenInput.setAttribute('data-image-id', imageId);

            const orderHiddenInput = document.createElement('input');
            orderHiddenInput.type = 'hidden';
            orderHiddenInput.name = 'imageOrders';
            orderHiddenInput.value = displayOrder;
            orderHiddenInput.setAttribute('data-image-id', imageId);

            dataContainer.appendChild(urlHiddenInput);
            dataContainer.appendChild(orderHiddenInput);

            imageUrlInput.value = '';
            imageOrderInput.value = '';
            imageUrlInput.focus();
        });

        previewContainer.addEventListener('click', function(e) {
            if (e.target && e.target.classList.contains('remove-thumb')) {
                const thumbItem = e.target.closest('.thumb-item');
                if (thumbItem) {
                    const imageId = thumbItem.getAttribute('data-image-id');
                    thumbItem.remove();
                    const inputsToRemove = dataContainer.querySelectorAll(`[data-image-id="${imageId}"]`);
                    inputsToRemove.forEach(input => input.remove());
                }
            }
        });
    }

    const categorySelect = document.getElementById('category-select');
    const attributeSelect = document.getElementById('attribute-select');
    const attributesContainer = document.getElementById('attributes-container');

    if (categorySelect && attributeSelect && attributesContainer) {

        categorySelect.addEventListener('change', function() {
            const categoryId = this.value;
            attributeSelect.innerHTML = '<option value="">Đang tải...</option>';

            if(categoryId) {
                const contextPath = (typeof globalContextPath !== 'undefined') ? globalContextPath : '';
                const apiUrl = contextPath + '/api/get-attributes?categoryId=' + categoryId;

                fetch(apiUrl)
                    .then(res => res.json())
                    .then(data => {
                        attributeSelect.innerHTML = '<option value="">-- Chọn thuộc tính --</option>';
                        if (data.length === 0) {
                            attributeSelect.innerHTML = '<option value="">(Không có thuộc tính nào)</option>';
                        } else {
                            data.forEach(attr => {
                                const option = document.createElement('option');
                                option.value = attr.id;
                                option.textContent = attr.name;
                                option.dataset.name = attr.name;
                                attributeSelect.appendChild(option);
                            });
                        }
                    })
                    .catch(err => {
                        console.error(err);
                        attributeSelect.innerHTML = '<option value="">Lỗi tải dữ liệu</option>';
                    });
            } else {
                attributeSelect.innerHTML = '<option value="">Chọn thuộc tính</option>';
            }
        });

        if (categorySelect.value) {
            categorySelect.dispatchEvent(new Event('change'));
        }

        attributeSelect.addEventListener('change', function() {
            const selectedOption = this.options[this.selectedIndex];
            const attrId = selectedOption.value;
            const attrName = selectedOption.dataset.name;

            if (!attrId) return;

            if (attributesContainer.querySelector(`input[name="specIds"][value="${attrId}"]`)) {
                alert("Thuộc tính này đã được thêm!");
                this.value = "";
                return;
            }

            const itemDiv = document.createElement('div');
            itemDiv.className = 'attribute-item active';

            itemDiv.innerHTML = `
                <div class="attr-top">
                    <span class="attr-name">${attrName}</span>
                    <div class="attr-controls">
                        <span class="attr-delete" title="Xóa thuộc tính này">Xóa</span>
                        <i class="fa-solid fa-chevron-down attr-toggle-icon"></i>
                    </div>
                </div>
                <div class="attr-body">
                    <input type="hidden" name="specIds" value="${attrId}">
                    <div class="attr-row">
                        <span class="attr-label">Tên:</span>
                        <span class="attr-static-val">${attrName}</span>
                    </div>
                    <div class="attr-row">
                        <span class="attr-label">Giá trị:</span>
                        <input type="text" name="specValues" class="form-input" placeholder="Nhập giá trị..." required>
                    </div>
                </div>
            `;
            attributesContainer.appendChild(itemDiv);
            this.value = "";
        });

        attributesContainer.addEventListener('click', function(e) {
            const target = e.target;

            if (target.classList.contains('attr-delete')) {
                e.stopPropagation();
                if(confirm('Bạn có chắc muốn xóa thuộc tính này?')) {
                    const item = target.closest('.attribute-item');
                    if(item) item.remove();
                }
                return;
            }

            const header = target.closest('.attr-top');
            if (header) {
                const item = header.closest('.attribute-item');
                if (item) item.classList.toggle('active');
            }
        });
    }
});