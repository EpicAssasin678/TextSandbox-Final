package Mechanics.Items;

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

    //! getter and setters below
    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String set) {
        this.identifier = set;
    }

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
    public ItemType specifyItemTypeOfClass() {
        return ItemType.UNDEFINED;
    }

    public String checkClass() {
        if (this.getClass().toString() == "Item") {
            return "Item";
        } else {
            return "Weapon";
        }
    }

    //sets the pointedWeapon or Item and holds it within the instance of this item
    //TODO make this work for every type of weapon
    public ItemType compareType () {

        if (this.type == "Weapon" || this.type == "weapon" || this.type == "WEAPON") {
            setType("Weapon");
            return ItemType.WEAPON;
        }
        else {

            System.out.println("ERROR: TYPE HAS BEEN RESOLVED TO UNDEFINED, PLEASE ENTER ACCEPTABLE FORMS");
            return ItemType.UNDEFINED;  
        }
    }

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
            test.createTypeObj();
            Weapon testBroadSword = test.pointedWeapon;
            testBroadSword.generateAttackMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    
}
