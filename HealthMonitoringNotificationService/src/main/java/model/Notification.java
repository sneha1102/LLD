package main.java.model;

import java.time.LocalDateTime;

public class Notification {
    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

    private String userId;
    private String message;
    private LocalDateTime dateTime;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
