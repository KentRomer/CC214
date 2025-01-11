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
    private JButton viewQueueButton;
    private JTextArea resultArea;
    private JButton logoutButton;
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

        // Create search label
        JLabel searchLabel = new JLabel("Search Books:");
        searchLabel.setBounds(0, 0, 100, 30);
        searchLabel.setForeground(Color.BLACK);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(searchLabel);

        // Create search field with styling
        searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.setBounds(520, -5, 250, 30);
        searchField.setBackground(new Color(232, 184, 109, 255));
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
        borrowButton.setBounds(810, -15, 160, 40);
        borrowButton.setBackground(new Color(0, 0, 0, 0));
        borrowButton.setOpaque(false);
        borrowButton.setContentAreaFilled(false);
        borrowButton.setBorderPainted(false);
        borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(borrowButton);

        viewBorrowedButton = new JButton("View Borrowed Books");////
        viewBorrowedButton.setBounds(160, 280, 150, 30);
        styleButton(viewBorrowedButton);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(0, 320, 150, 30);
        styleButton(returnBookButton);

        viewQueueButton = new JButton("View Pending Requests");
        viewQueueButton.setBounds(160, 320, 150, 30);
        styleButton(viewQueueButton);

        // Add profile button
        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(1115, 44, 70, 40);
        profileButton.setFont(new Font("Arial", Font.BOLD, 22));
        profileButton.setBackground(new Color(0, 0, 0, 0));
        profileButton.setForeground(new Color(0, 0, 0, 0));
        profileButton.setFocusPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        profileButton.addActionListener(e -> openProfile());
        losLabel.add(profileButton);

        // Add logout button
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(1200, 35, 70, 40);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(0, 0, 0, 0));
        logoutButton.setForeground(new Color(0, 0, 0, 0));
        logoutButton.setFocusPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.addActionListener(e -> handleLogout());
        losLabel.add(logoutButton);

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
        add(viewBorrowedButton);
        add(returnBookButton);
        add(viewQueueButton);

        // Add all book buttons
        addBookButtons(losLabel);

        // Set up search field key listener
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().trim();

                if (query.isEmpty()) {
                    resultArea.setText("");
                    scrollPane.setVisible(false);
                    return;
                }

                List<String> matches = BookSearch.searchBooks(query);
                resultArea.setText("");
                scrollPane.setVisible(true);

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
        viewQueueButton.addActionListener(e -> handleViewQueue());

        setVisible(true);
    }

    private void openProfile() {
        this.dispose();
        userMain profileWindow = new userMain();
        profileWindow.setSize(1300, 690);
        profileWindow.setLocationRelativeTo(null);
        profileWindow.setVisible(true);

    }

    private void handleLogout() {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Log Out Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            this.dispose();
            userDasboard dashboard = new userDasboard();
            dashboard.setSize(1300, 690);
            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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

    private void handleBorrowRequest() {
        String query = searchField.getText().trim();
        List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found to borrow.");
            return;
        }

        if (currentUsername == null || currentUsername.isEmpty()) {
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
            boolean requested = BookSearch.requestBorrow(selectedBook, currentUsername);
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
    }

    private void handleViewQueue() {
        List<String> borrowQueue = BookSearch.getBorrowQueue();

        if (borrowQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No pending borrow requests.");
            return;
        }

        String selectedBook = (String) JOptionPane.showInputDialog(
                this,
                "Select a request to approve or reject:",
                "Pending Requests",
                JOptionPane.QUESTION_MESSAGE,
                null,
                borrowQueue.toArray(),
                borrowQueue.get(0)
        );

        if (selectedBook != null) {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to approve the request for:\n" + selectedBook,
                    "Approve or Reject",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                boolean approved = BookSearch.approveRequest(selectedBook.trim());
                if (approved) {
                    JOptionPane.showMessageDialog(this, "Request approved for: " + selectedBook);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to approve the request. Try again.");
                }
            } else if (choice == JOptionPane.NO_OPTION) {
                boolean rejected = BookSearch.rejectRequest(selectedBook.trim());
                if (rejected) {
                    JOptionPane.showMessageDialog(this, "Request rejected for: " + selectedBook);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to reject the request. Try again.");
                }
            }
        }
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

        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(
                null,
                bookDetails,
                "Book Details",
                JOptionPane.INFORMATION_MESSAGE
        ));

        return bookButton;
    }

    private void addBookButtons(JLabel backgroundLabel) {
        // First Row
        backgroundLabel.add(createBookButton("", 173, 100,
                "Title: The Badboy and The Tomboy\n" +
                        "Author: B. N. Toler\n" +
                        "Rating: 4.5/5\n" +
                        "Description: This story follows the journey of Jace, a rebellious bad boy, and Riley, a tomboyish girl.\n" +
                        "They navigate friendship and love amidst unexpected challenges."));

        backgroundLabel.add(createBookButton("", 383, 100,
                "Title: The Mafia and his Angel\n" +
                        "Author: Lylah James\n" +
                        "Rating: 4.7/5\n" +
                        "Description: A woman trapped in a dangerous world finds solace in an unlikely ally.\n" +
                        "This thrilling romance keeps readers hooked."));

        backgroundLabel.add(createBookButton("", 593, 100,
                "Title: Harry Potter and the Sorcerer's Stone\n" +
                        "Author: J.K. Rowling\n" +
                        "Rating: 4.9/5\n" +
                        "Description: Follow Harry Potter's magical journey as he discovers his destiny\n" +
                        "and battles dark forces in the wizarding world."));

        backgroundLabel.add(createBookButton("", 803, 100,
                "Title: The Great Gatsby\n" +
                        "Author: F. Scott Fitzgerald\n" +
                        "Rating: 4.8/5\n" +
                        "Description: A classic tale of love, ambition, and betrayal set in the Jazz Age."));

        backgroundLabel.add(createBookButton("", 1013, 100,
                "Title: Pride and Prejudice\n" +
                        "Author: Jane Austen\n" +
                        "Rating: 4.6/5\n" +
                        "Description: A timeless romance that explores themes of love, class, and self-discovery."));

        // Second Row
        backgroundLabel.add(createBookButton("", 173, 300,
                "Title: To Kill a Mockingbird\n" +
                        "Author: Harper Lee\n" +
                        "Rating: 4.9/5\n" +
                        "Description: A profound novel about racial injustice and moral growth in the American South."));

        backgroundLabel.add(createBookButton("", 383, 300,
                "Title: 1984\n" +
                        "Author: George Orwell\n" +
                        "Rating: 4.8/5\n" +
                        "Description: A dystopian classic that delves into themes of surveillance, truth, and freedom."));

        backgroundLabel.add(createBookButton("", 593, 300,
                "Title: The Catcher in the Rye\n" +
                        "Author: J.D. Salinger\n" +
                        "Rating: 4.4/5\n" +
                        "Description: The story of Holden Caulfield, a teenager navigating life and identity in a complex world."));

        backgroundLabel.add(createBookButton("", 803, 300,
                "Title: The Hobbit\n" +
                        "Author: J.R.R. Tolkien\n" +
                        "Rating: 4.7/5\n" +
                        "Description: Bilbo Baggins embarks on a grand adventure in Middle-earth."));

        backgroundLabel.add(createBookButton("", 1013, 300,
                "Title: The Alchemist\n" +
                        "Author: Paulo Coelho\n" +
                        "Rating: 4.5/5\n" +
                        "Description: A journey of self-discovery and pursuing one's personal legend."));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Library());
    }
}