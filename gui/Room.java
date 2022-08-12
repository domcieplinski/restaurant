package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

public class Room{
    static JFrame tablesView = new JFrame("Room");
    private int tables_amount;
    private static JButton[] button;


    public Room() {

        try{
            Preferences pref = Preferences.userNodeForPackage(Settings.class);
            tables_amount = Integer.parseInt(pref.get("tables_amount", "root"));
        } catch(NumberFormatException e){
            tables_amount = 1;
        }
        Table[] tables = new Table[tables_amount];

        tablesView.setBounds(200, 220, Toolkit.getDefaultToolkit().getScreenSize().width/2, (Toolkit.getDefaultToolkit().getScreenSize().height/2));

        JPanel panel = new JPanel(new GridLayout(tables_amount/2,tables_amount/2,4,4));
        button = new JButton[tables_amount];

        for(int i = 0 ; i < tables_amount; i++){
            final int number = i;
            button[i] = new JButton(String.valueOf(i));
            button[i].setPreferredSize(new Dimension(20,20));
            button[i].setText("Table " + (i+1));
            panel.add(button[i]);
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        tables[number] = new Table(number);
                }
            });
        }


        tablesView.setContentPane(panel);
        tablesView.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        tablesView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                tablesView.dispose();
            }
        });
    }
    public static void button_Color(int number, boolean active){
        if(active)
            button[number].setBackground(Color.RED);
        else
            button[number].setBackground(Color.WHITE);

        button[number].setOpaque(true);
        button[number].setBorderPainted(false);
    }


}

