package Game;

import Item.Item;
import Character.Hero;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Item.Consumable;
import Item.Weapons;

public class Shop {
    private ArrayList<Item> allItems;

    public Shop(ArrayList<Item> allItems) {
        this.allItems = allItems;
    }


    //função de itens Random
    public ArrayList<Item> randomItems() {
        ArrayList<Item> result = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int index = r.nextInt(allItems.size());
            result.add(allItems.get(index));
        }

        return result;
    }

    //exibir loja
    public static void showShop(Shop shop, Hero hero) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== WELCOME TO THE SHOP =====\n");

            ArrayList<Item> todayStock = shop.randomItems();

            for (int i = 0; i < todayStock.size(); i++) {
                Item item = todayStock.get(i);

                System.out.print((i + 1) + " - " + item.getName());
                System.out.print(" | Effect: +" + item.getEffect());

                if (item instanceof Weapons) {
                    Weapons w = (Weapons) item;
                    if (w.getAbleToUse() != null) {
                        System.out.print(" | Class: " + w.getAbleToUse());
                    } else {
                        System.out.print(" | Class: All");
                    }
                }

                System.out.println(" | Price: " + item.getPrice() + "nc");
            }


            System.out.println("\nYou have: " + hero.getGold() + "nc");

            //compra do player
            System.out.print("\nChoose an item (1-" + todayStock.size() + "), or 0 to exit: ");

            choice = input.nextInt();

            if (choice == 0) {
                return;
            }

            if (choice < 0 || choice > todayStock.size()) {
                System.out.println("Invalid choice!");
                return;
            }

            Item selectedItem = todayStock.get(choice - 1);

            if (selectedItem instanceof Consumable) {

                if (selectedItem.getPrice() <= hero.getGold()) {
                    hero.setGold(hero.getGold() - selectedItem.getPrice());
                    hero.getInventory().add(selectedItem);
                    System.out.println(selectedItem.getName() + " added to your inventory");
                } else {
                    System.out.println("You don't have enough Noxian crowns.");
                }

            } else if (selectedItem instanceof Weapons) {

                Weapons weapon = (Weapons) selectedItem;

                if (weapon.getAbleToUse() == null || weapon.getAbleToUse().equals(hero.getPlayer())) {

                    if (selectedItem.getPrice() <= hero.getGold()) {
                        hero.setGold(hero.getGold() - selectedItem.getPrice());
                        hero.setAttack(hero.getAttack() + selectedItem.getEffect());
                        System.out.println(selectedItem.getName() + " upgraded your weapon in " + selectedItem.getEffect() + "+ atk");
                    } else {
                        System.out.println("You don't have enough Noxian crowns.");
                    }

                } else {
                    System.out.println("You cannot wield this weapon! Required class: "
                            + weapon.getAbleToUse());
                }
            }

        }
    }

}


