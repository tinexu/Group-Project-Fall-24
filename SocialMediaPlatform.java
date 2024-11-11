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

public class SocialMediaPlatform extends Thread implements SocialMediaPlatformInterface {
    private static ArrayList<User> users; // list of all the users stored in the database
    private static ArrayList<SocialMediaPlatform> platformUsers; // list of each SocialMediaPlatform object (assists in Threading)
    private static ArrayList<Post> images; // list of all the images that have been posted (the ones that currently exist on the platform)

    private static final Object lock = new Object(); // the shared Object, lock, between all Threads using this platform
    private static ArrayList<Boolean> locks; // list of boolean values that set a process to either used or not by a Thread

    // Constructor for the SocialMediaPlatform class that sets all instance variables to their default values and filename (for now) to the designated output filename
    public SocialMediaPlatform() {
        users = new ArrayList<>();
        platformUsers = new ArrayList<>();
        images = new ArrayList<>();
        locks = new ArrayList<>();
    }

    public ArrayList<String> readFile(String filename) {
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

    public void writeDatabaseFile(String username, String password) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("output.txt", true))) {
            pw.print("Username: " + username + ",");
            pw.println("Password: " + password);
        } catch (IOException e) {
            return;
        }
    }

    // Method that adds a User to the list of all the users in the database immediately after successful creation
    // returns void; changes an instance variable
    // involves concurrent programming to avoid race conditions: everytime a new User is added, a thread begins login execution
    public void addUser(User user) {
        SocialMediaPlatform newUserThread;
        synchronized (lock) {
            users.add(user);
            locks.add(false);

            newUserThread = new SocialMediaPlatform();
            platformUsers.add(newUserThread);
        }
        newUserThread.start();
    }

    // Accessor method for the list of users
    // returns ArrayList<User> of all the users stored in the database
    public ArrayList<User> getUsers() {
        return users;
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

    public User viewUser(String username) {
        ArrayList<String> people = readFile("/Users/christinexu/IdeaProjects/groupproject/output.txt");
        for (String s : people) {
            users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        System.out.println("That user does not exist.");
        return null;
    }

    // Method run() that overrides the run method in the Thread class that contains the code that each existing Thread will run
    // returns void; called when a thread starts
    @Override
    public void run() {
        SocialMediaPlatform platform = new SocialMediaPlatform();

        platform.writeDatabaseFile("HelloWorld", "Hello1234");

        ArrayList<String> people = platform.readFile("/Users/christinexu/IdeaProjects/groupproject/output.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello!");

        outer: do {
            System.out.println("Login or Create an Account"); // will eventually be a button when we're designing our GUI
            String choice = scanner.nextLine().toUpperCase();
            if (people.isEmpty()) {
                choice = "CREATE";
            }
            for (String s : people) {
                users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
            }
            //System.out.println(users);
            String username = "";
            if (choice.contains("LOGIN")) {
                inner1: do {
                    System.out.println("Enter your username");
                    username = scanner.nextLine();
                    boolean checkUsername = platform.checkForUsername(username);
                    if (!checkUsername) {
                        System.out.println("That username does not exist. Return to homepage or try again?");
                        String loginChoice = scanner.nextLine().toUpperCase();
                        if (loginChoice.contains("RETURN")) {
                            continue outer;
                        } else if (loginChoice.contains("AGAIN")) {
                            continue inner1;
                        }
                    } else {
                        break;
                    }
                } while (true);

                inner2: do {
                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    boolean checkPassword = platform.checkForPassword(username, password);
                    if (checkPassword) {
                        System.out.println("Login successful.");
                        break outer;
                    } else {
                        System.out.println("Wrong password. Return to homepage or try again?");
                        String loginChoice = scanner.nextLine().toUpperCase();
                        if (loginChoice.contains("RETURN")) {
                            continue outer;
                        } else if (loginChoice.contains("AGAIN")) {
                            continue inner2;
                        }
                    }
                } while (true);

            } else if (choice.contains("CREATE")) {
                String usernameCreate = "";
                inner3: do {
                    System.out.println("Enter a username");
                    usernameCreate = scanner.nextLine();
                    if (platform.checkUsername(usernameCreate)) {
                        System.out.println("Username created.");
                        break;
                    } else {
                        System.out.println("Username already exists.");
                        continue inner3;
                    }
                } while (true);

                inner4: do {
                    System.out.println("Please enter a strong password (8 characters, an uppercase, a lowercase, digits)");
                    String password = scanner.nextLine();
                    if (platform.checkPassword(password)) {
                        User newUser = new User(usernameCreate, password);
                        synchronized (lock) {
                            platform.addUser(newUser);
                            platform.writeDatabaseFile(usernameCreate, password);
                            System.out.println(newUser.toString());
                            System.out.println("Account successfully created.");
                            break outer;
                        }
                    } else {
                        // password criteria to be displayed with a JPanel
                         System.out.println("Weak password. Try again.");
                        continue inner4;
                    }
                } while (true);
            }
            //continue outer;
        } while (true);

        System.out.println("Welcome!");
    }

    // Main method of this class
    // For testing, program/debug running, and GUI
    public static void main(String[] args) {
        SocialMediaPlatform platform = new SocialMediaPlatform();
        platform.addUser(new User("Hello World", "beginning123"));

        try {
            for (SocialMediaPlatform newUserThread : platformUsers) {
                newUserThread.join();
            }
        } catch (Exception ex) {
            return;
        }
    }

}
