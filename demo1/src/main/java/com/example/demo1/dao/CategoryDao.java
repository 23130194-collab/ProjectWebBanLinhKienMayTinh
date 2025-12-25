package com.example.demo1.dao;

import com.example.demo1.model.Category;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryDao {
    private Jdbi jdbi = DatabaseDao.get();

    /**
     * Lấy tất cả các danh mục từ cơ sở dữ liệu.
     * @return Danh sách các danh mục, sắp xếp theo thứ tự hiển thị.
     */
    public List<Category> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, icon FROM categories ORDER BY display_order")
                        .mapToBean(Category.class)
                        .list()
        );
    }

    /**
     * Lấy thông tin của một danh mục bằng ID.
     * @param id ID của danh mục cần lấy.
     * @return Đối tượng Category hoặc null nếu không tìm thấy.
     */
    public Category getById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, name, display_order, icon FROM categories WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Category.class)
                        .findOne()
        ).orElse(null);
    }
}
