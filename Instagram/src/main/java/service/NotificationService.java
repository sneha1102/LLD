package main.java.service;

import main.java.model.Post;
import main.java.model.User;

import java.util.HashSet;
import java.util.Set;

public class NotificationService implements Observable{
    private Set<User> observers; // Track users (followers) to notify


    public NotificationService() {
        this.observers = new HashSet<>();
    }
    @Override
    public void addObserver(User observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(User observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Post post) {
        for(User observer: observers){
            observer.receiveNotification(post);
        }
    }

    public Set<User> getObservers() {
        return observers;
    }
}
