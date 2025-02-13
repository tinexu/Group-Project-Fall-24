import java.util.*;

/**
 * Group Project -- User
 * <p>
 * This class creates User objects for our platform
 *
 * @author L30-Team 1, CS180
 * @version Nov 2, 2024
 */

public class User implements UserInterface {
    private String username; // the username of a User
    private String password; // the password of a User's account
    private ArrayList<User> friends; // the list of friends for a User
    private ArrayList<User> blocked; // the list of blocked people/friends for a User
    private ArrayList<Post> posts; // An arrayList of the User's Posts
    private boolean priv; // A boolean variable that determines whether messages are restricted to friends only
    private ArrayList<String> messages; // An arrayList of messages holding all the messages the User sent

    public static final Object lock = new Object(); // the shared Object, lock, between all Threads using this platform

    // Constructor for the creation of a new User
    // User can specify their username and password, but all other instance variables are set to their default values
    public User(String username, String password) {
        this.username = username;
        this.password = password;

        friends = new ArrayList<>();
        blocked = new ArrayList<>();
        posts = new ArrayList<>();
        priv = false;
        messages = new ArrayList<>();
    }

    // A void method that adds the String message to the messages list
    public void addMessage(String message) {
        messages.add(message);
    }

    // An accessor method that returns the arrayList of the messages sent as Strings
    public ArrayList<String> getMessages() {
        return this.messages;
    }

    // Accessor method for the username
    // returns a String username
    public String getUsername() {
        return username;
    }

    // A boolean accessor method that returns whether messages are restricted to friends
    public boolean isPriv() {
        return priv;
    }

    // A void mutator method that sets the priv variable to what is passed in the parameter
    public void setPriv(boolean priv) {
        this.priv = priv;
    }

    // Mutator method for the username (in case a User wants to change their username)
    // void return; changes an instance variable
    // only used for test case (not actually meant to implemented)
    public void setUsername(String username) {
        this.username = username;
    }

    // Accessor method for the password
    // returns a String password
    public String getPassword() {
        return password;
    }

    // Mutator method for the password
    // void return; changes an instance variable
    public void setPassword(String password) {
        this.password = password;
    }

    // Accessor method for the friends list of a User
    // returns ArrayList<User> of friends
    public ArrayList<User> getFriends() {
        return friends;
    }

    // Accessor method for the blocked list of a User
    // returns ArrayList<User> of blocked people/friends
    public ArrayList<User> getBlocked() {
        return blocked;
    }

    // Accessor method for the posts of a User
    // returns ArrayList<Posts> of posts
    public ArrayList<Post> getPosts() {
        return posts;
    }

    // Mutator method for the list posts
    // void return; changes an instance variable
    public void addPost(Post post) {
        posts.add(post);
    }

    // Method to add a User friend (follow someone) to a User's list of friends
    // void return; changes an instance variable
    public void addFriend(User friend) {
        friends.add(friend);
    }

    // Method to remove a friend (unfollow someone) from a User's list of friends
    // void return; changes an instance variable
    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    // Method to block a friend (stop messaging and displaying posts) from the database of Users for a User
    // void return; changes instance variables
    public void blockUser(User user) {
        blocked.add(user);
        if (friends.contains(user)) {
            friends.remove(user);
        }
    }

    // Method to unblock a friend (reinstate messaging and post displaying privileges) from the database of Users for a User
    // void return; changes an instance variable
    public void unblockUser(User user) {
        blocked.remove(user);
    }

    // Method that checks if the usernames of two User objects is the same
    // Can check if a username already exists in the database of Users or for other functionalities included in Post, Feed, and Messaging classes
    // returns a boolean, true (equals) or false (not equal)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof User) {
            User user = (User) o;
            if (username.equals(user.username)) {
                return true;
            }
        }

        return false;
    }

    // Method that returns the String value of a user, displaying all of their necessary information (eventually used for profile viewing/displaying purposes
    // returns a String of a User's username, password, follower count, and following count
    @Override
    public String toString() {
        return String.format("Username: %s, Friends: %s", username, friends.size());
    }

    // Sets the arrayList of messages passed in the parameter to the instance variable
    public void setMessages(ArrayList<String> message) {
        this.messages = message;
    }
}
