package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class userMain extends admin {
    public userMain() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/userprofile.png"));
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
                String currentUser = UserManager.getCurrentUser();
                if (currentUser == null || currentUser.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: User session not found. Please log in again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Retrieve the borrowed books for the current user
                List<String> userBorrowedBooks = new ArrayList<>();
                for (BorrowRecord record : BookSearch.getBorrowRecords()) {
                    if (record.getBorrowerUsername().equals(currentUser)) {
                        userBorrowedBooks.add(record.getBookTitle());
                    }
                }

                if (userBorrowedBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You have not borrowed any books yet.",
                            "No Borrowed Books",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    String borrowedBooksMessage = String.join("\n", userBorrowedBooks);
                    JOptionPane.showMessageDialog(
                            null,
                            "Books you have borrowed:\n" + borrowedBooksMessage,
                            "Your Borrowed Books",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
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
                String currentUser = UserManager.getCurrentUser();
                if (currentUser == null || currentUser.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error: User session not found. Please log in again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Retrieve the borrowed books for the current user
                List<String> userBorrowedBooks = new ArrayList<>();
                for (BorrowRecord record : BookSearch.getBorrowRecords()) {
                    if (record.getBorrowerUsername().equals(currentUser)) {
                        userBorrowedBooks.add(record.getBookTitle());
                    }
                }

                if (userBorrowedBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "You have no books to return.",
                            "No Books to Return",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                String selectedBook = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a book to return:",
                        "Return Book",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        userBorrowedBooks.toArray(),
                        userBorrowedBooks.get(0)
                );

                if (selectedBook != null) {
                    boolean returned = BookSearch.returnBook(selectedBook);
                    if (returned) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Successfully returned: " + selectedBook,
                                "Book Returned",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to return: " + selectedBook,
                                "Return Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        add(returnButton);

        // Create "Back" button
        JButton backButton = new JButton("Back");
        backButton.setBounds(1120, 10, 70, 50); // Position and size of the button
        makeButtonTransparent(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back button clicked!");

                // Create and show AdminGUI when back button is clicked
                Library adminGUI = new Library();
                adminGUI.main(new String[0]);

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