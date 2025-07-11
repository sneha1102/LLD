package main.java.repository;

import main.java.model.HealthMetric;
import main.java.model.Notification;
import main.java.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationRepository {
    private Map<String, User> userStore = new ConcurrentHashMap<>();
    private List<HealthMetric> healthMetricsStore = new CopyOnWriteArrayList<>();
    private List<Notification> notificationStore = new CopyOnWriteArrayList<>();

    public void addUser(User user){
        userStore.put(user.getUserId(), user);
    }

    public User getUserById(String userId){
        return userStore.getOrDefault(userId, null);
    }

    public Collection<User> getAllUser(){
        return userStore.values();
    }

    public void addHealthMetric(HealthMetric healthMetric){
        healthMetricsStore.add(healthMetric);
        System.out.println("HealthMetric added successfully");
    }

    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricsStore;
    }

    public void addNotification(Notification notification){
        notificationStore.add(notification);
        System.out.println("Notification added successfully");
    }

    public List<Notification> getAllNotifications(){
        return notificationStore;
    }
}
