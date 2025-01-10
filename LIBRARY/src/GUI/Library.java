package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Library extends JFrame {
    private JTextField searchField;
    private JButton borrowButton;
    private JButton viewBorrowedButton;
    private JButton returnBookButton;
    private JButton viewQueueButton;
    private JTextArea resultArea;

    public Library() {
        super("Library System");
        setSize(1300, 690);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addGuiComponent();
        setVisible(true);
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
        searchPanel.add(searchLabel);

        searchField = new JTextField(20);
        searchField.setBounds(520, -5, 250, 30);
        searchPanel.add(searchField);

        // Create result area with scroll pane
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(520, 30, 250, 200);
        searchPanel.add(scrollPane);

        // Create buttons
        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(830, -5, 150, 30);
        searchPanel.add(borrowButton);

        viewBorrowedButton = new JButton("View Borrowed Books");
        viewBorrowedButton.setBounds(160, 280, 150, 30);

        returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(0, 320, 150, 30);


        viewQueueButton = new JButton("View Pending Requests");
        viewQueueButton.setBounds(160, 320, 150, 30);

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
        viewQueueButton.addActionListener(e -> handleViewQueue());
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

        backgroundLabel.add(createBookButton("", 587, 99,
                "Title: The One and Only Alpha\n" +
                        "Author: A. K. Koonce\n" +
                        "Rating: 4.6/5\n" +
                        "Description: Ivy discovers a new world and her true destiny in this paranormal romance.\n" +
                        "It's a journey of love, courage, and self-discovery."));

        backgroundLabel.add(createBookButton("", 796, 100,
                "Title: Chasing Red\n" +
                        "Author: Isabelle Ronin\n" +
                        "Rating: 4.7/5\n" +
                        "Description: Caleb and Veronica navigate love and life together.\n" +
                        "This captivating romance is full of passion and drama."));

        backgroundLabel.add(createBookButton("", 1003, 99,
                "Title: After We Collided\n" +
                        "Author: Anna Todd\n" +
                        "Rating: 4.7/5\n" +
                        "Description: The sequel explores Tessa and Hardin's complicated and passionate relationship.\n" +
                        "Their journey is as intense as it is heartfelt."));

        // Second Row
        backgroundLabel.add(createBookButton("", 173, 300,
                "Title: My Wattpad Love\n" +
                        "Author: Ariana Godoy\n" +
                        "Rating: 4.8/5\n" +
                        "Description: Diana and Andrés meet through Wattpad, a popular writing platform.\n" +
                        "Their connection grows as they explore both online and offline relationships."));

        backgroundLabel.add(createBookButton("", 380, 300,
                "Title: The Replacement Girlfriend\n" +
                        "Author: Rachael Lippincott\n" +
                        "Rating: 4.8/5\n" +
                        "Description: A young woman poses as someone's girlfriend in an unexpected arrangement.\n" +
                        "Her emotional journey takes surprising twists."));

        backgroundLabel.add(createBookButton("", 588, 300,
                "Title: The Cellphone Swap\n" +
                        "Author: Lindsey Summers\n" +
                        "Rating: 4.6/5\n" +
                        "Description: Keeley swaps phones with a stranger named Talon by accident.\n" +
                        "What starts as a mistake turns into a unique connection."));

        backgroundLabel.add(createBookButton("", 796, 300,
                "Title: The Bad Boy's Girl\n" +
                        "Author: Blair Holden\n" +
                        "Rating: 4.8/5\n" +
                        "Description: Tessa O'Connell, a shy teenager, finds herself drawn to bad boy Cole.\n" +
                        "It's a heartwarming Wattpad story of growth and love."));

        backgroundLabel.add(createBookButton("", 1003, 300,
                "Title: In 27 Days\n" +
                        "Author: Alison Gervais\n" +
                        "Rating: 4.8/5\n" +
                        "Description: Hadley Jamison is given 27 days to change someone's fate.\n" +
                        "This compelling story blends time travel and deep emotion."));

        // Third Row
        backgroundLabel.add(createBookButton("", 173, 500,
                "Title: The Last Virgin Standing\n" +
                        "Author: V. M. Ellis\n" +
                        "Rating: 4.6/5\n" +
                        "Description: Zoey struggles with societal expectations and her personal identity.\n" +
                        "This humorous and heartfelt romance will leave readers laughing and thinking."));

        backgroundLabel.add(createBookButton("", 383, 500,
                "Title: The Accidental Bride\n" +
                        "Author: Christina Hovland\n" +
                        "Rating: 4.7/5\n" +
                        "Description: Gina unexpectedly finds herself married in this romantic comedy.\n" +
                        "Love and laughter follow as she navigates her new reality."));

        backgroundLabel.add(createBookButton("", 589, 496,
                "Title: Cupid's Match\n" +
                        "Author: Lauren Palphreyman\n" +
                        "Rating: 4.8/5\n" +
                        "Description: Lila Black discovers she's matched with Cupid himself.\n" +
                        "Chaos and romance unfold in this intriguing tale."));

        backgroundLabel.add(createBookButton("", 799, 496,
                "Title: She's with Me\n" +
                        "Author: Jessica Cunsolo\n" +
                        "Rating: 4.9/5\n" +
                        "Description: This young adult romance delves into secrets, friendships, and love.\n" +
                        "Jessica Cunsolo crafts an engaging tale that keeps readers hooked."));

        backgroundLabel.add(createBookButton("", 1003, 496,
                "Title: Believe Me, I'm Lying\n" +
                        "Author: Ryan Holiday\n" +
                        "Rating: 4.9/5\n" +
                        "Description: Ryan Holiday explores the dark side of the media world in this memoir.\n" +
                        "It's an eye-opening take on manipulation and ethics."));
    }

    private JButton createBookButton(String text, int x, int y, String bookDetails) {
        JButton bookButton = new JButton(text);
        bookButton.setBounds(x, y, 111, 165);
        bookButton.setBackground(new Color(0, 0, 0, 0));
        bookButton.setOpaque(false);
        bookButton.setContentAreaFilled(false);
        bookButton.setBorderPainted(false);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE));
        return bookButton;
    }

    private void handleBorrowRequest() {
        String query = searchField.getText().trim();
        List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found to borrow.");
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
            boolean requested = BookSearch.requestBorrow(selectedBook);
            if (requested) {
                JOptionPane.showMessageDialog(this, "Borrow request added for: " + selectedBook);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to request borrow for: " + selectedBook);
            }
        }
    }

    private void viewBorrowedBooks() {
        List<String> borrowedBooks = BookSearch.getBorrowedBooks();

        if (borrowedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No borrowed books.");
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Borrowed Books:\n" + String.join("\n", borrowedBooks),
                "Borrowed Books",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleReturnBook() {
        List<String> borrowedBooks = BookSearch.getBorrowedBooks();

        if (borrowedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No borrowed books to return.");
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
                JOptionPane.showMessageDialog(this, "Returned: " + selectedBook);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to return: " + selectedBook);
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

    public static void main(String[] args) {
        new Library();
    }
}