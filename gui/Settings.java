package gui;

import com.google.zxing.WriterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.showMessageDialog;


public class Settings {

    private final JButton setButton = new JButton("Set");
    private JTextField tablesAmount_TextField = new JTextField(2);
    private JLabel tablesAmount_Label = new JLabel("Number of tables in restaurant : ");
    private JLabel setCompanyName_Label = new JLabel("Restaurant name : ");
    private JTextField setCompanyName_TextField = new JTextField();
    private JLabel setCompanyStreet_Label = new JLabel("Street : ");
    private JTextField setCompanyStreet_TextField = new JTextField();
    private JLabel setCompanyState_Label = new JLabel("State : ");
    private JTextField setCompanyState_TextField = new JTextField();
    private JLabel setCompanyZipcode_Label = new JLabel("Zip Code : ");
    private JTextField setCompanyZipcode_TextField = new JTextField();
    private JLabel taxLabel = new JLabel("Tax value (%) : ");
    private JTextField tax_TextField = new JTextField();
    private JLabel websiteLabel = new JLabel("Website address : ");
    private JTextField website_TextField = new JTextField();
    private JPanel panel = new JPanel();

    static JFrame frame = new JFrame("Settings");
    Preferences pref = Preferences.userNodeForPackage(Settings.class);


    public Settings() {

        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.add(panel);

        panel.setLayout( new GridLayout(7, 2) );
        panel.add(setCompanyName_Label);
        panel.add(setCompanyName_TextField);

        panel.add(setCompanyStreet_Label);
        panel.add(setCompanyStreet_TextField);

        panel.add(setCompanyZipcode_Label);
        panel.add(setCompanyZipcode_TextField);

        panel.add(setCompanyState_Label);
        panel.add(setCompanyState_TextField);

        panel.add(websiteLabel);
        panel.add(website_TextField);

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
                    int checking_integer;
                    try{
                        checking_integer = Integer.parseInt(tablesAmount_TextField.getText());
                        pref.put("tables_amount", String.valueOf(checking_integer));

                    }
                    catch(NumberFormatException s){
                        showMessageDialog(null, "Value has to be declared in number! Nothing changed!");
                    }

                }
                if(!tax_TextField.getText().isEmpty()){
                    pref.put("tax", tax_TextField.getText());
                }
                if(!setCompanyStreet_TextField.getText().isEmpty()){
                    pref.put("street", setCompanyStreet_TextField.getText());
                }
                if(!setCompanyState_TextField.getText().isEmpty()){
                    pref.put("state", setCompanyState_TextField.getText());
                }
                if(!website_TextField.getText().isEmpty()){
                    pref.put("website", website_TextField.getText());
                    try {
                        new GenerateQRCode(website_TextField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (WriterException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(!setCompanyZipcode_TextField.getText().isEmpty()){
                    pref.put("zipcode", setCompanyZipcode_TextField.getText());
                }
                showMessageDialog(null, "Restart this application to confirm your changes.");
            }
        });
    }
}
