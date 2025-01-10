package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class BookSearchGUI {
    private static String currentUsername;

    public static void main(String[] args) {
        currentUsername = UserManager.getCurrentUser();

        // Create JFrame
        JFrame frame = new JFrame("Book Search");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JTextField searchField = new JTextField(20);
        JButton borrowButton = new JButton("Borrow Book");
        JButton viewBorrowedButton = new JButton("View Borrowed Books");
        JButton returnBookButton = new JButton("Return Book");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        // Add components to frame
        JPanel panel = new JPanel();
        panel.add(new JLabel("Search Books:"));
        panel.add(searchField);
        panel.add(borrowButton);
        panel.add(viewBorrowedButton);
        panel.add(returnBookButton);
        frame.add(panel, "North");
        frame.add(new JScrollPane(resultArea), "Center");

        // Search books during typing
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().trim();
                resultArea.setText("");

                if (query.isEmpty()) return;

                List<String> matches = BookSearch.searchBooks(query);
                if (matches.isEmpty()) {
                    resultArea.append("No matching books found.\n");
                } else {
                    for (String book : matches) {
                        resultArea.append(book + "\n");
                    }
                }
            }
        });

        // Borrow button action listener
        borrowButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            List<String> matches = BookSearch.searchBooks(query);

            if (matches.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No books found to borrow.");
                return;
            }

            String selectedBook = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a book to borrow:",
                    "Borrow Book",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    matches.toArray(),
                    matches.get(0)
            );

            if (selectedBook != null) {
                boolean requested = BookSearch.requestBorrow(selectedBook, currentUsername);
                if (requested) {
                    JOptionPane.showMessageDialog(frame,
                            "Borrow request submitted for: " + selectedBook + "\nAwaiting admin approval.",
                            "Request Submitted",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Failed to request borrow. Book may already be pending approval.",
                            "Request Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // View Borrowed Books button action listener
        viewBorrowedButton.addActionListener(e -> {
            List<String> borrowedBooks = new ArrayList<>();
            for (BorrowRecord record : BookSearch.getBorrowRecords()) {
                if (record.getBorrowerUsername().equals(currentUsername)) {
                    borrowedBooks.add(record.getBookTitle());
                }
            }

            if (borrowedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "You have no borrowed books.");
                return;
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Your Borrowed Books:\n" + String.join("\n", borrowedBooks),
                    "Borrowed Books",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Return Book button action listener
        returnBookButton.addActionListener(e -> {
            List<String> borrowedBooks = new ArrayList<>();
            for (BorrowRecord record : BookSearch.getBorrowRecords()) {
                if (record.getBorrowerUsername().equals(currentUsername)) {
                    borrowedBooks.add(record.getBookTitle());
                }
            }

            if (borrowedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "You have no books to return.");
                return;
            }

            String selectedBook = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a book to return:",
                    "Return Book",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    borrowedBooks.toArray(),
                    borrowedBooks.get(0)
            );

            if (selectedBook != null) {
                boolean returned = BookSearch.returnBook(selectedBook);
                if (returned) {
                    JOptionPane.showMessageDialog(frame, "Successfully returned: " + selectedBook);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to return: " + selectedBook);
                }
            }
        });

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}