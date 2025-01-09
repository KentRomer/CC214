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
    private String currentUsername;

    public borrowBook(String username) {
        super("Borrow Book");
        this.currentUsername = username;
        addGuiComponent();
    }

    private void addGuiComponent() {
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/BorrowBook.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        JLabel AdminHomeLabel = new JLabel("Faena Aleph - Welcome " + currentUsername);
        AdminHomeLabel.setBounds(0, 0, 520, 100);
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE);
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        add(AdminHomeLabel);

        // Add Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(1150, 20, 120, 40);
        logoutButton.setOpaque(true);
        logoutButton.setBackground(new Color(255, 69, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.addActionListener(e -> handleLogout());
        add(logoutButton);

        bookField = new JTextField(20);
        bookField.setBounds(400, 390, 300, 60);
        bookField.setBorder(null);
        add(bookField);

        JLabel bookTextLabel = new JLabel();
        bookTextLabel.setBounds(400, 390, 300, 60);
        bookTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bookTextLabel.setForeground(Color.BLACK);
        bookTextLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(bookTextLabel);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setBounds(400, 470, 300, 100);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        resultList.setBackground(new Color(0, 0, 0, 0));
        resultList.setVisible(true);
        add(scrollPane);

        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(740, 480, 120, 70);
        borrowButton.setOpaque(false);
        borrowButton.setContentAreaFilled(false);
        borrowButton.setBorderPainted(false);
        borrowButton.setForeground(Color.BLACK);
        borrowButton.setVisible(true);
        add(borrowButton);

        JButton myBooksButton = new JButton("My Books");
        myBooksButton.setBounds(740, 560, 120, 70);
        myBooksButton.setOpaque(true);
        myBooksButton.setBackground(new Color(51, 153, 255));
        myBooksButton.setForeground(Color.WHITE);
        myBooksButton.addActionListener(e -> showMyBooks());
        add(myBooksButton);

        bookField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleSearchBooks();
            }
        });

        borrowButton.addActionListener(e -> handleBorrowBook());
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            userDasboard dashboard = new userDasboard();
            dashboard.setVisible(true);
        }
    }

    private void handleSearchBooks() {
        String query = bookField.getText().trim();
        listModel.clear();

        if (query.isEmpty()) {
            resultList.setVisible(false);
            borrowButton.setVisible(false);
            return;
        }

        List<String> matches = BookSearch.searchBooks(query);

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

        JOptionPane.showMessageDialog(this, scrollPane, "My Books", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        borrowBook borrowWindow = new borrowBook("testUser");
        borrowWindow.setSize(1300, 690);
        borrowWindow.setLayout(null);
        borrowWindow.setVisible(true);
    }
}