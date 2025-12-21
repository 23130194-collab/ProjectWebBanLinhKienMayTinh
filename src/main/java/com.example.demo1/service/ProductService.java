package com.example.demo1.service;

import com.example.demo1.dao.*;
import com.example.demo1.model.*;

import java.util.List;

public class ProductService {
    ProductDao pdao = new ProductDao();
    BrandDao bdao = new BrandDao();
    Product_SpecDao specDao = new Product_SpecDao();
    Product_ImageDao imgDao = new Product_ImageDao();
    ReviewDao reviewDao = new ReviewDao();

    //Lấy tất cả sản phẩm
    public List<Product> getListProduct() {
        return pdao.getAll();
    }

    //Lấy sản phẩm theo id sản phẩm
    public Product getProduct(int id) {
        return pdao.getById(id);
    }

    //Lấy thương hiệu của sản phẩm
    public Brand getBrand(int productId) { return bdao.getBrandByProductId(productId); }

    // Lấy danh sách thông số kỹ thuật
    public List<Product_Spec> getProductSpecs(int productId) {
        return specDao.getSpecsByProductId(productId);
    }

    // Lấy danh sách ảnh phụ của sản phẩm
    public List<Product_Image> getProductImages(int productId) {
        return imgDao.getImagesByProductId(productId);
    }

    // Lấy danh sách đánh giá (JOIN với bảng users)
    public List<Review> getProductReviews(int productId) {
        return reviewDao.getReviewsByProductId(productId);
    }
}
