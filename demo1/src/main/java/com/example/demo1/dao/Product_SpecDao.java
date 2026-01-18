package com.example.demo1.dao;

import com.example.demo1.model.Product_Spec;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class Product_SpecDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Product_Spec> getSpecsByProductId(int pid) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " +
                                "s.id, " +
                                "s.product_id AS productId, " +
                                "s.attribute_id AS attributeId, " +
                                "s.spec_value AS specValue, " +
                                "a.name AS attributeName " +
                                "FROM product_specs s " +
                                "JOIN attributes a ON s.attribute_id = a.id " +
                                "WHERE s.product_id = :pid AND a.status = 'active'")
                        .bind("pid", pid)
                        .mapToBean(Product_Spec.class)
                        .list()
        );
    }

    /**
     * Lấy danh sách các giá trị đặc tả (spec) duy nhất cho một thuộc tính cụ thể trong một danh mục.
     * Ví dụ: Lấy tất cả các loại socket duy nhất trong danh mục CPU.
     * @param categoryId ID của danh mục.
     * @param attributeId ID của thuộc tính.
     * @return Danh sách các chuỗi giá trị đặc tả.
     */
    public List<String> getDistinctSpecValues(int categoryId, int attributeId) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT DISTINCT ps.spec_value FROM product_specs ps " +
                                        "JOIN products p ON ps.product_id = p.id " +
                                        "WHERE p.category_id = :categoryId AND ps.attribute_id = :attributeId " +
                                        "ORDER BY ps.spec_value ASC"
                        )
                        .bind("categoryId", categoryId)
                        .bind("attributeId", attributeId)
                        .mapTo(String.class)
                        .list()
        );
    }

    public void insertProductSpec(int productId, int attributeId, String specValue) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO product_specs (product_id, attribute_id, spec_value) VALUES (:pid, :aid, :val)")
                        .bind("pid", productId)
                        .bind("aid", attributeId)
                        .bind("val", specValue)
                        .execute()
        );
    }

    public void deleteProductSpecs(int productId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM product_specs WHERE product_id = :productId")
                        .bind("productId", productId)
                        .execute()
        );
    }
}
