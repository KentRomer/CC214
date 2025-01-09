package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BookSearchGUI {
    private String currentUsername;
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton borrowButton;
    private JButton myBooksButton;

    public BookSearchGUI(String username) {
        this.currentUsername = username;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Book Search - " + currentUsername);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchField = new JTextField(20);
        borrowButton = new JButton("Borrow Book");
        myBooksButton = new JButton("My Books");
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Search Books:"));
        panel.add(searchField);
        panel.add(borrowButton);
        panel.add(myBooksButton);

        frame.add(panel, "North");
        frame.add(new JScrollPane(resultArea), "Center");

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleSearch();
            }
        });

        borrowButton.addActionListener(e -> handleBorrow());
        myBooksButton.addActionListener(e -> showMyBooks());
    }

    private void handleSearch() {
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

    private void handleBorrow() {
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
            boolean requested = BookSearch.requestBorrow(currentUsername, selectedBook);
            if (requested) {
                JOptionPane.showMessageDialog(frame, "Borrow request added for: " + selectedBook);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to request borrow. Book might already be in your queue.");
            }
        }
    }

    private void showMyBooks() {
        List<String> borrowedBooks = BookSearch.getBorrowedBooks(currentUsername);
        List<String> queuedBooks = BookSearch.getBorrowQueue(currentUsername);

        StringBuilder message = new StringBuilder();
        message.append("Your Borrowed Books:\n");
        if (borrowedBooks.isEmpty()) {
            message.append("- No books currently borrowed\n");
        } else {
            borrowedBooks.forEach(book -> message.append("- ").append(book).append("\n"));
        }

        message.append("\nYour Requested Books:\n");
        if (queuedBooks.isEmpty()) {
            message.append("- No pending requests\n");
        } else {
            queuedBooks.forEach(book -> message.append("- ").append(book).append("\n"));
        }

        JTextArea textArea = new JTextArea(message.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scrollPane, "My Books", JOptionPane.INFORMATION_MESSAGE);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BookSearchGUI gui = new BookSearchGUI("testUser");
        gui.show();
    }
}