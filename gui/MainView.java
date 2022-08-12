package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;


import static javax.swing.JOptionPane.showMessageDialog;


public class MainView {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button4;
    private JButton MainView;
    private JButton SettingsButton;
    private JLabel RestaurantName;
    private JButton MenuRestaurant;
    private JButton exitButton;
    private JFrame frame;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public MainView() {
        Room room = new Room();
        Settings settings = new Settings();
        RestaurantName.setText(pref.get("title", "root"));
        frame = new JFrame(pref.get("title", "root"));
        frame.setDefaultCloseOperation(3);

        // Getting screen size = dynamic sizes of MainView frame
        final double width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
        final double height = (Toolkit.getDefaultToolkit().getScreenSize().height)*(0.7);

        frame.setPreferredSize(new Dimension((int)width, (int)height));
        
        frame.setResizable(false);
        frame.pack();
        frame.add(mainPanel);
        frame.setVisible(true);

        /* Action listener for 'EXIT' button */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        MenuRestaurant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
            }
        });
        SettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.frame.setVisible(true);
            }
        });

        MainView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Room.tablesView.setVisible(true);
            }
        });

    }
}

