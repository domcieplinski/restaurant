package gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Guest.guest;

public class NewOrder {
    private JPanel panel;
    private JTree menuTree;
    private JTable table1;
    private JLabel titleLabel;
    private JButton addGuest  = new JButton("Add Guest");;
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
        //frame.add(menuTree);
        //JScrollPane treeView = new JScrollPane(menuTree);

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
            DefaultMutableTreeNode category = null;
            DefaultMutableTreeNode items = null;

            category = new DefaultMutableTreeNode("Pizza");
            treeNode.add(category);
            items = new DefaultMutableTreeNode("Test");
            category.add(items);

            category = new DefaultMutableTreeNode("Apperitif");
            treeNode.add(category);


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

