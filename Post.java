import java.util.*;

/**
 * Group Project -- Post
 * <p>
 * This class creates Post objects for our platform and assists the feed class
 *
 * @author L30-Team 1, CS180
 * @version Nov 2, 2024
 */

public class Post {
    private int likes; // An integer holding the number of likes the post received
    private int dislikes; // An integer holding the number of dislikes the post received
    private int comments; // An integer holding the number of comments the post received
    private boolean canComment; // A boolean that determines if a post can receive comments
    private User posted; // An object User that holds the User who made the post
    private ArrayList<User> postFriends; // A list of Users that a User wants to hide from their feed
    private ArrayList<Comments> commentList; // A list of Comment objects holding the comments on the post
    private String postText; // The String that contains the "caption" of the Post
    private String imagePath; // to be more implemented with forward phases; the String of the image's path

    // The Post class constructor assigns the User given in the parameter to the instance variable
    // It sets all the other values to our platform default settings
    public Post(User posted, String postText) {
        this.likes = 0;
        this.dislikes = 0;
        this.comments = 0;
        this.posted = posted;
        this.canComment = true;
        this.postFriends = posted.getFriends();
        this.postText = postText;
        this.commentList = new ArrayList<>();
    }

    // A void method that increments the likes on a post by 1
    public void likePost() {
        likes++;
    }

    // A void method that increments the dislikes on a post by 1 
    public void dislikePost() {
        dislikes++;
    }

    // An accessor method that returns the number of likes as an Integer
    public int getLikes() {
        return likes;
    }

    // An accessor method that returns the number of dislikes as an Integer
    public int getDislikes() {
        return dislikes;
    }

    // An accessor method that returns the array list of comments
    public ArrayList<Comments> getCommentList() {
        return commentList;
    }

    // An accessor method that returns a comment from the comment list at the specified index
    public Comments getComment(int index) {
        return commentList.get(index);
    }

    // A void mutator method that sets the comment status based on what the user passed to the parameter
    public void setCommentStatus(boolean commentStatus) {
        this.canComment = commentStatus;
    }

    // A boolean accessor method that returns the current comment status
    public boolean getCommentStatus() {
        return canComment;
    }


    // A void method that adds the comment passed in the parameter to the comment list
    public void addComment(Comments comment) {
        commentList.add(comment);
    }

    // A method that returns a boolean and checks if the User passed in the parameter posted the comment passed in the parameter
    // or was the User who made the post, if so it will remove the comment from the comment list
    public boolean removeComment(Comments comment, User com) {
        if (comment.getCommented().equals(com) || comment.getCommented().equals(posted)) {
            commentList.remove(comment);
            return true;
        }
        return false;
    }

    // A void method that adds the User that the main User wants Hidden to the postFriends list
    public void hidePost(User wantHidden) {
        if (!(postFriends.contains(wantHidden))) {
            postFriends.add(wantHidden);
        }
    }

    // A void method that removes the User that the main User wants unHidden from the postFriends list
    public void unHidePost(User wantUnHidden) {
        postFriends.remove(wantUnHidden);
    }

     // An Accessor method that returns the list of PostFriends (used for testing)
    public ArrayList<User> getPostFriends() {
        return postFriends;
    }
    
    // A void method that creates a comment object using the String comment
    // and the user who commented it, it then adds the object to the comment list
    public void justCommented(String comment, User commented) {
        Comments comments = new Comments(comment, commented);
        commentList.add(comments);
    }

    // A mutator method that sets the likes to the amount passed in the parameter
    public void setLikes(int likes) {
        this.likes = likes;
    }

    // A mutator method that sets the dislikes to the amount passed in the parameter
    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    // A toString method that returns the number of likes and dislikes, the String comments, and the text of the post
    public String toString() {
        String allComments = "";
        for (int i = 0; i < commentList.size(); i++) {
            allComments += commentList.get(i).toString() + " ";
        }

        String result = postText + " Likes: " + likes + " Dislikes: " + dislikes + " Comments: " + allComments;
        return result;
    }

}
