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
