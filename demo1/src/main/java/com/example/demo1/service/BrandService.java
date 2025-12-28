package com.example.demo1.service;

import com.example.demo1.dao.BrandDao;
import com.example.demo1.model.Brand;

import java.util.List;

public class BrandService {
    private BrandDao brandDao = new BrandDao();

    public List<Brand> getBrandsByCategoryId(int categoryId) {
        return brandDao.getBrandsByCategoryId(categoryId);
    }
}