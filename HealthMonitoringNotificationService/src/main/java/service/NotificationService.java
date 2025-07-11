package main.java.service;

import main.java.model.Notification;
import main.java.model.User;
import main.java.repository.NotificationRepository;

public class NotificationService {
    private final NotificationRepository repository;


    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public String sendNotification(User user, String message){
        System.out.println("Sending notification to user with userId:" + user.getUserId() + " message:" + message);
        Notification notification = new Notification(user.getUserId(), message);
        repository.addNotification(notification);
        return "Notification sent successfully";
    }
}
