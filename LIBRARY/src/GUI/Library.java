package GUI;

import javax.swing.*;
import java.awt.*;


public class Library extends admin {

    public Library() {
        super("Book Library");
        setLayout(null);  // Set layout to null for absolute positioning
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/books.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        // Create the JButton and set its properties
        JButton bookButton = new JButton("Show Book Details");
        bookButton.setBounds(173, 100, 111, 165); // Position and size of the button
        bookButton.setBackground(Color.CYAN); // Set background color for button
        bookButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton1 = new JButton("Show Book Details");
        bookButton1.setBounds(173, 300, 111, 165); // Position and size of the button
        bookButton1.setBackground(Color.RED); // Set background color for button
        bookButton1.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton1.addActionListener(e -> {
            String bookDetails = "Title: My Wattpad Love\n" +
                    "Rating: 4.8/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton2 = new JButton("Show Book Details");
        bookButton2.setBounds(173, 500, 111, 165); // Position and size of the button
        bookButton2.setBackground(Color.BLUE); // Set background color for button
        bookButton2.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton2.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton3 = new JButton("Show Book Details");
        bookButton3.setBounds(383, 500, 111, 165); // Position and size of the button
        bookButton3.setBackground(Color.BLUE); // Set background color for button
        bookButton3.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton3.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton4 = new JButton("Show Book Details");
        bookButton4.setBounds(380, 300, 111, 165); // Position and size of the button
        bookButton4.setBackground(Color.BLUE); // Set background color for button
        bookButton4.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton4.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton5 = new JButton("Show Book Details");
        bookButton5.setBounds(383, 100, 111, 165); // Position and size of the button
        bookButton5.setBackground(Color.BLUE); // Set background color for button
        bookButton5.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton5.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton6 = new JButton("Show Book Details");
        bookButton6.setBounds(587, 99, 111, 165); // Position and size of the button
        bookButton6.setBackground(Color.BLUE); // Set background color for button
        bookButton6.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton6.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton7 = new JButton("Show Book Details");
        bookButton7.setBounds(588, 300, 111, 165); // Position and size of the button
        bookButton7.setBackground(Color.BLUE); // Set background color for button
        bookButton7.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton7.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton8 = new JButton("Show Book Details");
        bookButton8.setBounds(589, 496, 111, 165); // Position and size of the button
        bookButton8.setBackground(Color.BLUE); // Set background color for button
        bookButton8.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton8.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton9 = new JButton("Show Book Details");
        bookButton9.setBounds(796, 100, 111, 165); // Position and size of the button
        bookButton9.setBackground(Color.BLUE); // Set background color for button
        bookButton9.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton9.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton10 = new JButton("Show Book Details");
        bookButton10.setBounds(796, 300, 111, 165); // Position and size of the button
        bookButton10.setBackground(Color.BLUE); // Set background color for button
        bookButton10.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton10.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton11 = new JButton("Show Book Details");
        bookButton11.setBounds(799, 496, 111, 165); // Position and size of the button
        bookButton11.setBackground(Color.BLUE); // Set background color for button
        bookButton11.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton11.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton12 = new JButton("Show Book Details");
        bookButton12.setBounds(1003, 99, 111, 165); // Position and size of the button
        bookButton12.setBackground(Color.BLUE); // Set background color for button
        bookButton12.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton12.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton13 = new JButton("Show Book Details");
        bookButton13.setBounds(1003, 300, 111, 165); // Position and size of the button
        bookButton13.setBackground(Color.BLUE); // Set background color for button
        bookButton13.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton13.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookButton14 = new JButton("Show Book Details");
        bookButton14.setBounds(1003, 496, 111, 165); // Position and size of the button
        bookButton14.setBackground(Color.BLUE); // Set background color for button
        bookButton14.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        // Add ActionListener to button
        bookButton14.addActionListener(e -> {
            String bookDetails = "Title: The Badboy and The Tomboy\n" +
                    "Rating: 4.5/5\n" +
                    "Recommendation: Recommended";
            JOptionPane.showMessageDialog(this, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
        });




        // Add the button to the frame
        add(bookButton);
        add(bookButton1);
        add(bookButton2);
        add(bookButton3);
        add(bookButton4);
        add(bookButton5);
        add(bookButton6);
        add(bookButton7);
        add(bookButton8);
        add(bookButton9);
        add(bookButton10);
        add(bookButton11);
        add(bookButton12);
        add(bookButton13);
        add(bookButton14);
    }

    public static void main(String[] args) {
        // Run the Library window
        Library libraryWindow = new Library();
        libraryWindow.setSize(1300, 690); // Set window size
        libraryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        libraryWindow.setVisible(true);
    }
}