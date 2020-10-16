[1mdiff --git a/.vscode/launch.json b/.vscode/launch.json[m
[1mindex dbf6346..073f10f 100644[m
[1m--- a/.vscode/launch.json[m
[1m+++ b/.vscode/launch.json[m
[36m@@ -4,6 +4,13 @@[m
     // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387[m
     "version": "0.2.0",[m
     "configurations": [[m
[32m+[m[32m        {[m
[32m+[m[32m            "type": "java",[m
[32m+[m[32m            "name": "CodeLens (Launch) - Enemy",[m
[32m+[m[32m            "request": "launch",[m
[32m+[m[32m            "mainClass": "Characters.Enemy",[m
[32m+[m[32m            "projectName": "TextSandbox_c4aab7d0"[m
[32m+[m[32m        },[m
         {[m
             "type": "java",[m
             "name": "CodeLens (Launch) - Item",[m
[1mdiff --git a/src/Characters/Character.java b/src/Characters/Character.java[m
[1mindex ce4741f..d4fc39e 100644[m
[1m--- a/src/Characters/Character.java[m
[1m+++ b/src/Characters/Character.java[m
[36m@@ -31,6 +31,14 @@[m [mpublic class Character {[m
     public String expBar = "[";[m
     public double levelCompletion;[m
 [m
[32m+[m[32m    /**[m
[32m+[m[32m     *[m[41m [m
[32m+[m[32m     * @param name[m
[32m+[m[32m     * @param level[m
[32m+[m[32m     * @param exp[m
[32m+[m[32m     * @param health[m
[32m+[m[32m     * @param inventory[m
[32m+[m[32m     */[m
     public Character(String name, int level, double exp, int health, Inventory inventory) {[m
         characterName = name;[m
         this.level = level;[m
[36m@@ -55,11 +63,17 @@[m [mpublic class Character {[m
         this.level = level;[m
         this.exp = exp;[m
 [m
[32m+[m[32m        //setting defaults if not specified[m[41m [m
[32m+[m[32m        this.health = CHARACTER_DEFAULT_HEALTH;[m
     }[m
 [m
     public Character(String name, int level) {[m
         characterName = name;[m
         this.level = level;[m
[32m+[m
[32m+[m[32m        //setting defaults if not specified[m
[32m+[m[32m        this.exp = CHARACTER_DEFAULT_EXP;[m
[32m+[m[32m        this.health = CHARACTER_DEFAULT_HEALTH;[m
     }[m
 [m
     //overloading Charcter object so that it has multiple forms [m
[36m@@ -212,9 +226,10 @@[m [mpublic class Character {[m
     [m
     public void attack(Character Target, int damage) {[m
         Target.setHealth(Target.getHealth() - damage);[m
[31m-[m
[32m+[m[41m        [m
         //Debug options:[m
[31m-        System.out.println("\nDamage was: " + damage);[m
[32m+[m[32m        System.out.println("\n" + this.getName() + " attacked " + Target.getName());[m
[32m+[m[32m        System.out.println("Damage was: " + damage);[m
         System.out.println(Target.getName() + "'s health is now " + Target.getHealth());[m
     }[m
 [m
[1mdiff --git a/src/Characters/Enemy.java b/src/Characters/Enemy.java[m
[1mindex ead5574..5fa81d3 100644[m
[1m--- a/src/Characters/Enemy.java[m
[1m+++ b/src/Characters/Enemy.java[m
[36m@@ -7,24 +7,35 @@[m [mpublic class Enemy extends Character {[m
         super.characterName = name;[m
         super.level = level;[m
         super.health = health;[m
[32m+[m
     }[m
 [m
[32m+[m
[32m+[m[32m    /**[m
[32m+[m[32m     * Goblin class attack method[m
[32m+[m[32m     */[m
     @Override[m
     public void attack(Character Target, int damage) {[m
         Target.setHealth(Target.getHealth() - damage);[m
 [m
         System.out.println("\n" + this.getName() + " attacked " + Target.getName());[m
[31m-        System.out.println(Target.getName() + "health is now" + Target.getHealth());[m
[32m+[m[32m        System.out.println("Damage was: " + damage);[m
[32m+[m[32m        System.out.println(Target.getName() + "'s health is now " + Target.getHealth());[m
[32m+[m
[32m+[m[32m        System.out.println("This is the overriden form of attack method");[m
     }[m
 [m
     public static void main(String[] args) {[m
         Character goblin = new Enemy("Goblin", 1, 100);[m
         System.out.println(goblin.getName());[m
 [m
[31m-        Character hero = new Character("Zach", 1, 100);[m
[32m+[m[32m        Character hero = new Character("Zach", 1);[m
         hero.attack(goblin, 20);[m
         hero.attack(goblin, 20);[m
[31m-        goblin.attack(hero, 20);[m
[32m+[m[32m        goblin.attack(hero, 20);[m[41m    [m
[32m+[m[41m        [m
[32m+[m[32m        goblin.attack(hero, 30);[m
 [m
[32m+[m[41m        [m
     }[m
 }[m
[1mdiff --git a/src/Mechanics/Items/Item.java b/src/Mechanics/Items/Item.java[m
[1mindex 08e9309..ff185c4 100644[m
[1m--- a/src/Mechanics/Items/Item.java[m
[1m+++ b/src/Mechanics/Items/Item.java[m
[36m@@ -7,13 +7,11 @@[m [mimport java.util.Scanner;[m
 import org.json.simple.parser.*;[m
 [m
 /**[m
[31m- * Class will be the superclass for other items. Most importantly, weapons for now.[m
[31m- * [m
[31m- * make items an abstract class, make methods for getters and setters, initItem, etc[m
[31m- * [m
[31m- * Item will be specified by type, and then the name of an item within that type[m
[31m- * Then the name of the item will be correlated to the WeaponStats which will display attacks[m
[32m+[m[32m * Item Class[m
  * [m
[32m+[m[32m * @author Zachery Uporsky aka darkf0x[m
[32m+[m[32m * @version 0.2[m[41m [m
[32m+[m[32m * @see[m
  * TODO fix the implementation of the class, make the functions of each to descend from it's child classes[m
  * [m
  */[m
[36m@@ -126,7 +124,7 @@[m [mpublic class Item {[m
         try {[m
             System.out.println((Object) i.getClass());[m
             i.setName("Broadsword");[m
[31m-            [m
[32m+[m
             [m
 [m
 [m
[1mdiff --git a/src/Mechanics/Items/Weapon.java b/src/Mechanics/Items/Weapon.java[m
[1mindex 43c79ca..3f41bb0 100644[m
[1m--- a/src/Mechanics/Items/Weapon.java[m
[1m+++ b/src/Mechanics/Items/Weapon.java[m
[36m@@ -36,7 +36,7 @@[m [mpublic class Weapon extends Item {[m
 [m
     //TODO implement methods to parent class, so that other items can use the functionality[m
     //TODO check the type of object via the checkClass() method[m
[31m-[m
[32m+[m[41m    [m
     /**[m
      * super descriptor contructor[m
      * @param identifier[m
