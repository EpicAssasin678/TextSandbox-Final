package Mechanics.items.tests;

import org.junit.Assert;
import junit.*;
import junit.framework.Test;
import Characters.Character;
import Mechanics.items.*;

public class InventoryTest {

  
    Character PLAYER = new Character("Gladius", 1, 0, 100, new Weapon("Beginners Dagger"), new Armor());

    @org.junit.Test
    void Test() {
        PLAYER.characterInventory.addToInventory(new Weapon("Begginners Dagger"));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
        Assert.assertEquals(PLAYER.characterInventory.getFromTypeMap(0).toString(), ItemType.WEAPON.toString());
    }

    public static void main(String[] args) {

        Character PLAYER = new Character("Gladius", 1, 0, 100, new Weapon("Beginners Dagger"), new Armor());
        PLAYER.characterInventory.addToInventory(new Weapon("Begginners Dagger"));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
        PLAYER.characterInventory.addToInventory(new Potion("Healing Potion", 20));
      
        PLAYER.characterInventory.displayInventoryMenu();
        
        Assert.assertEquals(PLAYER.characterInventory.getFromTypeMap(0).toString(), ItemType.WEAPON.toString());
        
    }
}
