package com.example.demo1.service;

import com.example.demo1.dao.CategoryDao;
import com.example.demo1.model.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao dao = new CategoryDao();

    public List<Category> getAllCategories() {
        return dao.getAll();
    }

    public Category getCategoryById(int id) {
        return dao.getById(id);
    }
}
