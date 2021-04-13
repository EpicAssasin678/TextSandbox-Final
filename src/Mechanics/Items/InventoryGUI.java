package Mechanics.items;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.SpringLayout.Constraints;




public class InventoryGUI {


    protected JComponent r;
    protected JTextArea ref;

    private static JLabel[] generateInventoryLabels (Inventory inv) {

        JLabel[] keyLabels = new JLabel[inv.size];
        //String [] keyArr = (String[]) inv.inventory.keySet().toArray();

        for (int i = 0 ; i < inv.size; i++ ) {
            keyLabels[i] = new JLabel( (String) inv.inventory.keySet().toArray()[i]);

        }
        System.out.println(keyLabels);
        return keyLabels;
    }

    
    private static void displayInventory(Inventory inv) {
        
        //create frame 
        JFrame frame = new JFrame("Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create menu bar 
        JMenuBar mainMenu = new JMenuBar();
        mainMenu.setOpaque(true);
        mainMenu.setBackground(Color.lightGray);
        mainMenu.setPreferredSize(new Dimension(200, 400));
        mainMenu.setBounds(200, 300, 10 ,20);
        //JPanel mainPanel = new JPanel();

        //main label
        JLabel mainLabel = new JLabel();
        mainLabel.setOpaque(true);        
        mainLabel.setBackground(new Color(100, 140, 150));
        mainLabel.setPreferredSize(new Dimension(200, 100));

        //

        //create ScrollPane
        JScrollPane inventoryContentPane = new JScrollPane(mainLabel);
        //JScrollPane config
        inventoryContentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inventoryContentPane.setPreferredSize(new Dimension (100, 200));
        inventoryContentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        

        //add Label to frame
        frame.setJMenuBar(mainMenu);
        frame.getContentPane().add(mainLabel, BorderLayout.CENTER);
        


        //display the window 
        frame.pack();
        frame.setVisible(true);

    }
    

    private static  void test() {
                //Create and set up the window.
                JFrame frame = new JFrame("TopLevelDemo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                //Create the menu bar.  Make it have a green background.
                JMenuBar greenMenuBar = new JMenuBar();
                greenMenuBar.setOpaque(true);
                greenMenuBar.setBackground(Color.RED);
                greenMenuBar.setPreferredSize(new Dimension(200, 20));
        
                //Create a yellow label to put in the content pane.
                JLabel yellowLabel = new JLabel();
                yellowLabel.setOpaque(true);
                yellowLabel.setBackground(new Color(248, 13, 131));
                yellowLabel.setPreferredSize(new Dimension(200, 180));

            
        
                //Set the menu bar and add the label to the content pane.
                frame.setJMenuBar(greenMenuBar);
                frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
        
                //Display the window.
                frame.pack();
                frame.setVisible(true);


    }
    

    public static void main(String[] args) {

        
        Inventory inv = new Inventory();
        inv.addToInventory(new Weapon("Broadsword"));
        inv.addToInventory(new Weapon("Scabbard"));
        System.out.println();
        
        
        System.out.println(InventoryGUI.generateInventoryLabels(inv));
        
        InventoryGUI.displayInventory(inv);
        //InventoryGUI.test();
        
    }
}
