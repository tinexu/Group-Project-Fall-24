import javax.swing.*;
import java.awt.*;

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

// Coding of class is still in progress...
public class VisualPlatform implements VisualPlatformInterface {
    public VisualPlatform() {
        JFrame frame = new JFrame(); // creates a new JFrame object
        JPanel panel = new JPanel(); // creates a new JPanel object

        // This block of code sets the icon of the following input dialogs to be a custom social media icon
        ImageIcon customIcon = new ImageIcon("/Users/christinexu/Downloads/social-media.png");
        Image holdImage = customIcon.getImage();
        Image newImage = holdImage.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        customIcon = new ImageIcon(newImage);

        // This block of code gets a User's username and password during account creation or login using input dialogs
        String username = (String) JOptionPane.showInputDialog(null, "Enter your username: ", "Login", JOptionPane.INFORMATION_MESSAGE, customIcon, null, "");
        String password = (String) JOptionPane.showInputDialog(null, "Enter your password: ", "Login", JOptionPane.INFORMATION_MESSAGE, customIcon, null, "");

        // Prints the username and password just created for testing purposes
//        System.out.println(username);
//        System.out.println(password);

        // This block of code sets the frame of the platform after the User creates the account or logs in
        frame.setSize(800, 500);
        frame.setTitle("Our Platform");
        frame.setResizable(true);

        // This block of code sets a label to the main frame and displays our default text
        JLabel label = new JLabel("Test");
        label.setText("Hello");
        panel.add(label);
        frame.add(panel);

        // This block of code sets the font of the frame
        Font font = new Font("Serif", Font.BOLD, 20);
        frame.setFont(font);

        // Sets the frame to be visible to the computer screen
        frame.setVisible(true);
    }

    // Current main method that runs the GUI (for visualization while testing)
    // Will change the running method to be more reliable in a later phase
    public static void main(String[] args) {
        VisualPlatform visualPlatform = new VisualPlatform();

    }
}

// SwingUtilities.invokeLater(Runnable method)
