import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.util.*;
import java.awt.*;

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
public class Client extends JFrame implements ListSelectionListener {
    private static ImageIcon welcomeImage = new ImageIcon("/Users/christinexu/Downloads/social-media.png");
    private static ImageIcon searchGifImage = new ImageIcon("/Users/christinexu/Downloads/target.gif");
    private static ImageIcon errorImage = new ImageIcon("/Users/christinexu/Downloads/computer.png");
    private static ImageIcon successGifImage = new ImageIcon("/Users/christinexu/Downloads/success.gif");
    private static ImageIcon postGifImage = new ImageIcon("/Users/christinexu/Downloads/instagram-post.gif");
    private static ImageIcon friendGifImage = new ImageIcon("/Users/christinexu/Downloads/friend.gif");
    private static ImageIcon removeFriendGifImage = new ImageIcon("/Users/christinexu/Downloads/remove-user.gif");
    private static ImageIcon blockGifImage = new ImageIcon("/Users/christinexu/Downloads/block.gif");
    private static ImageIcon feedGifImage = new ImageIcon("/Users/christinexu/Downloads/activity-feed.gif");
    private static ImageIcon likeGifImage = new ImageIcon("/Users/christinexu/Downloads/like.gif");
    private static ImageIcon dislikeGifImage = new ImageIcon("/Users/christinexu/Downloads/heart.gif");
    private static ImageIcon commentGifImage = new ImageIcon("/Users/christinexu/Downloads/comments.gif");
    private static ImageIcon logoutGifImage = new ImageIcon("/Users/christinexu/Downloads/logout.gif");
    private static ImageIcon yesLogoutGifImage = new ImageIcon("/Users/christinexu/Downloads/wave.gif");
    private static ImageIcon noLogoutGifImage = new ImageIcon("/Users/christinexu/Downloads/backward.gif");
    // default commands for the writer to decrease duplications
    public static void defaultCommandsForWriter(PrintWriter w, String input) {
        w.write(input);
        w.println();
        w.flush();
    }

    // main method for our client class
    public static void main(String[] args) throws IOException {
        // Images for our platform

        // Welcome message GIF
        ImageIcon welcomeGifImage = new ImageIcon("/Users/christinexu/Downloads/content-marketing.gif");
        Image scaledWelcomeGifImage = welcomeGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        welcomeGifImage = new ImageIcon(scaledWelcomeGifImage);

        Image scaledWelcomeImage = welcomeImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        welcomeImage = new ImageIcon(scaledWelcomeImage);

        // Successful login GIF
        ImageIcon loginGifImage = new ImageIcon("/Users/christinexu/Downloads/login.gif");
        Image scaledLoginGifImage = loginGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        loginGifImage = new ImageIcon(scaledLoginGifImage);

        // Welcome to the platform GIF
        ImageIcon welcomeToGifImage = new ImageIcon("/Users/christinexu/Downloads/happy.gif");
        Image scaledWelcomeToGifImage = welcomeToGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        welcomeToGifImage = new ImageIcon(scaledWelcomeToGifImage);

        // Search for a user GIF
        Image scaledSearchGifImage = searchGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        searchGifImage = new ImageIcon(scaledSearchGifImage);

        // Error image
        Image scaledErrorImage = errorImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        errorImage = new ImageIcon(scaledErrorImage);

        Image scaledSuccessGifImage = successGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        successGifImage = new ImageIcon(scaledSuccessGifImage);

        Image scaledPostGifImage = postGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        postGifImage = new ImageIcon(scaledPostGifImage);

        Image scaledFriendGifImage = friendGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        friendGifImage = new ImageIcon(scaledFriendGifImage);

        Image scaledRemoveFriendGifImage = removeFriendGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        removeFriendGifImage = new ImageIcon(scaledRemoveFriendGifImage);

        Image scaledBlockGifImage = blockGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        blockGifImage = new ImageIcon(scaledBlockGifImage);

        Image scaledFeedGifImage = feedGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        feedGifImage = new ImageIcon(scaledFeedGifImage);

        Image scaledLikeGifImage = likeGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        likeGifImage = new ImageIcon(scaledLikeGifImage);

        Image scaledDislikeGifImage = dislikeGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        dislikeGifImage = new ImageIcon(scaledDislikeGifImage);

        Image scaledCommentGifImage = commentGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        commentGifImage = new ImageIcon(scaledCommentGifImage);

        Image scaledLogoutGifImage = logoutGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        logoutGifImage = new ImageIcon(scaledLogoutGifImage);

        Image scaledYesLogoutGifImage = yesLogoutGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        yesLogoutGifImage = new ImageIcon(scaledYesLogoutGifImage);

        Image scaledNoLogoutGifImage = noLogoutGifImage.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        noLogoutGifImage = new ImageIcon(scaledNoLogoutGifImage);

        Socket socket = new Socket("localhost", 123);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        JOptionPane.showMessageDialog(null, "Hello!", "Welcome Screen", JOptionPane.INFORMATION_MESSAGE, welcomeGifImage);

        outer: do {
            String welcomeChoice = (String) JOptionPane.showInputDialog(null, "Login or Create an Account", "Welcome Screen", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
            welcomeChoice = welcomeChoice.toUpperCase();
            defaultCommandsForWriter(writer, welcomeChoice);

            if (welcomeChoice.contains("LOGIN")) {
                inner1: do {
                    String loginChoiceUsername = reader.readLine();
                    String username = (String) JOptionPane.showInputDialog(null, loginChoiceUsername, "Login", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                    defaultCommandsForWriter(writer, username);

                    String checkUsername = reader.readLine();
                    if (!Boolean.parseBoolean(checkUsername)) {
                        String usernameMessage = reader.readLine();
                        String errorUsernameChoice = (String) JOptionPane.showInputDialog(null, usernameMessage, "Login", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
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

                        String password = (String) JOptionPane.showInputDialog(null, loginChoicePassword, "Login", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");

                        defaultCommandsForWriter(writer, password);

                        String checkPassword = reader.readLine();
                        if (!Boolean.parseBoolean(checkPassword)) {
                            String passwordMessage = reader.readLine();
                            String errorPasswordChoice = (String) JOptionPane.showInputDialog(null, passwordMessage, "Login", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                            defaultCommandsForWriter(writer, errorPasswordChoice);

                            boolean passwordBool = Boolean.parseBoolean(reader.readLine());
                            if (passwordBool) {
                                continue outer;
                            } else {
                                continue inner2;
                            }
                        }
                        String loginSuccess = reader.readLine();
                        JOptionPane.showMessageDialog(null, loginSuccess, "Login", JOptionPane.INFORMATION_MESSAGE, loginGifImage);
                        break outer;
                    } while (true);
                } while (true);
            } else if (welcomeChoice.contains("CREATE")) {
                inner3:
                do {
                    String createChoiceUsername = reader.readLine();
                    String username = (String) JOptionPane.showInputDialog(null, createChoiceUsername, "Create", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                    defaultCommandsForWriter(writer, username);

                    String checkUsernameBool = reader.readLine();
                    if (Boolean.parseBoolean(checkUsernameBool)) {
                        String okayUsername = reader.readLine();
                        JOptionPane.showMessageDialog(null, okayUsername, "Create", JOptionPane.INFORMATION_MESSAGE, successGifImage);
                        break inner3;
                    } else {
                        String notOkayUsername = reader.readLine();
                        JOptionPane.showMessageDialog(null, notOkayUsername, "Create", JOptionPane.INFORMATION_MESSAGE, errorImage);
                        continue inner3;
                    }
                } while (true);

                inner4:
                do {
                    String createChoicePassword = reader.readLine();
                    String password = (String) JOptionPane.showInputDialog(null, createChoicePassword, "Create", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                    defaultCommandsForWriter(writer, password);

                    String checkPasswordBool = reader.readLine();
                    if (Boolean.parseBoolean(checkPasswordBool)) {
                        String okayPassword = reader.readLine();
                        JOptionPane.showMessageDialog(null, okayPassword, "Create", JOptionPane.INFORMATION_MESSAGE, loginGifImage);
                        break outer;
                    } else {
                        String notOkayPassword = reader.readLine();
                        JOptionPane.showMessageDialog(null, notOkayPassword, "Create", JOptionPane.INFORMATION_MESSAGE, errorImage);
                        continue inner4;
                    }
                } while (true);
            }
        } while (true);

        String welcomeMessage = reader.readLine();
        JOptionPane.showMessageDialog(null, welcomeMessage, "Entering the Platform", JOptionPane.INFORMATION_MESSAGE, welcomeToGifImage);

        String[] mainMenu = new String[8];
        String line = "";
        int i = 0;
        while (i < 7) {
            line = reader.readLine();
            mainMenu[i] = line;
            i++;
        }

        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our Platform");
        frame.setResizable(true);

        JPanel mainMenuPanel1 = new JPanel();
        JPanel mainMenuPanel2 = new JPanel();

        JList<String> mainMenuList = new JList<>(mainMenu);
        Font newFont = new Font("Serif", Font.PLAIN, 25);
        mainMenuList.setFont(newFont);
        mainMenuList.setPreferredSize(new Dimension(600, 200));

        JLabel mainMenuLabel = new JLabel();

        JScrollPane mainMenuListScrollPane = new JScrollPane(mainMenuList);

        mainMenuPanel1.setLayout(new BorderLayout());
        mainMenuPanel1.add(mainMenuListScrollPane);
        content.add(mainMenuPanel1, BorderLayout.CENTER);

//                mainMenuPanel2.add(mainMenuLabel);
//                content.add(mainMenuPanel2, BorderLayout.SOUTH);

        // frame.add(mainMenuPanel1);

        frame.setVisible(true);

        mainMenuList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int option = mainMenuList.getSelectedIndex() + 1;
                defaultCommandsForWriter(writer, option + "");
                defaultCommandsForWriter(writer, "Okay");
                // System.out.println(option);

                try {
                    switch (option) {
                        case 1:
                            inner1: do {
                                defaultCommandsForWriter(writer, "Okay");
                                String searchPromptMessage = reader.readLine();
                                String userToSearchFor = (String) JOptionPane.showInputDialog(null, searchPromptMessage, "Search", JOptionPane.INFORMATION_MESSAGE, searchGifImage, null, "");

                                if (userToSearchFor == null) {
                                    defaultCommandsForWriter(writer, "Cancel");
                                    break;
                                }
                                defaultCommandsForWriter(writer, "Not canceled");

                                defaultCommandsForWriter(writer, userToSearchFor);

                                String foundBool = reader.readLine();
                                boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                                if (!foundBoolBool) {
                                    String notFoundMessage = reader.readLine();
                                    String errorMessage = (String) JOptionPane.showInputDialog(null, notFoundMessage, "Search", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                    defaultCommandsForWriter(writer, errorMessage);

                                    String errorBool = reader.readLine();
                                    boolean errorBoolBool = Boolean.parseBoolean(errorBool);
                                    if (errorBoolBool) {
                                        continue inner1;
                                    } else {
                                        break;
                                    }
                                } else {
                                    String userFound = reader.readLine();
                                    JOptionPane.showMessageDialog(null, userFound, "Search", JOptionPane.INFORMATION_MESSAGE, successGifImage);
                                    break;
                                }
                            } while (true);
                            break;
                        case 2:
                            inner2: do {
                                String postPromptMessage = reader.readLine();
                                String thingToPost = (String) JOptionPane.showInputDialog(null, postPromptMessage, "Post", JOptionPane.INFORMATION_MESSAGE, postGifImage, null, "");

                                if (thingToPost == null) {
                                    defaultCommandsForWriter(writer, "Cancel");
                                    break;
                                }
                                defaultCommandsForWriter(writer, "Not canceled");

                                defaultCommandsForWriter(writer, thingToPost);

                                String successfulPostMessage = reader.readLine();
                                JOptionPane.showMessageDialog(null, successfulPostMessage, "Post", JOptionPane.INFORMATION_MESSAGE, successGifImage);

                                String optionPostPromptMessage = reader.readLine();
                                String optionPost = (String) JOptionPane.showInputDialog(null, optionPostPromptMessage, "Post", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                                defaultCommandsForWriter(writer, optionPost);

                                String reloopBool = reader.readLine();
                                boolean optionBool = Boolean.parseBoolean(reloopBool);
                                if (optionBool) {
                                    continue inner2;
                                } else {
                                    break;
                                }
                            } while (true);
                            break;
                        case 3:
                            inner3: do {
                                String addFriendPromptMessage = reader.readLine();
                                String userToAddFriend = (String) JOptionPane.showInputDialog(null, addFriendPromptMessage, "Add Friend", JOptionPane.INFORMATION_MESSAGE, friendGifImage, null, "");

                                if (userToAddFriend == null) {
                                    defaultCommandsForWriter(writer, "Cancel");
                                    break;
                                }
                                defaultCommandsForWriter(writer, "Not canceled");

                                defaultCommandsForWriter(writer, userToAddFriend);

                                String foundBool = reader.readLine();
                                boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                                if (!foundBoolBool) {
                                    String notFoundPromptMessage = reader.readLine();
                                    String notFoundChoice = (String) JOptionPane.showInputDialog(null, notFoundPromptMessage, "Add Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                    defaultCommandsForWriter(writer, notFoundChoice);

                                    String notFoundBool = reader.readLine();
                                    boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                                    if (notFoundBoolBool) {
                                        continue inner3;
                                    } else {
                                        break;
                                    }
                                } else {
                                    String checkFriendBool = reader.readLine();
                                    boolean checkFriendBoolBool = Boolean.parseBoolean(checkFriendBool);
                                    if (!checkFriendBoolBool) {
                                        String alreadyFriend = reader.readLine();
                                        String alreadyFriendChoice = (String) JOptionPane.showInputDialog(null, alreadyFriend, "Add Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                        defaultCommandsForWriter(writer, alreadyFriendChoice);

                                        String alreadyFriendBool = reader.readLine();
                                        boolean alreadyFriendBoolBool = Boolean.parseBoolean(alreadyFriendBool);
                                        if (alreadyFriendBoolBool) {
                                            continue inner3;
                                        } else {
                                            break;
                                        }
                                    } else {
                                        String checkBlockedBool = reader.readLine();
                                        boolean checkBlockedBoolBool = Boolean.parseBoolean(checkBlockedBool);
                                        if (!checkBlockedBoolBool) {
                                            String alreadyBlocked = reader.readLine();
                                            String alreadyBlockedChoice = (String) JOptionPane.showInputDialog(null, alreadyBlocked, "Add Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                            defaultCommandsForWriter(writer, alreadyBlockedChoice);

                                            String alreadyBlockedBool = reader.readLine();
                                            boolean alreadyBlockedBoolBool = Boolean.parseBoolean(alreadyBlockedBool);
                                            if (alreadyBlockedBoolBool) {
                                                continue inner3;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            String checkOtherBlockedBool = reader.readLine();
                                            boolean checkOtherBlockedBoolBool = Boolean.parseBoolean(checkOtherBlockedBool);
                                            if (!checkOtherBlockedBoolBool) {
                                                String alreadyOtherBlocked = reader.readLine();
                                                String alreadyOtherBlockedChoice = (String) JOptionPane.showInputDialog(null, alreadyOtherBlocked, "Add Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                                defaultCommandsForWriter(writer, alreadyOtherBlockedChoice);

                                                String alreadyOtherBlockedBool = reader.readLine();
                                                boolean alreadyOtherBlockedBoolBool = Boolean.parseBoolean(alreadyOtherBlockedBool);
                                                if (alreadyOtherBlockedBoolBool) {
                                                    continue inner3;
                                                } else {
                                                    break;
                                                }
                                            } else {
                                                String addFriendMessage = reader.readLine();
                                                JOptionPane.showMessageDialog(null, addFriendMessage, "Add Friend", JOptionPane.INFORMATION_MESSAGE, successGifImage);

                                                String endAddFriendMessage = reader.readLine();
                                                String endAddFriendChoice = (String) JOptionPane.showInputDialog(null, endAddFriendMessage, "Add Friend", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                                                defaultCommandsForWriter(writer, endAddFriendChoice);

                                                String endAddFriendBool = reader.readLine();
                                                boolean endAddFriendBoolBool = Boolean.parseBoolean(endAddFriendBool);
                                                if (endAddFriendBoolBool) {
                                                    continue inner3;
                                                } else {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            } while (true);
                            break;
                        case 4:
                            inner4: do {
                                String removePromptMessage = reader.readLine();
                                String userToRemove = (String) JOptionPane.showInputDialog(null, removePromptMessage, "Remove Friend", JOptionPane.INFORMATION_MESSAGE, removeFriendGifImage, null, "");

                                if (userToRemove == null) {
                                    defaultCommandsForWriter(writer, "Cancel");
                                    break;
                                }
                                defaultCommandsForWriter(writer, "Not canceled");

                                defaultCommandsForWriter(writer, userToRemove);

                                String foundBool = reader.readLine();
                                boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                                if (!foundBoolBool) {
                                    String notFoundPromptMessage = reader.readLine();
                                    String notFoundChoice = (String) JOptionPane.showInputDialog(null, notFoundPromptMessage, "Remove Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                    defaultCommandsForWriter(writer, notFoundChoice);

                                    String notFoundBool = reader.readLine();
                                    boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                                    if (notFoundBoolBool) {
                                        continue inner4;
                                    } else {
                                        break;
                                    }
                                } else {
                                    String canBlockBool = reader.readLine();
                                    boolean canBlockBoolBool = Boolean.parseBoolean(canBlockBool);
                                    if (!canBlockBoolBool) {
                                        String removedFriendMessage = reader.readLine();
                                        String removedFriendChoice = (String) JOptionPane.showInputDialog(null, removedFriendMessage, "Remove Friend", JOptionPane.INFORMATION_MESSAGE, successGifImage, null, "");
                                        defaultCommandsForWriter(writer, removedFriendChoice);

                                        String removedFriendBool = reader.readLine();
                                        boolean removedFriendBoolBool = Boolean.parseBoolean(removedFriendBool);
                                        if (removedFriendBoolBool) {
                                            continue inner4;
                                        } else {
                                            break;
                                        }
                                    } else {
                                        String errorRemoveMessage = reader.readLine();
                                        String errorChoice = (String) JOptionPane.showInputDialog(null, errorRemoveMessage, "Remove Friend", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                        defaultCommandsForWriter(writer, errorChoice);

                                        String errorRemoveBool = reader.readLine();
                                        boolean errorRemoveBoolBool = Boolean.parseBoolean(errorRemoveBool);
                                        if (errorRemoveBoolBool) {
                                            continue inner4;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            } while (true);
                            break;
                        case 5:
                            inner5: do {
                                String blockPromptMessage = reader.readLine();
                                String userToBlock = (String) JOptionPane.showInputDialog(null, blockPromptMessage, "Block", JOptionPane.INFORMATION_MESSAGE, blockGifImage, null, "");

                                if (userToBlock == null) {
                                    defaultCommandsForWriter(writer, "Cancel");
                                    break;
                                }
                                defaultCommandsForWriter(writer, "Not canceled");

                                defaultCommandsForWriter(writer, userToBlock);

                                String foundBool = reader.readLine();
                                boolean foundBoolBool = Boolean.parseBoolean(foundBool);
                                if (!foundBoolBool) {
                                    String notFoundPromptMessage = reader.readLine();
                                    String notFoundChoice = (String) JOptionPane.showInputDialog(null, notFoundPromptMessage, "Block", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                    defaultCommandsForWriter(writer, notFoundChoice);

                                    String notFoundBool = reader.readLine();
                                    boolean notFoundBoolBool = Boolean.parseBoolean(notFoundBool);
                                    if (notFoundBoolBool) {
                                        continue inner5;
                                    } else {
                                        break;
                                    }
                                } else {
                                    String checkFriendBool = reader.readLine();
                                    boolean checkFriendBoolBool = Boolean.parseBoolean(checkFriendBool);
                                    if (!checkFriendBoolBool) {
                                        String successfulBlockFriendMessage = reader.readLine();
                                        JOptionPane.showMessageDialog(null, successfulBlockFriendMessage, "Block", JOptionPane.INFORMATION_MESSAGE, successGifImage);

                                        String returnPromptMessage = reader.readLine();
                                        String returnChoice = (String) JOptionPane.showInputDialog(null, returnPromptMessage, "Block", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                                        defaultCommandsForWriter(writer, returnChoice);

                                        String returnBool = reader.readLine();
                                        boolean returnBoolBool = Boolean.parseBoolean(returnBool);
                                        if (returnBoolBool) {
                                            continue inner5;
                                        } else {
                                            break;
                                        }
                                    } else {
                                        String checkBlockedBool = reader.readLine();
                                        boolean checkBlockedBoolBool = Boolean.parseBoolean(checkBlockedBool);
                                        if (!checkBlockedBoolBool) {
                                            String alreadyBlocked = reader.readLine();
                                            String alreadyBlockedChoice = (String) JOptionPane.showInputDialog(null, alreadyBlocked, "Block", JOptionPane.INFORMATION_MESSAGE, errorImage, null, "");
                                            defaultCommandsForWriter(writer, alreadyBlockedChoice);

                                            String alreadyBlockedBool = reader.readLine();
                                            boolean alreadyBlockedBoolBool = Boolean.parseBoolean(alreadyBlockedBool);
                                            if (alreadyBlockedBoolBool) {
                                                continue inner5;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            String blockedChosenUserMessage = reader.readLine();
                                            JOptionPane.showMessageDialog(null, blockedChosenUserMessage, "Block", JOptionPane.INFORMATION_MESSAGE, successGifImage);

                                            String returnPromptMessage = reader.readLine();
                                            String returnChoice = (String) JOptionPane.showInputDialog(null, returnPromptMessage, "Block", JOptionPane.INFORMATION_MESSAGE, welcomeImage, null, "");
                                            defaultCommandsForWriter(writer, returnChoice);

                                            String returnBool = reader.readLine();
                                            System.out.println(returnBool);
                                            boolean returnBoolBool = Boolean.parseBoolean(returnBool);
                                            if (returnBoolBool) {
                                                continue inner5;
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                }
                            } while (true);
                            break;
                        case 6:
                            inner6: do {
                                setLocationToLeft(frame);
                                String randomPostPath1 = reader.readLine();
                                System.out.println(randomPostPath1);

                                String firstPartOfPath = randomPostPath1.substring(0, randomPostPath1.indexOf(" ", randomPostPath1.indexOf("Posts") + 7));
                                String urlPartOfPath = randomPostPath1.substring(randomPostPath1.indexOf("/"), randomPostPath1.indexOf(" ", randomPostPath1.indexOf("/")));

                                JFrame feedFrame = new JFrame();
                                Container content = feedFrame.getContentPane();
                                content.setLayout(new BorderLayout());
                                feedFrame.setSize(800, 500);
                                feedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                BufferedImage firstFeedImage = ImageIO.read(new File(urlPartOfPath));
                                BufferedImage secondFeedImage = ImageIO.read(new File("/Users/christinexu/Downloads/beach.png"));
                                BufferedImage thirdFeedImage = ImageIO.read(new File("/Users/christinexu/Downloads/bride.png"));

                                JLabel firstImageLabel = new JLabel(new ImageIcon(firstFeedImage));
                                JLabel secondImageLabel = new JLabel(new ImageIcon(secondFeedImage));
                                JLabel thirdImageLabel = new JLabel(new ImageIcon(thirdFeedImage));

                                JPanel feedPanel = new JPanel();
                                feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
                                feedPanel.add(firstImageLabel);
                                feedPanel.add(secondImageLabel);
                                feedPanel.add(thirdImageLabel);

                                JScrollPane feedScrollPane = new JScrollPane(feedPanel);

                                content.add(feedScrollPane, BorderLayout.CENTER);

                                feedFrame.setVisible(true);

                                setLocationToRight(feedFrame);

                                String interactWPost = reader.readLine();
                                String interactWPostChoice = showMessageAtBottomFeed(feedFrame, interactWPost);
                                defaultCommandsForWriter(writer, interactWPostChoice);

                                String likeOrDislikeBool = reader.readLine();
                                if (likeOrDislikeBool.equals("like")) {
                                    String likePost = reader.readLine();
                                    JOptionPane.showMessageDialog(null, likePost, "Feed", JOptionPane.INFORMATION_MESSAGE, likeGifImage);
                                    break;
                                } else if (likeOrDislikeBool.equals("dislike")) {
                                    String dislikePost = reader.readLine();
                                    JOptionPane.showMessageDialog(null, dislikePost, "Feed", JOptionPane.INFORMATION_MESSAGE, dislikeGifImage);

                                    break;
                                } else if (likeOrDislikeBool.equals("add")) {
                                    String commentToAddMessage = reader.readLine();
                                    String commentToAddChoice = (String) JOptionPane.showInputDialog(null, commentToAddMessage, "Feed", JOptionPane.INFORMATION_MESSAGE, commentGifImage, null, "");
                                    defaultCommandsForWriter(writer, commentToAddChoice);

                                    String addComment = reader.readLine();
                                    JOptionPane.showMessageDialog(null, addComment, "Feed", JOptionPane.INFORMATION_MESSAGE, commentGifImage);
                                    break;
                                }

                            } while (true);
                            break;
//                        case 7:
//                            inner7: do {
//                                String lineAcc = "";
//                                int k = 1;
//                                while (k <= 3) {
//                                    lineAcc = reader.readLine();
//                                    System.out.println(lineAcc);
//                                    k++;
//                                }
//
//                                String wantToDeleteCommentMessage = reader.readLine();
//                                String commentChoice = JOptionPane.showInputDialog(null, wantToDeleteCommentMessage, "Search", JOptionPane.INFORMATION_MESSAGE);
//                                defaultCommandsForWriter(writer, commentChoice);
//
//                                String commentChoiceBool = reader.readLine();
//                                boolean commentChoiceBoolBool = Boolean.parseBoolean(commentChoiceBool);
//                                if (!commentChoiceBoolBool) {
//                                    break;
//                                } else {
//                                    String commentLineSize = reader.readLine();
//                                    int commentLineSizeInt = Integer.parseInt(commentLineSize);
//
//                                    String commentLine = "";
//                                    int j = 1;
//                                    while (j <= commentLineSizeInt) {
//                                        commentLine = reader.readLine();
//                                        System.out.println(commentLine);
//                                        j++;
//                                    }
//
//                                    String commentChoiceMessage = reader.readLine();
//                                    String commentToDeleteChoice = JOptionPane.showInputDialog(null, commentChoiceMessage, "Search", JOptionPane.INFORMATION_MESSAGE);
//                                    defaultCommandsForWriter(writer, commentToDeleteChoice);
//
//                                    String successMessage = reader.readLine();
//                                    System.out.println(successMessage);
//
//                                    break;
//
//                                }
//                            } while (true);
//                            break;
                        case 7:
                            String logoutPromptMessage = reader.readLine();
                            String logoutClient = (String) JOptionPane.showInputDialog(null, logoutPromptMessage, "Logout", JOptionPane.INFORMATION_MESSAGE, logoutGifImage, null, "");
                            defaultCommandsForWriter(writer, logoutClient);

                            String logoutBool = reader.readLine();
                            boolean logoutBoolBool = Boolean.parseBoolean(logoutBool);
                            if (logoutBoolBool) {
                                frame.setVisible(false);
                                String yesOption = reader.readLine();
                                JOptionPane.showMessageDialog(null, yesOption, "Logout", JOptionPane.INFORMATION_MESSAGE, yesLogoutGifImage);
                                reader.close();
                                writer.close();
                                return;
                            } else {
                                String noOption = reader.readLine();
                                JOptionPane.showMessageDialog(null, noOption, "Logout", JOptionPane.INFORMATION_MESSAGE, noLogoutGifImage);
                                break;
                            }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static String showMessageAtBottomFeed(JFrame parentFrame, String prompt) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int dialogWidth = 300;
        int dialogHeight = 150;

        parentFrame.setLocation((screenSize.width - parentFrame.getWidth()) / 2, 0);

        String choice = (String) JOptionPane.showInputDialog(null, prompt, "Feed", JOptionPane.INFORMATION_MESSAGE, feedGifImage, null, "");

        return choice;
    }

    public static void setLocationToRight(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

        int x = bounds.x + bounds.width - insets.right - frame.getWidth();
        int y = bounds.y + insets.top;
        frame.setLocation(x, y);
    }

    public static void setLocationToLeft(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

        int x = bounds.x + insets.left;
        int y = bounds.y + insets.top;

        frame.setLocation(x, y);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

}
