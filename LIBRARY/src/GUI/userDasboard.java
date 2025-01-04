package GUI;

import javax.swing.*;
import java.awt.*;
import commonconstant.commonconstant;  // Ensure this import is correct

public class userDasboard extends admin {
    public userDasboard() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/LogIn.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        // Create and configure the AdminHomeLabel
        JLabel AdminHomeLabel = new JLabel("Faena Aleph");
        AdminHomeLabel.setBounds(0, 0, 520, 100);
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE);  // Use the color constant
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        add(AdminHomeLabel);

        // Create the text fields
        JTextField textField = new JTextField();
        textField.setBounds(225, 380, 240, 47);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(textField);

        JTextField passfield = new JTextField();
        passfield.setBounds(225, 465, 240, 47);
        passfield.setFont(new Font("Arial", Font.PLAIN, 20));
        add(passfield);

        // Add the transparent "Log In" button
        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(225, 530, 240, 55);  // Positioned under the passfield
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));  // Bold font
        loginButton.setForeground(Color.WHITE);  // White text
        loginButton.setOpaque(false);  // Make the button's background transparent
        loginButton.setContentAreaFilled(false);  // Remove content area fill
        loginButton.setBorderPainted(false);  // Remove border painting
        loginButton.setFocusPainted(false);  // Remove focus outline
        loginButton.addActionListener(e -> {
            borrowBook borrowWindow = new borrowBook();
            borrowWindow.setVisible(true);
            this.dispose();
    });
        add(loginButton);

        // Add the "Sign Up" button
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(425, 600, 120, 55);  // Positioned under the loginButton
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));  // Bold font
        signupButton.setForeground(Color.WHITE);  // White text
        signupButton.setOpaque(false);  // Make the button transparent
        signupButton.setContentAreaFilled(false);  // Remove button background
        signupButton.setBorderPainted(false);  // Remove border painting
        signupButton.addActionListener(e -> {
            // Open the signUp class and close the current window
            signUp signUpWindow = new signUp();
            signUpWindow.setVisible(true);
            this.dispose(); // Close the userDasboard window
        });
        add(signupButton);


        // Create a transparent Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(1150, 20, 120, 40);  // Position the button near the top-right corner
        exitButton.setOpaque(false);  // Make the button transparent
        exitButton.setContentAreaFilled(false);  // Remove content area fill
        exitButton.setBorderPainted(false);  // Remove border painting
        exitButton.setForeground(Color.RED);  // Red text to make it visible
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));  // Bold text
        exitButton.setFocusPainted(false);  // Remove focus outline when clicked
        exitButton.addActionListener(e -> System.exit(0));  // Close the application when clicked
        add(exitButton);

        setLayout(null);  // Disable layout manager and use absolute positioning
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the user dashboard
        userDasboard dashboard = new userDasboard();
        dashboard.setSize(1300, 690);  // Ensure the size matches the layout
        dashboard.setVisible(true);
    }
}
