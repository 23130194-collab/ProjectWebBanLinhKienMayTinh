package com.example.demo1.dao;

import com.example.demo1.model.Banner;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class BannerDao {
    private Jdbi jdbi = DatabaseDao.get();

    public List<Banner> getBannersByPosition(String position) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM banners WHERE position = :position ORDER BY display_order ASC")
                        .bind("position", position)
                        .mapToBean(Banner.class)
                        .list()
        );
    }
}
