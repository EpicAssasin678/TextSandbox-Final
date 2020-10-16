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

public class Item {
    
    //instance varaibles
    public String identifier;
    public String name;
    public String type;
    public String description;

    public JSONParser parser;
    public Scanner userPrompt;
    
    
    public Item (String identifier, String name) {
        
        this.identifier = identifier;
        this.name = name;
        
    }

    
    public Item (String identifier, String name, String type) {
        
        this.identifier = identifier;
        this.name = name;
        this.type = type;

    }
    
    
    public Item (String identifier, String name, String type, String description) {
        this.description = description;
        this.identifier = identifier;
        this.name = name;
        this.type = type;
        
    }

    public Item (String name, boolean isWeapon) {
        this.name = name;
        
    }
    
    public Item (String name) {
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

    public String checkClass() {
        if (this.getClass().toString() == "Item") {
            return "Item";
        } else {
            return "Weapon";
        }
    }

    public void printItemProperties(String key, String properties) {

    }

    /**
     * Initializes a weapon item via the initWeapon() method from the WEapon class
     * @param weapon
     */
    public void initItem (Weapon weapon) {

        weapon.initWeapon(weapon.getName());

    }

    public Weapon toWeapon (Item item) {
        Weapon weapon = new Weapon(item.name);
        return weapon;
    }

    public static void main(String[] args) {
        Item i = new Weapon();

        try {
            System.out.println((Object) i.getClass());
            i.setName("Broadsword");

            


            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

    
}
