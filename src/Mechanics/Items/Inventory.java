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
    JSONObject[] references;
    JSONArray[] invarray;
    int itemIndex = 0;

    //? inventory is just an instance variable of the class instead of any object 
    //? if this was so, Item would need an implicit superconstructor
    HashMap <Integer, Item> inv = new HashMap<Integer, Item>();
    

    public Inventory (HashMap <Integer, Item> inv) {
        this.inv = inv;
    }

    public Inventory () {
    }

    public HashMap inventory () {
        return inv;
     }

     public void addToInventory (Item item) {
        
            System.out.println(item);
            System.out.println(item.getName());

            item = new Item(item.getName());
            inv.put(itemIndex, item);
            itemIndex++;
        
            System.out.println("An item was added to the inventory: " + inv.get(itemIndex).toString() + "at " + itemIndex);
     }

    public void addToInventory(Weapon weapon) {
        
        System.out.println(weapon);
        System.out.println(weapon.getName());

        weapon = new Weapon(weapon.getName());
        inv.put(itemIndex, weapon);
        itemIndex++;
        
        System.out.println("A weapon was added to the inventory: " + inv.get(itemIndex).toString() + "at " + itemIndex);
    }
  
    int writeCount;
    public static void writeItem(String itemName, Integer damage) {

        JSONObject createdObject = new JSONObject();
        createdObject.put("Damage", Integer.toString(damage));
        
        JSONObject writtenList = new JSONObject();
        writtenList.put(itemName, createdObject);

        
        //writes the file 
        try (FileWriter file = new FileWriter("C:\\Users\\epicd\\OneDrive\\Documents\\Java Projects\\TextSandbox\\src\\Bin\\Json\\Items\\Items.JSON")){
            
            file.write(writtenList.toString());
    
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public String toString() {
        return this.inv.toString();
    }


    public static void main(String[] args) {
        
        //instantiating INventory inv and Item test
        Inventory inv = new Inventory();
        Item test = new Item("Broadsowrd");

        //printing both the test and the inv out
        System.out.println(test);
        System.out.println(inv);
        System.out.println(test.name);
        test.initItem(test.toWeapon(test));

        
        
        
    }
}
