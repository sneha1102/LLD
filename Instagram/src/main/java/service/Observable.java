package main.java.service;

import main.java.model.Post;
import main.java.model.User;

public interface Observable {
    void addObserver(User observer);   // Add observers (followers)
    void removeObserver(User observer); // Remove observers (like unfollow)
    void notifyObservers(Post post);   // Notify all observers about new posts
}
