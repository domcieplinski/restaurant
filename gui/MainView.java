package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;


import static javax.swing.JOptionPane.showMessageDialog;


public class MainView {
    private JPanel mainPanel;
    private JMenu File;
    private JMenuItem NewFile;
    private JButton button1;
    private JButton button4;
    private JButton MainView;
    private JButton SettingsButton;
    private JLabel RestaurantName;
    private JButton MenuRestaurant;
    private JFrame frame;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public MainView() {

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

        MenuRestaurant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
            }
        });
        SettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Settings.Status_Settings.status == true) {
                    Settings settings = new Settings();
                    Settings.Status_Settings.status = false;
                }else {
                    error();
                }
            }
        });

        MainView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Room.Status_Tables.status == true) {
                    Room room = new Room();
                    Room.Status_Tables.status = false;
                }else {
                    error();
                }
            }
        });

    }

    private void error() {
        showMessageDialog(null, "You have already opened this window!");
    }

}

