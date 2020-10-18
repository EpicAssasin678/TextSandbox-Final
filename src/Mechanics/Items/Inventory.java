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
    HashMap <Integer, Item> inventory = new HashMap<Integer, Item>();
    

    public Inventory (HashMap <Integer, Item> inv) {
        
    }

    public Inventory () {
    }


    public void addToInventory (Item item) {
        
            System.out.println(item);
            System.out.println(item.getName());

            item = new Item(item.getName());
            inventory.put(itemIndex, item);
            System.out.println("An item was added to the inventory: " + inventory.get(itemIndex).toString() + " at " + itemIndex);

            itemIndex++;
        
            
     }

    public void addToInventory(Weapon weapon) {
        
        System.out.println(weapon);
        System.out.println(weapon.getName());

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
        Item test = new Item("Broadsword");
        Weapon testWeapon = new Weapon("Broadsword");

        //printing both the test and the inv out
        System.out.println(test);
        System.out.println(inv);
        System.out.println(test.name);
        //inv.inventory.put(1, test);
        inv.addToInventory(test);
        System.out.println(inv.inventory);


        inv.inventory.put(1, test);
        System.out.println(inv.inventory);

        System.out.println(inv.inventory.get(1));
        inv.inventory.get(1).getName();

        System.out.println("\nTEST WITH WEAPONS");
        System.out.println(testWeapon);
        
        inv.inventory.put(2, testWeapon);
        System.out.println(inv.inventory);
        //tseting with a weapon
        inv.addToInventory(testWeapon);

        System.out.println(inv.inventory);
        
    }
}
