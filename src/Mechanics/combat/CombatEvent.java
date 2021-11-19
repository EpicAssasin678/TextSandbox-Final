package Mechanics.combat;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.spi.AudioFileReader;

import Characters.Character;
import Characters.Enemy;
import Mechanics.items.Armor;
import Mechanics.items.Inventory;
import Mechanics.items.Weapon;
import Mechanics.items.ItemType;
import Mechanics.items.Potion;


/**
 * @author Zachery Uporsky
 * @version alpha
 * 
 * Object representing interaction for a combat event.
 */
public class CombatEvent {

    Scanner input;


    public int turnTimer = 0;


    /**
     * 
     * @param player
     * @param opponent
     */
    public CombatEvent(Character player, Enemy opponent) {
        executeCombatTurn(player, opponent);
        
    }

    
    private void executeCombatTurn(Character player, Enemy opponent) {
        input = new Scanner(System.in);
        String[] inputArray;
        
        System.out.println("Select an action:\n");
        System.out.println("1) Attack with weapon.\n2) Use an item.\n3) Switch weapon.\n4) View inventory.");
        int choice;
        switch (input.nextInt()){
            case 1: 
                    System.out.println(player.equippedWeapon.printAttackMap());
                    System.out.println("\nSelect combat move to execute by entering in the position of the entry:");
                    //choice = input.nextInt();

                    Combat.attack( opponent, player.equippedWeapon.attackValues.get(input.nextInt() - 1) );
                    break;
            case 2:
                System.out.println("CASE 2");
                break;
            case 3:
            //
                System.out.println("CASE 3");
                break;
            //Weapon switching
                //display weapons 

                //check input

                //display inventory.get(i-1) weapon's stats if viewed
                //if not then equip
                //if niether then recurse 
            case 4:            
                //Inventory inspection
                //while player didn't exit menu
                String menuInput = "";
                
                //TODO fix bug: Menu printed twice due to nature of loop and IndexOutOfBoundsException
                do {

                    menuInput = input.nextLine();
                    inputArray = menuInput.split(" ");
                    Matcher inputMatcher = Pattern.compile("VIEW", Pattern.CASE_INSENSITIVE).matcher(menuInput); //should match input
                    if (menuInput != "") {
                        System.out.println("Inventory menu command usage: \nv or view <slot> to see an item's properties\nq or quit to leave the inventory menu");
                        player.characterInventory.displayInventoryMenu();   
                    }

                    if (inputArray[0] == "v" || inputMatcher.find()) {
                        
                        System.out.println("\nRetrieving item to view.");
                        int position = Integer.parseInt( inputArray[1] );

                        System.out.println("TypeMap is currently: " + player.characterInventory.getTypeMap());
                        ItemType type = player.characterInventory.getFromTypeMap(position - 1); //why is this happening to me
                        
                        //!switch statement doesn't act correctly
                        switch(type){
                            case WEAPON:
                                System.out.println(player.getWeaponInv().get((position - 1) - player.characterInventory.weaponInv.size()).toString());
                            case ARMOR:
                                System.out.println(player.getArmorInv().get((position - 1) - player.characterInventory.weaponInv.size() ).toString());    
                            case POTION:
                                System.out.println(player.getPotionInv().get((position -1)- (player.characterInventory.weaponInv.size() + player.characterInventory.armorInv.size()) ).toString() ) ;
                            case SPECIAL_ITEM:
                                System.out.println(player.getSpecialInv().get((position -1)).toString());
                        }

                    } else if (inputArray[0] == "q" || Pattern.compile("quit", Pattern.CASE_INSENSITIVE).matcher(menuInput).find()) {
                        break;
                    } 
                } while ( ! (inputArray[0] == "q" || Pattern.compile("quit", Pattern.CASE_INSENSITIVE).matcher(menuInput).find())); 
                //if player exited menu, recurse back to executeCombatTurn
                executeCombatTurn(player, opponent);
        }


        makeEnemyChoice(player, opponent);


        if (player.getHealth() <= 0) {
            System.out.println("You died.");
        } else if (opponent.getHealth() <= 0) {
            //need to implement level up screen afterwards
            System.out.println("You have won!");
        } 
        if (player.getHealth() > 0 && opponent.getHealth() > 0) {
            executeCombatTurn(player, opponent);
        }
        
    }

    //Eventually, this will be a process that the AI will choose, but for now it will be a simple fixed amount of damage.
    public void makeEnemyChoice(Character player, Enemy enemy) {
        Combat.attack(player, 10);
    }

    //TODO implement
    public void playBattleMusic() {

        Thread musicThread = new Thread(new Runnable() {
            public void run() {
                
            }
        });
        
    }


    public static void main(String[] args) {
        Character PLAYER = new Character("PLAYER", 1, 0, 100, new Weapon("Beginners Dagger"), new Armor());
        PLAYER.characterInventory.addToInventory(new Weapon("Sword of Luca"));
        PLAYER.characterInventory.addToInventory(new Potion("Basic Health Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Basic Health Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Basic Health Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Basic Health Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Basic Health Potion", 20));

        CombatEvent testEvent = new CombatEvent(PLAYER, new Enemy("newEnemy", 1, 100));

    }
}

/**
 *  Uses both Characters
 *  Character does turn
 *      Options are displayed 
 *      Parser parses actions 
 *  
 * Enemy does his attack
 * Calculations are done
 * Turn timer increases
 * Loop repeats 
 */

 //TODO Make an attack class for certain effect modifiers and then a parser for those effect codes.
