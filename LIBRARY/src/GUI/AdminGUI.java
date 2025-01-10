package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AdminGUI {
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Book Library");
        frame.setLayout(null);
        frame.setSize(1300, 690);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addGuiComponent(frame);
        frame.setVisible(true);
    }

    private static void addGuiComponent(JFrame frame) {
        ImageIcon losIcon = new ImageIcon(AdminGUI.class.getResource("/image/paynal.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        frame.add(losLabel);

        // Pending Request Button (unchanged)
        JButton btnPendingRequest = createTransparentButton("");
        btnPendingRequest.setBounds(150, 300, 500, 60);
        btnPendingRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> borrowQueue = BookSearch.getBorrowQueue();

                if (borrowQueue.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No pending borrow requests.");
                    return;
                }

                String selectedRequest = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a request to approve or reject:",
                        "Pending Requests",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        borrowQueue.toArray(),
                        borrowQueue.get(0)
                );

                if (selectedRequest != null) {
                    int choice = JOptionPane.showConfirmDialog(
                            frame,
                            "Do you want to approve the request for:\n" + selectedRequest,
                            "Approve or Reject",
                            JOptionPane.YES_NO_CANCEL_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        boolean approved = BookSearch.approveRequest(selectedRequest);
                        if (approved) {
                            JOptionPane.showMessageDialog(frame, "Request approved for: " + selectedRequest);
                            for (Window window : Window.getWindows()) {
                                if (window instanceof BorrowBookAdmin) {
                                    ((BorrowBookAdmin) window).refreshDisplay();
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to approve the request. Please try again.");
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {
                        boolean rejected = BookSearch.rejectRequest(selectedRequest);
                        if (rejected) {
                            JOptionPane.showMessageDialog(frame, "Request rejected for: " + selectedRequest);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to reject the request. Please try again.");
                        }
                    }
                }
            }
        });
        losLabel.add(btnPendingRequest);

        // Borrowed Book Button (unchanged)
        JButton btnBorrowedBook = createTransparentButton("");
        btnBorrowedBook.setBounds(670, 300, 500, 60);
        btnBorrowedBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BorrowBookAdmin borrowBookAdmin = new BorrowBookAdmin();
                borrowBookAdmin.setSize(1300, 690);
                borrowBookAdmin.setVisible(true);
            }
        });
        losLabel.add(btnBorrowedBook);

        // Add Book Button (modified)
        JButton btnAddBook = createTransparentButton("");
        btnAddBook.setBounds(150, 480, 500, 60);
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for adding a book
                JDialog addBookDialog = new JDialog(frame, "Add New Book", true);
                addBookDialog.setLayout(new GridLayout(4, 2, 10, 10));
                addBookDialog.setSize(400, 200);
                addBookDialog.setLocationRelativeTo(frame);

                JTextField titleField = new JTextField();
                JTextField authorField = new JTextField();
                JTextField isbnField = new JTextField();

                addBookDialog.add(new JLabel("Title:"));
                addBookDialog.add(titleField);
                addBookDialog.add(new JLabel("Author:"));
                addBookDialog.add(authorField);
                addBookDialog.add(new JLabel("ISBN:"));
                addBookDialog.add(isbnField);

                JButton addButton = new JButton("Add Book");
                JButton cancelButton = new JButton("Cancel");

                addBookDialog.add(addButton);
                addBookDialog.add(cancelButton);

                addButton.addActionListener(event -> {
                    String title = titleField.getText().trim();
                    String author = authorField.getText().trim();
                    String isbn = isbnField.getText().trim();

                    if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                        JOptionPane.showMessageDialog(addBookDialog,
                                "Please fill in all fields",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Format the book entry consistently with existing entries
                    String bookEntry = String.format("%s, %s, %s", title, author, isbn);

                    List<String> books = BookSearch.getBooks();

                    // Check if book already exists
                    if (books.stream().anyMatch(book -> book.toLowerCase().contains(isbn.toLowerCase()))) {
                        JOptionPane.showMessageDialog(addBookDialog,
                                "A book with this ISBN already exists",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Add the book to the array
                    books.add(bookEntry);
                    BookSearch.addBook(bookEntry);
                    JOptionPane.showMessageDialog(addBookDialog,
                            "Book added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    addBookDialog.dispose();
                });

                cancelButton.addActionListener(event -> addBookDialog.dispose());
                addBookDialog.setVisible(true);
            }
        });
        losLabel.add(btnAddBook);

        // Remove Book Button (modified)
        JButton btnRemoveBook = createTransparentButton("");
        btnRemoveBook.setBounds(670, 480, 500, 60);
        btnRemoveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> books = BookSearch.getBooks();

                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "No books available to remove.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a more user-friendly display of books
                String[] bookArray = books.stream()
                        .map(book -> {
                            String[] parts = book.split(", ");
                            return parts[0] + " by " + parts[1] + " (ISBN: " + parts[parts.length - 1] + ")";
                        })
                        .toArray(String[]::new);

                String selectedDisplay = (String) JOptionPane.showInputDialog(
                        frame,
                        "Select a book to remove:",
                        "Remove Book",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        bookArray,
                        bookArray[0]
                );

                if (selectedDisplay != null) {
                    // Find the original book entry
                    String isbn = selectedDisplay.substring(selectedDisplay.lastIndexOf("ISBN: ") + 6, selectedDisplay.length() - 1);
                    String bookToRemove = books.stream()
                            .filter(book -> book.contains(isbn))
                            .findFirst()
                            .orElse(null);

                    if (bookToRemove != null) {
                        int confirm = JOptionPane.showConfirmDialog(
                                frame,
                                "Are you sure you want to remove this book?\n" + selectedDisplay,
                                "Confirm Removal",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            books.remove(bookToRemove);
                            BookSearch.removeBook(bookToRemove); // Automatically notifies UI components
                            JOptionPane.showMessageDialog(frame,
                                    "Book removed successfully!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        losLabel.add(btnRemoveBook);


        // Settings and Logout buttons (unchanged)
        JButton btnSettings = createTransparentButton("⚙");
        btnSettings.setBounds(1200, 20, 50, 40);
        losLabel.add(btnSettings);

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(1120, 70, 125, 45);
        btnLogOut.setVisible(false);
        btnLogOut.setOpaque(true);
        btnLogOut.setBackground(Color.RED);
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setFont(new Font("Arial", Font.BOLD, 14));
        losLabel.add(btnLogOut);

        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogOut.setVisible(!btnLogOut.isVisible());
            }
        });

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to log out?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    userDasboard dashboard = new userDasboard();
                    dashboard.setSize(1300, 690);
                    dashboard.setVisible(true);
                }
            }
        });
    }

    private static JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }
}