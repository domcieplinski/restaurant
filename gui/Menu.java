package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu {
    private JPanel jpanel;
    private JPanel mainJPanel;
    private JPanel detailsJPanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JLabel priceLabel;
    private JTextField priceNet;
    private JLabel priceGrossLabel;
    private JButton addButton;
    private JButton clearButton;
    private JPanel tablePanel;
    private JTable table;
    private JFrame jframe;

    Menu() {

        comboBox1.addItem("Soups");
        comboBox1.addItem("Appetizers");
        comboBox1.addItem("Pizza");
        comboBox1.addItem("Fast Food");
        comboBox1.addItem("Drinks");
        jframe = new JFrame("Testujemy Menu");
        jframe.setDefaultCloseOperation(2);
        jframe.setVisible(true);
        jframe.add(jpanel);
        jframe.setBounds(200,200,600,400);


        /* Adding KeyListener for Enter. Inserting new values in "Price net" and clicking Enter
           causes that there automatically appear value in "Price gross"
        */
        
        priceNet.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e)
            {

                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    System.out.println("Test");
                }
            }
            public void keyReleased(KeyEvent e)
            {
                //code.
            }
            public void keyTyped(KeyEvent e)
            {
                //code.
            }});




       // comboBox1.addActionListener(this);
    }
}