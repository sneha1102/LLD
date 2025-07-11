package main.java.service;

import main.java.model.User;

public interface Observable {
    void addObserver(User user);
    void removeObserver(User user);
    void notifyObservers();

}
