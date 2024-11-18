import java.util.*;

/**
 * Group Project -- Comments
 * <p>
 * This class creates Comments objects for our platform and assists the Post class
 *
 * @author L30-Team 1, CS180
 * @version Nov 3, 2024
 */

public class Comments implements CommentsInterface {
    private String comment; // A variable that holds the String comment that was posted
    private User commented; // A variable that holds the User who posted the comment
    private int upVotes; // A variable that holds the number of upVotes a comment received as an Integer
    private int downVotes; // A variable that holds the number of downVotes a comment received as an Integer

    // The Comments class constructor assigns the User and String given in the parameter to the corresponding
    // instances variable, it sets all the other values to our platform default settings
    public Comments(String comment, User commented) {
        this.comment = comment;
        this.commented = commented;
        this.upVotes = 0;
        this.downVotes = 0;
    }

    // A void method that increments the upVotes on a comment by 1 
    public void upVoteComment() {
        this.upVotes++;
    }

    // A void method that increments the downVotes on a comment by 1 
    public void downVoteComment() {
        this.downVotes++;
    }

    // An accessor method that returns the number of upVotes on a comment as an Integer
    public int getUpVotes() {
        return this.upVotes;
    }

    // An accessor method that returns the number of downVotes on a comment as an Integer
    public int getDownVotes() {
        return downVotes;
    }

    // An accessor method that returns the User who posted the comment
    public User getCommented() {
        return this.commented;
    }

    // An accessor method that returns the comment that was posted as a String
    public String getComment() {
        return this.comment;
    }

    // A mutator method that sets the upVotes to the amount passed in the parameter
    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    // A mutator method that sets the downVotes to the amount passed in the parameter
    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    // A toString method that returns the String consisting of the number of 
    // upvotes and downvotes, as well as the number of comments
    public String toString() {
        return "Upvotes: " + upVotes + "Downvotes: " + downVotes + "Comment: " + comment;
    }

}
