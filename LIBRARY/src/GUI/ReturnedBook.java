package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnedBook extends admin {
    public ReturnedBook() {
        super("Returned Books");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from the resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/returnbook.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        setLayout(null); // Disable layout manager and use absolute positioning

        // Create the transparent Back button
        JButton btnBack = createTransparentButton(""); // Back button with transparent styling
        btnBack.setBounds(1110, 20, 100, 40); // Position the button
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current window
                dispose();

                // Open the AdminGUI window
                AdminGUI.main(null); // Call AdminGUI's main method to reopen it
            }
        });
        losLabel.add(btnBack);

        // Create the Settings button
        JButton btnSettings = createTransparentButton("⚙"); // Settings icon as text
        btnSettings.setBounds(1200, 20, 50, 40); // Position of the Settings button
        losLabel.add(btnSettings);

        // Create the Log Out button (initially invisible)
        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(1120, 70, 125, 45); // Position the Log Out button
        btnLogOut.setVisible(false); // Initially hidden
        btnLogOut.setOpaque(true);
        btnLogOut.setBackground(Color.RED);
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setFont(new Font("Arial", Font.BOLD, 14));
        losLabel.add(btnLogOut);

        // Add ActionListener to the Settings button to toggle Log Out button visibility
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogOut.setVisible(!btnLogOut.isVisible());
            }
        });

        // Add ActionListener to the Log Out button
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    userDasboard dashboard = new userDasboard();
                    dashboard.setSize(1300, 690);
                    dashboard.setVisible(true);
                }
            }
        });

        // Create a single panel to contain the two sections
        JPanel combinedPanel = new JPanel();
        combinedPanel.setBounds(40, 160, 1200, 470); // Position and size of the combined panel
        combinedPanel.setBackground(new Color(230, 230, 250, 200)); // Light purple background with transparency
        combinedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Black border
        combinedPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Two columns, no row spacing

        // Create the left panel for Books Returned
        JPanel booksPanel = new JPanel();
        booksPanel.setBackground(new Color(230, 230, 250)); // Light pastel blue background
        booksPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Books Returned",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18)
        ));
        combinedPanel.add(booksPanel);

        // Create the right panel for Borrower Names
        JPanel namesPanel = new JPanel();
        namesPanel.setBackground(new Color(255, 228, 225)); // Light coral background
        namesPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Borrower Names",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18)
        ));
        combinedPanel.add(namesPanel);

        // Add the combined panel to the main label
        losLabel.add(combinedPanel);

        setVisible(true);
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); // Remove focus border
        button.setOpaque(false); // Make the button background transparent
        button.setContentAreaFilled(false); // Remove the content area fill
        button.setBorderPainted(false); // Remove the border
        button.setForeground(java.awt.Color.BLACK); // Set text color to black (adjust as needed)
        button.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20)); // Set the font and size
        return button;
    }

    public static void main(String[] args) {
        // Run the ReturnedBook dashboard
        ReturnedBook returnedBook = new ReturnedBook();
        returnedBook.setSize(1300, 690); // Ensure the size matches the layout
        returnedBook.setVisible(true);
    }
}