package main.java.model;

import java.util.UUID;

public class Comment {
    private String id;
    private String content;
    private int userId;
    private int postId;

    public Comment(String content, int userId, int postId) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.userId = userId;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

}
