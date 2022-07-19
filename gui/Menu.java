package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;


import static javax.swing.JOptionPane.showMessageDialog;


public class Menu {
    private JPanel mainPanel;
    private JMenu File;
    private JMenuItem NewFile;
    private JButton button1;
    private JButton button4;
    private JButton MainView;
    private JButton SettingsButton;
    private JLabel RestaurantName;
    private JFrame frame;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public Menu() {


        //pref.put("title", "Pizza Romas");
        RestaurantName.setText(pref.get("title", "root"));
        frame = new JFrame(pref.get("title", "root"));

        frame.setDefaultCloseOperation(3);
        System.out.println("test");
        frame.setPreferredSize(new Dimension(900, 700));
        frame.setResizable(false);
        frame.pack();
        frame.add(mainPanel);
        frame.setVisible(true);



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
                if (Tables.Status_Tables.status == true) {
                    Tables tables = new Tables();
                    Tables.Status_Tables.status = false;
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

