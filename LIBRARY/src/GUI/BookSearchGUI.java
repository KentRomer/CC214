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
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        // Add components to frame
        JPanel panel = new JPanel();
        panel.add(new JLabel("Search Books:"));
        panel.add(searchField);
        panel.add(borrowButton);
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

        // Display the frame
        frame.setVisible(true);
    }
}
