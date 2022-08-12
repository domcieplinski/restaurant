package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import static gui.Guest.guest;

public class Table {
    static int number;
    private JButton addGuest = new JButton("Add Guest");
    private JButton removeGuest = new JButton("Remove Guest");
    static JFrame frame;

    public Table(int number) {
        this.number = number;

        frame = new JFrame("Table " + number);
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        if(guest[number] == null)
            frame.add(addGuest);
        else
            frame.add(removeGuest);

        frame.setVisible(true);

        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuest(number);
            }
        });

        removeGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeGuest(number);
            }
        });

    }
    public void newGuest(int number) {
        guest[number] = new Guest(number);
        Room.button_Color(number, true);
        frame.getContentPane().removeAll();
        frame.add(removeGuest);
        frame.revalidate();
        frame.repaint();
        removeGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeGuest(number);
            }
        });


    }

    public void removeGuest(int number) {
        guest[number] = null;
        Room.button_Color(number, false);
        frame.getContentPane().removeAll();
        frame.add(addGuest);
        frame.revalidate();
        frame.repaint();
        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuest(number);
            }
        });
    }
}
