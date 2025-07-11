package main.java.model;

import main.java.service.NotificationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private User user;
    private String content;
    private Date createdAt;
    private List<Like> likes;
    private List<Comment> comments;
    public Post(int id, User user, String content) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.createdAt = new Date();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

}
