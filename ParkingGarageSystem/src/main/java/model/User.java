package main.java.model;

public class User {
    private int userId;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User(int userId) {
        this.userId = userId;
    }
}
