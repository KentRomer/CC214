package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import commonconstant.commonconstant;

public class signUp extends admin {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton backButton;

    public signUp() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Background setup
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/image/SignUp.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1300, 690);
        add(backgroundLabel);

        // Title label
        JLabel AdminHomeLabel = new JLabel("Faena Aleph");
        AdminHomeLabel.setBounds(50, 30, 400, 50);
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE);
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 35));
        backgroundLabel.add(AdminHomeLabel);

        // Input fields
        fullNameField = createHintedTextField("Full Name");
        fullNameField.setBounds(650, 160, 360, 40);
        backgroundLabel.add(fullNameField);

        emailField = createHintedTextField("Email");
        emailField.setBounds(650, 235, 360, 40);
        backgroundLabel.add(emailField);

        usernameField = createHintedTextField("Username");
        usernameField.setBounds(650, 310, 360, 40);
        backgroundLabel.add(usernameField);

        passwordField = createHintedPasswordField("Password");
        passwordField.setBounds(650, 385, 360, 40);
        backgroundLabel.add(passwordField);

        // Sign up button
        signUpButton = new JButton();
        signUpButton.setBounds(750, 480, 180, 40);
        signUpButton.setOpaque(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.addActionListener(e -> onSignUpClick());
        backgroundLabel.add(signUpButton);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(650, 550, 180, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setOpaque(true);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> onBackClick());
        backgroundLabel.add(backButton);
    }

    private JTextField createHintedTextField(String hint) {
        JTextField textField = new JTextField(hint);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setOpaque(false);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

    private JPasswordField createHintedPasswordField(String hint) {
        JPasswordField passwordField = new JPasswordField(hint);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(hint)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');
                }
            }

            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(hint);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        return passwordField;
    }

    private void onSignUpClick() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (fullName.equals("Full Name") || email.equals("Email") ||
                username.equals("Username") || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        boolean success = UserManager.registerUser(username, password, fullName, email);
        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            onBackClick();
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists.");
        }
    }

    private void onBackClick() {
        this.dispose();
        userDasboard dashboard = new userDasboard();
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        signUp signUpWindow = new signUp();
        signUpWindow.setVisible(true);
    }
}