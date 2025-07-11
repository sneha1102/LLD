import main.java.common.AccountType;
import main.java.model.Comment;
import main.java.model.Post;
import main.java.model.User;
import main.java.repository.PostRepository;
import main.java.service.FeedService;
import main.java.service.NotificationService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Notification Service Setup
        NotificationService notificationService = new NotificationService();

        // Create Users with NotificationService dependency
        User john = new User(1, "John", "@john_doe", AccountType.USER, notificationService);
        User nike = new User(2, "Nike", "@nike", AccountType.BRAND, notificationService);
        User influencer = new User(3, "Ravi Kumar", "@ravi_influencer", AccountType.INFLUENCER, notificationService);

        // Posts Repository
        PostRepository postRepository = new PostRepository();

        // Post Service
        FeedService postService = new FeedService(postRepository, notificationService);

        // John starts following Nike and Influencer
        System.out.println("John starts following Nike and Influencer...");
        john.follow(nike);
        john.follow(influencer);

        // Nike creates a new post
        System.out.println("\nNike posts a new update:");
        postService.createPost(nike, "New Nike shoes are in stock!");

        // Influencer creates a new post
        System.out.println("\nInfluencer posts a new update:");
        int postId = postService.createPost(influencer, "Check out my review of Nike shoes!");

        // add comments
        postService.createComment("pretty", nike.getUserId(), postId);
        postService.createComment("nice", influencer.getUserId(), postId);
        postService.createComment("great", john.getUserId(), postId);

        // fetch all comments for post
        List<Comment> comments = postService.getAllCommentsForPost(postId);
        System.out.println("Comments for posts are:");
        for(Comment comment: comments){
            System.out.println(comment.getContent());
        }
        // like the post
        postService.likePost(john.getUserId(), postId);
        postService.likePost(influencer.getUserId(), postId);
        postService.likePost(nike.getUserId(), postId);

        // likes count
        postService.getTotalLikeForPost(postId);

        // dislikes the post
        postService.dislikePost(john.getUserId(), postId);

        // likes count
        postService.getTotalLikeForPost(postId);

        // John unfollows Nike
        System.out.println("\nJohn unfollows Nike...");
        john.unfollow(nike);

        // Nike creates another post (John shouldn't see this notification)
        System.out.println("\nNike posts another update:");
        postService.createPost(nike, "Exciting sale on Nike products!");
        }
    }