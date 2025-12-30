package com.example.demo1.dao;

import com.example.demo1.model.Category;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryDao {
    private Jdbi jdbi = DatabaseDao.get();

    /**
     * Lấy TẤT CẢ danh mục (dành cho trang Admin).
     */
    public List<Category> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, image, status FROM categories ORDER BY display_order")
                        .mapToBean(Category.class)
                        .list()
        );
    }

    /**
     * Lấy các danh mục đang HOẠT ĐỘNG (dành cho trang User).
     */
    public List<Category> getActiveCategories() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, image, status FROM categories WHERE status = 'active' ORDER BY display_order")
                        .mapToBean(Category.class)
                        .list()
        );
    }

    public Category getById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, image, status FROM categories WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Category.class)
                        .findOne()
        ).orElse(null);
    }

    public void addCategory(String name, int displayOrder, String image, String status) {
        jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO categories (name, display_order, image, status) VALUES (:name, :displayOrder, :image, :status)")
                        .bind("name", name)
                        .bind("displayOrder", displayOrder)
                        .bind("image", image)
                        .bind("status", status)
                        .execute()
        );
    }

    public List<Category> searchByName(String keyword) {
        String searchQuery = "%" + keyword + "%";
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, image, status FROM categories WHERE name LIKE :keyword ORDER BY display_order")
                        .bind("keyword", searchQuery)
                        .mapToBean(Category.class)
                        .list()
        );
    }

    public void updateCategory(Category category) {
        jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE categories SET name = :name, display_order = :display_order, image = :image, status = :status WHERE id = :id")
                        .bindBean(category)
                        .execute()
        );
    }

    public void deleteCategory(int id) {
        jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM categories WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }
}
