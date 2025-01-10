package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Library extends JFrame {
    private JTextField searchField;
    private JButton borrowButton;
    private JButton viewBorrowedButton;
    private JButton returnBookButton;
    private JTextArea resultArea;
    private String currentUsername;


    public Library() {
        super("Library System");
        setSize(1300, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        this.currentUsername = UserManager.getCurrentUser();
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/user.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 720);
        add(losLabel);

        // Create search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(100, 50, 1300, 720);
        searchPanel.setOpaque(false);

        // Create search components
        JLabel searchLabel = new JLabel("Search Books:");
        searchLabel.setBounds(0, 0, 100, 30);
        searchLabel.setForeground(Color.BLACK);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setBounds(520, -5, 250, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(searchField);

        // Create result area with scroll pane
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(520, 30, 250, 200);
        searchPanel.add(scrollPane);

        // Create buttons with styling
        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(830, -5, 150, 30);
        borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
        borrowButton.setBackground(new Color(70, 130, 180));
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setFocusPainted(false);
        borrowButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchPanel.add(borrowButton);

        viewBorrowedButton = new JButton("View Borrowed Books");
        viewBorrowedButton.setBounds(160, 280, 150, 30);
        viewBorrowedButton.setFont(new Font("Arial", Font.BOLD, 12));
        viewBorrowedButton.setBackground(new Color(70, 130, 180));
        viewBorrowedButton.setForeground(Color.WHITE);
        add(viewBorrowedButton);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(0, 320, 150, 30);
        returnBookButton.setFont(new Font("Arial", Font.BOLD, 12));
        returnBookButton.setBackground(new Color(70, 130, 180));
        returnBookButton.setForeground(Color.WHITE);
        add(returnBookButton);

        // Add back button
        JButton backButton = createTransparentButton("");
        backButton.setBounds(1150, 20, 50, 40);
        backButton.addActionListener(e -> {
            dispose();
            userDasboard dashboard = new userDasboard();
            dashboard.setSize(1300, 690);
            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
        });
        losLabel.add(backButton);

        add(searchPanel);

        // Add all book buttons
        addBookButtons(losLabel);

        // Set up search field key listener
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

        // Set up button listeners
        borrowButton.addActionListener(e -> handleBorrowRequest());
        viewBorrowedButton.addActionListener(e -> viewBorrowedBooks());
        returnBookButton.addActionListener(e -> handleReturnBook());

        setVisible(true);
    }

    private void handleBorrowRequest() {
        String query = searchField.getText().trim();
        List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found to borrow.");
            return;
        }

        String currentUser = UserManager.getCurrentUser(); // Get current user
        if (currentUser == null || currentUser.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error: User session not found. Please log in again.");
            dispose();
            new userDasboard().setVisible(true);
            return;
        }

        String selectedBook = (String) JOptionPane.showInputDialog(
                this,
                "Select a book to borrow:",
                "Borrow Book",
                JOptionPane.QUESTION_MESSAGE,
                null,
                matches.toArray(),
                matches.get(0)
        );

        if (selectedBook != null) {
            boolean requested = BookSearch.requestBorrow(selectedBook, currentUser);
            if (requested) {
                JOptionPane.showMessageDialog(this,
                        "Your borrow request for: " + selectedBook + "\nhas been submitted and is pending admin approval.",
                        "Request Submitted",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to request borrow for: " + selectedBook,
                        "Request Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void viewBorrowedBooks() {
        // Get borrowed books for the current user from the BorrowRecord list
        List<String> borrowedBooks = new ArrayList<>();
        for (BorrowRecord record : BookSearch.getBorrowRecords()) {
            if (record.getBorrowerUsername().equals(currentUsername)) {
                borrowedBooks.add(record.getBookTitle());
            }
        }

        if (borrowedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "You have no borrowed books.",
                    "Borrowed Books",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Your Borrowed Books:\n" + String.join("\n", borrowedBooks),
                "Borrowed Books",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleReturnBook() {
        // Get borrowed books for the current user
        List<String> borrowedBooks = new ArrayList<>();
        for (BorrowRecord record : BookSearch.getBorrowRecords()) {
            if (record.getBorrowerUsername().equals(currentUsername)) {
                borrowedBooks.add(record.getBookTitle());
            }
        }

        if (borrowedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "You have no books to return.",
                    "Return Book",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selectedBook = (String) JOptionPane.showInputDialog(
                this,
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
                JOptionPane.showMessageDialog(this,
                        "Successfully returned: " + selectedBook,
                        "Book Returned",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to return: " + selectedBook,
                        "Return Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (selectedBook != null) {
            boolean returned = BookSearch.returnBook(selectedBook);
            if (returned) {
                JOptionPane.showMessageDialog(this,
                        "Successfully returned: " + selectedBook,
                        "Book Returned",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to return: " + selectedBook,
                        "Return Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    private JButton createBookButton(String text, int x, int y, String bookDetails) {
        JButton bookButton = new JButton(text);
        bookButton.setBounds(x, y, 111, 165);
        bookButton.setOpaque(false);
        bookButton.setContentAreaFilled(false);
        bookButton.setBorderPainted(false);
        bookButton.setFocusPainted(false);

        bookButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                bookButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        bookButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    bookDetails,
                    "Book Details",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        return bookButton;
    }

    private void addBookButtons(JLabel backgroundLabel) {
        // Your existing book buttons code remains the same
        // First Row
        backgroundLabel.add(createBookButton("", 173, 100,
                "Title: The Badboy and The Tomboy\n" +
                        "Author: B. N. Toler\n" +
                        "Rating: 4.5/5\n" +
                        "Description: This story follows the journey of Jace, a rebellious bad boy, and Riley, a tomboyish girl.\n" +
                        "They navigate friendship and love amidst unexpected challenges."));

        // Add all your other book buttons here...
        // [Previous book buttons code remains unchanged]
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Library();
        });
    }
}