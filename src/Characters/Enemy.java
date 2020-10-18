package Characters;


public class Enemy extends Character {
        
    public Enemy (String name, int level, int health) {
        super.characterName = name;
        super.level = level;
        super.health = health;

    }

    public void enemMethod() {

    }


    /**
     * Goblin class attack method
     */
    @Override
    public void attack(Character Target, int damage) {
        Target.setHealth(Target.getHealth() - damage);

        System.out.println("\n" + this.getName() + " attacked " + Target.getName());
        System.out.println("Damage was: " + damage);
        System.out.println(Target.getName() + "'s health is now " + Target.getHealth());

        System.out.println("This is the overriden form of attack method");
    }

    public static void main(String[] args) {
        Character goblin = new Enemy("Goblin", 1, 100);
        System.out.println(goblin.getName());
        
        Character hero = new Character("Zach", 1);
        hero.attack(goblin, 20);
        hero.attack(goblin, 20);
        goblin.attack(hero, 20);    
        
        goblin.attack(hero, 30);
        
    }
}
