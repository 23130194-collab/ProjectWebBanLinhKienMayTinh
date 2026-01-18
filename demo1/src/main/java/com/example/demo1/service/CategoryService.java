package com.example.demo1.service;

import com.example.demo1.dao.CategoryDao;
import com.example.demo1.dao.DatabaseDao;
import com.example.demo1.dao.ProductDao;
import com.example.demo1.model.Category;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();
    private final ProductDao productDao = new ProductDao();
    private final Jdbi jdbi = DatabaseDao.get();

    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }

    public List<Category> getActiveCategories() {
        return categoryDao.getActiveCategories();
    }

    public Category getCategoryById(int id) {
        return categoryDao.getById(id);
    }

    public void addCategory(String name, int displayOrder, String image, String status) {
        jdbi.useTransaction(handle -> {
            handle.createUpdate("UPDATE categories SET display_order = display_order + 1 WHERE display_order >= :displayOrder")
                    .bind("displayOrder", displayOrder)
                    .execute();
            
            handle.createUpdate("INSERT INTO categories (name, display_order, image, status) VALUES (:name, :displayOrder, :image, :status)")
                    .bind("name", name)
                    .bind("displayOrder", displayOrder)
                    .bind("image", image)
                    .bind("status", status)
                    .execute();
        });
    }

    public List<Category> searchCategories(String keyword) {
        return categoryDao.searchByName(keyword);
    }

    public void updateCategory(Category category) {
        jdbi.useTransaction(handle -> {
            handle.createUpdate("UPDATE categories SET display_order = display_order + 1 WHERE display_order >= :displayOrder")
                    .bind("displayOrder", category.getDisplay_order())
                    .execute();
            
            handle.createUpdate("UPDATE categories SET name = :name, display_order = :display_order, image = :image, status = :status WHERE id = :id")
                    .bindBean(category)
                    .execute();
        });
    }

    public boolean deleteCategory(int id) {
        int productCount = productDao.countProductsByCategoryId(id);
        if (productCount > 0) {
            return false;
        }
        categoryDao.deleteCategory(id);
        return true;
    }

    public boolean isCategoryNameExists(String name, Integer id) {
        return categoryDao.isCategoryNameExists(name, id);
    }
}
