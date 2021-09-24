package Mechanics.items;

import java.io.FileReader;
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

    ItemType itemType = ItemType.WEAPON;
    //obj instance variables
    String identifier;
    protected String name;
    String description;
    
    //parser var
    JSONParser parser = new JSONParser();
    Weapon referenced;

    //for attack maps 
    String[] attackDescriptions = new String[20];
    public ArrayList <String> attackNames = new ArrayList<String>();
    public ArrayList <Integer> attackValues = new ArrayList<Integer>();
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
       super(); 
    }

    //overWrites Item
    @Override
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    //because of the extension of itemInterface
    public ItemType specifiedItemType() {
        return ItemType.WEAPON;
    }

    public HashMap <String, Integer> getAttackMap() {
        return this.attackMap;
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

            String desc = (String) obj.get("description");
            attackDescriptions[i] = desc;
        }
    }

    /**
     * Generates the attackMap HashMap of the Weapon instance by adding the values of attackNames and attackValues at the respective indexes
     * 
     * @return HashMap attackMap
     */
    public HashMap<String, Integer> generateAttackMap () {

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

    JSONObject jWeapons;

    /**
     * Meant only to add to the ArrayLists associated with the Weapon object.
     * 
     * @see uses the generateAttacks() method
     * @param weaponName
     */
    public void initWeapon(String weaponName) {

        System.out.println("\ninitWeapon() called\nWeapon: " + (Object) this.getClass().toString() + "@" + Integer.toHexString(this.hashCode()) + " Has been initialised.\n");
        try {

            Object weaponFile = parser.parse(new FileReader("src\\Bin\\Json\\Items\\Weapons\\Weapons.json"));
            jWeapons = (JSONObject) weaponFile;
            JSONObject pointedWeapon = (JSONObject) jWeapons.get(weaponName);
            JSONArray weaponAttacks = (JSONArray) pointedWeapon.get("Attacks");
            
            //DEBUG
            System.out.println(weaponFile);
            System.out.println(jWeapons);
            System.out.println(pointedWeapon);

            this.generateAttacks(weaponAttacks);
            this.generateAttackMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for debug screen format purposes
        System.out.println("\n");
    }

    

//Weapon print options


    /**
     * Prints a form view of the Item, useful for looking at changes or all values of the class fields.
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

    /**
     * Prints out a summary view of the weapons attacks and respective damage in an Hashmap form. 
     * 
     * @return String build
     */
    public String printAttackMap () {

        String build = "";
        build += "\n" + this.name + "'s Attacks:\n";
        build += "-------------------------------------------------------------\n";
        //make a foreach loop to a String and concatenate it
        for (String i: this.attackMap.keySet()) {
            build += "Attack: " + i + " | Damage: " + attackMap.get(i) + "HP\n" +
            attackDescriptions[attackNames.indexOf(i)] + "\n\n";
        }
        build += "-------------------------------------------------------------\n";
        return build;
    }

    public static void main(String[] args) {

        //!TEST OF POLYMORPHISM
        Weapon broadsword = new Weapon("Broadsword");
       // Item subBroad= new Weapon("Broadsowrd");
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
        System.out.println(broadsword.printAttackMap());
        
        //System.out.println(broadsword.checkClass());
        
        
    }
    
}
