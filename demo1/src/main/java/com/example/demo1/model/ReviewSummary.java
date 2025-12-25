package com.example.demo1.model;

import java.util.*;

public class ReviewSummary {
    private int totalReviews;
    private Map<String, Integer> starCounts; // Dùng String key
    private Map<String, Double> starPercentages;

    public ReviewSummary(Map<Integer, Integer> rawCounts) {
        this.starCounts = new HashMap<>();
        this.starPercentages = new HashMap<>();

        for (int i = 1; i <= 5; i++) {
            int count = rawCounts.getOrDefault(i, 0);
            this.starCounts.put(String.valueOf(i), count); // Lưu key là "1", "2"...
        }

        this.totalReviews = rawCounts.values().stream().mapToInt(Integer::intValue).sum();

        for (int i = 1; i <= 5; i++) {
            double percent = (totalReviews > 0)
                    ? (rawCounts.getOrDefault(i, 0) * 100.0 / totalReviews)
                    : 0.0;
            this.starPercentages.put(String.valueOf(i), percent);
        }
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public Map<String, Integer> getStarCounts() {
        return starCounts;
    }

    public void setStarCounts(Map<String, Integer> starCounts) {
        this.starCounts = starCounts;
    }

    public Map<String, Double> getStarPercentages() {
        return starPercentages;
    }

    public void setStarPercentages(Map<String, Double> starPercentages) {
        this.starPercentages = starPercentages;
    }
}
