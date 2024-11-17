import java.util.ArrayList;

/**
 * Group Project -- PostInterface
 * <p>
 * This class is the interface that our Post class will implement
 *
 * @author L30-Team 1, CS180
 * @version Nov 2, 2024
 */

public interface PostInterface {
    void likePost(); // A void method that increments the number of likes by 1

    void dislikePost(); // A void method that increments the number of dislikes by 1

    int getLikes(); // An accessor method that returns the integer number of likes a post received

    int getDislikes(); // An accessor method that returns the integer number of dislikes a post received

    ArrayList<Comments> getCommentList(); // Returns the ArrayList of Comments received on a Post

    Comments getComment(int index); // Returns the Comment from the comment list at the specified index

    void setCommentStatus(boolean commentStatus); // A mutator method that sets the commentStatus 
    // to what is passed in the parameter 

    boolean getCommentStatus(); // An accessor method that returns the current Comment Status 

    void addComment(Comments comment); // A void method that adds the comment 
    // passed in the parameter to the comment list

    void removeComment(Comments comment, User com); // A void method that removes the comment passed in the 
    // parameter from the comment list if the User being passed posted the comment or owns the post

    void hidePost(User wantHidden); // A void method that adds the User passed in the parameter to the postFriends list

    void unHidePost(User wantUnHidden); // A void method that removes the User passed in the 
    // parameter from the postFriends list

    ArrayList<User> getPostFriends(); // An Accessor method that returns the list of PostFriends (used for testing)

    void justCommented(String comment, User commented);       // A void method creating a comment object using the String comment 
    // and the user who commented it, it then adds the object to the comment list

    String toString(); // A toString method that returns the number of likes and dislikes, the String comments, and the text of the post

}
