package main.java.repository;

import main.java.model.Comment;
import main.java.model.Like;
import main.java.model.Post;
import main.java.model.User;

import java.util.*;

public class PostRepository {
    private int id = 1;
    private Map<Integer, List<Post>> postByUser = new HashMap<>();

    public void addPost(Post post){
        postByUser.computeIfAbsent(post.getUser().getUserId(), k -> new ArrayList<>()).add(post);
    }
    public void removePostsForUser(User user){
        postByUser.remove(user.getUserId());
    }

    public void addComment(String content, int userId, int postId){
        for (Map.Entry<Integer, List<Post>> postEntry : postByUser.entrySet()) {
            List<Post> posts = postEntry.getValue(); // Extract the posts list for each user
            for(Post post: posts){
                if(post.getId() == postId){
                    List<Comment> comments = post.getComments();
                    Comment comment1 = new Comment(content, userId, postId);
                    comments.add(comment1);
                    System.out.println("Comments added successfully");
                    return;
                }
            }
        }

        System.out.println("There is not any post by userid: " + userId);
    }

    public List<Comment> getCommentForPost(int postId){
        // Iterate through all the posts by users in postByUser
        for (Map.Entry<Integer, List<Post>> postEntry : postByUser.entrySet()) {
            List<Post> posts = postEntry.getValue(); // Extract the posts list for each user

            for (Post post : posts) {  // Loop through individual posts
                if (post.getId() == postId) {  // Check if the post ID matches
                    return post.getComments(); // Return the comments of the matched post
                }
            }
        }

        // If no matching post is found, return an empty list or null
        return new ArrayList<>();
    }
    public List<Post> getPostsForUser(User user){
        return postByUser.getOrDefault(user.getUserId(), new ArrayList<>());
    }

    public int generatePostId(){
        return id++;
    }
    public void likePost(int userId, int postId){
        for (Map.Entry<Integer, List<Post>> postEntry : postByUser.entrySet()) {
            List<Post> posts = postEntry.getValue(); // Extract the posts list for each user
            for(Post post: posts){
                if(post.getId() == postId){
                    List<Like> likes = post.getLikes();
                    Like like = new Like(userId, postId);
                    likes.add(like);
                    System.out.println(userId + " liked the post successfully");
                    return;
                }
            }
        }
        System.out.println("No post exist with postId:" + postId);
    }

    public void dislikePost(int userId, int postId){
        for (Map.Entry<Integer, List<Post>> postEntry : postByUser.entrySet()) {
            List<Post> posts = postEntry.getValue(); // Extract the posts list for each user
            for(Post post: posts){
                if(post.getId() == postId){
                    List<Like> likes = post.getLikes();
                    for(Like like: likes){
                        if(like.getPostId() == postId){
                            likes.remove(like);
                            System.out.println(userId + " disliked the post successfully");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No post exist with postId:" + postId);
    }

    public void getTotalLikesForPost(int postId){
        for (Map.Entry<Integer, List<Post>> postEntry : postByUser.entrySet()) {
            List<Post> posts = postEntry.getValue(); // Extract the posts list for each user
            for(Post post: posts){
                if(post.getId() == postId){
                    int count = post.getLikes().size();
                    System.out.println("Like count for post with postId: " + postId + " count: " + count);
                    return;
                }
            }
        }
        System.out.println("No post exist with postId:" + postId);
    }
}
