package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class BookSearchGUI {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Book Search");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JTextField searchField = new JTextField(20);
        JButton borrowButton = new JButton("Borrow Book");
        JButton viewBorrowedButton = new JButton("View Borrowed Books");
        JButton returnBookButton = new JButton("Return Book");
        JButton viewQueueButton = new JButton("View Pending Requests");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        // Add components to frame
        JPanel panel = new JPanel();
        panel.add(new JLabel("Search Books:"));
        panel.add(searchField);
        panel.add(borrowButton);
        panel.add(viewBorrowedButton);
        panel.add(returnBookButton);
        panel.add(viewQueueButton);
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
                boolean requested = BookSearch.requestBorrow(selectedBook);
                if (requested) {
                    JOptionPane.showMessageDialog(frame, "Borrow request added for: " + selectedBook);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to request borrow for: " + selectedBook);
                }
            }
        });

        // View Borrowed Books button action listener
        viewBorrowedButton.addActionListener(e -> {
            List<String> borrowedBooks = BookSearch.getBorrowedBooks();

            if (borrowedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No borrowed books.");
                return;
            }

            JOptionPane.showMessageDialog(
                    frame,
                    "Borrowed Books:\n" + String.join("\n", borrowedBooks),
                    "Borrowed Books",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Return Book button action listener
        returnBookButton.addActionListener(e -> {
            List<String> borrowedBooks = BookSearch.getBorrowedBooks();

            if (borrowedBooks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No borrowed books to return.");
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
                    JOptionPane.showMessageDialog(frame, "Returned: " + selectedBook);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to return: " + selectedBook);
                }
            }
        });

        // View Pending Requests button action listener
        viewQueueButton.addActionListener(e -> {
            List<String> borrowQueue = BookSearch.getBorrowQueue();

            if (borrowQueue.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No pending borrow requests.");
                return;
            }

            String selectedBook = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a request to approve or reject:",
                    "Pending Requests",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    borrowQueue.toArray(),
                    borrowQueue.get(0)
            );

            if (selectedBook != null) {
                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you want to approve the request for:\n" + selectedBook,
                        "Approve or Reject",
                        JOptionPane.YES_NO_CANCEL_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    boolean approved = BookSearch.approveRequest(selectedBook.trim());
                    if (approved) {
                        JOptionPane.showMessageDialog(frame, "Request approved for: " + selectedBook);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to approve the request. Try again.");
                    }
                } else if (choice == JOptionPane.NO_OPTION) {
                    boolean rejected = BookSearch.rejectRequest(selectedBook.trim());
                    if (rejected) {
                        JOptionPane.showMessageDialog(frame, "Request rejected for: " + selectedBook);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to reject the request. Try again.");
                    }
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}