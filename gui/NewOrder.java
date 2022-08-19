package gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import static gui.Guest.guest;

public class NewOrder {
    private JPanel panel;
    private JTree menuTree;
    private JTable table1;
    private JLabel titleLabel;
    private JButton addGuest  = new JButton("Add Guest");
    private JButton removeGuest;
    private JLabel valueLabel;
    private JButton removeClient;
    private JButton button2;
    public JFrame frame;

    public NewOrder(int number) {

        frame = new JFrame("Table " + (number + 1));
        frame.setBounds(100, 100, 600, 600);
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
            frame.add(removeGuest);
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
                removeGuest(number);
            }
        });
    }
        public void newGuest(int number) {
            guest[number] = new Guest(number);
            titleLabel.setText("New order - Table " + (number+1));
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

        private void createNodes(DefaultMutableTreeNode treeNode){
            DefaultMutableTreeNode pizza;
            DefaultMutableTreeNode appetizers;
            DefaultMutableTreeNode soups;
            DefaultMutableTreeNode fastFood;
            DefaultMutableTreeNode drinks;
            DefaultMutableTreeNode items;
            String line;


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
                line = readingMenu.readLine();
                int i = Integer.parseInt(line);

                for(int y = 0; y < i; y++){
                    line = readingMenu.readLine();
                    StringTokenizer token = new StringTokenizer(line, "|");
                    String[] word = {token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken()};
                    items = new DefaultMutableTreeNode(word[1]);
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

            DefaultTreeModel model =(DefaultTreeModel) menuTree.getModel();
            model.setRoot(treeNode);
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

