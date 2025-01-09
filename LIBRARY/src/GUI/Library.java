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
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/user.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 720);
        frame.add(losLabel);

        // Add buttons to the frame
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
