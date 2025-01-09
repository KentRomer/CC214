package GUI;

import commonconstant.commonconstant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Library extends JFrame {
    private JTextField bookField;
    private JButton borrowButton;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private String currentUsername;

    public Library() {
        super("Library Book Showcase"); // Set the title of the JFrame
        setSize(1300, 690); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation
        setLayout(null); // Use null layout for absolute positioning
        addGuiComponent(this); // Pass the current JFrame to the method
        setVisible(true); // Make the frame visible
    }

    private void addGuiComponent(JFrame frame) {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/user.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 720);
        frame.add(losLabel);

        // Add text field for search with a visible background
        JTextField searchField = new JTextField();
        searchField.setBounds(620, 45, 250, 30); // Set position and size of text field
        searchField.setBackground(Color.WHITE); // Set background color to white (or any color you prefer)
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border around the text field
        searchField.setCaretColor(Color.BLACK); // Ensure caret is visible
        searchField.setFocusable(true); // Make sure it can be focused
        frame.add(searchField);

        JLabel bookTextLabel = new JLabel();
        bookTextLabel.setBounds(620, 45, 250, 30);
        bookTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bookTextLabel.setForeground(Color.BLACK);
        bookTextLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(bookTextLabel);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setBounds(620, 45, 250, 30);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        resultList.setBackground(new Color(0, 0, 0, 0));
        resultList.setVisible(true);
        add(scrollPane);



        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(917, 45, 150, 30);
        borrowButton.setOpaque(false); // Make the button non-opaque
        borrowButton.setContentAreaFilled(false); // Disable background filling
        borrowButton.setBorderPainted(false); // Remove the border
        borrowButton.setForeground(Color.BLACK); // Set the text color
        borrowButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font
        add(borrowButton);


        // Add buttons to the frame for each book
        frame.add(createBookButton("Show Book Details", 173, 100,
                "Title: The Badboy and The Tomboy\n"
                        + "Author: B. N. Toler\n"
                        + "Rating: 4.5/5\n"
                        + "Description: This story follows the journey of Jace, a rebellious bad boy, and Riley, a tomboyish girl.\n"
                        + "They navigate friendship and love amidst unexpected challenges."));
        frame.add(createBookButton("Show Book Details", 173, 300,
                "Title: My Wattpad Love\n"
                        + "Author: Ariana Godoy\n"
                        + "Rating: 4.8/5\n"
                        + "Description: Diana and Andrés meet through Wattpad, a popular writing platform.\n"
                        + "Their connection grows as they explore both online and offline relationships."));
        frame.add(createBookButton("Show Book Details", 173, 500,
                "Title: The Last Virgin Standing\n"
                        + "Author: V. M. Ellis\n"
                        + "Rating: 4.6/5\n"
                        + "Description: Zoey struggles with societal expectations and her personal identity.\n"
                        + "This humorous and heartfelt romance will leave readers laughing and thinking."));
        frame.add(createBookButton("Show Book Details", 383, 500,
                "Title: The Accidental Bride\n"
                        + "Author: Christina Hovland\n"
                        + "Rating: 4.7/5\n"
                        + "Description: Gina unexpectedly finds herself married in this romantic comedy.\n"
                        + "Love and laughter follow as she navigates her new reality."));
        frame.add(createBookButton("Show Book Details", 380, 300,
                "Title: The Replacement Girlfriend\n"
                        + "Author: Rachael Lippincott\n"
                        + "Rating: 4.8/5\n"
                        + "Description: A young woman poses as someone's girlfriend in an unexpected arrangement.\n"
                        + "Her emotional journey takes surprising twists."));
        frame.add(createBookButton("Show Book Details", 383, 100,
                "Title: The Mafia and his Angel\n"
                        + "Author: Lylah James\n"
                        + "Rating: 4.7/5\n"
                        + "Description: A woman trapped in a dangerous world finds solace in an unlikely ally.\n"
                        + "This thrilling romance keeps readers hooked."));
        frame.add(createBookButton("Show Book Details", 587, 99,
                "Title: The One and Only Alpha\n"
                        + "Author: A. K. Koonce\n"
                        + "Rating: 4.6/5\n"
                        + "Description: Ivy discovers a new world and her true destiny in this paranormal romance.\n"
                        + "It's a journey of love, courage, and self-discovery."));
        frame.add(createBookButton("Show Book Details", 588, 300,
                "Title: The Cellphone Swap\n"
                        + "Author: Lindsey Summers\n"
                        + "Rating: 4.6/5\n"
                        + "Description: Keeley swaps phones with a stranger named Talon by accident.\n"
                        + "What starts as a mistake turns into a unique connection."));
        frame.add(createBookButton("Show Book Details", 589, 496,
                "Title: Cupid's Match\n"
                        + "Author: Lauren Palphreyman\n"
                        + "Rating: 4.8/5\n"
                        + "Description: Lila Black discovers she's matched with Cupid himself.\n"
                        + "Chaos and romance unfold in this intriguing tale."));
        frame.add(createBookButton("Show Book Details", 796, 100,
                "Title: Chasing Red\n"
                        + "Author: Isabelle Ronin\n"
                        + "Rating: 4.7/5\n"
                        + "Description: Caleb and Veronica navigate love and life together.\n"
                        + "This captivating romance is full of passion and drama."));
        frame.add(createBookButton("Show Book Details", 796, 300,
                "Title: The Bad Boy's Girl\n"
                        + "Author: Blair Holden\n"
                        + "Rating: 4.8/5\n"
                        + "Description: Tessa O'Connell, a shy teenager, finds herself drawn to bad boy Cole.\n"
                        + "It's a heartwarming Wattpad story of growth and love."));
        frame.add(createBookButton("Show Book Details", 799, 496,
                "Title: She's with Me\n"
                        + "Author: Jessica Cunsolo\n"
                        + "Rating: 4.9/5\n"
                        + "Description: This young adult romance delves into secrets, friendships, and love.\n"
                        + "Jessica Cunsolo crafts an engaging tale that keeps readers hooked."));
        frame.add(createBookButton("Show Book Details", 1003, 99,
                "Title: After We Collided\n"
                        + "Author: Anna Todd\n"
                        + "Rating: 4.7/5\n"
                        + "Description: The sequel explores Tessa and Hardin's complicated and passionate relationship.\n"
                        + "Their journey is as intense as it is heartfelt."));
        frame.add(createBookButton("Show Book Details", 1003, 300,
                "Title: In 27 Days\n"
                        + "Author: Alison Gervais\n"
                        + "Rating: 4.8/5\n"
                        + "Description: Hadley Jamison is given 27 days to change someone's fate.\n"
                        + "This compelling story blends time travel and deep emotion."));
        frame.add(createBookButton("Show Book Details", 1003, 496,
                "Title: Believe Me, I'm Lying\n"
                        + "Author: Ryan Holiday\n"
                        + "Rating: 4.9/5\n"
                        + "Description: Ryan Holiday explores the dark side of the media world in this memoir.\n"
                        + "It's an eye-opening take on manipulation and ethics."));
    }

    private JButton createBookButton(String text, int x, int y, String bookDetails) {
        JButton bookButton = new JButton(text);
        bookButton.setBounds(x, y, 111, 165);
        bookButton.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        bookButton.setOpaque(false);
        bookButton.setContentAreaFilled(false);
        bookButton.setBorderPainted(false);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE));
        return bookButton;
    }
    private void handleSearchBooks() {
        String query = bookField.getText().trim();
        listModel.clear();

        if (query.isEmpty()) {
            resultList.setVisible(false);
            borrowButton.setVisible(false);
            return;
        }

        java.util.List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            listModel.addElement("No matching books found.");
            resultList.setVisible(true);
            borrowButton.setVisible(false);
        } else {
            for (String book : matches) {
                listModel.addElement(book);
            }
            resultList.setVisible(true);
            borrowButton.setVisible(true);
        }
    }

    private void handleBorrowBook() {
        String selectedBook = resultList.getSelectedValue();

        if (selectedBook == null || selectedBook.equals("No matching books found.")) {
            JOptionPane.showMessageDialog(this, "Please select a valid book to borrow.");
            return;
        }

        boolean success = BookSearch.requestBorrow(currentUsername, selectedBook);
        if (success) {
            JOptionPane.showMessageDialog(this, "Borrow request added for: " + selectedBook);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to request borrow. Book might already be in your queue.");
        }
    }

    private void showMyBooks() {
        java.util.List<String> borrowedBooks = BookSearch.getBorrowedBooks(currentUsername);
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

        JOptionPane.showMessageDialog(this, scrollPane, "My Books", JOptionPane.INFORMATION_MESSAGE);
    }


    public static void main(String[] args) {
        new Library();

    }
}
