import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/**
 * Group Project -- VisualPlatform
 *
 * This class is currently a separate class for the initial design of our GUI
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 2, 2024
 *
 */
public class Client {
    public static void defaultCommandsForWriter(PrintWriter w, String input) {
        w.write(input);
        w.println();
        w.flush();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 123);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        SocialMediaPlatform platform = new SocialMediaPlatform();

        ArrayList<User> users = platform.getUsers();

        System.out.println("Hello!");

        outer: do {
            System.out.println("Login or Create an Account");
            String choice = scanner.nextLine().toUpperCase();
            defaultCommandsForWriter(writer, choice);

            if (choice.contains("LOGIN")) {
                inner1: do {
                    String loginChoiceUsername = reader.readLine();
                    System.out.println(loginChoiceUsername);
                    String username = scanner.nextLine();
                    defaultCommandsForWriter(writer, username);

                    String checkUsername = reader.readLine();
                    if (!Boolean.parseBoolean(checkUsername)) {
                        String usernameMessage = reader.readLine();
                        System.out.println(usernameMessage);
                        String errorUsernameChoice = scanner.nextLine().toUpperCase();
                        defaultCommandsForWriter(writer, errorUsernameChoice);

                        boolean usernameBool = Boolean.parseBoolean(reader.readLine());
                        if (usernameBool) {
                            continue outer;
                        } else {
                            continue inner1;
                        }
                    }

                    inner2: do {
                        String loginChoicePassword = reader.readLine();

                        System.out.println(loginChoicePassword);
                        String password = scanner.nextLine();
                        defaultCommandsForWriter(writer, password);

                        String checkPassword = reader.readLine();
                        if (!Boolean.parseBoolean(checkPassword)) {
                            String passwordMessage = reader.readLine();

                            System.out.println(passwordMessage);
                            String errorPasswordChoice = scanner.nextLine().toUpperCase();
                            defaultCommandsForWriter(writer, errorPasswordChoice);

                            boolean passwordBool = Boolean.parseBoolean(reader.readLine());
                            if (passwordBool) {
                                continue outer;
                            } else {
                                continue inner2;
                            }
                        }
                        break outer;
                    } while (true);
                } while (true);
            } else if (choice.contains("CREATE")) {
                inner3: do {
                    String createChoiceUsername = reader.readLine();
                    System.out.println(createChoiceUsername);
                    String username = scanner.nextLine();
                    defaultCommandsForWriter(writer, username);

                    String checkUsernameBool = reader.readLine();
                    if (Boolean.parseBoolean(checkUsernameBool)) {
                        String okayUsername = reader.readLine();
                        System.out.println(okayUsername);
                        break inner3;
                    } else {
                        String notOkayUsername = reader.readLine();
                        System.out.println(notOkayUsername);
                        continue inner3;
                    }
                } while (true);

                inner4: do {
                    String createChoicePassword = reader.readLine();
                    System.out.println(createChoicePassword);
                    String password = scanner.nextLine();
                    defaultCommandsForWriter(writer, password);

                    String checkPasswordBool = reader.readLine();
                    if (Boolean.parseBoolean(checkPasswordBool)) {
                        String okayPassword = reader.readLine();
                        System.out.println(okayPassword);
                        break outer;
                    } else {
                        String notOkayPassword = reader.readLine();
                        System.out.println(notOkayPassword);
                        continue inner4;
                    }
                } while (true);
            }
        } while (true);

        String welcomeMessage = reader.readLine();
        System.out.println(welcomeMessage);



    }
}
