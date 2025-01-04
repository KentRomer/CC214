package GUI;

import javax.swing.*;
import java.awt.*;
import commonconstant.commonconstant;  // Ensure this import is correct

public class borrowBook extends admin {
    public borrowBook(){
        super("Borrow Book");
        addGuiComponent();
    }

    private void addGuiComponent(){
        // Load the image from the resources folder (no "src" needed)
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/BorrowBook.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        // Create and configure the AdminHomeLabel
        JLabel AdminHomeLabel = new JLabel("Faena Aleph");
        AdminHomeLabel.setBounds(0, 0, 520, 100);
        AdminHomeLabel.setForeground(commonconstant.DARK_BLUE);  // Use the color constant
        AdminHomeLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        add(AdminHomeLabel);
    }

    public static void main(String[] args) {
        // Run the sign-up screen
        borrowBook borrowWindow = new borrowBook();
        borrowWindow.setVisible(true);
    }
}
