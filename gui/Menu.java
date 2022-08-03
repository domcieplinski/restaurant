package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.prefs.Preferences;

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
    private JLabel priceGross;
    private JScrollPane test;
    private JFrame jframe;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);

    Menu() {

        /* Creating model for table in our form */
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Price");
        table.setModel(model);

        comboBox1.addItem("Soups");
        comboBox1.addItem("Appetizers");
        comboBox1.addItem("Pizza");
        comboBox1.addItem("Fast Food");
        comboBox1.addItem("Drinks");

        jframe = new JFrame("Restaurant Menu");

        jframe.setDefaultCloseOperation(2);
        jframe.setVisible(true);

        jframe.add(jpanel);
        jframe.setBounds(200, 200, 600, 400);
        test.setViewportView(table);
        

        /* Adding KeyListener for Enter. Inserting new values in "Price net" and clicking Enter
           causes that there automatically appear value in "Price gross"
        */
        priceNet.addFocusListener(new FocusListener() {
                                      @Override
                                      public void focusGained(FocusEvent e) {
                                      }

                                      @Override
                                      public void focusLost(FocusEvent e) {
                                          PriceNetToGross();
                                      }
                                  });
                priceNet.addKeyListener(new KeyListener() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            PriceNetToGross();
                        }
                    }

                    public void keyReleased(KeyEvent e) {
                        //code.
                    }

                    public void keyTyped(KeyEvent e) {
                        //code.
                    }
                });


        // comboBox1.addActionListener(this);
    }

    private void PriceNetToGross(){
        try {
            double priceNets = Double.parseDouble(priceNet.getText());
            double tax = Double.parseDouble(pref.get("tax", "root"));
            tax = (100+tax)/100;
            double priceGrosss = priceNets * tax;
            priceGross.setText(String.valueOf(priceGrosss));
        } catch (NumberFormatException f) {
            priceGross.setText("Wrong input in Price Net field!");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}