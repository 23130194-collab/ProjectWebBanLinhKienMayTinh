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
}
