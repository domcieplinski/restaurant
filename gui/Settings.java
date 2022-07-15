package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Settings  {

    private Menu menu;
    private JButton setButton = new JButton("Set");
    private JTextField tablesAmount_TextField = new JTextField(2);
    private JLabel tablesAmount_Label = new JLabel("Number of tables in restaurant : ");
    private JLabel setCompanyName_Label = new JLabel("Restaurant name : ");
    private JTextField setCompanyName_TextField = new JTextField();
    private JPanel panel = new JPanel();


    public Settings() {

        JFrame ramka = new JFrame("Settings");
        ramka.setTitle("Settings");
        ramka.setBounds(100,100,500,500);
        ramka.setDefaultCloseOperation(2);

        ramka.add(panel);

        panel.setLayout( new GridLayout(2, 2) );  // 4x3 Grid
        panel.add(setCompanyName_Label);
        panel.add(setCompanyName_TextField);

        panel.add(tablesAmount_Label);
        panel.add(tablesAmount_TextField);
        ramka.add(setButton, BorderLayout.SOUTH);


        setButton.addActionListener(
                new setNumberOfChair()
        );

        ramka.addWindowListener(new WindowAdapter (){
            @Override
            public void windowClosing(WindowEvent e){
                super.windowClosed(e);
                Status_Settings.status = true;
                ramka.dispose();
            }
        });
        ramka.setVisible(true);
    }
    private class setNumberOfChair implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String amount = setCompanyName_TextField.getText();

           //RestaurantName.setText(amount);
        }
    }

    public class Status_Settings {
        public static boolean status = true;
    }
}
