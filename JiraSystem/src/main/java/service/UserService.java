package main.java.service;

import main.java.model.User;
import main.java.repository.UserRepository;

import java.util.UUID;

public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name){
        User user = new User(UUID.randomUUID().toString(), name);
        User user1 = userRepository.findById(user.getUserId());
        if(user1 != null){
            System.out.println("User is already present with user id " + user1.getUserId());
            return user;
        }
        userRepository.saveUser(user);
        return user;
    }

    public User getUser(String id){
        return userRepository.findById(id);
    }
}
