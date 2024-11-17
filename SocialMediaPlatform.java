import java.io.*;
import java.util.*;
// import java.io.*;
import java.lang.*;

/**
 * Group Project -- SocialMediaPlatform
 *
 * This class is a runner class for our program and extends the Thread class for "multiple-client" implementation purposes
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 2, 2024
 *
 */

public class SocialMediaPlatform extends Thread {
    private static ArrayList<User> users; // list of all the users stored in the database
    private static ArrayList<String> friends; // A String arrayList consisting of the lines in the friends file
    private static ArrayList<String> posts; // A String arrayList consisting of the lines in the posts file
    private static ArrayList<SocialMediaPlatform> platformUsers; // list of each SocialMediaPlatform object (assists in Threading)
    private static ArrayList<Post> images; // list of all the images that have been posted (the ones that currently exist on the platform)

    private static final Object lock = new Object(); // the shared Object, lock, between all Threads using this platform
    private static ArrayList<Boolean> locks; // list of boolean values that set a process to either used or not by a Thread

    // Constructor for the SocialMediaPlatform class that sets all instance variables to their default values and filename (for now) to the designated output filename
    public SocialMediaPlatform() {
        users = new ArrayList<>();
        friends = new ArrayList<>();
        posts = new ArrayList<>();
        platformUsers = new ArrayList<>();
        images = new ArrayList<>();
        locks = new ArrayList<>();
    }

    // This method reads the file given in the parameter and returns an ArrayList of the file where each index is a line in the file
    public synchronized ArrayList<String> readFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            String line = bfr.readLine();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return lines;
    }

    // This method writes the User's username and passsword (given in the parameter) to the output file (given in the parameter)
    public synchronized void writeDatabaseFile(String username, String password, String outputFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(outputFile, true))) {
            pw.print("Username: " + username + ",");
            pw.println("Password: " + password);
        } catch (IOException e) {
            return;
        }
    }

    // This methods writes the User and the user's username (given in the parameter) into the output file (given in the parameter)
    public synchronized void writeDatabaseFriendsFile(User user, String otherUsername, String outputFile) {
        ArrayList<String> contents = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                contents.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> friends = getUsernamesOfFriends(user.getUsername(), readFile(outputFile));
        String usersFriends = "Username: " + user.getUsername() + ",Friends: ";
        for (int f = 0; f < friends.size(); f++) {
            usersFriends += friends.get(f) + ",";
        }

        usersFriends += otherUsername + ",";

        boolean contains = false;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).contains(user.getUsername())) {
                contents.set(i, usersFriends);
                contains = true;
                break;
            }
        }

        if (!contains) {
            contents.add(usersFriends);
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile))) {
            for (int i = 0; i < contents.size(); i++) {
                printWriter.println(contents.get(i));
            }
        } catch (IOException e) {
            return;
        }
    }

    // This method returns an ArrayList of Strings consisting of the User's friend's usernames
    public ArrayList<String> getUsernamesOfFriends(String username, ArrayList<String> friends) {
        ArrayList<String> listOfUsernames = new ArrayList<>();

        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).contains(username)) {
                int begIndex = friends.get(i).indexOf(",") + 10;
                int endIndex = friends.get(i).indexOf(",", begIndex + 1);
                while (endIndex != -1) {
                    listOfUsernames.add(friends.get(i).substring(begIndex, endIndex));

                    begIndex = endIndex + 1;
                    endIndex = friends.get(i).indexOf(",", begIndex + 1);
                }
            }
        }
        return listOfUsernames;
    }

    // This method returns an ArrayList of Strings that consists of the User's (given in the parameter) posts
    public ArrayList<String> getPostsOfUser(String username, ArrayList<String> posts) {
        ArrayList<String> listOfPosts = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).contains(username)) {
                int begIndex = posts.get(i).indexOf(",") + 10;
                int endIndex = posts.get(i).indexOf(",", begIndex + 1);
                while (endIndex != -1) {
                    listOfPosts.add(posts.get(i).substring(begIndex, endIndex));

                    begIndex = endIndex + 1;
                    endIndex = posts.get(i).indexOf(",", begIndex + 1);
                }
            }
        }

        return listOfPosts;
    }
    
    // A void method that writes the User's user and post (given in the parameter) to the output file (given in the parameter)
    public synchronized void writeDatabasePostFile(User user, Post post, String outputFile) {
        ArrayList<String> contents = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(outputFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                contents.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> posts = getPostsOfUser(user.getUsername(), readFile(outputFile));
        String usersPosts = "Username: " + user.getUsername() + ",Posts: ";
        for (int f = 0; f < posts.size(); f++) {
            usersPosts += posts.get(f) + ",";
        }

        usersPosts += post + " ";

        boolean contains = false;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).contains(user.getUsername())) {
                contents.set(i, usersPosts);
                contains = true;
                break;
            }
        }

        if (!contains) {
            contents.add(usersPosts);
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile))) {
            for (int i = 0; i < contents.size(); i++) {
                printWriter.println(contents.get(i));
            }
        } catch (IOException e) {
            return;
        }
    }

    // Method that adds a User to the list of all the users in the database immediately after successful creation
    // returns void; changes an instance variable
    // involves concurrent programming to avoid race conditions: everytime a new User is added, a thread begins login execution
    public synchronized void addUser(User user) {
        //synchronized (lock) {
        users.add(user);
        platformUsers.add(new SocialMediaPlatform());
        //}
    }
//    public synchronized void addUser(User user) {
//        users.add(user);
//        //SocialMediaPlatform newUserThread = new SocialMediaPlatform();
//        //newUserThread.start();
////        synchronized (lock) {
////            users.add(user);
////            locks.add(false);
////
////            platformUsers.add(newUserThread);
////        }
////        newUserThread.start();
//    }

    // Accessor method for the list of users
    // returns ArrayList<User> of all the users stored in the database
    public ArrayList<User> getUsers() {
        return users;
    }

    // Accessor method for the list of friends
    // Returns ArrayList<String> of all the friends stored in the database
    public ArrayList<String> getFriends() {
        return friends;
    }

    // Accessor method for the list of posts
    // Returns ArrayList<String> of all the posts stored in the database
    public ArrayList<String> getPosts() {
        return posts;
    }

    // Accessor method for the list of images
    // returns ArrayList<Post> of all the images that exist and are stored in the database
    public ArrayList<Post> getImages() {
        return images;
    }

    // Method that checks if a username exists in the database
    // returns a boolean value true (it does exist) or false (it does not exist)
    public boolean checkForUsername(String username) {
        if (users.isEmpty()) {
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Method that checks if the password a User enters at login is associated with that username, or if it is the incorrect password
    // returns a boolean value true (it is correct) or false (it is the wrong password)
    public boolean checkForPassword(String username, String password) {
        if (users.isEmpty()) {
            return false;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method that follows the same logic as checkForUsername, but automatically handles if a User cannot use a certain username
    // returns a boolean true (the User can use that username) or false (the User cannot employ that username)
    public boolean checkUsername(String username) {
        System.out.println(users.size());
        if (users.isEmpty()) {
            return true;
        }

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    // Method that checks if a created password adheres to the criteria that we are setting for our platform or not
    // returns a boolean true (password passes all checks) or false (password needs to be modified)
    public boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        return true;
    }

    // Method that adds the '@' character to a username for presentation and processing
    // returns void; changes an instance variable
    public void editUsername(User user) {
        String holdUsername = user.getUsername();
        holdUsername = "@" + holdUsername;
        user.setUsername(holdUsername);
    }

    // Method that displays all the users to the platform screen for viewing purposes
    // returns void; designed so that a User knows who they can "follow"
    public void displayUsers() {
        /*
        to be implemented alongside the GUI
         */
    }

    // ** Accessor method that returns the list of Platform Users (used for testing)
    public ArrayList<SocialMediaPlatform> getPlatformUsers() {
        return platformUsers;
    }

    // Method that allows a User to view another user through searching
    // Returns User
    public User viewUser(String username) {
        ArrayList<String> people = readFile("/Users/christinexu/IdeaProjects/groupproject/src/output.txt");
        for (String s : people) {
            users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }

    // Method run() that overrides the run method in the Thread class that contains the code that each existing Thread will run
    // returns void; called when a thread starts
    @Override
    public void run() {
        SocialMediaPlatform platform = new SocialMediaPlatform();
        ArrayList<String> usernames = new ArrayList<>();
        User user = new User("hi", "dshfod456");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Username: hi, Friends: Helloo, Hello, hel");

        usernames = platform.getUsernamesOfFriends(user.getUsername(), friends);
        System.out.println(usernames);

//        try {
//            File file = new File("/Users/christinexu/IdeaProjects/groupproject/src/output.txt");
//            if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/src/output.txt")) == 0) {
//                try (PrintWriter printWriter = new PrintWriter(file)) {
//                    printWriter.println("Username: HelloWorld,Password: Hello1234");
//                }
//            }
//        } catch (IOException e) {
//            return;
//        }
//
//        ArrayList<String> people = platform.readFile("/Users/christinexu/IdeaProjects/groupproject/src/output.txt");
//        //System.out.println(people);
//        for (String s : people) {
//            usernames.add(s.substring(10, s.indexOf(",")));
//        }
//        System.out.println(usernames);
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Hello!");
//
//        outer:
//        do {
//            System.out.println("Login or Create an Account"); // will eventually be a button when we're designing our GUI
//            String choice = scanner.nextLine().toUpperCase();
//            if (people.isEmpty()) {
//                choice = "CREATE";
//            }
//            for (String s : people) {
//                users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
//            }
//            //System.out.println(users);
//            String username = "";
//            if (choice.contains("LOGIN")) {
//                inner1:
//                do {
//                    System.out.println("Enter your username");
//                    username = scanner.nextLine();
//                    boolean checkUsername = platform.checkForUsername(username);
//                    if (!checkUsername) {
//                        System.out.println("That username does not exist. Return to homepage or try again?");
//                        String loginChoice = scanner.nextLine().toUpperCase();
//                        if (loginChoice.contains("RETURN")) {
//                            continue outer;
//                        } else if (loginChoice.contains("AGAIN")) {
//                            continue inner1;
//                        }
//                    } else {
//                        break;
//                    }
//                } while (true);
//
//                inner2:
//                do {
//                    System.out.println("Enter your password");
//                    String password = scanner.nextLine();
//                    boolean checkPassword = platform.checkForPassword(username, password);
//                    if (checkPassword) {
//                        System.out.println("Login successful.");
//                        //System.out.println(String.format("Username: %s, Friends: %s", username, friends.size()));
//                        break outer;
//                    } else {
//                        System.out.println("Wrong password. Return to homepage or try again?");
//                        String loginChoice = scanner.nextLine().toUpperCase();
//                        if (loginChoice.contains("RETURN")) {
//                            continue outer;
//                        } else if (loginChoice.contains("AGAIN")) {
//                            continue inner2;
//                        }
//                    }
//                } while (true);
//
//            } else if (choice.contains("CREATE")) {
//                synchronized (lock) {
//                    String usernameCreate = "";
//                    inner3: do {
//                        System.out.println("Enter a username");
//                        usernameCreate = scanner.nextLine();
//
//                        if (platform.checkUsername(usernameCreate)) {
//                            System.out.println("Username created.");
//                        } else {
//                            System.out.println("Username already exists.");
//                            continue inner3;
//                        }
//
//                        inner4: do {
//                            synchronized (lock) {
//                                System.out.println("Please enter a strong password (8 characters, an uppercase, a lowercase, digits)");
//                                String password = scanner.nextLine();
//                                if (platform.checkPassword(password)) {
//                                    User newUser = new User(usernameCreate, password);
//                                    platform.addUser(newUser);
//                                    platform.writeDatabaseFile(usernameCreate, password, "output.txt");
//                                    for (String u : usernames) {
//                                        if (u.equals(usernameCreate)) {
//                                            System.out.println("Username already exists.");
//                                            break inner4;
//                                        } else {
//                                            usernames.add(usernameCreate);
//                                            System.out.println(usernames);
//                                            System.out.println("Account successfully created.");
//                                            break outer;
//                                        }
//                                    }
//
//                                } else {
//                                    // password criteria to be displayed with a JPanel
//                                    System.out.println("Weak password. Try again.");
//                                    continue inner4;
//                                }
//                            }
//                        } while (true);
//                    } while (true);
//                }
//                //continue outer;
//            }
//        } while (true) ;
//
//        System.out.println("Welcome!");
//
//    }
    }
//
//    // Main method of this class
//    // For testing, program/debug running, and GUI
    public static void main(String[] args){
            SocialMediaPlatform newPlatformThread = new SocialMediaPlatform();
            platformUsers.add(newPlatformThread);
            //System.out.println(platformUsers.size());
            try {
                for (int i = 0; i < platformUsers.size(); i++) {
                    platformUsers.get(i).start();
                }
                for (int i = 0; i < platformUsers.size(); i++) {
                    platformUsers.get(i).join();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
////        SocialMediaPlatform platform = new SocialMediaPlatform();
////        //platform.addUser(new User("Hello World", "beginning123"));
////        //System.out.println(platform.checkUsername("Hello World"));
////        for (SocialMediaPlatform newThread : platformUsers) {
////            newThread = new SocialMediaPlatform();
////            newThread.start();
////        }
////
////        try {
////            for (SocialMediaPlatform newUserThread : platformUsers) {
////                newUserThread.join();
////            }
////        } catch (Exception ex) {
////            return;
////        }
//    }
//
}
