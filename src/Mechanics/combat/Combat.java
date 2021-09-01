package Mechanics.combat;

import java.util.Random;

import Characters.Character;
import Mechanics.items.*;

public class Combat  {
    

    public double ranDamage;
    //Modifications
    public static final String[] modKey = {"DEFAULT"};
    public static final double[] addMods = {0, };
    public static final double[] subMods = {0, };
    public static final double[] mulMods = {1, };
    public static final double[] divMods = {1, };
    
    /**
     * default attack method
     * @param Character target
     * 
     * make the attack set the health minus whatever damage
     */
        
    public void attack(Character Target, int damage) {
        Target.setHealth(Target.getHealth() - damage);
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