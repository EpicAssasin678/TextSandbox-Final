package Mechanics.items;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.parser.*;

/**
 * Item Class
 * 
 * @author Zachery Uporsky aka darkf0x
 * @version 0.2 
 * @see
 * TODO fix the implementation of the class, make the functions of each to descend from it's child classes
 * 
 */

public class Item implements ItemInterface {
    
    //instance varaibles
    public String identifier;
    public String name;
    public String type;
    public String description;

    public JSONParser parser;
    public Scanner userPrompt;
    
    //this will be the Weapon that the item object actually represents as an instance of the object class 
    public Weapon pointedWeapon;
    
    public Item (String name) {
        
        this.name = name;
        
    }

    public Item (String name, String type) {
        this.name = name;
        this.type = type;

    }
    
    public Item (String name, String type, String description) {
        this.description = description;
        this.name = name;
        this.type = type;
        
    }

    public Item (String name, boolean isWeapon) {
        this.name = name;
        
    }
    
    public Item () {
    }

    //getters and setters 
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String set) {
        this.type = set;
    }

    //because of the extension of ItemInterface
    public ItemType specifyItemType() {
        return ItemType.UNDEFINED;
    }

    public String checkClass() {
        if (this.getClass().toString() == "Item") {
            return "Item";
        } else {
            return "Weapon";
        }
    }

    public Item clone() {
        return this;
    }


    /**
     * Initializes a weapon item via the initWeapon() method from the WEapon class
     * @param weapon
     */

    public static void main(String[] args) {
        Item i = new Weapon();

        try {
            System.out.println((Object) i.getClass());
            i.setName("Broadsword");

            String currentClassStr = i.getClass().toString();
            Item test = new Item("Broadsword", "Weapon");

            System.out.println(test.getType());
            //test.createTypeObj();
            Weapon testBroadSword = test.pointedWeapon;
            testBroadSword.generateAttackMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    
}



    /**
    //!DELETE
    public void createTypeObj () {

        ItemType comparedItem = compareType();

        switch (comparedItem) {
            case WEAPON:
            //set thte instance of the item object 
            //transfers Item instance field to Weapon instance field
            
            System.out.println("CASE WEAPON FOUND");
            if (this.name != null) {
                this.pointedWeapon = new Weapon(this.name);
                System.out.println("Item " + this.name + " pointedWeapon has been set to " + this.pointedWeapon.toString());
                if (this.identifier != null) {
                }
            }

        }

    }
    */