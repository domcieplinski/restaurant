package gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tables  {
    public Tables() {
        JFrame tablesView = new JFrame("Tables");
        tablesView.setBounds(300, 320, 300, 200);
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

