package gui;

import gui.pdf.PdfGenerator;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static gui.Guest.guest;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showMessageDialog;

public class NewOrder  {
    private JPanel panel;
    private JTree menuTree;
    private JTable tableOrderedFood;
    private JLabel titleLabel;
    private JButton addGuest = new JButton("Add Guest");
    private JButton removeFromOrder;
    private JButton addToOrder;
    private JLabel valueLabel;
    private JButton removeClient;
    private JButton completeOrder;
    public JFrame frame;
    private JScrollPane scrollPane;
    private JLabel chosenItem;

    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<String[] > menuList = new ArrayList<String[] >();
    ArrayList<String[] > orderList = new ArrayList<>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private int foundItems;
    private String data[];
    int i = 0;
    double sum = 0;
    String[] finalData;

    public NewOrder(int number) {

        frame = new JFrame("Table " + (number + 1));
        frame.setBounds(100, 100, 750, 500);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        valueLabel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(1,1,1,1, Color.WHITE),
                BorderFactory.createEmptyBorder(39,79,39,79)));

        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Menu");
        createNodes(treeNode);
        menuTree = new JTree(treeNode);


        if (guest[number] == null)
            frame.add(addGuest);
        else{
            frame.add(addToOrder);
        frame.add(panel);}
        frame.setVisible(true);



        addGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGuest(number);
            }
        });

        removeClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeClient(number);

            }
        });

        completeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to confirm this order?","Warning", YES_NO_OPTION );
                if(dialogResult == JOptionPane.YES_OPTION){
                    MainView.orderCounter++;
                    PdfGenerator pdfGenerator = new PdfGenerator(sum, MainView.orderCounter, orderList);
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("newssss.pdf");
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            // no application registered for PDFs
                        }
                    }

                    removeClient(number);
                    orderList.clear();

                }

            }
        });
        removeFromOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableOrderedFood.getSelectedRow() != -1){
                    //Remove selected row from jTable
                    double valueToRemove = Double.parseDouble(orderList.get(tableModel.getColumnCount())[2]);
                    orderList.remove(tableOrderedFood.getSelectedRow());
                    tableModel.removeRow(tableOrderedFood.getSelectedRow());
                    sum = sum - valueToRemove;
                    valueLabel.setText(String.valueOf(sum));

                    showMessageDialog(null, "Record deleted!");
                }
            }
        });
    }
        public void newGuest(int number) {
            guest[number] = new Guest(number);
            titleLabel.setText("New order - Table " + (number+1));
            valueLabel.setText(String.valueOf(sum));
            Room.button_Color(number, true);
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            frame.add(panel);
            frame.setVisible(true);

        }

        private void removeClient(int number){
            removeGuest(number);
            sum = 0;
            tableModel.setRowCount(0);
            frame.dispose();
        }

        private void createNodes(DefaultMutableTreeNode treeNode){
            DefaultMutableTreeNode pizza;
            DefaultMutableTreeNode appetizers;
            DefaultMutableTreeNode soups;
            DefaultMutableTreeNode fastFood;
            DefaultMutableTreeNode drinks;
            DefaultMutableTreeNode items;

            String line;


            scrollPane.setViewportView(tableOrderedFood);
            tableModel.addColumn("Name");
            tableModel.addColumn("Price");
            tableOrderedFood.setModel(tableModel);




            soups = new DefaultMutableTreeNode("Soups");
            treeNode.add(soups);
            appetizers = new DefaultMutableTreeNode("Appetizers");
            treeNode.add(appetizers);
            pizza = new DefaultMutableTreeNode("Pizza");
            treeNode.add(pizza);
            fastFood = new DefaultMutableTreeNode("Fast Food");
            treeNode.add(fastFood);
            drinks = new DefaultMutableTreeNode("Drinks");
            treeNode.add(drinks);

            try {
                BufferedReader readingMenu = new BufferedReader(new FileReader("data.dat"));
                lines.add(readingMenu.readLine());
                i = Integer.parseInt(lines.get(0));

                for(int y = 1; y <= i; y++){
                    lines.add(readingMenu.readLine());
                    StringTokenizer token = new StringTokenizer(lines.get(y), "|");
                    String[] word = {token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()};
                    items = new DefaultMutableTreeNode(word[1]);
                    menuList.add(word);
                    switch(word[2]){
                        case "Pizza":
                            pizza.add(items);
                            break;
                        case "Soups":
                            soups.add(items);
                            break;
                        case "Fast Food":
                            fastFood.add(items);
                            break;
                        case "Drinks":
                            drinks.add(items);
                            break;
                        case "Appetizers":
                            appetizers.add(items);
                            break;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            catch (NoSuchElementException f){
                System.out.println("No such element.");
            }

            DefaultTreeModel model =(DefaultTreeModel) menuTree.getModel();
            model.setRoot(treeNode);

            
            menuTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {

                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    TreePath treePath = e.getPath();


                    /* Every time I use again action listener it adds new item to the list.
                                   Below I'm clearing all of Action Listeners.
                    */
                    for(ActionListener deleter : addToOrder.getActionListeners()) {
                        addToOrder.removeActionListener(deleter);
                    }
                    int size = e.getNewLeadSelectionPath().getPathCount();
                    if(size == 3){
                        foundItems = findItems(String.valueOf(treePath.getLastPathComponent()));
                        String[] data  = {menuList.get(foundItems)[1], menuList.get(foundItems)[3]};
                        chosenItem.setText(data[0] + " " + data[1]);
                        addToOrder.addActionListener(new ActionListener()  {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                addToOrder(data);
                            }
                        });



                    }
                }
            });
        }
        private void addToOrder(String[] data){
            sum = sum + Double.parseDouble(menuList.get(foundItems)[3]);
            valueLabel.setText(String.valueOf(sum));
            tableModel.addRow(data);
            String one_name = menuList.get(foundItems)[1];
            String two_type = menuList.get(foundItems)[2];
            String three_price = menuList.get(foundItems)[3];
            String[] newList = {one_name, two_type, three_price};
            orderList.add(newList);
        }

        private int findItems(String name){
            for(int x = 0; x < menuList.size(); x++){
                if(menuList.get(x)[1] == name)
                {
                    return x;
                }
            }
            return 0;
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

