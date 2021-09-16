package Mechanics.combat;

import java.util.Scanner;
import Characters.Character;
import Characters.Enemy;
import Mechanics.items.Armor;
import Mechanics.items.Inventory;
import Mechanics.items.Weapon;

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
        
        
        System.out.println("Select an action:\n");
        System.out.println("1) Attack with weapon.\n2) Use an item.\n3) Switch weapon.\n4) View inventory.");
        switch (input.nextInt()){
            case 1:
                int choice;
                System.out.println(player.equippedWeapon.printAttackMap());
                System.out.println("\nSelect combat move to execute by entering in the position of the entry:");
                //choice = input.nextInt();
                Combat.attack( opponent, player.equippedWeapon.attackValues.get(input.nextInt() - 1) );
                
            case 2:
            //

            case 3:
            //

            case 4:
            //
            
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
        CombatEvent testEvent = new CombatEvent(new Character("PLAYER", 1, 0, 100, new Inventory(), new Weapon("Beginners Dagger"), new Armor()), 
        new Enemy("newEnemy", 1, 100));

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
