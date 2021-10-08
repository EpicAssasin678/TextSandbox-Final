package Mechanics.combat;
import Characters.Character;
import Characters.Enemy;
import Mechanics.items.*;

public class CombatTest {
    
    public static void main(String[] args) {
        //init characters
        Character PLAYER = new Character("Test", 1);
        Enemy newEnemy = new Enemy("Gako", 1, 100);
        PLAYER.characterInventory.addToInventory(new Weapon("Broadsword"));
        
    }
}
