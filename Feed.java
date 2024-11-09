import java.util.*;

/**
 * Group Project -- Feed
 *
 * This class creates Feed objects for our platform and assists in our visual display
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 2, 2024
 *
 */

// This class is still in progress and will be more detailed in implementation when we are completely focused on creating a functional GUI
public class Feed implements FeedInterface {
    private User user; // the user that this feed is associated with
    private ArrayList<String> imagePaths; // list of image paths that are associated with a User's feed
    private ArrayList<String> threads; // list of threads that are associated with a User's feed

    // Constructor for the feed class that takes in a User as its parameter in order to associate a User with their specialized feed
    public Feed(User user) {
        this.user = user;
        imagePaths = new ArrayList<>();
    }

    // Method that displays the images for a certain User's feed to the screen
    // returns void
    public void displayImageFeed() {
        /*
        to be implemented
         */
    }

    // Method that displays text for a certain User's feed to the screen (similar to Instagram Threads or Twitter)
    // returns void
    public void displayTextFeed() {
        /*
        to be implemented
         */
    }

    // Accessor method for the list of image paths
    // returns ArrayList<String> of the image paths in a feed for a certain User
    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    // Method that adds a new image to the ArrayList of images
    // returns void; changes an instance variable
    public void addToImagePaths(String path) {
        imagePaths.add(path);
    }

    // Method that adds a new thread to the ArrayList of threads
    // returns void; changes an instance variable
    public void addToThreads(String thread) {
        threads.add(thread);
    }

}
