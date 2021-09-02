package Mechanics.combat;

import java.util.Random;

import Characters.Character;
import Mechanics.items.*;

public class Combat  {
    

    public double ranDamage;

    //!USELESS FOR THE TIME BEING

    
    /**
     * make the attack set the health minus whatever damage
     * default attack method
     * @param Character target
     * 
     * 
     */
        
    public static void attack(Character Target, int damage) {
        Target.setHealth(Target.getHealth() - damage);
        System.out.println(Target.getName() + "was attacked" + damage + "HP");
    }
    
    public void dealRandomDamage(Character Target) {
        int damage = (int) Math.random();
        Target.setHealth(Target.getHealth() - damage);
        //debug options
        System.out.println( "Damage is: " + damage);
    }

    /**
     * Will create a damage calculation based on a range of percentage
     *  Ex:
     * character uses an attack, which has the potential damage range 
     * of 21-35
     */
    public int damageRange(int min, int max) {
        Random randInt = new Random();
        return randInt.ints(1, min, max).sum();
        
    }


    public static double damageCalc(double damageBase, double addModSum, 
    double subModSum, double mulModSum, double divModSum) {
        
        double T = (((damageBase + addModSum) - subModSum) * mulModSum) / divModSum;
        return T;
    }

    

    
    
    public static void main(String[] args) {
        
    }
}