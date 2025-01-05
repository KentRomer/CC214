package GUI;

import javax.swing.*;

public class userMain extends admin{
    public userMain() {
        super("Book Library");
        addGuiComponent();
    }

    private void addGuiComponent() {
        // Load the image from resources folder
        ImageIcon losIcon = new ImageIcon(getClass().getResource("/image/userMain.png"));
        JLabel losLabel = new JLabel(losIcon);
        losLabel.setBounds(0, 0, 1300, 690);
        add(losLabel);

        setLayout(null);  // Disable layout manager and use absolute positioning
        setVisible(true);

    }


        public static void main(String[] args) {
            // Run the user dashboard
            userMain dashboard = new userMain();
            dashboard.setSize(1300, 690);  // Ensure the size matches the layout
            dashboard.setVisible(true);
        }
}
