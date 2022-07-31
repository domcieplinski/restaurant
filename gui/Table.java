package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Guest.guest;

public class Table {
    static int number;
    private JButton addGuest = new JButton("Add Guest");
    private JButton removeGuest = new JButton("Remove Guest");
    private JFrame frame;

    public Table(int number) {
        this.number = number;

        frame = new JFrame("Table " + number);
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(2);
        if(guest[number] == null)
            frame.add(addGuest);
        else
            frame.add(removeGuest);
        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuest();
            }
        });

        removeGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeGuest();
            }
        });


        frame.setVisible(true);
    }


    public void newGuest() {

        guest[number] = new Guest();
        guest[number].active = true;
        Room.button_Color(number, true);
        frame.getContentPane().removeAll();
        frame.add(removeGuest);
        frame.revalidate();
        frame.repaint();
        removeGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeGuest();
            }
        });


    }

    public void removeGuest() {
        guest[number].active = false;
        Room.button_Color(number, false);
        frame.getContentPane().removeAll();
        frame.add(addGuest);
        frame.revalidate();
        frame.repaint();
        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuest();
            }
        });
    }
}
