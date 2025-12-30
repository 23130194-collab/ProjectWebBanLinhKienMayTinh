package com.example.demo1.dao;

import com.example.demo1.model.Attribute;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryAttributeDao {
    private Jdbi jdbi = DatabaseDao.get();

    /**
     * Lấy danh sách các thuộc tính có thể dùng để lọc cho một danh mục.
     * @param categoryId ID của danh mục.
     * @return Danh sách các thuộc tính được sắp xếp theo thứ tự hiển thị.
     */
    public List<Attribute> getFilterableAttributesByCategoryId(int categoryId) {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                        "SELECT a.id, a.name FROM attributes a " +
                        "JOIN category_attributes ca ON a.id = ca.attribute_id " +
                        "WHERE ca.category_id = :categoryId AND ca.is_filterable = 1 " +
                        "ORDER BY ca.display_order ASC"
                )
                .bind("categoryId", categoryId)
                .mapToBean(Attribute.class)
                .list()
        );
    }
}
