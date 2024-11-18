import java.util.*;

/**
 * Group Project -- SocialMediaPlatformInterface
 * <p>
 * This class is the interface that our SocialMediaPlatform class will implement
 *
 * @author L30-Team 1, CS180
 * @version Nov 2, 2024
 */

public interface SocialMediaPlatformInterface {

    ArrayList<String> readFile(String filename); // returns an ArrayList of Strings that consists of the content in each line of the file given in the parameter

    void writeDatabaseFile(String username, String password, String outputFile);  // A void method that writes the given username and password into the output file given in the parameter

    void writeDatabaseFriendsFile(User user, String otherUsername, String outputFile); // A void method that writes the user and the otherUsername passed in the parameter into the output file

    void writeDatabaseBlockedFile(User user, String otherUsername, String outputFile); // A void method that writes the user and other username into the output file passed in the parameter

    ArrayList<String> getUsernamesOfBlocked(String username, ArrayList<String> blocked); // // A method that returns an ArrayList of Strings consisting of the usernames of the users the main user has blocked

    void removeFriendUsername(String username, String friendUsername, ArrayList<String> friends, String outputFile); // a void method that removes the username of the file from the friends file and removes the user's name from the friends file

    ArrayList<String> getPostsOfUser(String username, ArrayList<String> posts); // A method that returns an ArrayList of Strings that consist of the user's username and their posts

    void writeDatabasePostFile(User user, Post post, boolean updateOrNot, String outputFile); // A void method that writes the User, their post, and the boolean into the output file given in the parameter

    ArrayList<String> getCommentsOfPost(String postText, ArrayList<String> comments); // A method that returns an ArrayList of strings containing the text in the posts and their comments

    void writeDatabaseCommentsFile(String postText, Comments newComment, boolean updateOrNot, String outputFile); // A void method that writes the post text, the comments, the boolean, into the output file given in the parameter

    void removeCommentFromFile(String postText, String comment, ArrayList<String> comments, String outputFile); // A void method that removes the post text, and the comment from the output file given in the parameter 
    
    void addUser(User user); // adds a new User to the list of all Users in the database

    ArrayList<User> getUsers(); // gets the list of Users

    ArrayList<Post> getImages(); // gets the list of images

    void editUsername(User user); // edits the username of a User to include the '@' symbol

    void displayUsers(); // displays the Users list to the screen

    void run(); // the run method that will employ synchronized threads to concurrently run our program

    boolean checkForUsername(String username); // checks that the inputted username exists in the database

    boolean checkForPassword(String username, String password); // checks that the password associated with the inputted username exists and matches

    boolean checkUsername(String username); // checks if a username already exists or not

    boolean checkPassword(String password); // checks that a created password adheres to our criteria

    ArrayList<SocialMediaPlatform> getPlatformUsers(); //An accessor method that returns the platform user (used for testing)

    User viewUser(); // method that returns the user that the User wants to search for
}
