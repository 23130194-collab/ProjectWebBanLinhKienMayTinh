package com.example.demo1.service;

import com.example.demo1.dao.*;
import com.example.demo1.model.*;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Lấy danh sách sản phẩm theo ID danh mục.
     * @param categoryId ID của danh mục.
     * @return Danh sách sản phẩm.
     */
    public List<Product> getProductsByCategoryId(int categoryId) {
        return pdao.getByCategoryId(categoryId);
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

    //Lấy thông tin đánh giá của sản phẩm
    public ReviewSummary getReviewSummary(int productId) {
        Map<Integer, Integer> rawData = reviewDao.getRawStarCounts(productId);
        return new ReviewSummary(rawData);
    }

    /**
     * Lấy danh sách các sản phẩm liên quan.
     *
     * @param currentProduct Sản phẩm đang xem.
     * @return Danh sách 3 sản phẩm liên quan nhất.
     */
    public List<Product> getRelatedProducts(Product currentProduct) {
        // 1. Lấy danh sách các sản phẩm từ DAO
        List<Product> candidates = pdao.getRelatedCandidateProducts(currentProduct, 1000000);

        // 2. Lấy danh sách thông số của sản phẩm hiện tại để so sánh
        List<Product_Spec> currentSpecs = getProductSpecs(currentProduct.getId());
        // Chuyển thành một Set để tra cứu nhanh hơn (O(1))
        Set<String> currentSpecsSet = currentSpecs.stream()
                .map(spec -> spec.getAttribute_name() + ":" + spec.getSpec_value())
                .collect(Collectors.toSet());

        // 3. Tính điểm tương đồng cho mỗi sản phẩm
        Map<Product, Long> productScores = new HashMap<>();
        for (Product candidate : candidates) {
            List<Product_Spec> candidateSpecs = getProductSpecs(candidate.getId());
            long score = candidateSpecs.stream()
                    .filter(spec -> currentSpecsSet.contains(spec.getAttribute_name() + ":" + spec.getSpec_value()))
                    .count(); // Đếm số lượng spec giống nhau
            productScores.put(candidate, score);
        }

        // 4. Sắp xếp danh sách sản phẩm dựa trên điểm số giảm dần
        List<Product> sortedCandidates = candidates.stream()
                .sorted(Comparator.comparing(productScores::get).reversed())
                .collect(Collectors.toList());

        // 5. Lấy 3 sản phẩm đầu tiên (hoặc ít hơn nếu không đủ)
        return sortedCandidates.stream().limit(3).collect(Collectors.toList());
    }
}
