package main.java.repository;

import main.java.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    public User saveUser(User user){
        return userMap.put(user.getUserId(), user);
    }

    public User findById(String id){
        return userMap.getOrDefault(id, null);
    }
}
