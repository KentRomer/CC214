package GUI;

import javax.swing.*;
import java.awt.*;

public class Library extends JFrame {

    public Library() {
        super("Borrow Book"); // Set the title of the JFrame
        setSize(1300, 690); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation
        setLayout(null); // Use null layout for absolute positioning
        addGuiComponent(this); // Pass the current JFrame to the method
        setVisible(true); // Make the frame visible
    }

    private void addGuiComponent(JFrame frame) {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/books.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        frame.add(losLabel);

        // Add buttons to the frame
        frame.add(createBookButton("Show Book Details", 173, 100,
                "Title: The Badboy and The Tomboy\nRating: 4.5/5\nRecommendation: The Badboy and The Tomboy by B. N. Toler..."));
        frame.add(createBookButton("Show Book Details", 173, 300,
                "Title: My Wattpad Love\nRating: 4.8/5\nRecommendation: My Wattpad Love by Ariana Godoy..."));
        frame.add(createBookButton("Show Book Details", 173, 500,
                "Title: The Last Virgin Standing\nRating: 4.6/5\nRecommendation: The Last Virgin Standing by V. M. Ellis..."));
        frame.add(createBookButton("Show Book Details", 383, 500,
                "Title: The Accidental Bride\nRating: 4.7/5\nRecommendation: The Accidental Bride by Christina Hovland..."));
        frame.add(createBookButton("Show Book Details", 380, 300,
                "Title: The Replacement Girlfriend\nRating: 4.8/5\nRecommendation: The Replacement Girlfriend by Rachael Lippincott..."));
        frame.add(createBookButton("Show Book Details", 383, 100,
                "Title: The Mafia and his Angel\nRating: 4.7/5\nRecommendation: The Mafia and His Angel by Lylah James..."));
        frame.add(createBookButton("Show Book Details", 587, 99,
                "Title: The One and Only Alpha\nRating: 4.6/5\nRecommendation: The One and Only Alpha by A.K. Koonce..."));
        frame.add(createBookButton("Show Book Details", 588, 300,
                "Title: The Cellphone Swap\nRating: 4.6/5\nRecommendation: The Cellphone Swap by Lindsey Summers..."));
        frame.add(createBookButton("Show Book Details", 589, 496,
                "Title: The Cupid's Match\nRating: 4.8/5\nRecommendation: Cupid's Match by Lauren Palphreyman..."));
        frame.add(createBookButton("Show Book Details", 796, 100,
                "Title: Chasing Red\nRating: 4.7/5\nRecommendation: Chasing Red by Isabelle Ronin..."));
        frame.add(createBookButton("Show Book Details", 796, 300,
                "Title: The Bad Boy's Girl\nRating: 4.8/5\nRecommendation: The Bad Boy's Girl by Blair Holden..."));
        frame.add(createBookButton("Show Book Details", 799, 496,
                "Title: She's with Me\nRating: 4.9/5\nRecommendation: She's With Me by Jessica Cunsolo..."));
        frame.add(createBookButton("Show Book Details", 1003, 99,
                "Title: After we Collided\nRating: 4.7/5\nRecommendation: After We Collided by Anna Todd..."));
        frame.add(createBookButton("Show Book Details", 1003, 300,
                "Title: In 27 Days\nRating: 4.8/5\nRecommendation: In 27 Days by Alison Gervais..."));
        frame.add(createBookButton("Show Book Details", 1003, 496,
                "Title: Believe Me, I'm Lying\nRating: 4.9/5\nRecommendation: Believe Me, I'm Lying by Ryan Holiday..."));
    }

    private JButton createBookButton(String text, int x, int y, String bookDetails) {
        JButton bookButton = new JButton(text);
        bookButton.setBounds(x, y, 111, 165);
        bookButton.setBackground(new Color(0, 0, 0, 0)); // Set transparent background
        bookButton.setOpaque(false); // Make the button fully transparent
        bookButton.setContentAreaFilled(false); // Remove background color
        bookButton.setBorderPainted(false); // Remove the border
        bookButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        bookButton.addActionListener(e -> JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE));
        return bookButton;
    }

    public static void main(String[] args) {
        new Library(); // Run the Library GUI
    }
}
