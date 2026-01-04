package Game;
import java.util.Random;
import java.util.Scanner;
import Character.Hero;

public abstract class Tavern {

    public static void showTavernMenu(Hero hero) {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String YELLOW = "\u001B[33m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";

        System.out.println();
        System.out.println(RED + "                   ============================================" + RESET);
        System.out.println(RED + "                            N O X I A N   H E A R T H" + RESET);
        System.out.println(RED + "                                 T A V E R N" + RESET);
        System.out.println(RED + "                   ============================================" + RESET);
        System.out.println();
        System.out.println("                        " + CYAN + "What will you do, " + YELLOW + hero.getName() + CYAN + "?" + RESET);
        System.out.println();
        System.out.println("                            " + YELLOW + "[1]" + WHITE + " Enter the Shop" + RESET);
        System.out.println("                            " + YELLOW + "[2]" + WHITE + " Gambling Games" + RESET);
        System.out.println("                            " + YELLOW + "[3]" + WHITE + " Accept Missions" + RESET);
        System.out.println("                            " + YELLOW + "[4]" + WHITE + " Inspect Inventory" + RESET);
        System.out.println("                            " + YELLOW + "[5]" + WHITE + " Review Status" + RESET);
        System.out.println();
        System.out.println(RED + "                   ============================================" + RESET);
        System.out.println("                            " + CYAN + "Choose an option:" + RESET);
        System.out.println();

    }

    public static void gamblingGames(Shop shop, Hero hero) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("\nIt costs 15nc to try your luck and role the dice!");
        System.out.println("Will you take the gamble?");
        System.out.println("[1] Yes, I'm in  |  [2] No, I'm out\n");

        int choice = input.nextInt();
        if (hero.getGold() < 15) {
            System.out.println("Not enough noxian crowns to play!");
        } else {
            switch (choice) {
                case 1:
                    if (gamblingResult()) {
                        hero.setGold(hero.getGold() + 60);
                        System.out.println("\nVictory! Your bet was right, warrior! You earned 60 noxian crowns. You now have " + hero.getGold() + " nc\n");
                    } else {
                        hero.setGold(hero.getGold() - 15);
                        System.out.println("\nYou lost... luck was not on your side this time. You now have " + hero.getGold() + " nc\n");
                    }
                    Thread.sleep(2000);
                    return;

                case 2:
                    tavernMenu(shop, hero);
                    break;

                default:
                    System.out.println("Invalid option.\n");
                    tavernMenu(shop, hero);
                    break;
            }
        }
    }

    public static boolean gamblingResult() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        boolean result = false;

        System.out.println("Place your bet, warrior! Choose a number between 1 and 6:");
        int betNum = input.nextInt();

        Random r = new Random();

        Thread.sleep(2000);
        int dice = r.nextInt(6) + 1;
        System.out.println("The dice strikes the table and reveals: " + dice);

        if (betNum == dice) {
            result = true;
        }
        return result;
    }

    public static void tavernMenu(Shop shop, Hero hero) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int option = -1;

        while (option != 3) {
            showTavernMenu(hero);
            option = input.nextInt();

            switch (option) {
                case 1:
                    Shop.showShop(shop, hero);
                    break;
                case 2:
                    gamblingGames(shop, hero);
                    break;
                case 3:
                    Quest.questMenu(hero, shop);
                    break;
                case 4:
                    hero.heroInventory();
                    break;
                case 5:
                    hero.heroDetails();
                    break;
                default:
                    System.out.println("Inlavid option, choose again.\n");
            }
        }
    }
}