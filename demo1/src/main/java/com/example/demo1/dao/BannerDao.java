package com.example.demo1.dao;

import com.example.demo1.model.Banner;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class BannerDao {
    private Jdbi jdbi = DatabaseDao.get();

    public Banner getById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM banners WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Banner.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    public int insert(Banner banner) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO banners (name, start_time, end_time, position, display_order, image) " +
                                "VALUES (:name, :start_time, :end_time, :position, :display_order, :image)")
                        .bindBean(banner)
                        .execute()
        );
    }

    public int update(Banner banner) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE banners SET name=:name, start_time=:start_time, end_time=:end_time, " +
                                "position=:position, display_order=:display_order, image=:image " +
                                "WHERE id=:id")
                        .bindBean(banner)
                        .execute()
        );
    }

    public int delete(int id) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM banners WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public List<Banner> getBannersByPosition(String position) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM banners WHERE position = :position ORDER BY display_order ASC")
                        .bind("position", position)
                        .mapToBean(Banner.class)
                        .list()
        );
    }

    public List<Banner> getBanners(String keyword, int limit, int offset) {
        String sql = "SELECT * FROM banners ";
        if (keyword != null && !keyword.isEmpty()) {
            sql += "WHERE name LIKE :keyword ";
        }
        sql += "ORDER BY display_order ASC LIMIT :limit OFFSET :offset";

        final String finalSql = sql;
        return jdbi.withHandle(handle -> {
            if (keyword != null && !keyword.isEmpty()) {
                return handle.createQuery(finalSql)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .bind("keyword", "%" + keyword + "%")
                        .mapToBean(Banner.class)
                        .list();
            } else {
                return handle.createQuery(finalSql)
                        .bind("limit", limit)
                        .bind("offset", offset)
                        .mapToBean(Banner.class)
                        .list();
            }
        });
    }

    public int countBanners(String keyword) {
        String sql = "SELECT COUNT(*) FROM banners ";
        if (keyword != null && !keyword.isEmpty()) {
            sql += "WHERE name LIKE :keyword";
        }
        final String finalSql = sql;
        return jdbi.withHandle(handle -> {
            if (keyword != null && !keyword.isEmpty()) {
                return handle.createQuery(finalSql)
                        .bind("keyword", "%" + keyword + "%")
                        .mapTo(Integer.class)
                        .one();
            } else {
                return handle.createQuery(finalSql)
                        .mapTo(Integer.class)
                        .one();
            }
        });
    }
}