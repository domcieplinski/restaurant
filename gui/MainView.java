package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;


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

        RestaurantName.setText(pref.get("title", "root"));
        frame = new JFrame(pref.get("title", "root"));
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        // Getting screen size = dynamic sizes of MainView frame
        final double width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
        final double height = (Toolkit.getDefaultToolkit().getScreenSize().height)*(0.7);

        frame.setPreferredSize(new Dimension((int)width, (int)height));
        frame.setResizable(false);
        frame.pack();
        frame.add(mainPanel);
        frame.setVisible(true);


        /* Variables to control created objects [can be only 1]. */
        final boolean[] test = {true, true, true};


        /* Action listeners - main menu */
        /*----------------------------------------------------------------------------------*/
        MenuRestaurant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(test[0]){
                    Menu menu = new Menu();
                    System.out.println("jestem w 1");
                    test[0] = false;
                }
                Menu.jframe.setVisible(true);
            }
        });
        SettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(test[1]){
                    Settings settings = new Settings();
                    test[1] = false;
                }
                Settings.frame.setVisible(true);
            }
        });

        MainView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(test[2]){
                    Room room = new Room();
                    test[2] = false;
                }
                Room.tablesView.setVisible(true);
            }
        });

        /* Action listener for 'EXIT' button */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /* ---------------------------------------------------------------------------------*/

    }
}

