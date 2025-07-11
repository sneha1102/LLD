package main.java.model;

import java.time.LocalDateTime;

public class HealthMetric {
    private String userId;
    private String metricType;
    private double metricValue;
    LocalDateTime dateTime;

    public HealthMetric(String userId, String metricType, double metricValue) {
        this.userId = userId;
        this.metricType = metricType;
        this.metricValue = metricValue;
        this.dateTime = LocalDateTime.now();
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public double getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(double metricValue) {
        this.metricValue = metricValue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


}
