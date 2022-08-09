package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.showMessageDialog;


public class Settings {

    private JButton setButton = new JButton("Set");
    private JTextField tablesAmount_TextField = new JTextField(2);
    private JLabel tablesAmount_Label = new JLabel("Number of tables in restaurant : ");
    private JLabel setCompanyName_Label = new JLabel("Restaurant name : ");
    private JTextField setCompanyName_TextField = new JTextField();
    private JLabel taxLabel = new JLabel("Tax value (%) : ");
    private JTextField tax_TextField = new JTextField();
    private JPanel panel = new JPanel();
    private JFrame ramka;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public Settings() {
        ramka = new JFrame("Settings");

        ramka.setBounds(100,100,500,500);
        ramka.setDefaultCloseOperation(2);

        ramka.add(panel);

        panel.setLayout( new GridLayout(3, 2) );
        panel.add(setCompanyName_Label);
        panel.add(setCompanyName_TextField);

        panel.add(tablesAmount_Label);
        panel.add(tablesAmount_TextField);

        panel.add(taxLabel);
        panel.add(tax_TextField);
        ramka.add(setButton, BorderLayout.SOUTH);

        ramka.setVisible(true);

        /*  ActionListener for "Set" button. It checks whether text field is empty or not. If not then it sets
            this text to preferences (java.util.prefs).
        */
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!setCompanyName_TextField.getText().isEmpty()){
                    pref.put("title", setCompanyName_TextField.getText());
                }
                if(!tablesAmount_TextField.getText().isEmpty()){
                    pref.put("tables_amount", tablesAmount_TextField.getText());
                }
                if(!tax_TextField.getText().isEmpty()){
                    pref.put("tax", tax_TextField.getText());
                }
                showMessageDialog(null, "Restart this application to confirm your changes.");
            }
        });
        /*
            Listening to Settings frame - if it gets closed, Status_Settings.status changes statement to
            true, so it means, that this frame can be opened again from MainView.
            On the other hand, if Status_Settings.status if false, then it blocks from opening second
            frame (only one can be visible at a time).
         */
        ramka.addWindowListener(new WindowAdapter (){
            @Override
            public void windowClosing(WindowEvent e){
                super.windowClosed(e);
                Status_Settings.status = true;
                ramka.dispose();
            }
        });
    }

    public class Status_Settings {
        public static boolean status = true;
    }
}
