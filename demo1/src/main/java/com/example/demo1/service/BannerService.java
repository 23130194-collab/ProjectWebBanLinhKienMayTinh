package com.example.demo1.service;

import com.example.demo1.dao.BannerDao;
import com.example.demo1.model.Banner;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

public class BannerService {
    private BannerDao bannerDao = new BannerDao();

    public Banner getBannerById(int id) {
        return bannerDao.getById(id);
    }

    public void deleteBanner(int id) {
        bannerDao.delete(id);
    }

    public boolean createBanner(String name, String startTime, String endTime,
                                String position, int displayOrder,
                                Part filePart, String linkUrl) {

        String finalImage = processImage(filePart, linkUrl);

        Banner banner = new Banner();
        banner.setName(name);
        banner.setStart_time(startTime);
        banner.setEnd_time(endTime);
        banner.setPosition(position);
        banner.setDisplay_order(displayOrder);
        banner.setImage(finalImage != null ? finalImage : "");
        return bannerDao.insert(banner) > 0;
    }

    public boolean updateBanner(int id, String name, String startTime, String endTime,
                                String position, int displayOrder,
                                Part filePart, String linkUrl) {

        Banner oldBanner = bannerDao.getById(id);
        if (oldBanner == null) return false;

        String newImage = processImage(filePart, linkUrl);

        oldBanner.setName(name);
        oldBanner.setStart_time(startTime);
        oldBanner.setEnd_time(endTime);
        oldBanner.setPosition(position);
        oldBanner.setDisplay_order(displayOrder);

        if (newImage != null && !newImage.isEmpty()) {
            oldBanner.setImage(newImage);
        }

        return bannerDao.update(oldBanner) > 0;
    }

    private String processImage(Part filePart, String linkUrl) {
        String finalImage = null;
        try {
            if (filePart != null && filePart.getSize() > 0) {
                try (InputStream inputStream = filePart.getInputStream();
                     ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    String base64Content = Base64.getEncoder().encodeToString(outputStream.toByteArray());
                    finalImage = "data:" + filePart.getContentType() + ";base64," + base64Content;
                }
            }
            if (finalImage == null && linkUrl != null && !linkUrl.trim().isEmpty()) {
                finalImage = linkUrl.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalImage;
    }

    public List<Banner> getBannersByPosition(String position) {
        return bannerDao.getBannersByPosition(position);
    }

    public List<Banner> getBanners(String keyword, String position, int limit, int offset) {
        return bannerDao.getBanners(keyword, position, limit, offset);
    }

    public int countBanners(String keyword, String position) {
        return bannerDao.countBanners(keyword, position);
    }

    public boolean isNameExists(String name, String position, int excludeId) {
        return bannerDao.isNameExists(name, position, excludeId);
    }

    public boolean isDisplayOrderExists(String position, int displayOrder, int excludeId) {
        return bannerDao.isDisplayOrderExists(position, displayOrder, excludeId);
    }

    public void shiftDisplayOrders(String position, int fromOrder) {
        bannerDao.shiftDisplayOrders(position, fromOrder);
    }
}