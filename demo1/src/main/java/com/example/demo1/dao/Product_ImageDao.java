package com.example.demo1.dao;

import com.example.demo1.model.Product_Image;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class Product_ImageDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Product_Image> getImagesByProductId(int productId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM product_images " +
                                "WHERE product_id = :pid " +
                                "ORDER BY display_order ASC")
                        .bind("pid", productId)
                        .mapToBean(Product_Image.class)
                        .list()
        );
    }
}
