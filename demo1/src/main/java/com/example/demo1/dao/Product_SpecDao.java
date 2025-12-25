package com.example.demo1.dao;

import com.example.demo1.model.Product_Spec;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class Product_SpecDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Product_Spec> getSpecsByProductId(int pid) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT s.*, a.name AS attribute_name " +
                                "FROM product_specs s " +
                                "JOIN attributes a ON s.attribute_id = a.id " +
                                "WHERE s.product_id = :pid")
                        .bind("pid", pid)
                        .mapToBean(Product_Spec.class)
                        .list()
        );
    }
}
