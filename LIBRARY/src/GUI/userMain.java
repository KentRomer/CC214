package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userMain extends admin {
    public userMain() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/userMain.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        // Create "Book Borrow" button
        JButton borrowButton = new JButton("Book Borrow");
        borrowButton.setBounds(350, 300, 180, 30); // Position and size of the button
        makeButtonTransparent(borrowButton);
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Book Borrow button clicked!");
                JOptionPane.showMessageDialog(
                        null,
                        "Successfully Borrowed!!",
                        "Notification",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // Add your book borrow functionality here
            }
        });
        add(borrowButton);

        // Create "Return Book" button
        JButton returnButton = new JButton("Return Book");
        returnButton.setBounds(745, 300, 200, 30); // Position and size of the button
        makeButtonTransparent(returnButton);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Return Book button clicked!");
                JOptionPane.showMessageDialog(
                        null,
                        "Successfully Returned!!",
                        "Notification",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // Add your return book functionality here
            }
        });
        add(returnButton);

        // Create "Back" button
        JButton backButton = new JButton("Back");
        backButton.setBounds(1000, 10, 70, 50); // Position and size of the button
        makeButtonTransparent(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button clicked!");

                // Create and show AdminGUI when back button is clicked
                AdminGUI adminGUI = new AdminGUI();  // Instantiate the AdminGUI class
                adminGUI.main(new String[0]);        // Call main method of AdminGUI (which sets it up)

                dispose(); // Close the current userMain frame
            }
        });
        add(backButton);


        // Create "Log Out" button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(1200, 10, 70, 50); // Position and size of the button
        makeButtonTransparent(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Confirm Log Out",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("Log Out button clicked! Logging out...");
                    dispose(); // Close the current frame
                } else {
                    System.out.println("Log Out canceled.");
                }
            }
        });
        add(logoutButton);

        setLayout(null); // Disable layout manager and use absolute positioning
        setVisible(true);
    }

    private void makeButtonTransparent(JButton button) {
        button.setOpaque(false); // Makes the button background transparent
        button.setContentAreaFilled(false); // Removes the filled background
        button.setBorderPainted(false); // Removes the border
    }

    public static void main(String[] args) {
        // Run the user dashboard
        userMain dashboard = new userMain();
        dashboard.setSize(1300, 690); // Ensure the size matches the layout
        dashboard.setVisible(true);
    }
}
