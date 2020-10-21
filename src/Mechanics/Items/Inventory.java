package Mechanics.Items;

import java.io.FileWriter;

import java.util.HashMap;


import org.json.simple.*;



/**
 * Inventory represents a HashMap of Items 
 * Items is superclass for weapons, tools, etc
 * Inventory class is not an object but rather a set of methods
 */
public class Inventory {
    
    //instance variables
    String[] items;
    String[] itemStats;
    int itemIndex = 0;

    //? inventory is just an instance variable of the class instead of any object 
    //? do we even need an object form of the class and if so what can we make static????(ie)
    //? if this was so, Item would need an implicit superconstructor
    //TODO change HashMap to type <String, Item> instead
    HashMap <Integer, Item> inventory = new HashMap<Integer, Item>();

    public Inventory (HashMap <Integer, Item> inv) {
        
    }

    public Inventory () {
    }

    
    /**
     * Passes in pointer and creates new item obj from fields of item obj, points to a new one in memory
     * @param item
     */
    public void addToInventory (Item item) {
        
            //System.out.println(item);
            //System.out.println(item.getName());
            item = new Item(item.getName());
            inventory.put(itemIndex, item);
            System.out.println("An item was added to the inventory: " + inventory.get(itemIndex).toString() + " at " + itemIndex);

            itemIndex++;  
     }

     /**
      * Passes in pointer and creates new item obj from fields of weapon obj, points to a new one in memory
      * @param weapon
      */
    public void addToInventory(Weapon weapon) {
        
        //System.out.println(weapon);
        //System.out.println(weapon.getName());
        weapon = new Weapon(weapon.getName());
        inventory.put(itemIndex, weapon);
        System.out.println("A weapon was added to the inventory: " + inventory.get(itemIndex).toString() + " at " + itemIndex);

        itemIndex++;
    }
  

    public String toString() {
        return this.inventory.toString();
    }


    public static void main(String[] args) {
        
        //instantiating INventory inv and Item test
        Inventory inv = new Inventory();
        Weapon testWeapon = new Weapon("Broadsword");

        System.out.println("\nTEST TO SHOW MEMORY ID\ninstantiated Inventory inv and Weapon testWeapon");
        System.out.println(testWeapon);

        System.out.println("\nThen testing traditional put method of testWeapon");
        inv.inventory.put(0, testWeapon);
        
        System.out.println("Inventory now is: " + inv + "\nNow time to do the add method:");
        inv.addToInventory(testWeapon);
        System.out.println("Test weapon now should be changed.\nInventory now" + inv);
        
        System.out.println("\nNow changing name of that inventory assignment");
        System.out.println("BEFORE: " + inv.inventory.get(0).getName());
        inv.inventory.get(0).setName("Newname");
        System.out.println("AFTER: " + inv.inventory.get(0).getName());

        

        
    }
}
