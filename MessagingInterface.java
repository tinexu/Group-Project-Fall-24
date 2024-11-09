import java.util.ArrayList;
/**
 * Group Project -- MessagingInterface
 *
 * This class is the interface that our Messaging class will implement
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 2, 2024
 *
 */
public interface MessagingInterface {
    void sendMessage() throws InvalidException; // Sending a message and checking if target user is either blocked or not a friend
    void deleteMessage(String targetMessage) throws InvalidException; // deletes a message
    User getSender(); // retrieves the User sender of a message
    User getReceiver(); // retrievers the User receiver of a message
    String getMessageContent(); // returns the String implementation of the message
}
