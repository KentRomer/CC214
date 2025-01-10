package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Book Library");
        frame.setLayout(null); // Set layout to null for absolute positioning
        frame.setSize(1300, 690); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components to the frame
        addGuiComponent(frame);

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void addGuiComponent(JFrame frame) {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(AdminGUI.class.getResource("/image/AdminGUI.png")); // Corrected path
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        frame.add(losLabel);

        // Create buttons with transparent backgrounds and add ActionListeners
        JButton btnPendingRequest = createTransparentButton("");
        btnPendingRequest.setBounds(150, 300, 500, 60); // Adjusted position to match the image
        btnPendingRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Book Pending Request clicked!");
            }
        });
        losLabel.add(btnPendingRequest);

        JButton btnBorrowedBook = createTransparentButton("");
        btnBorrowedBook.setBounds(670, 300, 500, 60); // Adjusted position to match the image
        btnBorrowedBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Borrowed Book clicked!");
            }
        });
        losLabel.add(btnBorrowedBook);

        JButton btnAddBook = createTransparentButton("");
        btnAddBook.setBounds(150, 480, 500, 60); // Adjusted position to match the image
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Add Book clicked!");
            }
        });
        losLabel.add(btnAddBook);

        JButton btnReturnedBook = createTransparentButton("");
        btnReturnedBook.setBounds(670, 480, 500, 60); // Adjusted position to match the image
        btnReturnedBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Returned Book clicked!");
            }
        });
        losLabel.add(btnReturnedBook);

        // Create the Settings button
        JButton btnSettings = createTransparentButton("⚙"); // Settings icon as text (or replace with an icon)
        btnSettings.setBounds(1200, 20, 50, 40); // Position of the Settings icon
        losLabel.add(btnSettings);

        // Create the Log Out button (initially invisible)
        JButton btnLogOut = createTransparentButton("Log Out");
        btnLogOut.setBounds(1120, 70, 125, 45); // Increased width to 120
        btnLogOut.setVisible(false); // Initially hidden
        losLabel.add(btnLogOut);


        // Add ActionListener to the Settings button to toggle Log Out button visibility
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of the Log Out button
                btnLogOut.setVisible(!btnLogOut.isVisible());
            }
        });

        // Add ActionListener to the Log Out button
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You have been logged out!");
                frame.dispose(); // Close the current frame
            }
        });
    }

    private static JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); // Remove focus outline
        button.setOpaque(false); // Make button background transparent
        button.setContentAreaFilled(false); // Disable content fill
        button.setBorderPainted(false); // Disable border
        button.setForeground(Color.BLACK); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size
        return button;
    }
}