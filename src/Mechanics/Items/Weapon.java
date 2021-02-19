package Mechanics.items;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.border.Border;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Weapon class:
 *  Represents a weapon object with fields for name, description, identifier, etc.
 *  Has implementation for the Item superclass. 
 * @author Zachery Uporsky aka darkf0x
 * @version 0.2
 */
public class Weapon extends Item {
    
    //obj instance variables
    String identifier;
    String name;
    String description;
    
    //parser var
    JSONParser parser = new JSONParser();
    Weapon referenced;

    ArrayList <String> attackNames = new ArrayList<String>();
    ArrayList <Integer> attackValues = new ArrayList<Integer>();
    HashMap <String, Integer> attackMap = new HashMap<String, Integer>(); 
    
    boolean weaponHasGenerated = false;

    //TODO implement methods to parent class, so that other items can use the functionality
    //TODO check the type of object via the checkClass() method
    

    public Weapon (String name, String identifier) {
        super(name, identifier);
        initWeapon(name);
    }

    public Weapon (String name) {
        super(name);
        this.name = name;
        initWeapon(name);
    }
    
    /**
     * Default blank weapon constructor
     */
    public Weapon () {
        
    }

    //because of the extension of itemInterface
    public ItemType specifiedItemType() {
        return ItemType.WEAPON;
    }

    public void generateAttacks(JSONArray arr) {
        
        for (int i = 0; i < arr.size(); i++) {

            JSONObject obj = (JSONObject) arr.get(i);
            String objStr = (String) obj.get("name");
            attackNames.add(objStr);
            System.out.println(attackNames);
            
            long objInt = (Long) obj.get("damage");
            
            attackValues.add((int) objInt);
            System.out.println(attackValues);
        }
    }

    /**
     * Generates the attackMap HashMap of the Weapon instance by adding the values of attackNames and attackValues at the respective indexes
     * 
     * @return HashMap attackMap
     */
    public HashMap generateAttackMap () {

        System.out.println("generateAttackMap() called");
        try {
            for (int i = 0; i < attackNames.size(); i++) {
                attackMap.put(attackNames.get(i), attackValues.get(i));
                System.out.println(attackMap);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attackMap;
    }

    /**
     * Prints out a summary view of the weapons attacks and respective damage.
     * @return String build
     */
    public String printAttackMap () {

        String build = "";
        build += "\n" + super.name + "'s Attacks:\n";
        build += "-------------------------------------------------------------\n";
        //make a foreach loop to a String and concatenate it
        for (String i: this.attackMap.keySet()) {
            build += "Attack: " + i + " | Damage: " + attackMap.get(i) + "HP\n";
        }
        build += "-------------------------------------------------------------\n";
        return build;
    }


    /**
     * meant only to add to the ArrayLists associated with the Weapon object
     * @see uses the generateAttacks() method
     * @param weaponName
     */

    JSONObject jWeapon;
    public void initWeapon(String weaponName) {

        System.out.println("\ninitWeapon() called\nWeapon: " + (Object) this.getClass().toString() + "@" + Integer.toHexString(this.hashCode()) + " Has been initialised.\n");
        try {

            Object weaponFile = parser.parse(new FileReader("src\\Bin\\Json\\Items\\Weapons\\Weapons.json"));
            jWeapon = (JSONObject) weaponFile;
            JSONObject pointedWeapon = (JSONObject) jWeapon.get(weaponName);
            JSONArray weaponAttacks = (JSONArray) pointedWeapon.get("Attacks");
            
            //DEBUG
            System.out.println(weaponFile);
            System.out.println(jWeapon);
            System.out.println(pointedWeapon);

            this.generateAttacks(weaponAttacks);
            this.generateAttackMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for debug screen format purposes
        System.out.println("\n");
    }

    /**
     * Prints a form view of the Item, useful for looking at changes or all values of the class.
     * @return String formStr
     */
    public String printForm() {
        String formStr = 
        "\n====================================" + 
        "\n[WEAPON DETAILS]" + "\nCLASS: " + this.getClass().getName() + "\nHASHCODE(HEXSTRING): " + "@" + Integer.toHexString(this.hashCode())  
         + "\nNAME: " + this.name + "\nIDENTIFIER: " + this.identifier + "\nATTACK NAMES TOSTRING: " +
         this.attackNames.toString() + "\nATTACK VALUES TOSTRING: "  + this.attackValues.toString() + 
         "\nATTACK MAP TOSTRING: " + this.attackMap.toString() + 
         "\n=================================\n" ;
        return formStr;
    }

    public static void main(String[] args) {

        //!TEST OF POLYMORPHISM
        Weapon broadsword = new Weapon("Broadsword");
        System.out.println(broadsword.checkClass());

        //System.out.println(broadsword.printAttackMap());
        System.out.println("\n" + broadsword);

        
        //System.out.println(test.getName());
        System.out.println(broadsword.getName());
        System.out.println(broadsword.name);
        //broadsword.attacks.add( (Array broadsword.attackNames.get(0), broadsword.attackValues.get(0) );
        System.out.println(broadsword.printForm());

        System.out.println(broadsword.getName());
        //the super fields and the instance fields are completely seperate
        broadsword.setName("WimpySword");
        System.out.println(broadsword.getName());
        System.out.println(broadsword.name);
        
        
    }
    
}
