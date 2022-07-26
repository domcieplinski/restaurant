package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Guest.guest;

public class Table {
    static int number;
    private JButton addGuest = new JButton("Add Guest");
    private JFrame frame;

    public Table(int number) {
        this.number = number;

        frame = new JFrame("Table " + number);
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(2);
        if (Guest.guest[number] == null) {
            frame.add(addGuest);
            addGuest.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guest[number] = new Guest();
                    guest[number].active = true;
                    Room.button_Color(number);

                    // Refreshing frame - to delete "Add Guest" button"
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                }
            });
        }
        frame.setVisible(true);
    }
}
