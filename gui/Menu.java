package gui;

import javax.management.BadStringOperationException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.prefs.Preferences;

import static javax.swing.JOptionPane.showMessageDialog;

public class Menu {
    private JPanel jpanel;
    private JPanel mainJPanel;
    private JPanel detailsJPanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JComboBox comboBox;
    private JTextField nameField;
    private JLabel priceLabel;
    private JTextField priceNet;
    private JLabel priceGrossLabel;
    private JButton addButton;
    private JButton clearButton;
    private JPanel tablePanel;
    private JTable table;
    private JLabel priceGross;
    private JScrollPane scrollPane;
    private JLabel showingId;
    private JFrame jframe;
    int i = 1;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);

    Menu() {

        /* Creating model for table in our form */
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Price");
        table.setModel(model);

        comboBox.addItem("Soups");
        comboBox.addItem("Appetizers");
        comboBox.addItem("Pizza");
        comboBox.addItem("Fast Food");
        comboBox.addItem("Drinks");




        jframe = new JFrame("Restaurant Menu");

        jframe.setDefaultCloseOperation(2);
        jframe.setVisible(true);

        jframe.add(jpanel);
        jframe.setBounds(200, 200, 600, 400);
        scrollPane.setViewportView(table);

        showingId.setText(String.valueOf(i));


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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(nameField.getText().equals("") || priceNet.getText().equals("") || !PriceNetToGross()){
                    showMessageDialog(null,"Field cannot be empty!");
                }
                else{
                    String index = String.valueOf(i);
                    String name = nameField.getText();
                    String type = comboBox.getSelectedItem().toString();
                    Double price = Double.parseDouble(priceNet.getText());
                    String[] data = {index, name, type, priceNet.getText()};
                    try {
                        PrintWriter savingToFile = new PrintWriter(new FileWriter("data.dat", true));
                        savingToFile.println(index + "|" + name + "|" + type + "|" + price);
                        savingToFile.close();
                    }catch(IOException c){
                        showMessageDialog(null, "Problem with a file opening!");
                    }

                    i++;
                    showingId.setText(String.valueOf(i));
                    model.addRow(data);

                }

            }
        });
    }

    private boolean PriceNetToGross(){
        try {
            double priceNets = Double.parseDouble(priceNet.getText());
            double tax = Double.parseDouble(pref.get("tax", "root"));
            tax = (100+tax)/100;
            double priceGrosss = priceNets * tax;
            priceGross.setText(String.valueOf(priceGrosss));
            return true;
        } catch (NumberFormatException f) {
            priceGross.setText("Wrong input in Price Net field!");
            return false;
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}