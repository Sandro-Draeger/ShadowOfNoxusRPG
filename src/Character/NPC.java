package Character;

public class NPC extends Entity{
    public int difficulty;
    public String specialAbility;
    public int abilityDamage;

    public NPC(String name, int hp, int maxHp, int attack, String specialAbility, int abilityDamage, int difficulty) {
        super(name, hp, maxHp, attack);
        this.difficulty = difficulty;
        this.specialAbility = specialAbility;
        this.abilityDamage = abilityDamage;
    }

    public void attackEnemy(Hero hero) {
        hero.setHp(hero.getHp() - this.attack);
        System.out.println("The "+this.name+" deals " + this.attack + " damage!\n");
    }

    public void useSpecialAtk(Hero hero) {
        hero.setHp(hero.getHp() - this.abilityDamage);
        System.out.println("The "+this.name+" hits you with a SPECIAL attack "+this.specialAbility+ " and deals "+ this.abilityDamage + " damage!\n");
    }


}

