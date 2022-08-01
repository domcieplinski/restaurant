package gui;

import javax.swing.*;

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
    private JTextField textField2;
    private JLabel priceGrossLabel;
    private JButton addButton;
    private JButton clearButton;
    private JPanel tablePanel;
    private JTable table;
    private JFrame jframe;

    Menu() {
        jframe = new JFrame("Testujemy Menu");
        jframe.setDefaultCloseOperation(2);
        jframe.setVisible(true);
        jframe.add(jpanel);
        jframe.setBounds(200,200,600,400);
    }
}