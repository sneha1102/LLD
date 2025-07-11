package main.java.service;

import main.java.model.HealthMetric;
import main.java.model.User;
import main.java.repository.NotificationRepository;

import java.util.Map;

public class HealthMonitoringService {
    private static final Map<String, Double> THRESHOLDS = Map.of(
            "heart-rate", 100.0,
            "pressure", 140.0
    );
    private NotificationRepository notificationRepository;
    private NotificationService notificationService;

    public HealthMonitoringService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationService = new NotificationService(notificationRepository);
    }

    public void monitorMetrics(){
        for(HealthMetric metric: notificationRepository.getAllHealthMetrics()){
            double threshold = THRESHOLDS.getOrDefault(metric.getMetricType(), 150.0);
            if(metric.getMetricValue() > threshold){
                User user = notificationRepository.getUserById(metric.getUserId());
                if(user != null){
                    String message = "ALERT! Your " + metric.getMetricType() + " is above threshold: " + metric.getMetricValue();
                    notificationService.sendNotification(user, message);
                }
            }
        }
    }
}
