package com.example.demo1.service;

import com.example.demo1.dao.*;
import com.example.demo1.model.*;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Timestamp;

public class ProductService {
    ProductDao pdao = new ProductDao();
    BrandDao bdao = new BrandDao();
    Product_SpecDao specDao = new Product_SpecDao();
    Product_ImageDao imgDao = new Product_ImageDao();
    ReviewDao reviewDao = new ReviewDao();
    DiscountService discountService = new DiscountService();
    Product_ImageService imageService = new Product_ImageService();

    public int createProduct(String name, int categoryId, int brandId, String description, double oldPrice, int stock, String status, Double discountValue, Timestamp discountStart, Timestamp discountEnd, List<Product_Image> images) {
        Product newProduct = new Product();
        populateProductFields(newProduct, name, categoryId, brandId, description, oldPrice, stock, status, images);

        Integer discountId = handleDiscount(null, discountValue, discountStart, discountEnd);
        newProduct.setDiscountId(discountId != null ? discountId : 0);

        if (discountId != null) {
            newProduct.setPrice(oldPrice * (1.0 - (discountValue / 100.0)));
        }

        return pdao.addProductAndReturnId(newProduct);
    }

    public void updateProduct(int productId, String name, int categoryId, int brandId, String description, double oldPrice, int stock, String status, Double discountValue, Timestamp discountStart, Timestamp discountEnd, List<Product_Image> images) {
        Product product = pdao.getById(productId);
        if (product == null) {
            return; // Or throw an exception
        }

        populateProductFields(product, name, categoryId, brandId, description, oldPrice, stock, status, images);

        Integer discountId = handleDiscount(product.getDiscountId(), discountValue, discountStart, discountEnd);
        product.setDiscountId(discountId != null ? discountId : 0);

        if (discountValue != null && discountValue > 0) {
            product.setPrice(oldPrice * (1.0 - (discountValue / 100.0)));
        } else {
            product.setPrice(oldPrice);
        }

        pdao.update(product);

        // Update images and specs
        imgDao.deleteImagesByProductId(productId);
        if (images != null && !images.isEmpty()) {
            imageService.addImages(productId, images);
        }
    }

    private void populateProductFields(Product product, String name, int categoryId, int brandId, String description, double oldPrice, int stock, String status, List<Product_Image> images) {
        product.setName(name);
        product.setCategoryId(categoryId);
        product.setBrandId(brandId);
        product.setDescription(description);
        product.setOldPrice(oldPrice);
        product.setStock(stock);
        product.setStatus(status != null ? status : "active");
        if (images != null && !images.isEmpty()) {
            product.setImage(images.get(0).getImage());
        } else if (product.getImage() == null || product.getImage().isEmpty()){
            product.setImage("assets/images/no-image.png");
        }
    }

    private Integer handleDiscount(Integer existingDiscountId, Double discountValue, Timestamp startTime, Timestamp endTime) {
        if (discountValue != null && discountValue > 0) {
            if (existingDiscountId != null && existingDiscountId > 0) {
                discountService.updateDiscount(existingDiscountId, discountValue, startTime, endTime);
                return existingDiscountId;
            } else {
                Discount newDiscount = new Discount();
                newDiscount.setDiscountValue(discountValue);
                newDiscount.setStartTime(startTime);
                newDiscount.setEndTime(endTime);
                return discountService.createDiscount(newDiscount);
            }
        } else if (existingDiscountId != null && existingDiscountId > 0) {
            discountService.deleteDiscount(existingDiscountId);
        }
        return null;
    }


    public void deleteProduct(int productId) {
        Product product = pdao.getById(productId);
        if (product != null) {
            product.setStatus("inactive"); // Chỉ cần đặt trạng thái là 'inactive'
            pdao.update(product); // Và gọi phương thức update
        }
    }


    // ... other methods
    public Product getProduct(int id) {
        return pdao.getById(id);
    }
    
    public Product getPublicProduct(int id) {
        return pdao.getById(id, "active");
    }

    public Brand getBrand(int productId) { return bdao.getBrandByProductId(productId); }
    public List<Product_Spec> getProductSpecs(int productId) {
        List<Product_Spec> specs = specDao.getSpecsByProductId(productId);
        return (specs != null) ? specs : Collections.emptyList();
    }
    public List<Product_Image> getProductImages(int productId) {
        return imgDao.getImagesByProductId(productId);
    }
    public ReviewSummary getReviewSummary(int productId) {
        Map<Integer, Integer> rawData = reviewDao.getRawStarCounts(productId);
        return new ReviewSummary(rawData);
    }
    public List<Product> getRelatedProducts(Product currentProduct) {
        List<Product> candidates = pdao.getRelatedProducts(currentProduct.getCategoryId(), currentProduct.getId(), 100, 0);
        if (candidates == null || candidates.isEmpty()) {
            return Collections.emptyList();
        }
        List<Product_Spec> currentSpecs = getProductSpecs(currentProduct.getId());
        if (currentSpecs.isEmpty()) {
            return candidates.stream().limit(3).collect(Collectors.toList());
        }
        Set<String> currentSpecsSet = currentSpecs.stream()
                .map(spec -> spec.getAttributeName() + ":" + spec.getSpecValue())
                .collect(Collectors.toSet());
        List<Map.Entry<Product, Long>> scoredProducts = new ArrayList<>();
        for (Product candidate : candidates) {
            List<Product_Spec> candidateSpecs = getProductSpecs(candidate.getId());
            long score = candidateSpecs.stream()
                    .filter(spec -> currentSpecsSet.contains(spec.getAttributeName() + ":" + spec.getSpecValue()))
                    .count();
            scoredProducts.add(new AbstractMap.SimpleEntry<>(candidate, score));
        }
        scoredProducts.sort((e1, e2) -> {
            int scoreCompare = e2.getValue().compareTo(e1.getValue());
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            return Integer.compare(e1.getKey().getId(), e2.getKey().getId());
        });
        return scoredProducts.stream()
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
    }
    public ProductPage filterAndSortProducts(Integer categoryId, String status, String keyword, Integer brandId, Map<Integer, List<String>> specFilters, String sortOrder, int page, int pageSize) {
        return pdao.filterAndSortProducts(categoryId, status, keyword, brandId, specFilters, sortOrder, page, pageSize);
    }
}
