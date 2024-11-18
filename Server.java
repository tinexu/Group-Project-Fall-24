import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Group Project -- Server
 *
 * This class runs the server aspect of our platform
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 17, 2024
 *
 */
public class Server extends Thread {
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    // A method to just write the input and flush the writer automatically
    public static void defaultCommandsForWriter(PrintWriter w, String input) {
        w.write(input);
        w.println();
        w.flush();
    }

    // The run method for the server's threading functionality
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            SocialMediaPlatform smp = new SocialMediaPlatform();

            try {
                File myFile = new File("output.txt");
                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File file = new File("/Users/christinexu/IdeaProjects/groupproject/output.txt");
                if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/output.txt")) == 0) {
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        printWriter.println("Username: HelloWorld,Password: Hello1234");
                    }
                }
            } catch (IOException e) {
                return;
            }

            try {
                File myFile = new File("blocked.txt");

                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File file = new File("/Users/christinexu/IdeaProjects/groupproject/blocked.txt");
                if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/blocked.txt")) == 0) {
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        printWriter.println("Username: HelloWorld,Blocked: Username: HellW");
                    }
                }
            } catch (IOException e) {
                return;
            }

            try {
                File myFile = new File("posts.txt");

                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File file = new File("/Users/christinexu/IdeaProjects/groupproject/posts.txt");
                if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/posts.txt")) == 0) {
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        printWriter.println("Username: HelloWorld,Posts: HelloWorld Likes: 0 Dislikes: 0 Comments: 0");
                    }
                }
            } catch (IOException e) {
                return;
            }

            try {
                File myFile = new File("posts.txt");

                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File file = new File("/Users/christinexu/IdeaProjects/groupproject/posts.txt");
                if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/posts.txt")) == 0) {
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        printWriter.println("Username: HelloWorld,HelloWorld Likes: 0 Dislikes: 0 Comments: 0");
                    }
                }
            } catch (IOException e) {
                return;
            }

            try {
                File myFile = new File("comments.txt");
                if (myFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                File file = new File("/Users/christinexu/IdeaProjects/groupproject/comments.txt");
                if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/comments.txt")) == 0) {
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        printWriter.println("HelloWord,Comments: stunning Upvotes: 0 Downvotes: 0 Owner: Username: hellw, Friends: 0,");
                    }
                }
            } catch (IOException e) {
                return;
            }

            ArrayList<String> people = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/output.txt");
            ArrayList<String> friends = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/friends.txt");
            ArrayList<String> blocked = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/blocked.txt");
            ArrayList<String> posts = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/posts.txt");
            ArrayList<String> comments = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/comments.txt");

            System.out.println(comments);

            ArrayList<User> users = smp.getUsers();
            ArrayList<String> friendsList = smp.getFriends();
            ArrayList<String> postsList = smp.getPosts();

            for (String s : friends) {
                friendsList.add(s);
            }
            System.out.println(friendsList);
            for (String s : posts) {
                postsList.add(s);
            }
            System.out.println(postsList);

            User user;
            String username1;

            ArrayList<String> listOfFriendUsernames = new ArrayList<>();

            outer:
            do {
                String choice = reader.readLine();

                if (people.isEmpty()) {
                    choice = "CREATE";
                }
                for (String s : people) {
                    users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
                }
                //System.out.println(users);

                String username = "";

                if (choice.contains("LOGIN")) {
                    inner1:
                    do {
                        String usernameMessage = "Enter your username";
                        defaultCommandsForWriter(writer, usernameMessage);

                        username = reader.readLine();
                        boolean checkUsername = smp.checkForUsername(username);
                        defaultCommandsForWriter(writer, Boolean.toString(checkUsername));
                        if (!checkUsername) {
                            String errorMessage = "That username does not exist. Return to homepage or try again?";
                            defaultCommandsForWriter(writer, errorMessage);
                            String usernameChoice = reader.readLine().toUpperCase();
                            if (usernameChoice.contains("RETURN")) {
                                String usernameBool = "TRUE";
                                defaultCommandsForWriter(writer, usernameBool);
                                continue outer;
                            } else if (usernameChoice.contains("TRY") || usernameChoice.contains("AGAIN")) {
                                String usernameBool = "FALSE";
                                defaultCommandsForWriter(writer, usernameBool);
                                continue inner1;
                            }
                        }
                        break inner1;
                    } while (true);

                    inner2:
                    do {
                        String passwordMessage = "Enter your password";
                        defaultCommandsForWriter(writer, passwordMessage);

                        String password = reader.readLine();
                        boolean checkPassword = smp.checkForPassword(username, password);
                        defaultCommandsForWriter(writer, Boolean.toString(checkPassword));
                        if (checkPassword) {
                            user = new User(username, password);
                            //System.out.println(user.toString());
                            String loginSuccess = "Login successful";
                            defaultCommandsForWriter(writer, loginSuccess);
                            break outer;
                        } else {
                            String errorMessage = "Wrong password. Return to homepage or try again?";
                            defaultCommandsForWriter(writer, errorMessage);
                            String passwordChoice = reader.readLine().toUpperCase();
                            if (passwordChoice.contains("RETURN")) {
                                String passwordBool = "TRUE";
                                defaultCommandsForWriter(writer, passwordBool);
                                continue outer;
                            } else if (passwordChoice.contains("AGAIN")) {
                                String passwordBool = "FALSE";
                                defaultCommandsForWriter(writer, passwordBool);
                                continue inner2;
                            }
                        }
                    } while (true);
                } else if (choice.contains("CREATE")) {
                    inner3:
                    do {
                        String createUsernameMessage = "Enter a username";
                        defaultCommandsForWriter(writer, createUsernameMessage);

                        username = reader.readLine();
                        if (smp.checkUsername(username)) {
                            boolean checkUsername = smp.checkUsername(username);
                            defaultCommandsForWriter(writer, Boolean.toString(checkUsername));
                            String checkDupUsernameMessage = "Username created.";
                            defaultCommandsForWriter(writer, checkDupUsernameMessage);
                            break inner3;
                        } else {
                            boolean checkUsername = smp.checkUsername(username);
                            defaultCommandsForWriter(writer, Boolean.toString(checkUsername));
                            String checkDupUsernameMessage = "Username already exists. Try again.";
                            defaultCommandsForWriter(writer, checkDupUsernameMessage);
                            continue inner3;
                        }
                    } while (true);

                    inner4:
                    do {
                        String createPasswordMessage = "Enter a password";
                        defaultCommandsForWriter(writer, createPasswordMessage);

                        String password = reader.readLine();
                        if (smp.checkPassword(password)) {
                            boolean checkPassword = smp.checkPassword(password);
                            defaultCommandsForWriter(writer, Boolean.toString(checkPassword));
                            smp.writeDatabaseFile(username, password, "output.txt");
                            user = new User(username, password);
                            String createdPasswordMessage = "Account successfully created.";
                            defaultCommandsForWriter(writer, createdPasswordMessage);
                            break outer;
                        } else {
                            boolean checkPassword = smp.checkPassword(password);
                            defaultCommandsForWriter(writer, Boolean.toString(checkPassword));
                            String createdPasswordMessage = "Weak password. Try again.";
                            defaultCommandsForWriter(writer, createdPasswordMessage);
                            continue inner4;
                        }
                    } while (true);
                }
            } while (true);

            String welcomeMessage = "Welcome!";
            defaultCommandsForWriter(writer, welcomeMessage);

            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("1-Search for a User");
            optionList.add("2-Post");
            optionList.add("3-Add Friend");
            optionList.add("4-Remove Friend");
            optionList.add("5-Block Friend");
            optionList.add("6-View Feed");
            optionList.add("7-View My Account");
            optionList.add("8-Exit");

            outer2:
            do {
                for (int i = 0; i < optionList.size(); i++) {
                    defaultCommandsForWriter(writer, optionList.get(i));
                }

                String optionStr = reader.readLine();
                int option = Integer.parseInt(optionStr);

                switch (option) {
                    case 1:
                        switchInner1:
                        do {
                            String searchPrompt = "Type in the username of the User you are looking for.";
                            defaultCommandsForWriter(writer, searchPrompt);

                            String userToSearchFor = reader.readLine();
                            User userFound = smp.viewUser(userToSearchFor);
                            if (userFound == null) {
                                String foundBool = "false";
                                defaultCommandsForWriter(writer, foundBool);
                                String notFoundMessage = "That user does not exist. Do you want to try again or go back to the home page? (Try/Back)";
                                defaultCommandsForWriter(writer, notFoundMessage);

                                String errorMessage = reader.readLine();
                                if (errorMessage.equals("Try")) {
                                    String errorBool = "true";
                                    defaultCommandsForWriter(writer, errorBool);
                                    continue switchInner1;
                                } else {
                                    String errorBool = "false";
                                    defaultCommandsForWriter(writer, errorBool);
                                    continue outer2;
                                }
                            } else {
                                String foundBool = "true";
                                defaultCommandsForWriter(writer, foundBool);
                                String foundUser = userFound.toString();
                                defaultCommandsForWriter(writer, foundUser);
                                continue outer2;
                            }
                        } while (true);
                    case 2:
                        switchInner2:
                        do {
                            String postPrompt = "Type in what you would like to post!";
                            defaultCommandsForWriter(writer, postPrompt);

                            String thingToPost = reader.readLine();

                            Post post = new Post(user, thingToPost);
                            user.addPost(post);
                            smp.writeDatabasePostFile(user, post, false, "posts.txt");

                            String successfulPost = "\"" + post.getPostText() + "\"" + " has been successfully posted!";
                            defaultCommandsForWriter(writer, successfulPost);

                            String optionPostPrompt = "Would you like to make another post or go back to the home page? (Post/Home)";
                            defaultCommandsForWriter(writer, optionPostPrompt);

                            String optionPost = reader.readLine();
                            String optionBool = "";
                            if (optionPost.equals("Post")) {
                                optionBool = "true";
                                defaultCommandsForWriter(writer, optionBool);
                                continue switchInner2;
                            } else {
                                optionBool = "false";
                                defaultCommandsForWriter(writer, optionBool);
                                continue outer2;
                            }
                        } while (true);
                    case 3:
                        switchInner3:
                        do {
                            listOfFriendUsernames = smp.getUsernamesOfFriends(user.getUsername(), friends);
                            String addFriendPrompt = "Who would you like to add as your friend?";
                            defaultCommandsForWriter(writer, addFriendPrompt);

                            String userToAdd = reader.readLine();
                            User userFound = smp.viewUser(userToAdd);

                            String foundBool = "";
                            if (userFound == null) {
                                System.out.println("nulll");
                                foundBool = "false";
                                defaultCommandsForWriter(writer, foundBool);
                                String notFoundPrompt = "That user does not exist. Would you like to add someone else or return to the home page? (Add/Home)";
                                defaultCommandsForWriter(writer, notFoundPrompt);

                                String notFoundChoice = reader.readLine();
                                String notFoundBool = "";
                                if (notFoundChoice.equals("Add")) {
                                    notFoundBool = "true";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue switchInner3;
                                } else {
                                    notFoundBool = "false";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue outer2;
                                }
                            }

                            foundBool = "true";
                            defaultCommandsForWriter(writer, foundBool);

                            String checkFriend = "true";
                            boolean checkFriendBool = false;
                            for (int i = 0; i < listOfFriendUsernames.size(); i++) {
                                if (listOfFriendUsernames.get(i).equals(userFound.getUsername())) {
                                    System.out.println("alr friend");
                                    checkFriend = "false";
                                    defaultCommandsForWriter(writer, checkFriend);
                                    checkFriendBool = true;
                                    String alreadyFriend = "This User is already your friend! Would you like to add someone else or return to the home page? (Add/Home)";
                                    defaultCommandsForWriter(writer, alreadyFriend);

                                    String alreadyFriendChoice = reader.readLine();
                                    String alreadyFriendBool = "";
                                    if (alreadyFriendChoice.equals("Add")) {
                                        alreadyFriendBool = "true";
                                        defaultCommandsForWriter(writer, alreadyFriendBool);
                                        continue switchInner3;
                                    } else {
                                        alreadyFriendBool = "false";
                                        defaultCommandsForWriter(writer, alreadyFriendBool);
                                        continue outer2;
                                    }
                                }
                            }

                            for (int i = 0; i < user.getFriends().size(); i++) {
                                if (user.getFriends().get(i).equals(userFound)) {
                                    System.out.println("alr friend");
                                    checkFriend = "false";
                                    defaultCommandsForWriter(writer, checkFriend);
                                    checkFriendBool = true;
                                    String alreadyFriend = "This User is already your friend! Would you like to add someone else or return to the home page? (Add/Home)";
                                    defaultCommandsForWriter(writer, alreadyFriend);

                                    String alreadyFriendChoice = reader.readLine();
                                    String alreadyFriendBool = "";
                                    if (alreadyFriendChoice.equals("Add")) {
                                        alreadyFriendBool = "true";
                                        defaultCommandsForWriter(writer, alreadyFriendBool);
                                        continue switchInner3;
                                    } else {
                                        alreadyFriendBool = "false";
                                        defaultCommandsForWriter(writer, alreadyFriendBool);
                                        continue outer2;
                                    }
                                }
                            }
                            defaultCommandsForWriter(writer, checkFriend);

                            String checkBlocked = "true";
                            for (int j = 0; j < user.getBlocked().size(); j++) {
                                if (user.getBlocked().get(j).equals(userFound)) {
                                    System.out.println("she blocked bro");
                                    checkBlocked = "false";
                                    defaultCommandsForWriter(writer, checkBlocked);
                                    checkFriendBool = true;
                                    String alreadyBlocked = "You have blocked this user! They cannot be your friend. Would you like to add someone else or return to the home page? (Add/Home)";
                                    defaultCommandsForWriter(writer, alreadyBlocked);

                                    String alreadyBlockedChoice = reader.readLine();
                                    String alreadyBlockedBool = "";
                                    if (alreadyBlockedChoice.equals("Add")) {
                                        alreadyBlockedBool = "true";
                                        defaultCommandsForWriter(writer, alreadyBlockedBool);
                                        continue switchInner3;
                                    } else {
                                        alreadyBlockedBool = "false";
                                        defaultCommandsForWriter(writer, alreadyBlockedBool);
                                        continue outer2;
                                    }
                                }
                            }
                            defaultCommandsForWriter(writer, checkBlocked);


                            String checkOtherBlocked = "true";
                            for (int k = 0; k < userFound.getBlocked().size(); k++) {
                                if (user.getBlocked().get(k).equals(user)) {
                                    System.out.println("she blocked u loser");
                                    checkOtherBlocked = "false";
                                    defaultCommandsForWriter(writer, checkOtherBlocked);
                                    checkFriendBool = true;
                                    String otherAlreadyBlocked = "This user has blocked you! They cannot be your friend. Would you like to add someone else or return to the home page? (Add/Home)";
                                    defaultCommandsForWriter(writer, otherAlreadyBlocked);

                                    String otherAlreadyBlockedChoice = reader.readLine();
                                    String otherAlreadyBlockedBool = "";
                                    if (otherAlreadyBlockedChoice.equals("Add")) {
                                        otherAlreadyBlockedBool = "true";
                                        defaultCommandsForWriter(writer, otherAlreadyBlockedBool);
                                        continue switchInner3;
                                    } else {
                                        otherAlreadyBlockedBool = "false";
                                        defaultCommandsForWriter(writer, otherAlreadyBlockedBool);
                                        continue outer2;
                                    }
                                }
                            }
                            defaultCommandsForWriter(writer, checkOtherBlocked);

                            System.out.println("slayyy");
                            String addFriendMessage = "You and " + userFound.getUsername() + " are now friends!";
                            defaultCommandsForWriter(writer, addFriendMessage);

                            user.addFriend(userFound);
                            userFound.addFriend(user);

                            smp.writeDatabaseFriendsFile(user, userFound.getUsername(), "friends.txt");
                            //smp.writeDatabaseFriendsFile(userFound, user.getUsername(), "friends.txt");
                            //System.out.println(addFriendMessage);

                            String endAddFriendPrompt = "Would you like to add someone else or return to the home page? (Add/Home)";
                            defaultCommandsForWriter(writer, endAddFriendPrompt);

                            String endAddFriendChoice = reader.readLine();
                            String endAddFriendBool = "";
                            if (endAddFriendChoice.equals("Add")) {
                                endAddFriendBool = "true";
                                defaultCommandsForWriter(writer, endAddFriendBool);
                                continue switchInner3;
                            } else {
                                endAddFriendBool = "false";
                                defaultCommandsForWriter(writer, endAddFriendBool);
                                continue outer2;
                            }
                        } while (true);
                    case 4:
                        switchInner4:
                        do {
                            listOfFriendUsernames = smp.getUsernamesOfFriends(user.getUsername(), friends);
                            String removePrompt = "Type in the username of the friend you would like to remove.";
                            defaultCommandsForWriter(writer, removePrompt);

                            String userToRemove = reader.readLine();
                            User userFound = smp.viewUser(userToRemove);

                            String foundBool = "";
                            if (userFound == null) {
                                foundBool = "false";
                                defaultCommandsForWriter(writer, foundBool);
                                String notFound = "That user does not exist. Would you like to remove someone else or return to the home page? (Remove/Home)";
                                defaultCommandsForWriter(writer, notFound);

                                String notFoundChoice = reader.readLine();
                                String notFoundBool = "";
                                if (notFoundChoice.equals("Add")) {
                                    notFoundBool = "true";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue switchInner4;
                                } else {
                                    notFoundBool = "false";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue outer2;
                                }
                            }
                            foundBool = "true";
                            defaultCommandsForWriter(writer, foundBool);

                            String canBlockBool = "true";
                            for (int i = 0; i < listOfFriendUsernames.size(); i++) {
                                if (listOfFriendUsernames.get(i).equals(userFound.getUsername())) {
                                    canBlockBool = "false";
                                    defaultCommandsForWriter(writer, canBlockBool);
                                    user.removeFriend(userFound);
                                    userFound.removeFriend(user);

                                    smp.removeFriendUsername(user.getUsername(), userFound.getUsername(), friends, "friends.txt");

                                    String removedFriendPrompt = userFound.getUsername() + " was successfully removed from your friends list. " +
                                            "Would you like to remove someone else or return to the home page? (Remove/Home)";
                                    defaultCommandsForWriter(writer, removedFriendPrompt);

                                    String removedFriendChoice = reader.readLine();
                                    String removedFriendBool = "";
                                    if (removedFriendChoice.equals("Remove")) {
                                        removedFriendBool = "true";
                                        defaultCommandsForWriter(writer, removedFriendBool);
                                        continue switchInner4;
                                    } else {
                                        removedFriendBool = "false";
                                        defaultCommandsForWriter(writer, removedFriendBool);
                                        continue outer2;
                                    }
                                }
                            }

                            for (int i = 0; i < user.getFriends().size(); i++) {
                                if (user.getFriends().get(i).equals(userFound)) {
                                    canBlockBool = "false";
                                    defaultCommandsForWriter(writer, canBlockBool);
                                    user.removeFriend(userFound);
                                    userFound.removeFriend(user);

                                    smp.removeFriendUsername(user.getUsername(), userFound.getUsername(), friends, "friends.txt");

                                    String removedFriendPrompt = userFound.getUsername() + " was successfully removed from your friends list. " +
                                            "Would you like to remove someone else or return to the home page? (Remove/Home)";
                                    defaultCommandsForWriter(writer, removedFriendPrompt);

                                    String removedFriendChoice = reader.readLine();
                                    String removedFriendBool = "";
                                    if (removedFriendChoice.equals("Remove")) {
                                        removedFriendBool = "true";
                                        defaultCommandsForWriter(writer, removedFriendBool);
                                        continue switchInner4;
                                    } else {
                                        removedFriendBool = "false";
                                        defaultCommandsForWriter(writer, removedFriendBool);
                                        continue outer2;
                                    }
                                }
                            }
                            defaultCommandsForWriter(writer, canBlockBool);

                            String errorRemove = userFound.getUsername() + " is not your friend, so you cannot remove them! " +
                                    "Would you like to remove someone else or return to the home page? (Remove/Home)";
                            defaultCommandsForWriter(writer, errorRemove);

                            String errorChoice = reader.readLine();
                            String errorBool = "";
                            if (errorChoice.equals("Remove")) {
                                errorBool = "true";
                                defaultCommandsForWriter(writer, errorBool);
                                continue switchInner4;
                            } else {
                                errorBool = "false";
                                defaultCommandsForWriter(writer, errorBool);
                                continue outer2;
                            }
                        } while (true);
                    case 5:
                        switchInner5:
                        do {
                            listOfFriendUsernames = smp.getUsernamesOfFriends(user.getUsername(), friends);
                            String blockPrompt = "Who would you like to block?";
                            defaultCommandsForWriter(writer, blockPrompt);

                            String userToBlock = reader.readLine();
                            User userFound = smp.viewUser(userToBlock);

                            ArrayList<String> listOfBlocked = smp.getUsernamesOfBlocked(user.getUsername(), blocked);

                            String foundBool = "";
                            if (userFound == null) {
                                foundBool = "false";
                                defaultCommandsForWriter(writer, foundBool);
                                String notFoundPrompt = "That user does not exist. Would you like to block someone else or return to the home page? (Block/Home)";
                                defaultCommandsForWriter(writer, notFoundPrompt);

                                String notFoundChoice = reader.readLine();
                                String notFoundBool = "";
                                if (notFoundChoice.equals("Block")) {
                                    notFoundBool = "true";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue switchInner5;
                                } else {
                                    notFoundBool = "false";
                                    defaultCommandsForWriter(writer, notFoundBool);
                                    continue outer2;
                                }
                            } else {
                                foundBool = "true";
                                defaultCommandsForWriter(writer, foundBool);

                                String checkFriend = "";
                                String checkBlocked = "";
                                boolean checkFriendBool = false;
                                boolean checkBlockedBool = false;
                                for (int i = 0; i < listOfFriendUsernames.size(); i++) {
                                    if (listOfFriendUsernames.get(i).equals(userFound.getUsername())) {
                                        checkFriend = "false";
                                        defaultCommandsForWriter(writer, checkFriend);
                                        checkFriendBool = true;
                                        String blockFriendMessage = "This user is your friend, unadding and blocking now...";
                                        defaultCommandsForWriter(writer, blockFriendMessage);

                                        smp.writeDatabaseBlockedFile(user, userFound.getUsername(), "blocked.txt");

                                        user.blockUser(userFound);
                                        user.removeFriend(userFound);
                                        userFound.removeFriend(user);

                                        String returnPrompt = "Would you like to block someone else or return to the home page? (Block/Home)";
                                        defaultCommandsForWriter(writer, returnPrompt);
                                        String returnChoice = reader.readLine();
                                        String returnBool = "";
                                        if (returnChoice.equals("Block")) {
                                            returnBool = "true";
                                            defaultCommandsForWriter(writer, returnBool);
                                            continue switchInner5;
                                        } else {
                                            returnBool = "false";
                                            defaultCommandsForWriter(writer, returnBool);
                                            continue outer2;
                                        }
                                    }
                                }

                                for (int i = 0; i < user.getFriends().size(); i++) {
                                    if (user.getFriends().get(i).equals(userFound)) {
                                        defaultCommandsForWriter(writer, checkFriend);
                                        checkFriendBool = true;
                                        String blockFriendMessage = "This user is your friend, unadding and blocking now...";
                                        defaultCommandsForWriter(writer, blockFriendMessage);

                                        smp.writeDatabaseBlockedFile(user, userFound.getUsername(), "blocked.txt");

                                        user.blockUser(userFound);
                                        user.removeFriend(userFound);
                                        userFound.removeFriend(user);

                                        String returnPrompt = "Would you like to block someone else or return to the home page? (Block/Home)";
                                        defaultCommandsForWriter(writer, returnPrompt);
                                        String returnChoice = reader.readLine();
                                        String returnBool = "";
                                        if (returnChoice.equals("Block")) {
                                            returnBool = "true";
                                            defaultCommandsForWriter(writer, returnBool);
                                            continue switchInner5;
                                        } else {
                                            returnBool = "false";
                                            defaultCommandsForWriter(writer, returnBool);
                                            continue outer2;
                                        }
                                    }
                                }

                                checkFriend = "true";
                                defaultCommandsForWriter(writer, checkFriend);

                                for (int j = 0; j < listOfBlocked.size(); j++) {
                                    if (listOfBlocked.get(j).equals(userFound.getUsername())) {
                                        checkBlocked = "false";
                                        defaultCommandsForWriter(writer, checkBlocked);
                                        checkBlockedBool = true;
                                        String alreadyBlocked = "You have already blocked this user! Would you like to block someone else or return to the home page? (Block/Home)";
                                        defaultCommandsForWriter(writer, alreadyBlocked);

                                        String alreadyBlockedChoice = reader.readLine();
                                        String alreadyBlockedBool = "";
                                        if (alreadyBlockedChoice.equals("Block")) {
                                            alreadyBlockedBool = "true";
                                            defaultCommandsForWriter(writer, alreadyBlockedBool);
                                            continue switchInner5;
                                        } else {
                                            alreadyBlockedBool = "false";
                                            defaultCommandsForWriter(writer, alreadyBlockedBool);
                                            continue outer2;
                                        }
                                    }
                                }

                                for (int i = 0; i < user.getBlocked().size(); i++) {
                                    if (user.getBlocked().get(i).equals(userFound)) {
                                        checkBlocked = "false";
                                        defaultCommandsForWriter(writer, checkBlocked);
                                        checkBlockedBool = true;
                                        String alreadyBlocked = "You have already blocked this user! Would you like to block someone else or return to the home page? (Block/Home)";
                                        defaultCommandsForWriter(writer, alreadyBlocked);

                                        String alreadyBlockedChoice = reader.readLine();
                                        String alreadyBlockedBool = "";
                                        if (alreadyBlockedChoice.equals("Block")) {
                                            alreadyBlockedBool = "true";
                                            defaultCommandsForWriter(writer, alreadyBlockedBool);
                                            continue switchInner5;
                                        } else {
                                            alreadyBlockedBool = "false";
                                            defaultCommandsForWriter(writer, alreadyBlockedBool);
                                            continue outer2;
                                        }
                                    }
                                }

                                checkBlocked = "true";
                                defaultCommandsForWriter(writer, checkBlocked);

                                user.blockUser(userFound);

                                smp.writeDatabaseBlockedFile(user, userFound.getUsername(), "blocked.txt");

                                String blockedChosenUser = userFound.getUsername() + " has been blocked!";
                                defaultCommandsForWriter(writer, blockedChosenUser);

                                String returnPrompt = "Would you like to block someone else or return to the home page? (Block/Home)";
                                defaultCommandsForWriter(writer, returnPrompt);

                                String returnChoice = reader.readLine();
                                String returnBool = "";
                                if (returnChoice.equals("Block")) {
                                    returnBool = "true";
                                    defaultCommandsForWriter(writer, returnBool);
                                    continue switchInner5;
                                } else {
                                    returnBool = "false";
                                    defaultCommandsForWriter(writer, returnBool);
                                    continue outer2;
                                }
                            }
                        } while (true);
                    case 6:
                        switchInner6:
                        do {
                            Random random = new Random();
                            int randomInt = random.nextInt(0, posts.size());
                            String postPath = posts.get(randomInt);
                            String newPostPath = postPath.substring(0, postPath.indexOf(",", postPath.indexOf("Posts")));
                            defaultCommandsForWriter(writer, newPostPath);

                            String interactWithPostPrompt = "Do you want to like or dislike this post, or add a comment? (Like/Dislike/Add)";
                            defaultCommandsForWriter(writer, interactWithPostPrompt);

                            String interactWithPostChoice = reader.readLine();
                            String likeOrDislikeBool = "";
                            if (interactWithPostChoice.equals("Like")) {
                                //System.out.println(people);
                                likeOrDislikeBool = "like";
                                defaultCommandsForWriter(writer, likeOrDislikeBool);

                                String userPosted = newPostPath.substring(newPostPath.indexOf(":") + 2, newPostPath.indexOf(","));
                                User userPostedObj = smp.viewUser(userPosted);

                                int indexPostColon = newPostPath.indexOf(":", newPostPath.indexOf("Posts")) + 2;
                                int indexSpace = newPostPath.indexOf(" ", indexPostColon);

                                String postText = newPostPath.substring(indexPostColon, indexSpace);

                                int indexLikesWord = newPostPath.indexOf(":", newPostPath.indexOf("Likes")) + 2;
                                int spaceAfterLikes = newPostPath.indexOf(" ", indexLikesWord);

                                int currentLikes = Integer.parseInt(newPostPath.substring(indexLikesWord, spaceAfterLikes));

                                Post displayedPost = new Post(userPostedObj, postText);
                                displayedPost.setLikes(currentLikes);
                                displayedPost.likePost();

                                smp.writeDatabasePostFile(userPostedObj, displayedPost, true, "posts.txt");

                                String postLiked = "Post has been liked. Returning you to the home page!";
                                defaultCommandsForWriter(writer, postLiked);

                                continue outer2;

                            } else if (interactWithPostChoice.equals("Dislike")) {
                                likeOrDislikeBool = "dislike";
                                defaultCommandsForWriter(writer, likeOrDislikeBool);

                                String userPosted = newPostPath.substring(newPostPath.indexOf(":") + 2, newPostPath.indexOf(","));
                                User userPostedObj = smp.viewUser(userPosted);

                                int indexPostColon = newPostPath.indexOf(":", newPostPath.indexOf("Posts")) + 2;
                                int indexSpace = newPostPath.indexOf(" ", indexPostColon);

                                String postText = newPostPath.substring(indexPostColon, indexSpace);

                                int indexDislikesWord = newPostPath.indexOf(":", newPostPath.indexOf("Likes")) + 2;
                                int spaceAfterDislikes = newPostPath.indexOf(" ", indexDislikesWord);

                                int currentDislikes = Integer.parseInt(newPostPath.substring(indexDislikesWord, spaceAfterDislikes));

                                Post displayedPost = new Post(userPostedObj, postText);
                                displayedPost.setDislikes(currentDislikes);
                                displayedPost.dislikePost();

                                smp.writeDatabasePostFile(userPostedObj, displayedPost, true, "posts.txt");

                                String postDisliked = "Post has been disliked. Returning you to the home page!";
                                defaultCommandsForWriter(writer, postDisliked);

                                continue outer2;
                            } else if (interactWithPostChoice.equals("Add")) {
                                String commentPath = comments.get(0);
                                likeOrDislikeBool = "add";
                                defaultCommandsForWriter(writer, likeOrDislikeBool);

                                String commentToAddPrompt = "What comment would you like to add?";
                                defaultCommandsForWriter(writer, commentToAddPrompt);

                                String userPosted = newPostPath.substring(newPostPath.indexOf(":") + 2, newPostPath.indexOf(","));
                                User userPostedObj = smp.viewUser(userPosted);

                                //System.out.println(commentPath);

                                int indexPostColon = commentPath.indexOf("Comments:") + 2;
                                int indexSpace = commentPath.indexOf(" ", indexPostColon);

                                String postText = newPostPath.substring(indexPostColon, indexSpace);

                                System.out.println(postText);

                                String commentToAdd = reader.readLine();
                                Comments commentToAddObj = new Comments(commentToAdd, user);

                                Post displayedPost = new Post(userPostedObj, postText);

                                smp.writeDatabaseCommentsFile(postText, commentToAddObj, false, "comments.txt");

                                String commentAdded = "Comment has been added. Returning you to the home page!";
                                defaultCommandsForWriter(writer, commentAdded);

                                continue outer2;
                            }
                        } while (true);
                    case 7:
                        switchInner7:
                        do {
                            listOfFriendUsernames = smp.getUsernamesOfFriends(user.getUsername(), friends);
                            String userFriends = "";
                            userFriends += user.getFriends() + " ";
                            for (int i = 0; i < listOfFriendUsernames.size(); i++) {
                                userFriends += listOfFriendUsernames.get(i) + " ";
                            }

                            ArrayList<String> listOfPosts = smp.getPostsOfUser(user.getUsername(), posts);
                            String userPosts = "";
                            userPosts += user.getPosts() + " ";
                            for (int i = 0; i < listOfPosts.size(); i++) {
                                userPosts += listOfPosts.get(i) + " ";
                            }

                            ArrayList<String> accountList = new ArrayList<>();
                            accountList.add(user.toString());
                            accountList.add("Friend's Names: " + userFriends);
                            accountList.add("Posts: " + listOfPosts.get(0));

//                        Post post = new Post(user, listOfPosts.get(0));

                            for (String acc : accountList) {
                                defaultCommandsForWriter(writer, acc);
                            }

                            String wantToDeleteComment = "Do you want to delete a comment? (Yes/No)";
                            defaultCommandsForWriter(writer, wantToDeleteComment);

                            String commentChoiceBool = "";
                            String commentChoice = reader.readLine();
                            if (commentChoice.equals("No")) {
                                commentChoiceBool = "false";
                                defaultCommandsForWriter(writer, commentChoiceBool);
                                continue outer2;
                            } else {
                                System.out.println(listOfPosts);
                                commentChoiceBool = "true";
                                defaultCommandsForWriter(writer, commentChoiceBool);
                                ArrayList<String> postComments = new ArrayList<>();
                                String text = "";
                                for (int i = 0; i < listOfPosts.size(); i++) {
                                    //if (listOfPosts.get(i).contains(user.getUsername())) {
                                    String userPost = listOfPosts.get(i);
                                    text = userPost.substring(0, userPost.indexOf(" "));
                                    System.out.println(text);
                                    for (int j = 0; j < comments.size(); j++) {
                                        postComments = smp.getCommentsOfPost(text, comments);
                                        System.out.println(postComments);
                                    }
                                    break;
                                    //}
                                }
                                String postCommentsString = String.join(",", postComments);
                                String[] arrOfComments = postCommentsString.split(",");

                                String postCommentsSize = arrOfComments.length + "";
                                defaultCommandsForWriter(writer, postCommentsSize);

                                for (String c : arrOfComments) {
                                    defaultCommandsForWriter(writer, c);
                                }

                                for (int i = 0; i < arrOfComments.length; i++) {
                                    if (!arrOfComments[i].contains("^[a-zA-Z0-9]+$")) {
                                        continue outer2;
                                    }
                                }

                                String commentChoicePrompt = "Which comment do you want to delete? Enter the valid index that it's at.";
                                defaultCommandsForWriter(writer, commentChoicePrompt);

                                String commentToDelete = reader.readLine();
                                int commentToDeleteInt = Integer.parseInt(commentToDelete);

                                ArrayList<String> splitListOfComments = new ArrayList<>();
                                for (int i = 0; i < arrOfComments.length; i++) {
                                    splitListOfComments.add(arrOfComments[i]);
                                }

                                String commentToRemove = splitListOfComments.remove(commentToDeleteInt);

                                smp.removeCommentFromFile(text, commentToRemove, comments, "comments.txt");

                                String successRemoved = "Comment successfully removed. Returning to the home page...";
                                defaultCommandsForWriter(writer, successRemoved);

                                continue outer2;
                            }
                        } while (true);
                    case 8:
                        String logoutPrompt = "Are you sure you would like to log out? (Yes/No)";
                        defaultCommandsForWriter(writer, logoutPrompt);

                        String logoutClientOption = reader.readLine();
                        String logoutBool = "";
                        if (logoutClientOption.equals("Yes")) {
                            logoutBool = "true";
                            defaultCommandsForWriter(writer, logoutBool);
                            String yesOption = "Okay! Come back soon!";
                            defaultCommandsForWriter(writer, yesOption);
                            break outer2;
                        } else {
                            logoutBool = "false";
                            defaultCommandsForWriter(writer, logoutBool);
                            String noOption = "Okay, sending you back to the homepage...";
                            defaultCommandsForWriter(writer, noOption);
                            continue outer2;
                        }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // main method for our server
    public static void main(String[] args) throws IOException {
        int port = 123;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
