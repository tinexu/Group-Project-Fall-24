import java.util.*;

/**
 * Group Project -- UserInterface
 * <p>
 * This class is the interface that our User class will implement
 *
 * @author L30-Team 1, CS180
 * @version Nov 2, 2024
 */

public interface UserInterface {
    String getUsername(); // method that returns the username of a User

    String getPassword(); // method that returns the password of a User

    void setPassword(String password); // sets that sets/changes the password for a User

    void setUsername(String username); // method that sets/changes the username for a User

    void addFriend(User friend); // adds a friend to a User's list of friends

    void removeFriend(User friend); // removes a friend from a User's list of friends

    void blockUser(User user); // blocks a user (regardless of friend status)

    void unblockUser(User user); // unblocks a user (regardless of friend status)

    boolean equals(Object o); // checks if two Users are equal by username

    ArrayList<User> getFriends(); // gets a User's friends list

    ArrayList<User> getBlocked(); // get a User's blocked list

    ArrayList<Post> getPosts(); // gets a User's list of existing posts

    void addPost(Post post); // adds to individual post lists each time a User posts

    String toString(); // stores all of a User's information in a String implementation

    void addMessage(String message); // Adds the message passed in the parameter to the messages list

    ArrayList<String> getMessages(); // Returns the ArrayList of messages sent

    boolean isPriv(); // Returns the boolean variable determining if the user can only receive messages from friends

    void setPriv(boolean priv); // Sets the boolean variable determining the User's message restrictions

    // to what is passed in the parameter
    void setMessages(ArrayList<String> message); // Sets the arrayList of messages passed in the parameter to the
    //instance variable

}
