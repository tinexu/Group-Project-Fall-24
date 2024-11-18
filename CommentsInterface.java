/**
 * Group Project -- CommentsInterface
 * <p>
 * This class is the interface for the Comments class
 *
 * @author L30-Team 1, CS180
 * @version Nov 3, 2024
 */

public interface CommentsInterface {
  void upVoteComment(); // updates the upvote instance variable
  void downVoteComment(); // updates the downvote instance variable
  int getUpVotes(); // returns the variable associated with the upvote count
  int getDownVotes(); // returns the variable associated with the downvote count
  User getCommented(); // returns the User that commented the comment
  String getComment(); // returns the comment
  void setUpVotes(int upVotes); // A mutator method that sets the upVotes to the amount passed in the parameter
  void setDownVotes(int downVotes) // A mutator method that sets the downVotes to the amount passed in the parameter
  String toString(); // returns number of upVotes, downVotes, and comments in String format
}
