package main.java.controller;

import main.java.model.HealthMetric;
import main.java.model.Notification;
import main.java.model.User;
import main.java.repository.NotificationRepository;
import main.java.service.HealthMonitoringService;

import java.util.List;
import java.util.UUID;

public class MonitoringController {
    private HealthMonitoringService healthMonitoringService;
    private NotificationRepository notificationRepository;

    public MonitoringController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.healthMonitoringService = new HealthMonitoringService(notificationRepository);
    }

    public String addUser(String userId, String name, String email, String phoneNo){
        User user = new User(userId, name, email, phoneNo);
        notificationRepository.addUser(user);
        return "User added successfully with userId: " + userId;
    }

    public String addHealthMetric(String userId, String metricType, double metricValue){
        HealthMetric healthMetric = new HealthMetric(userId, metricType, metricValue);
        notificationRepository.addHealthMetric(healthMetric);
        return "Metric added successfully with userId: " + userId;
    }

    public List<Notification> getNotifications() {
        return notificationRepository.getAllNotifications();
    }


    public void startMonitoring() {
        healthMonitoringService.monitorMetrics();
    }
}
