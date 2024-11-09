import java.util.*;

/**
 * Group Project -- FeedInterface
 *
 * This class is the interface that our Feed class will implement
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 2, 2024
 *
 */

public interface FeedInterface {
    void displayImageFeed(); // displays the posts to the User's feed
    void displayTextFeed(); // displays the threads to the User's feed
    ArrayList<String> getImagePaths(); // gets the image paths stored in a User's feed
    void addToImagePaths(String path); // adds a new post to a User's feed
    void addToThreads(String thread); // adds a new thread to a User's feed
}
