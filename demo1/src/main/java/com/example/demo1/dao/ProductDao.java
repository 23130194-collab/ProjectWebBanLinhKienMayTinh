package com.example.demo1.dao;

import com.example.demo1.model.Product;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.List;
import java.util.Map;

public class ProductDao {
    private Jdbi jdbi = DatabaseDao.get();

    // Định nghĩa các trường cần lấy, bao gồm cả việc tính toán giá động
    private final String DYNAMIC_PRICE_SQL =
        "FLOOR( (CASE WHEN d.discount_value > 0 THEN p.old_price * (1 - d.discount_value / 100) ELSE p.old_price END) / 10000 ) * 10000";

    private final String SELECT_PRODUCT_FIELDS =
            "p.id, p.category_id, p.brand_id, p.name, p.discount_id, p.description, p.stock, p.image, p.created_at, " +
            "p.old_price, " + // Giữ nguyên old_price là giá gốc
            "d.discount_value, " +
            "IFNULL(ROUND(AVG(r.rating), 1), 0) AS avg_rating, " +
            // Tính toán giá bán cuối cùng (price) dựa trên old_price và discount
            DYNAMIC_PRICE_SQL + " AS price ";

    private String buildSubQueryForFilter(int categoryId, Integer brandId, Map<Integer, List<String>> specFilters) {
        StringBuilder subQuerySql = new StringBuilder("SELECT p.id FROM products p ");
        if (specFilters != null && !specFilters.isEmpty()) {
            subQuerySql.append("JOIN product_specs ps ON p.id = ps.product_id ");
        }
        subQuerySql.append("WHERE p.category_id = :categoryId ");

        if (brandId != null) {
            subQuerySql.append("AND p.brand_id = :brandId ");
        }

        if (specFilters != null && !specFilters.isEmpty()) {
            subQuerySql.append("AND (");
            int i = 0;
            for (Integer attrId : specFilters.keySet()) {
                if (i > 0) subQuerySql.append(" OR ");
                subQuerySql.append("(ps.attribute_id = ").append(attrId)
                           .append(" AND ps.spec_value IN (<values_").append(attrId).append(">))");
                i++;
            }
            subQuerySql.append(") ");
        }

        subQuerySql.append("GROUP BY p.id ");

        if (specFilters != null && !specFilters.isEmpty()) {
            subQuerySql.append("HAVING COUNT(DISTINCT ps.attribute_id) = ").append(specFilters.size());
        }
        return subQuerySql.toString();
    }

    private void bindFilterParams(Query query, int categoryId, Integer brandId, Map<Integer, List<String>> specFilters) {
        query.bind("categoryId", categoryId);
        if (brandId != null) {
            query.bind("brandId", brandId);
        }
        if (specFilters != null && !specFilters.isEmpty()) {
            for (Map.Entry<Integer, List<String>> entry : specFilters.entrySet()) {
                query.bindList("values_" + entry.getKey(), entry.getValue());
            }
        }
    }

    public int countFilteredProducts(int categoryId, Integer brandId, Map<Integer, List<String>> specFilters) {
        return jdbi.withHandle(handle -> {
            String subQuery = buildSubQueryForFilter(categoryId, brandId, specFilters);
            String countSql = "SELECT COUNT(*) FROM (" + subQuery + ") as filtered_products";
            Query query = handle.createQuery(countSql);
            bindFilterParams(query, categoryId, brandId, specFilters);
            return query.mapTo(Integer.class).one();
        });
    }

    public List<Product> filterAndSortProducts(int categoryId, Integer brandId, Map<Integer, List<String>> specFilters, String sortOrder, int limit, int offset) {
        return jdbi.withHandle(handle -> {
            String subQuery = buildSubQueryForFilter(categoryId, brandId, specFilters);

            StringBuilder finalSql = new StringBuilder("SELECT " + SELECT_PRODUCT_FIELDS +
                              "FROM products p " +
                              "LEFT JOIN discounts d ON p.discount_id = d.id " +
                              "LEFT JOIN reviews r ON p.id = r.product_id " +
                              "WHERE p.id IN (").append(subQuery).append(") " +
                              "GROUP BY p.id ");

            switch (sortOrder) {
                case "price_asc":
                    finalSql.append("ORDER BY price ASC, p.id ASC ");
                    break;
                case "price_desc":
                    finalSql.append("ORDER BY price DESC, p.id DESC ");
                    break;
                case "popular":
                default:
                    finalSql.append("ORDER BY p.created_at ASC, p.id ASC ");
                    break;
            }

            finalSql.append("LIMIT :limit OFFSET :offset");

            Query query = handle.createQuery(finalSql.toString());
            bindFilterParams(query, categoryId, brandId, specFilters);
            query.bind("limit", limit);
            query.bind("offset", offset);

            return query.mapToBean(Product.class).list();
        });
    }

    public Product getById(int productId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.id = :id " +
                                "GROUP BY p.id")
                        .bind("id", productId)
                        .mapToBean(Product.class)
                        .findOne()
        ).orElse(null);
    }

    public List<Product> getRelatedProducts(int categoryId, int currentProductId, int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT " + SELECT_PRODUCT_FIELDS +
                                "FROM products p " +
                                "LEFT JOIN discounts d ON p.discount_id = d.id " +
                                "LEFT JOIN reviews r ON p.id = r.product_id " +
                                "WHERE p.category_id = :categoryId AND p.id != :currentProductId " +
                                "GROUP BY p.id " +
                                "ORDER BY p.created_at DESC " +
                                "LIMIT :limit OFFSET :offset")
                        .bind("categoryId", categoryId)
                        .bind("currentProductId", currentProductId)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public int countRelatedProducts(int categoryId, int currentProductId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM products WHERE category_id = :categoryId AND id != :currentProductId")
                        .bind("categoryId", categoryId)
                        .bind("currentProductId", currentProductId)
                        .mapTo(Integer.class)
                        .one()
        );
    }
}
