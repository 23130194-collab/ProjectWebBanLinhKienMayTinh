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

    //Lấy thông tin đánh giá của sản phẩm
    public ReviewSummary getReviewSummary(int productId) {
        Map<Integer, Integer> rawData = reviewDao.getRawStarCounts(productId);
        return new ReviewSummary(rawData);
    }

    /**
     * Lấy danh sách các sản phẩm liên quan dựa trên sự tương đồng về thông số kỹ thuật.
     *
     * @param currentProduct Sản phẩm đang xem.
     * @return Danh sách tối đa 3 sản phẩm liên quan nhất.
     */
    public List<Product> getRelatedProducts(Product currentProduct) {
        // 1. Lấy danh sách các sản phẩm ứng viên từ DAO (cùng category, giới hạn số lượng lớn để có đủ dữ liệu so sánh)
        List<Product> candidates = pdao.getRelatedProducts(currentProduct.getCategory_id(), currentProduct.getId(), 100, 0);

        // 2. Lấy danh sách thông số của sản phẩm hiện tại để so sánh
        List<Product_Spec> currentSpecs = getProductSpecs(currentProduct.getId());
        // Chuyển thành một Set để tra cứu nhanh hơn (O(1))
        Set<String> currentSpecsSet = currentSpecs.stream()
                .map(spec -> spec.getAttribute_name() + ":" + spec.getSpec_value())
                .collect(Collectors.toSet());

        // 3. Tính điểm tương đồng cho mỗi sản phẩm ứng viên
        Map<Product, Long> productScores = new HashMap<>();
        for (Product candidate : candidates) {
            if (candidate.getId() == currentProduct.getId()) continue; // Bỏ qua chính sản phẩm hiện tại
            List<Product_Spec> candidateSpecs = getProductSpecs(candidate.getId());
            long score = candidateSpecs.stream()
                    .filter(spec -> currentSpecsSet.contains(spec.getAttribute_name() + ":" + spec.getSpec_value()))
                    .count(); // Đếm số lượng spec giống nhau
            productScores.put(candidate, score);
        }

        // 4. Sắp xếp danh sách ứng viên dựa trên điểm số giảm dần
        List<Product> sortedCandidates = candidates.stream()
                .filter(p -> p.getId() != currentProduct.getId()) // Lọc lại để chắc chắn không chứa sản phẩm hiện tại
                .sorted(Comparator.comparing(productScores::get, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());

        // 5. Lấy 3 sản phẩm đầu tiên (hoặc ít hơn nếu không đủ)
        return sortedCandidates.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * Lọc, sắp xếp và phân trang sản phẩm.
     *
     * @param categoryId  ID của danh mục.
     * @param brandId     ID của thương hiệu (có thể null).
     * @param specFilters Map các bộ lọc thông số kỹ thuật.
     * @param sortOrder   Thứ tự sắp xếp.
     * @param page        Trang hiện tại.
     * @param pageSize    Số sản phẩm mỗi trang.
     * @return Một đối tượng ProductPage chứa danh sách sản phẩm và tổng số sản phẩm.
     */
    public ProductPage filterAndSortProducts(int categoryId, Integer brandId, Map<Integer, List<String>> specFilters, String sortOrder, int page, int pageSize) {
        int offset = (page - 1) * pageSize;

        // Lấy danh sách sản phẩm đã được lọc và sắp xếp từ DAO
        List<Product> products = pdao.filterAndSortProducts(categoryId, brandId, specFilters, sortOrder, pageSize, offset);

        // Đếm tổng số sản phẩm phù hợp với bộ lọc (để tính tổng số trang)
        int totalProducts = pdao.countFilteredProducts(categoryId, brandId, specFilters);

        return new ProductPage(products, totalProducts);
    }
}
