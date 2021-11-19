package Mechanics.items;

import Characters.Character;

public class Potion extends Item {
    
    ItemType itemType = ItemType.POTION;
    
    String description;
    String name = "default";

    int healthGain;
    double mod; //for later use
    
    PotionType potionType;
    
    
    public Potion (String name, int healthGain) {
        this.name = name;
        this.healthGain = healthGain;
    }

    public Potion (PotionType type) {
        
    }

    public PotionType getPotionType() {
        return this.potionType;
    }
    
    public void use (Character PLAYER) {
        //adds healthGain to player
        PLAYER.health = (PLAYER.health + healthGain)-(PLAYER.health + healthGain)%PLAYER.maxHealth; 
    }

    public static void main(String[] args) {
        
    }
}
