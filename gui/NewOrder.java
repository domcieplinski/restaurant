package gui;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Guest.guest;

public class NewOrder {
    private JPanel panel;
    private JTree menu;
    private JTable table1;
    private JLabel titleLabel;
    private JButton addGuest  = new JButton("Add Guest");;
    private JButton removeGuest;
    private JLabel valueLabel;
    public JFrame frame;

    public NewOrder(int number) {

        frame = new JFrame("Table " + (number + 1));
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        if (guest[number] == null)
            frame.add(addGuest);
        else{
            frame.add(removeGuest);
        frame.add(panel);}
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
            valueLabel.setText(String.valueOf(number));
            Room.button_Color(number, true);
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            frame.add(panel);
            frame.setVisible(true);
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

