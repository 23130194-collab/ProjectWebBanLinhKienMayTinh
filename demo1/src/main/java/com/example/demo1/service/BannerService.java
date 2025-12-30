package com.example.demo1.service;

import com.example.demo1.dao.BannerDao;
import com.example.demo1.model.Banner;

import java.util.List;

public class BannerService {
    private BannerDao bannerDao = new BannerDao();

    public List<Banner> getBannersByPosition(String position) {
        return bannerDao.getBannersByPosition(position);
    }
}
