package main.java.service;

import main.java.model.Comment;
import main.java.model.Post;
import main.java.model.User;
import main.java.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class FeedService {
    private PostRepository postRepository;
    private NotificationService notificationService;


    public FeedService(PostRepository postRepository, NotificationService notificationService) {
        this.postRepository = postRepository;
        this.notificationService = notificationService;
    }

    public int createPost(User user, String content) {
        int id = postRepository.generatePostId();
        Post post = new Post(id, user, content);
        postRepository.addPost(post);

        // Notify followers about the new post
        notificationService.notifyObservers(post);
        return id;
    }

    public List<Post> generateFeed(User user){
        List<Post> feed = new ArrayList<>();
        for(User following: user.getFollowing()){
            feed.addAll(postRepository.getPostsForUser(following));
        }
        return feed;
    }

    public void refreshFeedOnUnfollow(User user, User unfollow){
        user.getFollowing().remove(unfollow);
    }

    public void addFollowerToNotifications(User follower) {
        notificationService.addObserver(follower);
    }

    public void removeFollowerFromNotifications(User follower) {
        notificationService.removeObserver(follower);
    }

    public void createComment(String content, int userId, int postId){
        postRepository.addComment(content, userId, postId);
    }

    public List<Comment> getAllCommentsForPost(int postId){
        return postRepository.getCommentForPost(postId);
    }

    public void likePost(int userId, int postId){
        postRepository.likePost(userId, postId);
    }

    public void dislikePost(int userId, int postId){
        postRepository.dislikePost(userId, postId);
    }

    public void getTotalLikeForPost(int postId){
         postRepository.getTotalLikesForPost(postId);
    }
}
