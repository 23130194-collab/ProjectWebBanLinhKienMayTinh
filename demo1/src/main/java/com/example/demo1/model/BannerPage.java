package com.example.demo1.model;

import java.util.List;

public class BannerPage {
    private List<Banner> banners;
    private int totalBanners;

    public BannerPage() {
    }

    public BannerPage(List<Banner> banners, int totalBanners) {
        this.banners = banners;
        this.totalBanners = totalBanners;
    }

    // Getters
    public List<Banner> getBanners() {
        return banners;
    }

    public int getTotalBanners() {
        return totalBanners;
    }

    // Setters
    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public void setTotalBanners(int totalBanners) {
        this.totalBanners = totalBanners;
    }
}