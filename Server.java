import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Server implements Runnable {
    public static void defaultCommandsForWriter(PrintWriter w, String input) {
        w.write(input);
        w.println();
        w.flush();
    }

    public void run() {

    }

    public static void main(String[] args) throws IOException  {
        ServerSocket serverSocket = new ServerSocket(123);

        Socket socket = serverSocket.accept();
        System.out.println("Connected");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        SocialMediaPlatform smp = new SocialMediaPlatform();

        try {
            File file = new File("/Users/christinexu/IdeaProjects/groupproject/output.txt");
            if (Files.size(Paths.get("/Users/christinexu/IdeaProjects/groupproject/output.txt")) == 0) {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.println("Username: HelloWorld,Password: Hello1234");
            }
        } catch (IOException e) {
            return;
        }

        ArrayList<String> people = smp.readFile("/Users/christinexu/IdeaProjects/groupproject/output.txt");
        ArrayList<User> users = smp.getUsers();

        outer: do {
            String choice = reader.readLine();

            if (people.isEmpty()) {
                choice = "CREATE";
            }
            for (String s : people) {
                users.add(new User(s.substring(10, s.indexOf(",")), s.substring(s.indexOf(",") + 11)));
            }

            String username = "";

            if (choice.contains("LOGIN")) {
                inner1: do {
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
                        } else if (usernameChoice.contains("AGAIN")) {
                            String usernameBool = "FALSE";
                            defaultCommandsForWriter(writer, usernameBool);
                            continue inner1;
                        }
                    }
                    break inner1;
                } while (true);

                inner2: do {
                    String passwordMessage = "Enter your password";
                    defaultCommandsForWriter(writer, passwordMessage);

                    String password = reader.readLine();
                    boolean checkPassword = smp.checkForPassword(username, password);
                    defaultCommandsForWriter(writer, Boolean.toString(checkPassword));
                    if (checkPassword) {
                        System.out.println("Login successful.");
                        break outer;
                    } else {
                        String errorMessage = "Wrong password. Return to homepage or try again?";
                        defaultCommandsForWriter(writer, errorMessage);
                        String passwordChoice = reader.readLine().toUpperCase();
                        if (passwordChoice.contains("RETURN")) {
                            String usernameBool = "TRUE";
                            defaultCommandsForWriter(writer, usernameBool);
                            continue outer;
                        } else if (passwordChoice.contains("AGAIN")) {
                            String usernameBool = "FALSE";
                            defaultCommandsForWriter(writer, usernameBool);
                            continue inner2;
                        }
                    }
                } while (true);
            } else if (choice.contains("CREATE")) {
                inner3: do {
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

                inner4: do {
                    String createPasswordMessage = "Enter a password";
                    defaultCommandsForWriter(writer, createPasswordMessage);

                    String password = reader.readLine();
                    if (smp.checkPassword(password)) {
                        boolean checkPassword = smp.checkPassword(password);
                        defaultCommandsForWriter(writer, Boolean.toString(checkPassword));
                        String createdPasswordMessage = "Account successfully created.";
                        defaultCommandsForWriter(writer, createdPasswordMessage);
                        break outer;
                    } else {
                        boolean checkUsername = smp.checkUsername(username);
                        defaultCommandsForWriter(writer, Boolean.toString(checkUsername));
                        String createdPasswordMessage = "Weak password. Try again.";
                        defaultCommandsForWriter(writer, createdPasswordMessage);
                        continue inner4;
                    }
                } while (true);
            }
        } while (true);

        String welcomeMessage = "Welcome!";
        defaultCommandsForWriter(writer, welcomeMessage);





//        outer2: do {
//            System.out.println("Do you want to search for a User or make a Post? (Search/Post) ");
//            String response = scanner.nextLine();
//            inside1: do {
//                if (response.equals("Search")) {
//                    System.out.println("Type in the username of the User you are looking for.");
//                    String response2 = scanner.nextLine();
//                    User userFound = platform.viewUser(response2);
//                    if (userFound == null) {
//                        System.out.println(" Do you want to try again or go back to the home page? (Try/Back)");
//                        String response3 = scanner.nextLine();
//                        if (response3.equals("Try")) {
//                            continue inside1;
//                        } else
//                            continue outer2;
//                    } else {
//                        System.out.println(userFound.toString());
//                        break outer2;
//                    }
//                }
//            } while (true);
//        } while (true);
    }
}
