package com.example.demo1.service;

import com.example.demo1.dao.BrandDao;
import com.example.demo1.dao.ProductDao;
import com.example.demo1.model.Brand;
import com.example.demo1.model.BrandPage;

import java.util.List;

public class BrandService {
    private final BrandDao brandDao = new BrandDao();
    private final ProductDao productDao = new ProductDao();

    public BrandPage getPagedBrands(String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Brand> brands = brandDao.getBrands(keyword, pageSize, offset);
        int totalBrands = brandDao.countBrands(keyword);
        return new BrandPage(brands, totalBrands);
    }

    public Brand getBrandById(int id) {
        return brandDao.getBrandById(id);
    }

    public List<Brand> getBrandsByCategoryId(int categoryId) {
        return brandDao.getBrandsByCategoryId(categoryId);
    }

    public List<Brand> getAllBrands() {
        return brandDao.getAllBrands();
    }

    public void saveBrand(Brand brand) {
        if (brand.getId() == 0) {
            brandDao.addBrand(brand);
        } else {
            brandDao.updateBrand(brand);
        }
    }

    public boolean deleteBrand(int id) {
        // Kiểm tra xem thương hiệu có đang được sử dụng bởi sản phẩm nào không
        int productCount = productDao.countProductsByBrandId(id);
        if (productCount > 0) {
            return false; // Không cho phép xóa nếu đang được sử dụng
        }
        brandDao.deleteBrand(id);
        return true;
    }
}
