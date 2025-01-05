package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import commonconstant.commonconstant; // Ensure this import is correct

public class signUp extends admin {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton backButton;  // Declare back button

    public static HashTable userTable; // Make the HashTable static to share data globally

    public signUp() {
        super("Book Library");
        userTable = new HashTable(10); // Initialize the hashtable with a capacity of 10
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/image/SignUp.png"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 1300, 690); // Adjusted size to fit image resolution
        add(backgroundLabel);

        // Add the AdminHomeLabel (Title/Heading)
        JLabel AdminHomeLabel = new JLabel("Faena Aleph");
        AdminHomeLabel.setBounds(50, 30, 400, 50); // Adjusted positioning
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE); // Use the color constant
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 35)); // Larger, bold font for title
        backgroundLabel.add(AdminHomeLabel);

        // Create and configure the Full Name field
        fullNameField = createHintedTextField("Full Name");
        fullNameField.setBounds(650, 160, 360, 40); // Aligned with expected position
        backgroundLabel.add(fullNameField);

        // Create and configure the Email field
        emailField = createHintedTextField("Email");
        emailField.setBounds(650, 235, 360, 40); // Aligned for email
        backgroundLabel.add(emailField);

        // Create and configure the Username field
        usernameField = createHintedTextField("Username");
        usernameField.setBounds(650, 310, 360, 40); // Adjusted for username field
        backgroundLabel.add(usernameField);

        // Create and configure the Password field
        passwordField = createHintedPasswordField("Password");
        passwordField.setBounds(650, 385, 360, 40); // Password field aligned properly
        backgroundLabel.add(passwordField);

        // Create and configure the invisible Sign-Up button
        signUpButton = new JButton();
        signUpButton.setBounds(750, 480, 180, 40); // Centered below the fields
        signUpButton.setOpaque(false); // Make the button transparent
        signUpButton.setContentAreaFilled(false); // Remove the background
        signUpButton.setBorderPainted(false); // Remove the border
        signUpButton.setFocusPainted(false); // Remove focus outline
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Add a hand cursor for interaction
        signUpButton.addActionListener(e -> onSignUpClick());
        backgroundLabel.add(signUpButton);

        // Create and configure the Back button
        backButton = new JButton("Back");
        backButton.setBounds(650, 550, 180, 40); // Positioned below the sign-up button
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setOpaque(true);
        backButton.setBackground(Color.GRAY);  // Set background color for visibility
        backButton.setForeground(Color.WHITE); // Set text color
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Add a hand cursor for interaction
        backButton.addActionListener(e -> onBackClick());
        backgroundLabel.add(backButton);
    }

    private JTextField createHintedTextField(String hint) {
        JTextField textField = new JTextField(hint);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setOpaque(false); // Make transparent
        textField.setForeground(Color.GRAY); // Hint color
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(hint)) {
                    textField.setText(""); // Clear the hint
                    textField.setForeground(Color.BLACK); // Normal text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(hint); // Reset the hint
                    textField.setForeground(Color.GRAY); // Hint color
                }
            }
        });

        return textField;
    }

    private JPasswordField createHintedPasswordField(String hint) {
        JPasswordField passwordField = new JPasswordField(hint);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setOpaque(false); // Make transparent
        passwordField.setForeground(Color.GRAY); // Hint color
        passwordField.setEchoChar((char) 0); // Disable echo char for the hint
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(hint)) {
                    passwordField.setText(""); // Clear the hint
                    passwordField.setForeground(Color.BLACK); // Normal text color
                    passwordField.setEchoChar('\u2022'); // Enable echo char
                }
            }

            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(hint); // Reset the hint
                    passwordField.setForeground(Color.GRAY); // Hint color
                    passwordField.setEchoChar((char) 0); // Disable echo char
                }
            }
        });

        return passwordField;
    }

    private void onSignUpClick() {
        // Retrieve input values from HashTable
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate input fields
        if (fullName.equals("Full Name") || email.equals("Email") || username.equals("Username") || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "All fields are required. Please fill out the form completely.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Store user data in the HashTable
        userTable.put("username", username);
        userTable.put("password", password);

        // Show success message
        JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear the fields
        fullNameField.setText("Full Name");
        emailField.setText("Email");
        usernameField.setText("Username");
        passwordField.setText("Password");
    }

    private void onBackClick() {
        // Close the current sign-up window and go back to the user dashboard
        this.dispose();
        userDasboard dashboard = new userDasboard();  // Open the user dashboard
        dashboard.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the sign-up screen
        signUp signUpWindow = new signUp();
        signUpWindow.setVisible(true);
    }
}
