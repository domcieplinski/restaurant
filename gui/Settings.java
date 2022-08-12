package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.showMessageDialog;


public class Settings {

    private final JButton setButton = new JButton("Set");
    private JTextField tablesAmount_TextField = new JTextField(2);
    private JLabel tablesAmount_Label = new JLabel("Number of tables in restaurant : ");
    private JLabel setCompanyName_Label = new JLabel("Restaurant name : ");
    private JTextField setCompanyName_TextField = new JTextField();
    private JLabel taxLabel = new JLabel("Tax value (%) : ");
    private JTextField tax_TextField = new JTextField();
    private JPanel panel = new JPanel();
    static JFrame frame = new JFrame("Settings");
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public Settings() {

        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.add(panel);

        panel.setLayout( new GridLayout(3, 2) );
        panel.add(setCompanyName_Label);
        panel.add(setCompanyName_TextField);

        panel.add(tablesAmount_Label);
        panel.add(tablesAmount_TextField);

        panel.add(taxLabel);
        panel.add(tax_TextField);
        frame.add(setButton, BorderLayout.SOUTH);


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
    }
}
