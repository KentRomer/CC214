package GUI;

import javax.swing.*;
import java.awt.*;

public class Library {

    public Library() {
        JFrame frame = new JFrame("Book Library");
        frame.setLayout(null);  // Set layout to null for absolute positioning
        frame.setSize(1300, 690); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addGuiComponent(frame);
        frame.setVisible(true);
    }

    private void addGuiComponent(JFrame frame) {
        // Set up the image icon as a background
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/books.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        frame.add(losLabel);

        // Create the JButton and set its properties with transparent background
        JButton bookButton = createBookButton("Show Book Details", 173, 100,
                "Title: The Badboy and The Tomboy\nRating: 4.5/5\nRecommendation: The Badboy and The Tomboy by B. N. Toler...");

        JButton bookButton1 = createBookButton("Show Book Details", 173, 300,
                "Title: My Wattpad Love\nRating: 4.8/5\nRecommendation: My Wattpad Love by Ariana Godoy...");

        JButton bookButton2 = createBookButton("Show Book Details", 173, 500,
                "Title: The Last Virgin Standing\nRating: 4.6/5\nRecommendation: The Last Virgin Standing by V. M. Ellis...");

        JButton bookButton3 = createBookButton("Show Book Details", 383, 500,
                "Title: The Accidental Bride\nRating: 4.7/5\nRecommendation: The Accidental Bride by Christina Hovland...");

        JButton bookButton4 = createBookButton("Show Book Details", 380, 300,
                "Title: The Replacement Girlfriend\nRating: 4.8/5\nRecommendation: The Replacement Girlfriend by Rachael Lippincott...");

        JButton bookButton5 = createBookButton("Show Book Details", 383, 100,
                "Title: The Mafia and his Angel\nRating: 4.7/5\nRecommendation: The Mafia and His Angel by Lylah James...");

        JButton bookButton6 = createBookButton("Show Book Details", 587, 99,
                "Title: The One and Only Alpha\nRating: 4.6/5\nRecommendation: The One and Only Alpha by A.K. Koonce...");

        JButton bookButton7 = createBookButton("Show Book Details", 588, 300,
                "Title: The Cellphone Swap\nRating: 4.6/5\nRecommendation: The Cellphone Swap by Lindsey Summers...");

        JButton bookButton8 = createBookButton("Show Book Details", 589, 496,
                "Title: The Cupid's Match\nRating: 4.8/5\nRecommendation: Cupid's Match by Lauren Palphreyman...");

        JButton bookButton9 = createBookButton("Show Book Details", 796, 100,
                "Title: Chasing Red\nRating: 4.7/5\nRecommendation: Chasing Red by Isabelle Ronin...");

        JButton bookButton10 = createBookButton("Show Book Details", 796, 300,
                "Title: The Bad Boy's Girl\nRating: 4.8/5\nRecommendation: The Bad Boy's Girl by Blair Holden...");

        JButton bookButton11 = createBookButton("Show Book Details", 799, 496,
                "Title: She's with Me\nRating: 4.9/5\nRecommendation: She's With Me by Jessica Cunsolo...");

        JButton bookButton12 = createBookButton("Show Book Details", 1003, 99,
                "Title: After we Collided\nRating: 4.7/5\nRecommendation: After We Collided by Anna Todd...");

        JButton bookButton13 = createBookButton("Show Book Details", 1003, 300,
                "Title: In 27 Days\nRating: 4.8/5\nRecommendation: In 27 Days by Alison Gervais...");

        JButton bookButton14 = createBookButton("Show Book Details", 1003, 496,
                "Title: Believe Me, I'm Lying\nRating: 4.9/5\nRecommendation: Believe Me, I'm Lying by Ryan Holiday...");

        // Add the buttons to the frame
        frame.add(bookButton);
        frame.add(bookButton1);
        frame.add(bookButton2);
        frame.add(bookButton3);
        frame.add(bookButton4);
        frame.add(bookButton5);
        frame.add(bookButton6);
        frame.add(bookButton7);
        frame.add(bookButton8);
        frame.add(bookButton9);
        frame.add(bookButton10);
        frame.add(bookButton11);
        frame.add(bookButton12);
        frame.add(bookButton13);
        frame.add(bookButton14);
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
        // Run the Library window
        new Library(); // Call the Library constructor to set up the GUI
    }
}
