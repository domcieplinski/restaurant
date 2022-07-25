package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

public class Room {
    private JFrame tablesView;
    private int tables_amount;

    public Room() {

        try{
            Preferences pref = Preferences.userNodeForPackage(Settings.class);
            tables_amount = Integer.parseInt(pref.get("tables_amount", "root"));
        } catch(NumberFormatException e){
            tables_amount = 1;
        }

        tablesView = new JFrame("Room");
        tablesView.setBounds(200, 220, 700, 500);

        JPanel panel = new JPanel(new GridLayout(tables_amount/2,tables_amount/2,4,4));
        for(int i = 1 ; i <= tables_amount; i++){
            JButton test = new JButton("test");
            test.setPreferredSize(new Dimension(20,20));
            panel.add(test);
        }
        tablesView.setContentPane(panel);
        tablesView.setDefaultCloseOperation(2);
        tablesView.setVisible(true);

        tablesView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                Status_Tables.status = true;
                tablesView.dispose();
            }
        });
    }

    public class Status_Tables {
        public static boolean status = true;
    }
}

