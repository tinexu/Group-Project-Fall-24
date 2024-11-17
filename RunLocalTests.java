//All necessary imports
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;

public class RunLocalTests {
    //Main method that runs all of the test cases
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunLocalTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful() ? "All tests passed." : "Some tests failed.");
    }

    //Tests that the equals method in the User class returns the correct boolean value
    @Test
    public void testUserEquals() {
        User user = new User("User1", "1234");
        Assert.assertTrue("The result should have been true but it was false",
                user.equals(new User("User1", "1234")));

        Assert.assertFalse("The result should have been false but it was true",
                user.equals(new User("User2", "1234")));
    }

    //Tests that the constructor in the User class intitializes the right values for the instance variables
    @Test
    public void testUserConstructor() {
        User user = new User("user1", "1234");
        Assert.assertEquals("user1", user.getUsername());
        Assert.assertTrue(user.getFriends().isEmpty());
        Assert.assertTrue(user.getBlocked().isEmpty());
        Assert.assertTrue(user.getPosts(). isEmpty());
    }

    //Tests that the add and remove friend methods update the instance variable correctly
    @Test
    public void testAddAndRemoveFriend() {
        User user = new User("user1", "1234");
        User user2 = new User("user2", "1234");
        user.addFriend(user2);
        Assert.assertTrue(user.getFriends().contains(user2));
        user.removeFriend(user2);
        Assert.assertFalse(user.getFriends().contains(user2));
    }

    //Tests that the add and remove friend methods update the instance variable correctly
    @Test
    public void testAddAndRemoveBlocked() {
        User user = new User("user1", "1234");
        User user2 = new User("user2", "1234");
        user.blockUser(user2);
        Assert.assertTrue(user.getBlocked().contains(user2));
        user.unblockUser(user2);
        Assert.assertFalse(user.getBlocked().contains(user2));
    }

    //Tests that the add and remove comment methods update the instance variable correctly
    @Test
    public void testAddAndRemoveComment() {
        User user = new User("user1", "1234");
        Post post = new Post(user, "");
        Comments comment = new Comments("Hello", user);
        post.addComment(comment);
        Assert.assertTrue(post.getCommentList().contains(comment));
        post.removeComment(comment, user);
        Assert.assertFalse(post.getCommentList().contains(comment));
    }

    //Tests that the toString() overridden method of the User class returns the correct information values
    @Test
    public void testUserToString() {
        User user = new User("user1", "1234");
        Assert.assertEquals("Username: user1, Password: 1234, Followers: 0, Following: 0", user.toString());
    }

    //Tests if the hidePost() and unhidePost() methods work as expected
    @Test
    public void testHideAndUnhidePost() {
        User user1 = new User("user1", "1234");
        User user2 = new User("user2", "1234");
        Post post = new Post(new User("poster", "password"), "");
        post.hidePost(user1);
        post.hidePost(user2);

        // Test unhiding a post from a user in postFriends
        post.unHidePost(user1);
        Assert.assertFalse(post.getPostFriends().contains(user1));
        Assert.assertEquals(1, post.getPostFriends().size());

        // Test unhiding a post from a user not in postFriends
        post.unHidePost(user1);
        Assert.assertEquals(1, post.getPostFriends().size());

        // Test unhiding the last user
        post.unHidePost(user2);
        Assert.assertTrue(post.getPostFriends().isEmpty());

    }

    //Tests that the constructor in the Comments class initializes the values correctly
    @Test
    public void testCommentsConstructor() {
        User user = new User("user1", "1234");
        Comments comment = new Comments("Hello", user);
        Assert.assertEquals("Hello", comment.getComment());
        Assert.assertTrue(user.equals(comment.getCommented()));
    }

    //Tests that the upvote and downvote methods work as expected
    @Test
    public void testCommentUpvoteandDownvote() {
        User user = new User("user1", "1234");
        Comments comment = new Comments("Hello", user);
        for (int i = 0; i < 10; i++) {
            comment.upVoteComment();
        }
        Assert.assertTrue(comment.getUpVotes() == 10);
        for (int i = 0; i < 5; i++) {
            comment.downVoteComment();
        }
        Assert.assertTrue(comment.getDownVotes() == 5);
    }

    //Tests that the constructor for the SocialMediaPlatform class initializes the instance variables correctly
    @Test
    public void testSocialMediaPlatformConstructor() {
        SocialMediaPlatform socialMediaPlatform = new SocialMediaPlatform();
        Assert.assertTrue(socialMediaPlatform.getImages().isEmpty());
        Assert.assertTrue(socialMediaPlatform.getUsers().isEmpty());
        Assert.assertTrue(socialMediaPlatform.getPlatformUsers().isEmpty());
        Assert.assertTrue(socialMediaPlatform.getImages().isEmpty());
    }

    //Tests the like and dislike methods for Post
    @Test
    public void testLikeAndDislikePost() {
        User user = new User("user1", "1234");
        Post post = new Post(user, "");
        post.dislikePost();
        post.dislikePost();
        Assert.assertEquals(2, post.getDislikes());
        post.likePost();
        post.likePost();
        post.likePost();
        Assert.assertEquals(3, post.getLikes());
    }

    //Tests the Post toString method
    @Test
    public void testPostToString() {
        User user = new User("user1", "1234");
        Post post = new Post(user, "Hello");
        Assert.assertEquals("Hello Likes: 0 Dislikes: 0 Comments: ", post.toString());
    }

    //Tests the justCommented method in Post
    @Test
    public void testJustCommented() {
        User user = new User("user1", "1234");
        Post post = new Post(user, "Hello");
        User user2 = new User("user2", "1234");
        post.justCommented("Hello", user2);
        var comments = new ArrayList<Comments>();
        comments.add(new Comments("Hello", user2));
        Assert.assertEquals(comments, post.getCommentList());
    }

    //Tests the getUsernameOfFriends method in SocialMediaPlatform
    @Test
    public void testGetUsernameOfFriends() {
        User user = new User("user1", "1234");
        User user2 = new User("user2", "1234");
        User user3 = new User("user3", "1234");
        SocialMediaPlatform smp = new SocialMediaPlatform();
        user.addFriend(user2);
        user.addFriend(user3);
        var userFriends = new ArrayList<String>();
        userFriends.add("user2");
        userFriends.add("user3");
        var friendsData = new ArrayList<String>();
        for (int i = 2; i < 10; i++) {
            String friend = "User" + i + ":";
            for (int j = 0; j < 5; j++) {
                friend = friend + ",user" + j + ",";
            }
            friendsData.add(friend);
        }
        friendsData.add("user: user2,user3");
        friendsData.add("User100: user102,user230");
        Assert.assertEquals(userFriends, smp.getUsernamesOfFriends("user", friendsData));
    }

    //Tests the getPostsOfUser method in SocialMediaPlatform
    @Test
    public void testGetPostsOfUser() {
        SocialMediaPlatform smp = new SocialMediaPlatform();
        var userPosts = new ArrayList<String>();
        userPosts.add("Hello");
        userPosts.add("Bye");
        var postData = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String postLine = "user" + i + ":";
            for (int j = 0; j < 4; j++) {
                postLine += "blah blah,";
            }
            postLine += "blah blah";
            postData.add(postLine);
        }
        postData.add("user: Hello,Bye");
        Assert.assertEquals(userPosts, smp.getPostsOfUser("user", postData));
    }
}
