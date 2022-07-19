package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

public class Tables  {
    private JFrame tablesView;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);
    private int tables_amount = Integer.parseInt(pref.get("tables_amount", "root"));
    public Tables() {

        tablesView = new JFrame("Tables");
        tablesView.setBounds(200, 220, 700, 500);
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

