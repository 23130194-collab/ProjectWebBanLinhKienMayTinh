package com.example.demo1.model;

import java.util.*;

public class ReviewSummary {
    // SỬA: Dùng Key là String để JSP dễ lấy dữ liệu
    private final Map<String, Integer> starCounts;
    private final int totalReviews;
    private final double averageRating;

    // Constructor chính: Nhận dữ liệu từ DAO (Map<Integer, Integer>) và chuyển sang Map<String, Integer>
    public ReviewSummary(Map<Integer, Integer> rawData) {
        this.starCounts = new HashMap<>();
        // Đảm bảo tất cả các mức sao (1-5) đều có giá trị, kể cả 0
        for (int i = 1; i <= 5; i++) {
            // Chuyển i thành String.valueOf(i)
            this.starCounts.put(String.valueOf(i), rawData.getOrDefault(i, 0));
        }
        this.totalReviews = calculateTotalReviews();
        this.averageRating = calculateAverageRating();
    }

    // Constructor rỗng (cho trường hợp null)
    public ReviewSummary() {
        this.starCounts = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            this.starCounts.put(String.valueOf(i), 0);
        }
        this.totalReviews = 0;
        this.averageRating = 0.0;
    }

    private int calculateTotalReviews() {
        return starCounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    private double calculateAverageRating() {
        if (totalReviews == 0) {
            return 0.0;
        }
        double totalScore = 0;
        for (Map.Entry<String, Integer> entry : starCounts.entrySet()) {
            // Parse Key từ String về Int để tính điểm trung bình
            totalScore += Integer.parseInt(entry.getKey()) * entry.getValue();
        }
        return totalScore / totalReviews;
    }

    // Getter trả về Map<String, Integer>
    public Map<String, Integer> getStarCounts() {
        return starCounts;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public Map<String, Double> getStarPercentages() {
        Map<String, Double> percentages = new HashMap<>();
        if (totalReviews == 0) {
            for (int i = 1; i <= 5; i++) {
                percentages.put(String.valueOf(i), 0.0);
            }
            return percentages;
        }
        for (Map.Entry<String, Integer> entry : starCounts.entrySet()) {
            double percentage = ((double) entry.getValue() / totalReviews) * 100;
            percentages.put(entry.getKey(), percentage);
        }
        return percentages;
    }
}