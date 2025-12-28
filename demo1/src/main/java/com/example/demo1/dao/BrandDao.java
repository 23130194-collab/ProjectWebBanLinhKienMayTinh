package com.example.demo1.dao;

import com.example.demo1.model.Brand;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class BrandDao {
    private Jdbi jdbi = DatabaseDao.get();

    public Brand getBrandByProductId(int pid) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT b.* " +
                                "FROM products p " +
                                "JOIN brands b ON p.brand_id = b.id " +
                                "WHERE p.id = :pid")
                        .bind("pid", pid)
                        .mapToBean(Brand.class)
                        .findOne()
                        .orElse(null) // Lấy giá trị hoặc trả về null nếu không thấy
        );
    }

    public List<Brand> getBrandsByCategoryId(int categoryId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT DISTINCT b.* FROM brands b " +
                                "JOIN products p ON b.id = p.brand_id " +
                                "WHERE p.category_id = :categoryId " +
                                "ORDER BY b.display_order ASC")
                        .bind("categoryId", categoryId)
                        .mapToBean(Brand.class)
                        .list()
        );
    }
}
