package com.example.demo1.dao;

import org.jdbi.v3.core.Jdbi;

public class OrderDetailsDao {
    private final Jdbi jdbi = DatabaseDao.get();

    public void deleteByProductId(int productId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM order_details WHERE product_id = :productId")
                        .bind("productId", productId)
                        .execute());
    }
}
