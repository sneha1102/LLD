package main.java.model;

import main.java.common.AccountType;
import main.java.service.NotificationService;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int userId;
    private String name;
    private AccountType accountType; // USER, BRAND, INFLUENCER
    private Set<User> follower; // Users following this account
    private Set<User> following; // Accounts this user is following
    private String handle;
    private NotificationService notificationService;

    public User(int userId, String name, String handle, AccountType accountType, NotificationService notificationService) {
        this.userId = userId;
        this.name = name;
        this.follower = new HashSet<>();
        this.following = new HashSet<>();
        this.accountType = accountType;
        this.handle = handle;
        this.notificationService = notificationService;
    }
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Set<User> getFollower() {
        return follower;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getHandle() {
        return handle;
    }

    public void addFollower(User user){
        follower.add(user);
    }

    public void removeFollower(User user){
        follower.remove(user);
    }
    public void follow(User user){
        following.add(user);
        user.addFollower(this);
        // Automatically add the follower (this user) to notifications for the followed account
        notificationService.addObserver(this);
        System.out.println("User @" + handle + " is now following @" + user.getHandle()
                + " and notifications have been registered.");
    }

    public void unfollow(User user){
        following.remove(user);
        user.removeFollower(this);
        // Automatically remove the follower (this user) from notifications for the unfollowed account
        notificationService.removeObserver(this);
        System.out.println("User @" + handle + " has unfollowed @" + user.getHandle()
                + " and notifications have been unregistered.");
    }

    public void receiveNotification(Post post){
        System.out.println("Notification: " + handle + " - New post from @" +
                post.getUser().getHandle() + ": " + post.getContent());
    }
}
