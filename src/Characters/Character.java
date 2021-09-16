
package Characters;

import java.io.File;

import Mechanics.items.*;


/**
 * Character Class
 *
 * @author (Zachery Uporsky)
 * @version (0.1 8/21/20)
 * 
 * need to make "how close to next level for displayCharacter method" 
 * need to make an xp bar system
 */
public class Character {
    
    public int debugMode;
    
    // instance variables - replace the example below with your own
    public String characterName = "";
    public int level;
    public double exp;
    public int health;
    public int maxHealth; 
    
    public Weapon equippedWeapon;
    public Armor equippedArmor;
    public Inventory characterInventory = new Inventory();

    //for insufficient field filling
    public static int CHARACTER_DEFAULT_HEALTH = 100;
    public static int CHARACTER_DEFAULT_EXP = 0;
    
    
    //for xp stats
    public double expToNextLevel;
    public String expBar = "[";
    public double levelCompletion;

    //combat modifier traits
    public double addMod = 0;
    public double subMod = 0;
    public double mulMod = 0;
    public double divMod = 0;

    /**
     * High specificty constructor.
     * @param name
     * @param level
     * @param exp
     * @param health
     * @param inventory
     * @param weapon
     * @param armor
     */
    public Character(String name, int level, double exp, int health, Inventory inventory, 
    Weapon weapon, Armor armor) {
        characterName = name;
        this.level = level;
        this.exp = exp;
        this.health = health;
        this.equippedWeapon = weapon;
        this.equippedArmor = armor;

        this.maxHealth = health;
    }
 
    
    /**
     * 
     * @param name
     * @param level
     * @param exp
     * @param health
     * @param inventory
     */
    public Character(String name, int level, double exp, int health, Inventory inventory) {
        characterName = name;
        this.level = level;
        this.exp = exp;
        this.health = health;
        this.maxHealth = health;
    }
 
    /**
     * Constructor for objects of class Character
     */
    public Character(String name, int level, double exp, int health) {
        characterName = name;
        this.level = level;
        this.exp = exp;
        this.health = health;
        this.maxHealth = health;
    }
    

    public Character(String name, int level, double exp) {
        characterName = name;
        this.level = level;
        this.exp = exp;

        //setting defaults if not specified 
        this.health = CHARACTER_DEFAULT_HEALTH;
        this.maxHealth = health;
    }

    public Character(String name, int level) {
        characterName = name;
        this.level = level;

        //setting defaults if not specified
        this.exp = CHARACTER_DEFAULT_EXP;
        this.health = CHARACTER_DEFAULT_HEALTH;
        this.maxHealth = health;
    }

    //overloading Charcter object so that it has multiple forms 
    public Character(String name) {
        characterName = name;
        this.exp = CHARACTER_DEFAULT_EXP;
        this.health = CHARACTER_DEFAULT_HEALTH;
        this.maxHealth = health;
    }

    public Character() {
        this.exp = CHARACTER_DEFAULT_EXP;
        this.health = CHARACTER_DEFAULT_HEALTH;
        this.maxHealth = health;
    }

    public Integer getDefaultHealth() {
        return CHARACTER_DEFAULT_HEALTH;
    }

    public Integer getDefaultExp() {
        return CHARACTER_DEFAULT_EXP;
    }

    public String getName() {
        return characterName;
    }

    public void setName(String newname) {
        characterName = newname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int newLevel) {
        this.level = newLevel;
    }

    // xp will fill up every time and once it equals the next level, it will add
    public double getExp() {
        return exp;
    }

    public void setExp(double newExp) {
        this.exp = newExp;
    }

    public double getExpToNextLevel() {
        return this.expToNextLevel;
    }

    public void setExpToNextLevel(double expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDebug(int debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Set mod type as either 'add', 'sub', 'mul' , or 'div' for the modtype param.
     * @param modtype
     * @param setMod
     */
    public void setMods(String modtype, double setMod) {      
        switch(modtype) {
            case "add":
            this.addMod = setMod;
            case "sub":
            this.subMod = setMod;
            case "mul":
            this.mulMod = setMod;
            case "div":
            this.divMod = setMod;
        }
    }

    /*
     * will create an experience bar system that will look something like this: XP
     * METER: [***** ] (current experiece/exp needed for next level)
     * 
     * may be a problem in the future, but if done repeadetly it may
     * add again and again on top of itself
    */
    public void generateExpBar() {
        //count through the levelCompletion
        //int barFill = 0;
        
        //make a rewrite so it is by instance
        this.expBar = "[";

        levelCompletion = exp / expToNextLevel;
        //System.out.println("levelCompletion: " + levelCompletion);

        int barFill = (int)(levelCompletion / .10);
        //System.out.println("barFill = " + barFill);
        
        for (int i = 0; i < barFill; i++) {
            this.expBar += "*";
            //System.out.println("Added a star to xp bar");
            //System.out.println("integer count: " + i);
            //System.out.println("Exp Bar:" + this.expBar);
        }

        for (int i = 0; i <= (10 - this.expBar.length()); i++) {
            if (10 - this.expBar.length() != 0) {
                this.expBar += " ";
                //System.out.println("integer i" + i);
                //System.out.println("added space");
            }
        }
        this.expBar += "]";
        
    }

    //needs impimentation
    public void generateRandomStats() {

    }

    public void attack(Character Target, int damage) {
        Target.setHealth(Target.getHealth() - damage);
        
        //Debug options:
        System.out.println("\n" + this.getName() + " attacked " + Target.getName());
        System.out.println("Damage was: " + damage);
        System.out.println(Target.getName() + "'s health is now " + Target.getHealth());
    }

    public void dealRandomDamage(Character Target) {
        int damage = (int) Math.random();
        Target.setHealth(Target.getHealth() - damage);
        //debug options
        System.out.println( "Damage is: " + damage);
    }

    //do not know if I should pass through parameters or not
    public void displayXpStats() {
        System.out.println("\nExperience Statistics:");
        System.out.println("Exp: " + expBar);
        System.out.println("Level " + (1 + level) + " Completion: " + exp + "/" + expToNextLevel + "");
    }


    //overloaded form
    public void displayCharacterStats() {
        //basic character stats
        System.out.println("Name: " + this.getName());
        System.out.println("Level: " + this.level);
        System.out.println("Exp: "  + this.exp);

    }

    //basically displayCharacterStats()
    public String toString() {
        return "\nName: " + this.getName() + "\nLevel: " + level + "\nExp: " + exp + "\nHealth: " + health;
    }
    

   
    public static void init() {
        
    }


    
    public static void main(String[] args) {
        
    }

}
