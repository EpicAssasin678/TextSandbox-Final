package Mechanics.items;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

import org.json.simple.*;

import Characters.Character;

/**
 * Inventory is a structure meant to hold instances of class Item. Inventory holds items through ArrayLists 
 * of differing class of Item.
 * 
 * 
 */
public class Inventory  {
    int itemIndex = 0;
    boolean needsUpdate = false;

    //already methods within HashMap, but carrying over
    int size = 0;
    int maxSize;
    
    //instance variables
    //public HashMap <String, Item> inventory = new HashMap<String, Item>();
    public ArrayList<Item> itemInv = new ArrayList<Item>();
    public ArrayList<Weapon> weaponInv = new ArrayList<Weapon>();
    public ArrayList<Armor> armorInv = new ArrayList<Armor>();
    public ArrayList<Potion> potionInv = new ArrayList<Potion>();
    ItemType[] typeMap;
    
    
    

    //? inventory is just an instance variable of the class instead of any object 
    //? do we even need an object form of the class and if so what can we make static????(ie)
    //? if this was so, Item would need an implicit superconstructor
    //TODO create a text menu for viewing items
    

    public Inventory  () {
        //this.maxSize = 20;
        this.setSize(20);
    }

    public Inventory (int maxsize) {
        //this.maxSize = maxsize;  
        //this.typeMap = new ItemType[maxsize];    
        this.setSize(maxSize);  
    }

    //resizes both the size and typeMap array
    public void setSize(int size) {
        this.typeMap = new ItemType[size];
        this.maxSize = size;
    }

     /**
      * Passes in pointer and creates new item obj from fields of weapon obj, points to a new one in memory
      * @param weapon
      */
    public void addToInventory(Weapon weapon) {
        if(this.size != maxSize) {
        System.out.println("Adding weapon to inventory.");
        weaponInv.add(weapon);
        needsUpdate = true;
        //updateTypeMap();
        size++;
        }
    }

    public void addToInventory(Potion potion) {
        if(this.size != maxSize) {
        System.out.println("Adding potion to inventory.");
        potionInv.add(potion);
        needsUpdate = true;
        //updateTypeMap();
        size++;
        }
    }

    public void addToInventory(Armor armor) {
        if(this.size != maxSize) {
        System.out.println("Adding armor to inventory.");
        armorInv.add(armor); 
        needsUpdate = true;
        //updateTypeMap();
        size++;
        }
    }

    public String getWeaponList() {
        int cout = 1;
        String temp = String.format("%30s", "Weapons\n") + "|   Slot   |        Item Name      |   Type   |";
        for(Weapon weapon: this.weaponInv) {
            temp += "\n" + String.format("%8s", "[" + cout + "] ") + String.format("%25s", "\"" + weapon.getName() + "\"" + String.format("%11s","WEAPON"));
            cout++;
        }
        return temp;
    }

    public void updateTypeMap() {
        int cout = 0;
        if (needsUpdate) {
            System.out.println("Updating typeMap.\nUpdating weapons");
            if (weaponInv.size() > 0) for(Weapon weapon: weaponInv) {
                typeMap[cout] = ItemType.WEAPON; cout++;
            }
            System.out.println("Updating for armor.");
            if (armorInv.size() > 0) for(Armor armor: armorInv) {
                typeMap[cout] = ItemType.ARMOR; cout++;
            }
            System.out.println("Updating for potions.");
            if (potionInv.size() > 0) for(Potion potion: potionInv) {
                typeMap[cout] = ItemType.POTION; cout++;
            }
            needsUpdate = false;
        }
    }

    //TODO make a method of retrieving items that doesn't alter type
    //if I was in Python this wouldn't be an issue
        
    public Item get(int pos) {
        if(pos  <= weaponInv.size()) {
            return weaponInv.get(pos);
        }
        if(pos  <= weaponInv.size() + armorInv.size()) {
            return armorInv.get(pos);
        } 
        if (pos  <= weaponInv.size() + armorInv.size() + potionInv.size()) {
            return potionInv.get(pos);
        }else {
            return itemInv.get(pos);
        }
    }

    /**
     * Used for determining which functions to call upon in menus and other game mechanics. Updates typeMap before returning.
     * @param pos
     * @return ItemType of Item in inventory at position pos
     */
    public ItemType getFromTypeMap (int pos) {
        System.out.println("Getting type from typemap at: " + pos );
        if (needsUpdate) {
            this.updateTypeMap(); 
        }
        return typeMap[pos];
    }

    public ItemType[] getTypeMap() {
        return this.typeMap;
    }

    public String toString() {
        return this.toString();
    }


    public void displayInventoryMenu() {
        System.out.println("PRINTING INVENTORY MENU: ");
        int cout = 1;
        System.out.println(String.format("%30s", "Inventory Menu\n") + "|   Slot   |        Item Name      |   Type   |");
        for(Weapon weapon: this.weaponInv) {
            System.out.println(String.format("%8s", "[" + cout + "] ") + String.format("%25s", "\"" + weapon.getName() + "\"") + String.format("%11s","WEAPON"));
            cout++;
        }
        for(Armor armor: this.armorInv) {
            System.out.println(String.format("%8s", "[" + cout + "] ") + String.format("%25s", "\"" + armor.getName() + "\"")  + String.format("%11s","ARMOR"));
            cout++;
        }   
        for(Potion potion: this.potionInv) {
            System.out.println(String.format("%8s", "[" + cout + "] ") +  String.format("%25s", "\"" + potion.getName() + "\"")  + String.format("%11s","POTION"));
            cout++;
        }
        for(Item keyItem: this.itemInv) {
            System.out.println(String.format("%8s", "[" + cout + "] ") + String.format("%25s", "\"" + keyItem.getName() + "\"")  + String.format("%11s","ITEM"));
            cout++;
        }
    }

    static String getViewString(Weapon weapon) {
        return weapon.getName() + "\nAttacks:" + weapon.printAttackMap() + "Description: " + weapon.getDescription();
    }

    /**
     * Utility class for returning items of the inventory without loss in conversion of classes. 
     */
    static class InventoryUtility <T> {
        
        Inventory referencedInventory;

        public InventoryUtility (Inventory inv){
            referencedInventory = inv;
        }

        public T getFromInventory(int pos) {
            return null;
        }

    }
    

    public static void main(String[] args) {
        
        Character PLAYER = new Character("Gladius", 1, 0, 100, new Weapon("Beginners Dagger"), new Armor());
        PLAYER.characterInventory.addToInventory(new Weapon("Begginners Dagger"));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
      
        PLAYER.characterInventory.displayInventoryMenu();
        PLAYER.characterInventory.updateTypeMap();
        System.out.println(PLAYER.characterInventory.getTypeMap().toString());
        
    }
}


    