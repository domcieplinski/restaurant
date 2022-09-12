package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
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
    private JButton deleteSelectedButton;
    static JFrame frame = new JFrame("Restaurant Menu");;
    int i = 1;
    int test = 0;
    int official = 1;
    Preferences pref = Preferences.userNodeForPackage(Settings.class);

    Menu() {



        /* Creating model for table in our form */
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Price");
        table.setModel(model);
        ArrayList<String> line = new ArrayList<String>();

        /* Opening file to check what's the size and saving to array */
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data.dat"));
            String firstLine = bufferedReader.readLine();
            i = Integer.parseInt(firstLine);
            official = i+1;
            boolean status = true;

            /* Showing data in table */
            for(int y = 0; y < i; y++){
                line.add(bufferedReader.readLine());
                StringTokenizer token = new StringTokenizer(line.get(y), "|");
                String[] data = {token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()};
                if((Integer.parseInt(data[0]) - test) != 1 && status == true){
                    test = Integer.parseInt(data[0])-1;
                    status = false;
                }
                else
                    test = Integer.parseInt(data[0]);


                model.addRow(data);
            }
        }
        catch(NullPointerException a){
            showMessageDialog(null, "Menu is empty.");
        }
        catch(FileNotFoundException b){
            showMessageDialog(null, "File data.dat not found!");
        }
        catch(IOException c) {
           showMessageDialog(null, "File is empty!");
        }
        catch(NumberFormatException d){
            i = 0;
        }


        comboBox.addItem("Soups");
        comboBox.addItem("Appetizers");
        comboBox.addItem("Pizza");
        comboBox.addItem("Fast Food");
        comboBox.addItem("Drinks");

        frame.setDefaultCloseOperation(2);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            /* Saving data in file */
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    Collections.sort(line, new NumericalStringComparator());
                    PrintWriter savingToFile = new PrintWriter(new FileWriter("data.dat", false));
                    savingToFile.append(String.valueOf(i));
                    for(int z = 0; z < i; z++){
                        savingToFile.append('\n');
                        savingToFile.append(line.get(z));

                    }
                    savingToFile.close();
                }catch(IOException c){
                    showMessageDialog(null, "Problem with a file opening!");
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


        frame.add(jpanel);
        frame.setBounds(200, 200, 600, 400);
        scrollPane.setViewportView(table);
        System.out.println("offical test to  :" + official);

        showingId.setText(String.valueOf(official));


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


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(nameField.getText().equals("") || priceNet.getText().equals("") || !PriceNetToGross()){
                    showMessageDialog(null,"Field cannot be empty!");
                }
                else{
                    i++;
                    String index = String.valueOf(official);
                    String name = nameField.getText();
                    String type = comboBox.getSelectedItem().toString();
                    Double price = Double.parseDouble(priceNet.getText());
                    String[] data = {index, name, type, priceNet.getText()};

                    line.add(index + "|" + name + "|" + type + "|" + price);
                    official++;

                    showingId.setText(String.valueOf(i+1));

                    model.addRow(data);
                    cleanFields();

                }

            }
        });
        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1){
                    //Remove selected row from jTable
                    line.remove(table.getSelectedRow());
                    model.removeRow(table.getSelectedRow());
                    i--;

                    showMessageDialog(null, "Record deleted!");
                }
            }
        });
    }

    private void cleanFields(){
        nameField.setText("");
        priceNet.setText("");
        priceGross.setText("");
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

/* Arraylist is String, so I made my own comparator,
   because it has to be sorted by the ID (which is integer).
*/
class NumericalStringComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        int i1 = Integer.parseInt(o1.split("\\D")[0]);
        int i2 = Integer.parseInt(o2.split("\\D")[0]);
        int cmp = Integer.compare(i1, i2);
        if (cmp != 0) {
            return cmp;
        }
        return o1.compareTo(o2);
    }
}