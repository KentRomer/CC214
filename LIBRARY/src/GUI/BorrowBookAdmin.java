package GUI;

import javax.swing.*;


public class BorrowBookAdmin extends admin{
    public BorrowBookAdmin() {
        super("Borrow Books Admin");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/bookpend.png"));            JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        setLayout(null);  // Disable layout manager and use absolute positioning
        setVisible(true);

    }


    public static void main(String[] args) {
        // Run the user dashboard
        BorrowBookAdmin borrowBookAdmin = new BorrowBookAdmin();
        borrowBookAdmin.setSize(1300, 690);  // Ensure the size matches the layout
        borrowBookAdmin.setVisible(true);
    }
}