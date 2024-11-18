import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Group Project -- Client
 *
 * This class runs the client aspect of our platform
 *
 * @author L30-Team 1, CS180
 *
 * @version Nov 17, 2024
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
                        String loginSuccess = reader.readLine();
                        System.out.println(loginSuccess);
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

        outer2: do {
            String line = "";
            int i = 1;
            while (i <= 8) {
                line = reader.readLine();
                System.out.println(line);
                i++;
            }

            System.out.println("Pick an option (the numerical value)");
            String optionStr = scanner.nextLine();
            int option = Integer.parseInt(optionStr);
            defaultCommandsForWriter(writer, optionStr);

            switch (option) {
                case 1:
                    inner1: do {
                        String searchPromptMessage = reader.readLine();
                        System.out.println(searchPromptMessage);

                        String userToSearchFor = scanner.nextLine();
                        defaultCommandsForWriter(writer, userToSearchFor);

                        String foundBool = reader.readLine();
                        Boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                        if (!foundBoolBool) {
                            String notFoundMessage = reader.readLine();
                            System.out.println(notFoundMessage);
                            String errorMessage = scanner.nextLine().toUpperCase();
                            defaultCommandsForWriter(writer, errorMessage);

                            String errorBool = reader.readLine();
                            boolean errorBoolBool = Boolean.parseBoolean(errorBool);
                            if (errorBoolBool) {
                                continue inner1;
                            } else {
                                continue outer2;
                            }
                        } else {
                            String userFound = reader.readLine();
                            System.out.println(userFound);
                            continue outer2;
                        }
                    } while (true);
                case 2:
                    inner2: do {
                       String postPromptMessage = reader.readLine();
                       System.out.println(postPromptMessage);

                       String thingToPost = scanner.nextLine();
                       defaultCommandsForWriter(writer, thingToPost);

                       String successfulPostMessage = reader.readLine();
                       System.out.println(successfulPostMessage);

                       String optionPostPromptMessage = reader.readLine();
                       System.out.println(optionPostPromptMessage);
                       String optionPost = scanner.nextLine();
                       defaultCommandsForWriter(writer, optionPost);

                       String reloopBool = reader.readLine();
                       boolean optionBool = Boolean.parseBoolean(reloopBool);
                       if (optionBool) {
                           continue inner2;
                       } else {
                           continue outer2;
                       }
                    } while (true);
                case 3:
                    inner3: do {
                        String addFriendPromptMessage = reader.readLine();
                        System.out.println(addFriendPromptMessage);

                        String userToAddFriend = scanner.nextLine();
                        defaultCommandsForWriter(writer, userToAddFriend);

                        String foundBool = reader.readLine();
                        boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                        if (!foundBoolBool) {
                            System.out.println("oop not found");
                            String notFoundPromptMessage = reader.readLine();
                            System.out.println(notFoundPromptMessage);
                            String notFoundChoice = scanner.nextLine();
                            defaultCommandsForWriter(writer, notFoundChoice);

                            String notFoundBool = reader.readLine();
                            boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                            if (notFoundBoolBool) {
                                continue inner3;
                            } else {
                                continue outer2;
                            }
                        } else {
                            String checkFriendBool = reader.readLine();
                            boolean checkFriendBoolBool = Boolean.parseBoolean(checkFriendBool);
                            if (!checkFriendBoolBool) {
                                System.out.println("oop alr friend");
                                String alreadyFriend = reader.readLine();
                                System.out.println(alreadyFriend);
                                String alreadyFriendChoice = scanner.nextLine();
                                defaultCommandsForWriter(writer, alreadyFriendChoice);

                                String alreadyFriendBool = reader.readLine();
                                boolean alreadyFriendBoolBool = Boolean.parseBoolean(alreadyFriendBool);
                                if (alreadyFriendBoolBool) {
                                    continue inner3;
                                } else {
                                    continue outer2;
                                }
                            } else {
                                String checkBlockedBool = reader.readLine();
                                boolean checkBlockedBoolBool = Boolean.parseBoolean(checkBlockedBool);
                                if (!checkBlockedBoolBool) {
                                    System.out.println("oop she blocked");
                                    String alreadyBlocked = reader.readLine();
                                    System.out.println(alreadyBlocked);
                                    String alreadyBlockedChoice = scanner.nextLine();
                                    defaultCommandsForWriter(writer, alreadyBlockedChoice);

                                    String alreadyBlockedBool = reader.readLine();
                                    boolean alreadyBlockedBoolBool = Boolean.parseBoolean(alreadyBlockedBool);
                                    if (alreadyBlockedBoolBool) {
                                        continue inner3;
                                    } else {
                                        continue outer2;
                                    }
                                } else {
                                    String checkOtherBlockedBool = reader.readLine();
                                    boolean checkOtherBlockedBoolBool = Boolean.parseBoolean(checkOtherBlockedBool);
                                    if (!checkOtherBlockedBoolBool) {
                                        System.out.println("oop you blocked");
                                        String alreadyOtherBlocked = reader.readLine();
                                        System.out.println(alreadyOtherBlocked);
                                        String alreadyOtherBlockedChoice = scanner.nextLine();
                                        defaultCommandsForWriter(writer, alreadyOtherBlockedChoice);

                                        String alreadyOtherBlockedBool = reader.readLine();
                                        boolean alreadyOtherBlockedBoolBool = Boolean.parseBoolean(alreadyOtherBlockedBool);
                                        if (alreadyOtherBlockedBoolBool) {
                                            continue inner3;
                                        } else {
                                            continue outer2;
                                        }
                                    } else {
                                        System.out.println("slayy ig");
                                        String addFriendMessage = reader.readLine();
                                        System.out.println(addFriendMessage);

                                        String endAddFriendMessage = reader.readLine();
                                        System.out.println(endAddFriendMessage);
                                        String endAddFriendChoice = scanner.nextLine();
                                        defaultCommandsForWriter(writer, endAddFriendChoice);

                                        String endAddFriendBool = reader.readLine();
                                        boolean endAddFriendBoolBool = Boolean.parseBoolean(endAddFriendBool);
                                        if (endAddFriendBoolBool) {
                                            continue inner3;
                                        } else {
                                            continue outer2;
                                        }
                                    }
                                }
                            }
                        }

                    } while (true);
                case 4:
                    inner4: do {
                        String removePromptMessage = reader.readLine();
                        System.out.println(removePromptMessage);
                        String userToRemove = scanner.nextLine();
                        defaultCommandsForWriter(writer, userToRemove);

                        String foundBool = reader.readLine();
                        boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                        if (!foundBoolBool) {
                            String notFoundPromptMessage = reader.readLine();
                            System.out.println(notFoundPromptMessage);
                            String notFoundChoice = scanner.nextLine();
                            defaultCommandsForWriter(writer, notFoundChoice);

                            String notFoundBool = reader.readLine();
                            boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                            if (notFoundBoolBool) {
                                continue inner4;
                            } else {
                                continue outer2;
                            }
                        } else {
                            String canBlockBool = reader.readLine();
                            boolean canBlockBoolBool = Boolean.parseBoolean(canBlockBool);
                            if (!canBlockBoolBool) {
                                String removedFriendMessage = reader.readLine();
                                System.out.println(removedFriendMessage);
                                String removedFriendChoice = scanner.nextLine();
                                defaultCommandsForWriter(writer, removedFriendChoice);

                                String removedFriendBool = reader.readLine();
                                boolean removedFriendBoolBool = Boolean.parseBoolean(removedFriendBool);
                                if (removedFriendBoolBool) {
                                    continue inner4;
                                } else {
                                    continue outer2;
                                }
                            } else {
                                String errorRemoveMessage = reader.readLine();
                                System.out.println(errorRemoveMessage);
                                String errorChoice = scanner.nextLine();
                                defaultCommandsForWriter(writer, errorChoice);

                                String errorRemoveBool = reader.readLine();
                                boolean errorRemoveBoolBool = Boolean.parseBoolean(errorRemoveBool);
                                if (errorRemoveBoolBool) {
                                    continue inner4;
                                } else {
                                    continue outer2;
                                }
                            }
                        }
                    } while (true);
                case 5:
                    inner5: do {
                        String blockPromptMessage = reader.readLine();
                        System.out.println(blockPromptMessage);
                        String userToBlock = scanner.nextLine();
                        defaultCommandsForWriter(writer, userToBlock);

                        String foundBool = reader.readLine();
                        boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                        if (!foundBoolBool) {
                            String notFoundPromptMessage = reader.readLine();
                            System.out.println(notFoundPromptMessage);
                            String notFoundChoice = scanner.nextLine();
                            defaultCommandsForWriter(writer, notFoundChoice);

                            String notFoundBool = reader.readLine();
                            boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                            if (notFoundBoolBool) {
                                continue inner5;
                            } else {
                                continue outer2;
                            }
                        } else {
                            String checkFriendBool = reader.readLine();
                            boolean checkFriendBoolBool = Boolean.parseBoolean(checkFriendBool);
                            if (!checkFriendBoolBool) {
                                String successfulBlockFriendMessage = reader.readLine();
                                System.out.println(successfulBlockFriendMessage);

                                String returnPromptMessage = reader.readLine();
                                System.out.println(returnPromptMessage);
                                String returnChoice = scanner.nextLine();
                                defaultCommandsForWriter(writer, returnChoice);

                                String returnBool = reader.readLine();
                                boolean returnBoolBool = Boolean.parseBoolean(returnBool);
                                if (returnBoolBool) {
                                    continue inner5;
                                } else {
                                    continue outer2;
                                }
                            } else {
                                String checkBlockedBool = reader.readLine();
                                boolean checkBlockedBoolBool = Boolean.parseBoolean(checkBlockedBool);
                                if (!checkBlockedBoolBool) {
                                    String alreadyBlocked = reader.readLine();
                                    System.out.println(alreadyBlocked);
                                    String alreadyBlockedChoice = scanner.nextLine();
                                    defaultCommandsForWriter(writer, alreadyBlockedChoice);

                                    String alreadyBlockedBool = reader.readLine();
                                    boolean alreadyBlockedBoolBool = Boolean.parseBoolean(alreadyBlockedBool);
                                    if (alreadyBlockedBoolBool) {
                                        continue inner5;
                                    } else {
                                        continue outer2;
                                    }
                                } else {
                                    String blockedChosenUserMessage = reader.readLine();
                                    System.out.println(blockedChosenUserMessage);

                                    String returnPromptMessage = reader.readLine();
                                    System.out.println(returnPromptMessage);
                                    String returnChoice = scanner.nextLine();
                                    defaultCommandsForWriter(writer, returnChoice);

                                    String returnBool = reader.readLine();
                                    boolean returnBoolBool = Boolean.parseBoolean(returnBool);
                                    if (returnBoolBool) {
                                        continue inner5;
                                    } else {
                                        continue outer2;
                                    }
                                }
                            }
                        }
                    } while (true);
                case 6:
                    inner6: do {
                        String randomPostPath = reader.readLine();
                        System.out.println(randomPostPath);

                        String interactWPost = reader.readLine();
                        System.out.println(interactWPost);
                        String interactWPostChoice = scanner.nextLine();
                        defaultCommandsForWriter(writer, interactWPostChoice);

                        String likeOrDislikeBool = reader.readLine();
                        //boolean likeOrDislikeBoolBool = Boolean.parseBoolean(likeOrDislikeBool);
                        if (likeOrDislikeBool.equals("like")) {
                            String likePost = reader.readLine();
                            System.out.println(likePost);
                            continue outer2;
                        } else if (likeOrDislikeBool.equals("dislike")) {
                            String dislikePost = reader.readLine();
                            System.out.println(dislikePost);
                            continue outer2;
                        } else if (likeOrDislikeBool.equals("add")) {
                            String commentToAddMessage = reader.readLine();
                            System.out.println(commentToAddMessage);
                            String commentToAddChoice = scanner.nextLine();
                            defaultCommandsForWriter(writer, commentToAddChoice);

                            String addComment = reader.readLine();
                            System.out.println(addComment);
                            continue outer2;
                        }

                    } while (true);
                case 7:
                    inner7: do {
                        String lineAcc = "";
                        int k = 1;
                        while (k <= 3) {
                            lineAcc = reader.readLine();
                            System.out.println(lineAcc);
                            k++;
                        }

                        String wantToDeleteCommentMessage = reader.readLine();
                        System.out.println(wantToDeleteCommentMessage);
                        String commentChoice = scanner.nextLine();
                        defaultCommandsForWriter(writer, commentChoice);

                        String commentChoiceBool = reader.readLine();
                        boolean commentChoiceBoolBool = Boolean.parseBoolean(commentChoiceBool);
                        if (!commentChoiceBoolBool) {
                            continue outer2;
                        } else {
                            String commentLineSize = reader.readLine();
                            //System.out.println(commentLineSize);
                            int commentLineSizeInt = Integer.parseInt(commentLineSize);

                            String commentLine = "";
                            int j = 1;
                            while (j <= commentLineSizeInt) {
                                commentLine = reader.readLine();
                                System.out.println(commentLine);
                                j++;
                            }

                            String commentChoiceMessage = reader.readLine();
                            System.out.println(commentChoiceMessage);
                            String commentToDeleteChoice = scanner.nextLine();
                            defaultCommandsForWriter(writer, commentToDeleteChoice);

                            String successMessage = reader.readLine();
                            System.out.println(successMessage);

                            continue outer2;

                        }
                    } while (true);
                case 8:
                    String logoutPromptMessage = reader.readLine();
                    System.out.println(logoutPromptMessage);

                    String logoutClient = scanner.nextLine();
                    defaultCommandsForWriter(writer, logoutClient);

                    String logoutBool = reader.readLine();
                    boolean logoutBoolBool = Boolean.parseBoolean(logoutBool);
                    if (logoutBoolBool) {
                        String yesOption = reader.readLine();
                        System.out.println(yesOption);
                        break outer2;
                    } else {
                        String noOption = reader.readLine();
                        System.out.println(noOption);
                        continue outer2;
                    }
            }
        } while (true);
    }
}
