package gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static gui.Guest.guest;

public class NewOrder  {
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

    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<String[] > menuList = new ArrayList<String[] >();

    int i = 0;
    double sum = 0;

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
                sum = 0;
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

            Map <String, List<String>> map = new Map<String, List<String>>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public List<String> get(Object key) {
                    return null;
                }

                @Override
                public List<String> put(String key, List<String> value) {
                    return null;
                }

                @Override
                public List<String> remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends List<String>> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<List<String>> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, List<String>>> entrySet() {
                    return null;
                }
            };
            try {
                BufferedReader readingMenu = new BufferedReader(new FileReader("data.dat"));
                lines.add(readingMenu.readLine());
                i = Integer.parseInt(lines.get(0));

                for(int y = 1; y < i; y++){
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
                    int size = e.getNewLeadSelectionPath().getPathCount();
                    if(size == 3){
                        String test = String.valueOf(treePath.getLastPathComponent());
                        int foundItems = findItems(test);
                        sum = sum + Double.parseDouble(menuList.get(foundItems)[3]);
                        valueLabel.setText(String.valueOf(sum));
                        System.out.println(menuList.get(foundItems)[2]);
                    }
                }

            });

        }

        public int findItems(String name){
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

