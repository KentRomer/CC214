package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowBookAdmin extends admin {
    private JPanel booksPanel;
    private JPanel borrowersPanel;
    private DefaultListModel<String> borrowedBooksModel;
    private DefaultListModel<String> borrowersModel;

    public BorrowBookAdmin() {
        super("Borrow Books Admin");
        addGuiComponent();
        updateBorrowedBooksDisplay();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/baraw.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        setLayout(null);

        // Create the transparent Back button
        JButton btnBack = createTransparentButton("");
        btnBack.setBounds(1110, 20, 100, 40);
        btnBack.addActionListener(e -> {
            dispose();
            AdminGUI.main(null);
        });
        losLabel.add(btnBack);

        // Settings and Logout buttons
        addSettingsAndLogout(losLabel);

        // Create combined panel
        JPanel combinedPanel = new JPanel();
        combinedPanel.setBounds(40, 160, 1200, 470);
        combinedPanel.setBackground(new Color(230, 230, 250, 200));
        combinedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        combinedPanel.setLayout(new GridLayout(1, 2, 10, 0));

        // Initialize list models
        borrowedBooksModel = new DefaultListModel<>();
        borrowersModel = new DefaultListModel<>();

        // Create the left panel for Books Borrowed
        booksPanel = createListPanel("Books Borrowed", borrowedBooksModel);
        combinedPanel.add(booksPanel);

        // Create the right panel for Borrower Names
        borrowersPanel = createListPanel("Borrower Names", borrowersModel);
        combinedPanel.add(borrowersPanel);

        // Add the combined panel to the main label
        losLabel.add(combinedPanel);
        setVisible(true);
    }

    private JPanel createListPanel(String title, DefaultListModel<String> model) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 230, 250));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                title,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18)
        ));

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setBackground(new Color(240, 240, 255));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }


    private void updateBorrowedBooksDisplay() {
        // Clear existing items
        borrowedBooksModel.clear();
        borrowersModel.clear();

        // Get borrowed books and add them to the display
        List<BorrowRecord> borrowRecords = BookSearch.getBorrowRecords();

        // Debug print
        System.out.println("Number of borrow records: " + borrowRecords.size());

        for (BorrowRecord record : borrowRecords) {
            borrowedBooksModel.addElement(record.getBookTitle());
            borrowersModel.addElement(record.getBorrowerUsername());
        }
    }

    // Add a method to refresh the display
    public void refreshDisplay() {
        updateBorrowedBooksDisplay();
    }

    private void addSettingsAndLogout(JLabel losLabel) {
        JButton btnSettings = createTransparentButton("⚙");
        btnSettings.setBounds(1200, 20, 50, 40);
        losLabel.add(btnSettings);

        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(1120, 70, 125, 45);
        btnLogOut.setVisible(false);
        btnLogOut.setOpaque(true);
        btnLogOut.setBackground(Color.RED);
        btnLogOut.setForeground(Color.WHITE);
        btnLogOut.setFont(new Font("Arial", Font.BOLD, 14));
        losLabel.add(btnLogOut);

        btnSettings.addActionListener(e -> btnLogOut.setVisible(!btnLogOut.isVisible()));

        btnLogOut.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to log out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                userDasboard dashboard = new userDasboard();
                dashboard.setSize(1300, 690);
                dashboard.setVisible(true);
            }
        });
    }

    private JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        return button;
    }

    public static void main(String[] args) {
        BorrowBookAdmin borrowBookAdmin = new BorrowBookAdmin();
        borrowBookAdmin.setSize(1300, 690);
        borrowBookAdmin.setVisible(true);
    }
}