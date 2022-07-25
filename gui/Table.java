package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private int number = 0;
    private boolean isOccupied = false;
    private JButton addGuest = new JButton("Add Guest");
    private JFrame frame;

    public Table(int number){
        this.number = number;

        frame = new JFrame(String.valueOf(number));
        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(2);
        frame.add(addGuest);
        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOccupied = true;
                Room.button_Color(number);

            }
        });
        frame.setVisible(true);
    }
}
