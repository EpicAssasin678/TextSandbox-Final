package Mechanics.combat;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        switch (input.nextInt()){
            case 1: int choice;
                    System.out.println(player.equippedWeapon.printAttackMap());
                    System.out.println("\nSelect combat move to execute by entering in the position of the entry:");
                    //choice = input.nextInt();
                    Combat.attack( opponent, player.equippedWeapon.attackValues.get(input.nextInt() - 1) );
            
                
            case 2:
            System.out.println("CASE 2");
            //Weapon switching
                //display weapons 

                //check input

                //display inventory.get(i-1) weapon's stats if viewed
                //if not then equip
                //if niether then recurse 

            case 3:
            //
                System.out.println("CASE 3");
            case 4:            //Inventory inspection
                //while player didn't exit menu
                String menuInput = "";
                //TODO fix bug: Menu printed twice due to nature of loop and IndexOutOfBoundsException
                do {

                    System.out.println("Inventory menu command usage: \nv or view <slot> to see an item's properties\nq or quit to leave the inventory menu");
                    
                    player.characterInventory.displayInventoryMenu();    
                    menuInput = input.nextLine();
                    inputArray = menuInput.split(" ");
                    Matcher inputMatcher = Pattern.compile("VIEW", Pattern.CASE_INSENSITIVE).matcher(menuInput); //should match input

                    if (inputArray[0] == "v" || inputMatcher.find()) {
                        int position = Integer.parseInt( inputArray[1] );
                        ItemType type = player.characterInventory.getFromTypeMap(position - 1); //why is this happening to me
                        switch (type) {
                            case WEAPON:
                                System.out.println(player.characterInventory.weaponInv.get(position).printForm());
                            case ARMOR:
                                System.out.println(player.characterInventory.armorInv.get(position - player.characterInventory.weaponInv.size()).toString());
                                case POTION:
                                System.out.println(player.characterInventory.potionInv.get(position - (player.characterInventory.weaponInv.size() + player.characterInventory.armorInv.size()) ).toString() ) ;
                            case SPECIAL_ITEM:
                                System.out.println(player.characterInventory.itemInv.get(position).toString());
                        }
                    } else if (inputArray[0] == "q" || Pattern.compile("quit", Pattern.CASE_INSENSITIVE).matcher(menuInput).find()) {
                        break;
                    } 
                } while ( ! (inputArray[0] == "q" || Pattern.compile("quit", Pattern.CASE_INSENSITIVE).matcher(menuInput).find())); 
                
                
                //if player exited menu, recurse back to executeCombatTurn
            
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
