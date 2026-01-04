import Character.Hero;
import Character.NPC;
import Enums.ClassType;
import Enums.ItemType;
import Item.Consumable;
import Item.Weapons;
import Game.Quest;
import Game.Shop;

import java.util.ArrayList;

public class Main {
    static void main(String[] args) throws InterruptedException {

        //Instanciar os Itens

        // Healing potions
        Consumable smallHealPotion = new Consumable("Small Heal Potion", ItemType.HEAL, 15, 15);
        Consumable mediumHealPotion = new Consumable("Medium Heal Potion", ItemType.HEAL, 25, 20);
        Consumable bigHealPotion = new Consumable("Big Heal Potion", ItemType.HEAL, 30, 25);

        // Attack buff consumables (temporary; effect applied by battle code)
        Consumable smallAttackBuff = new Consumable("Small Attack Increase", ItemType.ATTACK, 10, 30);
        Consumable bigAttackBuff = new Consumable("Big Attack Increase", ItemType.ATTACK, 20, 60);

// Legionnaire – Sword Upgrades
        Weapons swordUpgrade1 = new Weapons("Sword Upgrade I - Damage Boost", ItemType.ATTACK, 10, 100, ClassType.LEGIONNAIRE);
        Weapons swordUpgrade2 = new Weapons("Sword Upgrade II - Sharpness Boost", ItemType.ATTACK, 15, 150, ClassType.LEGIONNAIRE);
        Weapons swordUpgrade3 = new Weapons("Sword Upgrade III - Enhanced Blade", ItemType.ATTACK, 20, 200, ClassType.LEGIONNAIRE);

// Bloody Mage – Staff Upgrades
        Weapons staffUpgrade1 = new Weapons("Staff Upgrade I - Power Core", ItemType.ATTACK, 10, 100, ClassType.BLOODY_MAGE);
        Weapons staffUpgrade2 = new Weapons("Staff Upgrade II - Arcane Channeling", ItemType.ATTACK, 15, 150, ClassType.BLOODY_MAGE);
        Weapons staffUpgrade3 = new Weapons("Staff Upgrade III - Enhanced Focus", ItemType.ATTACK, 20, 200, ClassType.BLOODY_MAGE);

// Crimson Archer – Bow Upgrades
        Weapons bowUpgrade1 = new Weapons("Bow Upgrade I - Fire-Tipped Arrows", ItemType.ATTACK, 10, 100, ClassType.CRIMSON_ARCHER);
        Weapons bowUpgrade2 = new Weapons("Bow Upgrade II - Silver-Tipped Arrows", ItemType.ATTACK, 15, 150, ClassType.CRIMSON_ARCHER);
        Weapons bowUpgrade3 = new Weapons("Bow Upgrade III - Reinforced String", ItemType.ATTACK, 20, 200, ClassType.CRIMSON_ARCHER);

// Red Bastion – Shield Upgrades
        Weapons shieldUpgrade1 = new Weapons("Shield Upgrade I - Reinforced Plating", ItemType.ATTACK, 10, 100, ClassType.RED_BASTION);
        Weapons shieldUpgrade2 = new Weapons("Shield Upgrade II - Runic Layer", ItemType.ATTACK, 15, 150, ClassType.RED_BASTION);
        Weapons shieldUpgrade3 = new Weapons("Shield Upgrade III - Impact Boost", ItemType.ATTACK, 20, 200, ClassType.RED_BASTION);

// Universal
        Weapons universalUpgrade = new Weapons("Universal Upgrade - Crimson Essence", ItemType.ATTACK, 12, 110, null);


        //Declaração da loja e adicição dos itens
        ArrayList shop = new ArrayList<>();
        shop.add(smallHealPotion);
        shop.add(mediumHealPotion);
        shop.add(bigHealPotion);
        shop.add(smallAttackBuff);
        shop.add(bigAttackBuff);
        shop.add(swordUpgrade1);
        shop.add(swordUpgrade2);
        shop.add(swordUpgrade3);
        shop.add(staffUpgrade1);
        shop.add(staffUpgrade2);
        shop.add(staffUpgrade3);
        shop.add(bowUpgrade1);
        shop.add(bowUpgrade2);
        shop.add(bowUpgrade3);
        shop.add(shieldUpgrade1);
        shop.add(shieldUpgrade2);
        shop.add(shieldUpgrade3);
        shop.add(universalUpgrade);

        //Adicionar a Array a loja da Taverna
        Shop actualShop = new Shop(shop);
        //itens random
        actualShop.randomItems();


// =====================
// Game Start
// =====================

        Hero player = Hero.chooseCharacter();

        Thread.sleep(1500);

        Hero.showClassWeaponDetails(player.getPlayer());

        Thread.sleep(1500);

        player.chooseYourName();

        Thread.sleep(1500);

        System.out.println("*You have received a starting bonus of 50 Noxian Crowns to begin your journey.*\n");
        player.setGold(1000);

        Thread.sleep(1000);

        player.heroDetails();

        Thread.sleep(2000);

        Game.Tavern.tavernMenu(actualShop, player);


    }
}
