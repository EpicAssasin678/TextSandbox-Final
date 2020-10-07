package Characters;


public class Enemy extends Character {
        
    public Enemy (String name, int level, int health) {
        super.characterName = name;
        super.level = level;
        super.health = health;
    }

    @Override
    public void attack(Character Target, int damage) {
        Target.setHealth(Target.getHealth() - damage);

        System.out.println("\n" + this.getName() + " attacked " + Target.getName());
        System.out.println(Target.getName() + "health is now" + Target.getHealth());
    }

    public static void main(String[] args) {
        Character goblin = new Enemy("Goblin", 1, 100);
        System.out.println(goblin.getName());

        Character hero = new Character("Zach", 1, 100);
        hero.attack(goblin, 20);
        hero.attack(goblin, 20);
        goblin.attack(hero, 20);

    }
}
