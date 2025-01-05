package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import commonconstant.commonconstant; 

public class borrowBook extends admin {
    private JTextField bookField;
    private JButton borrowButton;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;

    public borrowBook() {
        super("Borrow Book");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from the resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/BorrowBook.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        // Create and configure the AdminHomeLabel
        JLabel AdminHomeLabel = new JLabel("Faena Aleph");
        AdminHomeLabel.setBounds(0, 0, 520, 100);
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE);
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        add(AdminHomeLabel);

        // Add a text field for entering the book name
        bookField = new JTextField(20);
        bookField.setBounds(400, 390, 300, 60);
        bookField.setBorder(null); // Hide the border of the text field
        add(bookField);

        // Add a JLabel to display the text from the JTextField
        JLabel bookTextLabel = new JLabel();
        bookTextLabel.setBounds(400, 390, 300, 60); // Position same as the JTextField
        bookTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bookTextLabel.setForeground(Color.BLACK); // Text color
        bookTextLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Optional border to mimic field
        add(bookTextLabel);

        // Add a list to display search results
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setOpaque(false); // Make the JList transparent
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setBounds(400, 470, 300, 100);
        scrollPane.getViewport().setOpaque(false); // Make the scroll pane background transparent
        scrollPane.setBorder(null); // Remove the border of the scroll pane
        resultList.setBackground(new Color(0, 0, 0, 0)); // Set background of the JList to transparent
        resultList.setVisible(true); // Ensure the result box is revealed
        add(scrollPane);

        // Add a button for borrowing the book
        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(740, 480, 120, 70);
        borrowButton.setOpaque(false); // Make the button transparent
        borrowButton.setContentAreaFilled(false); // Remove background fill
        borrowButton.setBorderPainted(false); // Remove the border
        borrowButton.setForeground(Color.BLACK); // Set text color
        borrowButton.setVisible(true); // Ensure the button is visible
        add(borrowButton);

        // Add a key listener to the text field for searching books
        bookField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleSearchBooks();
            }
        });

        // Action listener for the borrow button
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBorrowBook();
            }
        });
    }

    private void handleSearchBooks() {
        String query = bookField.getText().trim();
        listModel.clear();

        if (query.isEmpty()) {
            resultList.setVisible(false); // Hide result box if no query
            borrowButton.setVisible(false); // Hide borrow button
            return;
        }

        // Simulate searching logic (replace with actual implementation)
        List<String> matches = BookSearch.searchBooks(query);

        if (matches.isEmpty()) {
            listModel.addElement("No matching books found.");
            resultList.setVisible(true); // Show result box with this message
            borrowButton.setVisible(false); // Hide borrow button
        } else {
            for (String book : matches) {
                listModel.addElement(book);
            }
            resultList.setVisible(true); // Show result box with the results
            borrowButton.setVisible(true); // Show borrow button
        }
    }

    private void handleBorrowBook() {
        String selectedBook = resultList.getSelectedValue();

        if (selectedBook == null || selectedBook.equals("No matching books found.")) {
            JOptionPane.showMessageDialog(this, "Please select a valid book to borrow.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Simulate borrowing logic (replace with actual implementation)
        boolean success = BookSearch.requestBorrow(selectedBook);
        if (success) {
            JOptionPane.showMessageDialog(this, "Borrow request added for: " + selectedBook, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to borrow the book: " + selectedBook, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the borrow book screen
        borrowBook borrowWindow = new borrowBook();
        borrowWindow.setSize(1300, 690); // Set frame size
        borrowWindow.setLayout(null); // Use absolute layout
        borrowWindow.setVisible(true); // Make frame visible
    }
}
