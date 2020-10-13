package Mechanics.Items;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


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
    ArrayList <Long> attackValues = new ArrayList<Long>();
    HashMap <String, Long> attackMap = new HashMap<String, Long>(); 
    
    boolean weaponHasGenerated = false;

    //TODO implement methods to parent class, so that other items can use the functionality
    //TODO check the type of object via the checkClass() method

    /**
     * super descriptor contructor
     * @param identifier
     * @param name
     * @param description
     * @param invAssigned
     * @param isHoldable
     */
    public Weapon (String identifier, String name, String description, int invAssigned, boolean isHoldable) {
        super(identifier, name);
        this.description = description;
    
    }

    /**
     * Class specific constructor no super\implementation
     * @param name
     * @param attackNames
     * @param attackValues
     */
    public Weapon (String name, ArrayList <String> attackNames, ArrayList <Long> attackValues) {
        super(name);
        this.name = name;
        this.attackNames = attackNames;
        this.attackValues = attackValues;

    }

    public Weapon (String identifier, String name) {
        super(identifier, name);
        
    }

    public Weapon (String name) {
        super(name);
        this.name = name;
        
    }

    /**
     * Default blank weapon constructor
     */
    public Weapon () {
        
    }

    public void generateAttacks(JSONArray arr) {
        
        for (int i = 0; i < arr.size(); i++) {

            JSONObject obj = (JSONObject) arr.get(i);
            String objStr = (String) obj.get("name");
            attackNames.add(objStr);
            System.out.println(attackNames);
            
            long objInt = (Long) obj.get("damage");
            attackValues.add(objInt);
            System.out.println(attackValues);
        }
    }

    /**
     * Generates the attackMap HashMap of the Weapon instance by adding the values of attackNames and attackValues at the respective indexes
     * 
     * @return HashMap attackMap
     */
    public HashMap generateAttackMap () {

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
        build += "\n" + this.name + "'s Attacks:\n";
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
        
    }

    public String itemType(Item item) {
        return item.type;
    }

    public String checkClass() {
        if (this.getClass().toString() == "Item") {
            return "Item";
        } else {
            return "Weapon";
        }
    }



    public static void main(String[] args) {

        Weapon broadsword = new Weapon("Broadsword");
        System.out.println(broadsword.checkClass());
        System.out.println(broadsword.itemType(broadsword));
    
        broadsword.initWeapon("Broadsword");

        System.out.println(broadsword.printAttackMap());
        System.out.println(broadsword);

        System.out.println(broadsword.checkClass());
        System.out.println(broadsword.itemType(broadsword));
    

        Item test = new Weapon("Bruh");
        System.out.println(test.getName());
        //broadsword.attacks.add( (Array broadsword.attackNames.get(0), broadsword.attackValues.get(0) );

        
    }
    
}
