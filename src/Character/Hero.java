package Character;

import Enums.ClassType;
import Enums.ItemType;

import java.util.ArrayList;
import java.util.Scanner;

import Item.Item;


public abstract class Hero extends Entity {
    public ClassType player;
    public String specialAbility;
    public int abilityDamage;
    public int gold;
    public ArrayList<Item> inventory;
    public int level;


    public Hero(String name, int hp, int maxHp, int attack, ClassType player, String specialAbility, int abilityDamage, int gold) {
        super(name, hp, maxHp, attack);
        this.player = player;
        this.specialAbility = specialAbility;
        this.abilityDamage = abilityDamage;
        this.gold = gold;
        this.inventory = new ArrayList<>();
        this.level = 1;
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ClassType getPlayer() {
        return player;
    }

    public int getAbilityDamage() {
        return abilityDamage;
    }

    public void setAbilityDamage(int abilityDamage) {
        this.abilityDamage = abilityDamage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static void showClassWeaponDetails(ClassType classType) {

        System.out.println("\n=== SELECTED CLASS ===");

        switch (classType) {

            case LEGIONNAIRE:
                System.out.println("Class: LEGIONNAIRE");
                System.out.println("Weapon: Sword");
                System.out.println("Description: Warrior specialized in close combat.");
                System.out.println("During the game, the player can purchase weapon-specific upgrades in the shop.\n");
                break;

            case BLOODY_MAGE:
                System.out.println("Class: BLOODY MAGE");
                System.out.println("Weapon: Staff");
                System.out.println("Description: Mage who uses arcane energy to attack enemies.");
                System.out.println("During the game, the player can purchase weapon-specific upgrades in the shop.\n");
                break;

            case CRIMSON_ARCHER:
                System.out.println("Class: CRIMSON ARCHER");
                System.out.println("Weapon: Bow");
                System.out.println("Description: Agile archer specialized in long-range attacks.");
                System.out.println("During the game, the player can purchase weapon-specific upgrades in the shop.\n");
                break;

            case RED_BASTION:
                System.out.println("Class: RED BASTION");
                System.out.println("Weapon: Shield");
                System.out.println("Description: Resilient defender who uses the shield as a weapon.");
                System.out.println("During the game, the player can purchase weapon-specific upgrades in the shop.\n");
                break;

            default:
                System.out.println("Unknown class.\n");
                break;
        }
    }

    public void heroDetails() {
        System.out.println("============STATUS============");
        System.out.println("* Class: " + this.player);
        System.out.println("* Level: " + this.level);
        System.out.println("* HP: " + this.hp);
        System.out.println("* Attack Damage: " + this.attack);
        System.out.println("* Special Ability: " + this.specialAbility + " (70% chance to hit)");
        System.out.println("* Ability Damage: " + this.abilityDamage);
        System.out.println("* Noxian Crowns: " + this.gold + " nc");
        System.out.println("==============================");
    }

    public static Hero chooseCharacter() {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose your warrior:");
        System.out.println("[1] - Legionnaire");
        System.out.println("[2] - Bloody Mage");
        System.out.println("[3] - Crimson Archer");
        System.out.println("[4] - Red Bastion");

        int choose = input.nextInt();

        switch (choose) {
            case 1:
                System.out.println("You chose the Legionnaire!");
                return new Legionnaire();
            case 2:
                System.out.println("You chose the Bloody Mage!");
                return new BloodyMage();
            case 3:
                System.out.println("You chose the Crimson Archer!");
                return new CrimsonArcher();
            case 4:
                System.out.println("You chose the Red Bastion!");
                return new RedBastion();
            default:
                System.out.println("Invalid option, try again.\n");
                return chooseCharacter();
        }
    }

    public void chooseYourName() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nNow brave hero, tell us the name that shall echo through legends:");
        String newName = input.next();
        setName(newName);
        System.out.println("Welcome! " + this.name + "! Your journey begins now.\n");
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void removeGold(int gold) {
        this.gold -= gold;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public void heroInventory() {

        if(this.inventory.isEmpty()){
            System.out.println("\n============ Empty Inventory ============");
            System.out.println("=========================================\n");
        } else {
            System.out.println("\n============ Inventory ============");

            for (int i = 0; i < this.inventory.size(); i++) {
                Item item = this.inventory.get(i);

                System.out.println("[" + (i + 1) + "] | Name: " + item.getName() + " | Effect: " + item.getEffect() + " | Type: " + item.getType());
                System.out.println("-----------------------------------");
            }

            System.out.println("===================================\n");
        }
    }

    public void attackEnemy(NPC enemy) {
        enemy.hp -= this.attack;
        System.out.println("You dealt " + this.attack + " damage!");
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        System.out.println("You received " + damage + " damage!");
    }

    public void useConsumable() {
        Scanner input = new Scanner(System.in);


        if (this.inventory.isEmpty()) {
            System.out.println("\nYou don't have any items to use.\n");
            return;
        }

        heroInventory();
        System.out.println("\nChoose one of your items to use or press 0 to close the inventory:");

        int index = input.nextInt();

        // FECHAR INVENTÁRIO
        if (index == 0) {
            return;
        }

        // VALIDAÇÃO DE ÍNDICE
        if (index < 1 || index > this.inventory.size()) {
            System.out.println("Invalid item selection.");
            return;
        }

        Item itemSelected = this.inventory.get(index - 1);

        // ===== HEAL =====
        if (itemSelected.getType() == ItemType.HEAL) {

            int healedAmount;

            if (this.hp + itemSelected.getEffect() > this.maxHp) {
                healedAmount = this.maxHp - this.hp;
                this.hp = this.maxHp;
            } else {
                healedAmount = itemSelected.getEffect();
                this.hp += itemSelected.getEffect();
            }

            System.out.println(
                    "\nYou drink a healing potion and recover "
                            + healedAmount + " HP.\n"
            );

            removeItem(itemSelected);
        }

        // ===== ATTACK BUFF =====
        else if (itemSelected.getType() == ItemType.ATTACK) {

            this.attack += itemSelected.getEffect();

            System.out.println(
                    "\nYou drink an attack potion and increase your attack by "
                            + itemSelected.getEffect() + ".\n"
            );

            removeItem(itemSelected);
        }
    }

    public void useSpecialAtk(NPC enemy) {
        enemy.hp -= this.abilityDamage;
        System.out.println("You dealt " + this.abilityDamage + " damage with your special ability");
    }

    public static void levelUp(Hero hero) {

        Scanner input = new Scanner(System.in);

        hero.setLevel(hero.getLevel() + 1);

        System.out.println("\n=== LEVEL UP ===\n"
                + "You reached level " + hero.getLevel() + "!\n"
                + "Choose an attribute to improve:\n");

        System.out.println("[1] Increase Max HP (+20)");
        System.out.println("[2] Increase Attack Damage (+5)");
        System.out.println("[3] Increase Ability Damage (+8)");

        int choice = input.nextInt();

        switch (choice) {

            case 1:
                hero.setMaxHp(hero.getMaxHp() + 20);
                hero.setHp(hero.getHp() + 20);
                System.out.println("\nYour body endures more punishment. Max HP increased.\n");
                break;

            case 2:
                hero.setAttack(hero.getAttack() + 5);
                System.out.println("\nYour strikes grow deadlier. Attack Damage increased.\n");
                break;

            case 3:
                hero.setAbilityDamage(hero.getAbilityDamage() + 8);
                System.out.println("\nYour mastery of combat abilities deepens. Ability Damage increased.\n");
                break;

            default:
                break;
        }
    }

}



