package com.example.demo1.dao;

import com.example.demo1.model.Attribute;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.List;

public class AttributeDao { // 1. Bỏ "extends DatabaseDao"

    // 2. Khai báo biến Jdbi
    private final Jdbi jdbi = DatabaseDao.get();

    public List<Attribute> getAttributes(String keyword, int limit, int offset) {
        // 3. Thay "get()" bằng "jdbi"
        return jdbi.withHandle(handle -> {
            // SỬA: Thêm JOIN và lấy thêm cột categoryId, displayOrder
            String sql = "SELECT a.id, a.name, a.status, " +
                    "       ca.category_id AS categoryId, " +
                    "       ca.display_order AS displayOrder " + // Quan trọng: Phải có alias AS đúng tên biến trong Java
                    "FROM attributes a " +
                    "LEFT JOIN category_attributes ca ON a.id = ca.attribute_id ";

            boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
            if (hasKeyword) {
                sql += "WHERE a.name LIKE :keyword ";
            }

            // Sắp xếp
            sql += "ORDER BY a.id DESC LIMIT :limit OFFSET :offset";

            Query query = handle.createQuery(sql)
                    .bind("limit", limit)
                    .bind("offset", offset);

            if (hasKeyword) {
                query.bind("keyword", "%" + keyword + "%");
            }

            return query.mapToBean(Attribute.class).list();
        });
    }

    public int countAttributes(String keyword) {
        return jdbi.withHandle(handle -> {
            String sql = "SELECT COUNT(*) FROM attributes ";
            boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
            if (hasKeyword) {
                sql += "WHERE name LIKE :keyword ";
            }

            Query query = handle.createQuery(sql);

            if (hasKeyword) {
                query.bind("keyword", "%" + keyword + "%");
            }

            return query.mapTo(Integer.class).one();
        });
    }

    public Attribute getAttributeById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT a.id, a.name, a.status, ca.category_id as categoryId, ca.display_order as displayOrder, ca.is_filterable as isFilterable " +
                                "FROM attributes a " +
                                "LEFT JOIN category_attributes ca ON a.id = ca.attribute_id " +
                                "WHERE a.id = :id")
                        .bind("id", id)
                        .mapToBean(Attribute.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    public int addAttributeAndReturnId(Attribute attribute) {
        // Dùng inTransaction để đảm bảo: Cả 2 lệnh INSERT đều thành công, hoặc cả 2 đều thất bại
        return jdbi.inTransaction(handle -> {

            // BƯỚC 1: Lưu tên và trạng thái vào bảng cha (attributes)
            int newAttrId = handle.createUpdate("INSERT INTO attributes (name, status) VALUES (:name, :status)")
                    .bindBean(attribute)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();

            // BƯỚC 2: Lưu danh mục và thứ tự vào bảng con (category_attributes)
            // Kiểm tra xem người dùng có chọn danh mục không (categoryId > 0)
            if (attribute.getCategoryId() > 0) {
                handle.createUpdate("INSERT INTO category_attributes (attribute_id, category_id, display_order, is_filterable) " +
                                "VALUES (:attrId, :categoryId, :displayOrder, :isFilterable)")
                        .bind("attrId", newAttrId)      // ID vừa sinh ra ở bước 1
                        .bindBean(attribute)            // Lấy các field categoryId, displayOrder từ object truyền vào
                        .execute();
            }

            return newAttrId;
        });
    }

    public void updateAttribute(Attribute attribute) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE attributes SET name = :name, status = :status WHERE id = :id")
                        .bindBean(attribute)
                        .execute()
        );
    }

    public void deleteAttribute(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM attributes WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public boolean isAttributeExists(String name, int categoryId) {
        return jdbi.withHandle(handle -> {
            String sql = "SELECT COUNT(*) FROM attributes a " +
                    "JOIN category_attributes ca ON a.id = ca.attribute_id " +
                    "WHERE a.name = :name AND ca.category_id = :categoryId";

            int count = handle.createQuery(sql)
                    .bind("name", name)
                    .bind("categoryId", categoryId)
                    .mapTo(Integer.class)
                    .one();

            return count > 0; // Trả về true nếu đã tồn tại
        });
    }
}