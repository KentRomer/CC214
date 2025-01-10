package GUI;

import javax.swing.*;
import java.awt.*;

public class userDasboard extends admin {
    private String currentUsername;
    private JTextField textField;
    private JPasswordField passField;

    public userDasboard() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/LogIn.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, -2, 1300, 690);
        add(losLabel);

        textField = new JTextField();
        textField.setBounds(225, 380, 240, 47);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(textField);

        passField = new JPasswordField();
        passField.setBounds(225, 465, 240, 47);
        passField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(passField);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(225, 530, 240, 55);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> handleLogin());
        add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(425, 600, 120, 55);
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.setForeground(Color.WHITE);
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);
        signupButton.addActionListener(e -> handleSignup());
        add(signupButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(1150, 20, 120, 40);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(Color.RED);
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    private void handleLogin() {
        String username = textField.getText();
        String password = new String(passField.getPassword());

        if (UserManager.isAdmin(username, password)) {
            UserManager.setCurrentUser(username); // Set current user for admin
            JOptionPane.showMessageDialog(this, "Welcome Admin!", "Success", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(() -> {
                AdminGUI.main(new String[0]);
                this.dispose();
            });
        } else if (UserManager.isValidUser(username, password)) {
            // Current user is already set in isValidUser method
            JOptionPane.showMessageDialog(this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            Library borrowWindow = new Library();
            borrowWindow.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSignup() {
        signUp signUpWindow = new signUp();
        signUpWindow.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        userDasboard dashboard = new userDasboard();
        dashboard.setSize(1300, 690);
        dashboard.setVisible(true);
    }
}