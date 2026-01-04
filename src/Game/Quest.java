package Game;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Character.Hero;
import Character.NPC;
import Enums.ItemType;
import Item.Consumable;
import helper.GameHelper;
import static Game.Tavern.tavernMenu;

/**
 * Centralizes all quest logic, battles and narrative flows of the game.
 * <p>
 * This class controls quest progression, battle systems, special encounters
 * and the final trial. Quest completion states are tracked through static flags.
 * </p>
 */
public abstract class Quest {


    static boolean quest1Complete = false;
    static boolean quest2Complete = false;
    static boolean quest3Complete = false;
    static boolean quest4Complete = false;
    static boolean quest5Complete = false;

    /**
     * Displays the quest selection menu and controls quest navigation.
     * <p>
     * Allows the player to choose available quests, access the tavern,
     * or initiate the final trial. Completed quests cannot be replayed.
     * </p>
     *
     * @param hero the current player character
     * @param shop the shop instance used for tavern interactions
     * @throws InterruptedException if execution is interrupted during timed events
     */
    public static void questMenu(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Great warrior " + hero.getName() + ", the path awaits. Choose your next journey.\n");
            GameHelper.printNoxusMap();

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    if (!quest1Complete) {
                        borderOfNoxus(hero, shop);
                    } else {
                        System.out.println(
                                "\nYou have already cleared the road of the bandit threat.\n"
                        );
                    }
                    break;

                case 2:
                    if (!quest2Complete) {
                        bloodRitual(hero, shop);
                    } else {
                        System.out.println("\nThe Crimson Ritual has already been stopped.\n");
                    }
                    break;

                case 3:
                    if (!quest3Complete) {
                        immortalBastionSwamp(hero, shop);
                    } else {
                        System.out.println("\nThe Immortal Bastion swamps have already been cleared.\n");
                    }
                    break;

                case 4:
                    if (!quest4Complete) {
                        crimsonGorge(hero, shop);
                    } else {
                        System.out.println("\nThe Crimson Gorge has already been sealed.\n");
                    }
                    break;

                case 5:
                    if (!quest5Complete) {
                        lostCitadelOfZaunMor(hero, shop);
                    } else {
                        System.out.println("\nThe Lost Citadel of Zaun-Mor has been conquered.\n");
                    }
                    break;

                case 6:
                    finalTrial(hero, shop);
                    break;

                case 7:
                    tavernMenu(shop, hero);
                    return;

                default:
                    System.out.println("Invalid choice, great warrior.");
            }
        }
    }


    /**
     * Starts a turn-based battle system between a hero and an enemy (NPC).
     * <p>
     * During the battle, the player can choose actions such as attacking,
     * using special abilities, consuming items, or running away. The battle
     * continues until the hero is defeated, the enemy is defeated, or the
     * player chooses to flee.
     * </p>
     *
     * @param hero  the player-controlled hero who participates in the battle
     * @param enemy the enemy NPC faced by the hero
     * @throws InterruptedException if the thread is interrupted during
     *                              the pauses between turns (Thread.sleep)
     */
    public static void startBattle(Hero hero, NPC enemy) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean battleOver = false;
        int initialAttack = hero.getAttack();


        System.out.println("\n===================================");
        System.out.println("           A BATTLE BEGINS");
        System.out.println("===================================");
        System.out.println(enemy.getName() + " appeared!\n");

        while (!battleOver) {

            System.out.println("----------- BATTLE STATUS -----------");
            System.out.println("Hero HP:  " + hero.getHp() + " / " + hero.getMaxHp() + "max");
            System.out.println("Enemy HP: " + enemy.getHp() + " / " + enemy.getMaxHp() + "max");
            System.out.println("--------------------------------\n");

            hero.heroDetails();

            //Player Turn
            System.out.println("\n----------- BATTLE MENU -----------");
            System.out.println("1. Attack");
            System.out.println("2. Special Ability");
            System.out.println("3. Use Potion");
            System.out.print("Choose your action: ");

            int choice = sc.nextInt();

            System.out.println("\n-------------------------------------");

            switch (choice) {
                case 1:
                    System.out.println("\nYou attack!");
                    hero.attackEnemy(enemy);
                    break;

                case 2:
                    if (Math.random() < 0.70) {
                        System.out.println("\nYou use " + hero.specialAbility);
                        hero.useSpecialAtk(enemy);
                    } else {
                        System.out.println("\nYou tried to use your special attack, but it failed!\n");
                    }
                    break;

                case 3:
                    hero.useConsumable();
                    continue;

                default:
                    System.out.println("Invalid choice!");
            }

            if (enemy.getHp() <= 0) {
                GameHelper.healMessage();
                hero.setAttack(initialAttack);
                hero.setHp(hero.getMaxHp());
                break;
            }

            //Enemy Turn
            System.out.println("\nWaiting for enemy response...\n");
            Thread.sleep(1000);


            if (Math.random() < 0.20) {
                enemy.useSpecialAtk(hero);
            } else {
                enemy.attackEnemy(hero);
            }

            Thread.sleep(1000);

            if (hero.getHp() <= 0) {
                hero.setAttack(initialAttack);
                System.out.println("\nYou have been defeated...");
                System.out.println("===================================");
                battleOver = true;
            }
        }
    }

    /**
     * Evaluates the outcome of a final quest battle.
     * <p>
     * Restores hero health, handles quest completion rewards
     * and level progression.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance (used for flow control)
     * @return {@code true} if the hero wins the battle, {@code false} otherwise
     * @throws InterruptedException if execution is interrupted
     */
    private static boolean checkFinalBattleResult(Hero hero, Shop shop) throws InterruptedException {

        if (hero.getHp() <= 0) {
            System.out.println(
                    "\nYou are forced to retreat.\n"
            );
            hero.setHp(hero.getMaxHp());
            return false;
        }

        System.out.println(
                "\nThe enemy falls.\n"
                        + "You complete your mission.\n"
        );
        hero.setHp(hero.getMaxHp());
        GameHelper.QuestComplete();
        Hero.levelUp(hero);

        return true;
    }

    /**
     * Executes a riddle-based battle against an enemy NPC.
     * <p>
     * Both hero and enemy have equal health. Correct answers
     * damage the enemy, while wrong answers damage the hero.
     * No items or abilities are allowed.
     * </p>
     *
     * @param hero  the player-controlled hero
     * @param enemy the enemy NPC presenting the riddles
     * @throws InterruptedException if interrupted during delays
     */
    public static void riddleBattle(Hero hero, NPC enemy) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        ArrayList<Riddle> riddles = Riddle.initRiddles();
        enemy.setMaxHp(hero.getMaxHp());
        enemy.setHp(hero.getHp());

        System.out.println("\n===================================");
        System.out.println("           RIDDLE BATTLE");
        System.out.println("===================================");
        System.out.println("A wild " + enemy.getName() + " appeared!\n");
        System.out.println("The enemy does not fight with weapons, but with knowledge." +
                "\nIt will challenge you with riddles.\nFor each correct answer, the enemy suffers 30 damage." +
                "\nFor each wrong answer, you suffer 30 damage.\nNo potions, abilities, or items can be used in this trial." +
                "\nChoose wisely — intelligence is your only weapon.\n");


        while (true) {

            // STATUS
            System.out.println("----------- BATTLE STATUS -----------");
            System.out.println("Hero HP:  " + hero.getHp() + " / " + hero.getMaxHp() + " max");
            System.out.println("Enemy HP: " + enemy.getHp() + " / " + enemy.getMaxHp() + " max");
            System.out.println("--------------------------------\n");

            // ENEMY TURN
            Riddle riddle = Riddle.getRandomRiddle(riddles);

            System.out.println(riddle.getQuestion());

            for (int i = 0; i < riddle.getOptions().size(); i++) {
                System.out.println((i + 1) + ". " + riddle.getOptions().get(i));
            }

            System.out.print("\nChoose your answer (1-3): ");
            int playerAnswer = input.nextInt();


            System.out.println("\nWaiting for enemy response...");
            Thread.sleep(800);

            // CHECK ANSWER (1–3)
            if (playerAnswer == riddle.getAnswer()) {
                System.out.println("Correct answer!");
                enemy.setHp(enemy.getHp() - 30);
                System.out.println("You outsmart the enemy and deal 30 damage.");
            } else {
                System.out.println("Wrong answer!");
                hero.takeDamage(30);
            }

            Thread.sleep(800);

            // END CONDITIONS
            if (enemy.getHp() <= 0) {
                System.out.println("\nYou defeated " + enemy.getName() + " with intelligence!");
                System.out.println("===================================");
                return;
            }

            if (hero.getHp() <= 0) {
                System.out.println("\nYou have been defeated...");
                System.out.println("===================================");
                return;
            }

            System.out.println("-------------------------------------\n");
        }
    }

    //=============== QUESTS ===============

    /**
     * Executes the "Border of Noxus" quest.
     * <p>
     * Includes exploration choices, random events and multiple battles.
     * Completion unlocks further quests.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance for retreat handling
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void borderOfNoxus(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        hero.setHp(hero.getMaxHp());

        NPC BanditScout = new NPC("Bandit Scout", 55, 55, 6, "quick stab", 10, 1);
        NPC BanditBruiser = new NPC("Bandit Bruiser", 90, 90, 10, "heavy punch", 18, 1);

        System.out.println(
                "\nYou advance along the dusty roads of Noxus.\n"
                        + "The sky hangs heavy, and rumors speak of bandits stalking travelers.\n"
        );

        Thread.sleep(1000);

        // ================= FIRST EVENT =================
        System.out.println(
                "\nThe road splits ahead. What do you do?\n"
        );

        System.out.println(
                "[1] Take the left path\n"
                        + "[2] Help a wounded villager\n"
                        + "[3] Search the area\n"
        );

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println(
                        "\nA hidden thug ambushes you!\n"
                );
                hero.removeGold(15);
                System.out.println("He stole 15 Noxian Crowns.\n");
                break;

            case 2:
                System.out.println(
                        "\nThe villager rewards your kindness.\n"
                );
                hero.addGold(50);
                System.out.println("You receive 50 Noxian Crowns!\n");
                break;

            case 3:
                System.out.println(
                        "\nYou find a small stash of Crowns.\n"
                );
                hero.addGold(15);
                System.out.println("You receive 15 Noxian Crowns!\n");
                break;

            default:
                System.out.println(
                        "\nYou hesitate and move on.\n"
                );
                break;
        }

        Thread.sleep(1000);

        // ================= FIRST BATTLE =================
        System.out.println(
                "\nMovement in the bushes draws your attention.\n"
                        + BanditScout.getName() + " lunges at you!\n"
        );

        System.out.println(
                "[1] Fight\n"
                        + "[2] Retreat\n"
        );

        choice = input.nextInt();

        if (choice == 2) {
            return;
        }

        startBattle(hero, BanditScout);

        if (hero.getHp() <= 0) {
            System.out.println(
                    "\nThe bandit overwhelms you.\n"
            );
            return;
        }

        System.out.println(
                "\nYou defeat the scout and press onward.\n"
        );
        hero.setHp(hero.getMaxHp());

        Thread.sleep(1000);

        // ================= SECOND EVENT =================
        System.out.println(
                "\nFurther ahead, tension fills the air.\n"
                        + "What do you investigate?\n"
        );

        System.out.println(
                "[1] Dark shadows\n"
                        + "[2] Distant noise\n"
                        + "[3] Rest briefly\n"
        );

        choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println(
                        "\nA trap snaps shut!\n"
                );
                hero.takeDamage(35);
                System.out.println("Current HP: " + hero.getHp() + "\n");
                break;

            case 2:
                System.out.println(
                        "\nA dying soldier hands you his pouch.\n"
                );
                hero.addGold(60);
                System.out.println("You receive 60 Noxian Crowns!\n");
                break;

            case 3:
                System.out.println(
                        "\nYou steady yourself and recover supplies.\n"
                );
                hero.addGold(20);
                System.out.println("You receive 20 Noxian Crowns!\n");
                break;

            default:
                System.out.println(
                        "\nYou move on cautiously.\n"
                );
                break;
        }

        Thread.sleep(1000);

        // ================= FINAL BOSS =================
        System.out.println(
                "\n========================================\n"
                        + "A massive figure steps onto the road.\n"
                        + "The Boss " + BanditBruiser.getName() + " blocks your path.\n"
                        + "========================================\n"
        );

        System.out.println(
                "[1] Fight\n"
                        + "[2] Retreat\n"
        );

        choice = input.nextInt();

        if (choice == 2) {
            return;
        }

        startBattle(hero, BanditBruiser);


        if (checkFinalBattleResult(hero, shop)) {
            quest1Complete = true;
        }
    }

    /**
     * Executes the "Blood Ritual" quest.
     * <p>
     * Features random underground events, cultist encounters
     * and a final boss battle tied to blood magic.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance for retreat handling
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void bloodRitual(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        hero.setHp(hero.getMaxHp());

        // Enemies
        NPC CrimsonAcolyte = new NPC("Crimson Acolyte", 70, 70, 8, "blood bolt", 15, 1);
        NPC BloodLeech = new NPC("Blood Leech", 40, 40, 5, "life drain", 8, 1);
        NPC AcolyteOfVladimir = new NPC("Acolyte of Vladimir", 140, 140, 12, "blood slash", 25, 2);

        // ================= INTRO =================
        System.out.println(
                "\nBeneath the iron streets of Noxus, rumors speak of a forbidden gathering.\n"
                        + "The Crimson Circle prepares a ritual — fueled by blood and devotion.\n"
                        + "Those who vanish near the old catacombs are never seen again.\n"
        );

        Thread.sleep(1000);

        // ================= RANDOM EVENT =================
        Random random = new Random();
        int randomEvent = random.nextInt(3);

        System.out.println(
                "\nYou move deeper into the underground halls of Noxus,\n"
                        + "the air heavy with blood magic...\n"
        );

        switch (randomEvent) {
            case 0:
                System.out.println(
                        "\nForbidden chants echo through the tunnels.\n"
                                + "A surge of blood magic erupts toward you!\n"
                );
                hero.takeDamage(25);
                System.out.println("Current HP: " + hero.getHp() + "\n");

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }
                break;

            case 1:
                System.out.println(
                        "\nA fresh trail of blood stains the stone floor.\n"
                                + "You discover a pouch dropped by fleeing cultists.\n"
                );
                hero.addGold(30);
                System.out.println("You gain 30 Noxian Crowns.\n");
                break;

            case 2:
                System.out.println(
                        "\nYou remain hidden in the shadows.\n"
                                + "The cultists leave a few scattered coins behind.\n"
                );
                hero.addGold(15);
                System.out.println("You gain 15 Noxian Crowns.\n");
                break;

            default:
                System.out.println("Something feels off...");
                break;
        }

        Thread.sleep(1000);

        // ================= FIRST BATTLE =================
        System.out.println(
                "\nA hooded figure slowly turns toward you.\n"
                        + CrimsonAcolyte.getName() + " raises his hand.\n"
        );

        System.out.println("[1] Confront him\n[2] Retreat to safety");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                startBattle(hero, CrimsonAcolyte);

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }

                System.out.println(
                        "\nThe Crimson Acolyte collapses.\n"
                                + "You press forward.\n"
                );
                hero.setHp(hero.getMaxHp());
                break;

            case 2:
                tavernMenu(shop, hero);
                return;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        Thread.sleep(1000);

        // ================= INTERMEDIATE EVENT =================
        System.out.println(
                "\nThe blood beneath your feet begins to shift,\n"
                        + "alive with dark intent.\n"
        );

        System.out.println("[1] Attempt to destroy it\n[2] Move carefully");
        choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println(
                        "\nThe blood reacts violently,\n"
                                + "forming sharp blades that tear into you!\n"
                );
                hero.takeDamage(20);
                System.out.println("Current HP: " + hero.getHp() + "\n");

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }
                break;

            case 2:
                System.out.println(
                        "\nFrom the pool of blood,\n"
                                + "a grotesque creature takes shape!\n"
                );
                startBattle(hero, BloodLeech);

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }

                hero.setHp(hero.getMaxHp());
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        Thread.sleep(1000);

        // ================= FINAL BOSS =================
        System.out.println(
                "\n========================================\n"
                        + "At the heart of the ritual chamber stands:\n"
                        + AcolyteOfVladimir.getName() + "\n"
                        + "========================================\n"
        );

        System.out.println("[1] Interrupt the ritual\n[2] Retreat to the Tavern");
        choice = input.nextInt();

        switch (choice) {
            case 1:
                startBattle(hero, AcolyteOfVladimir);

                if (checkFinalBattleResult(hero, shop)) {
                    quest2Complete = true;
                }
                return;

            case 2:
                tavernMenu(shop, hero);
                return;

            default:
                System.out.println("Invalid choice.");
                return;
        }
    }

    /**
     * Executes the "Immortal Bastion Swamp" quest.
     * <p>
     * Includes random swamp hazards, repeated encounters
     * and a chance-based final boss appearance.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance for retreat handling
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void immortalBastionSwamp(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int fullHp = hero.getMaxHp();
        hero.setHp(hero.getMaxHp());

        // Enemies
        NPC ToxicSludge = new NPC("Toxic Sludge", 130, 130, 11, "acid spit", 22, 2);
        NPC CorruptedBeast = new NPC("Corrupted Beast", 190, 190, 14, "wild maul", 28, 2);
        NPC ChemBaronRenegadeCaptain = new NPC(
                "Chem-Baron Renegade Captain", 260, 260, 18, "toxic cleaver", 40, 3
        );

        // ================= INTRO =================
        System.out.println(
                "\nYou arrive at the swamps surrounding the Immortal Bastion.\n"
                        + "The water is black, thick, and bubbling with chemical waste.\n"
                        + "Noxus has attempted to purge this land before — without success.\n"
        );

        Thread.sleep(1000);

        // ================= INITIAL RANDOM EVENT =================
        int randomEvent = random.nextInt(3);

        switch (randomEvent) {
            case 0:
                System.out.println(
                        "\nA toxic cloud rises from the swamp,\n"
                                + "burning your lungs as you struggle to breathe!\n"
                );
                hero.takeDamage(30);
                System.out.println("Current HP: " + hero.getHp() + "\n");

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }
                break;

            case 1:
                System.out.println(
                        "\nYou discover the remains of an old Noxian outpost.\n"
                                + "Among the wreckage, a few Crowns still remain.\n"
                );
                hero.addGold(35);
                break;

            case 2:
                System.out.println(
                        "\nAn eerie silence settles over the swamp.\n"
                                + "You steel your mind for what lies ahead.\n"
                );
                break;

            default:
                System.out.println("The swamp feels unsettling...");
                break;
        }

        Thread.sleep(1000);

        // ================= RANDOM ENCOUNTERS =================
        int encounters = random.nextInt(2) + 1;

        for (int i = 0; i < encounters; i++) {

            NPC enemy;
            int chances = random.nextInt(100);

            if (chances < 60) {
                enemy = ToxicSludge;
            } else {
                enemy = CorruptedBeast;
            }

            System.out.println(
                    "\nSomething stirs beneath the contaminated water...\n"
                            + enemy.getName() + " rises from the swamp!\n"
            );

            System.out.println("[1] Fight\n[2] Retreat to the Tavern");
            int choice = input.nextInt();

            if (choice == 2) {
                tavernMenu(shop, hero);
                return;
            } else if (choice != 1) {
                System.out.println("Invalid choice.");
                tavernMenu(shop, hero);
                return;
            }

            startBattle(hero, enemy);

            if (hero.getHp() <= 0) {
                tavernMenu(shop, hero);
                return;
            }

            System.out.println(
                    "\nYou defeat the creature,\n"
                            + "but the swamp remains hostile and unforgiving.\n"
            );

            enemy.setHp(enemy.getMaxHp());
            hero.setHp(fullHp);

            Thread.sleep(1000);
        }

        // ================= BOSS CHANCE =================
        int bossAppears = random.nextInt(100);

        if (bossAppears > 60) {
            System.out.println(
                    "\nAfter hours of struggle,\n"
                            + "the swamp finally grows quiet.\n"
                            + "The purge was only partial — but sufficient for now.\n"
            );
            GameHelper.QuestComplete();
            Hero.levelUp(hero);
            questMenu(hero, shop);
            return;
        }

        // ================= FINAL BOSS =================
        System.out.println(
                "\n========================================\n"
                        + "The ground trembles.\n"
                        + "From the depths of the swamp emerges:\n"
                        + ChemBaronRenegadeCaptain.getName() + "\n"
                        + "========================================\n"
        );

        System.out.println("[1] Confront the Captain\n[2] Retreat to the Tavern");
        int choice = input.nextInt();

        if (choice == 2) {
            tavernMenu(shop, hero);
            return;
        } else if (choice != 1) {
            System.out.println("Invalid choice.");
            tavernMenu(shop, hero);
            return;
        }

        startBattle(hero, ChemBaronRenegadeCaptain);

        if (hero.getHp() <= 0) {
            tavernMenu(shop, hero);
            return;
        }

        if (checkFinalBattleResult(hero, shop)) {
            quest3Complete = true;
        }
    }

    /**
     * Executes the "Crimson Gorge" quest.
     * <p>
     * The player must choose between two distinct paths,
     * each with unique challenges, leading to a final boss.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance for retreat handling
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void crimsonGorge(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int fullHp = hero.getMaxHp();
        hero.setHp(hero.getMaxHp());

        // Enemies
        NPC ArcaneWraith = new NPC("Arcane Wraith", 150, 150, 14, "arcane burn", 28, 2);
        NPC ZaunSentinelArmor = new NPC(
                "Zaun Sentinel Armor", 220, 220, 16, "gauntlet smash", 32, 3
        );
        NPC BrokenHexCoreGolem = new NPC(
                "Broken Hex Core Golem", 320, 320, 20, "core burst", 45, 3
        );

        // Bonus Item
        Consumable mediumHealPotion = new Consumable("Medium Heal Potion", ItemType.HEAL, 25, 30);

        System.out.println(
                "\nAt the heart of the Crimson Gorge, two unstable paths reveal themselves.\n"
                        + "One pulses with raw arcane energy.\n"
                        + "The other echoes with Zaunite machinery.\n"
        );

        System.out.println("[1] Arcane Path\n[2] Zaun Path");
        int choice = input.nextInt();
        int pathEvent = random.nextInt(2);

        switch (choice) {

            // ================= ARCANE PATH =================
            case 1:
                System.out.println(
                        "\nYou step into a chamber where crimson arcane forces distort reality.\n"
                );

                hero.setMaxHp(90);
                hero.setHp(90);

                System.out.println(
                        "An Arcane Wraith materializes, testing your will...\n"
                );

                riddleBattle(hero, ArcaneWraith);

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }

                System.out.println(
                        "\nThe wraith dissipates, leaving arcane energy behind.\n"
                );
                hero.addItem(mediumHealPotion);
                System.out.println("A medium heal potion was added to your inventory.\n");

                hero.setMaxHp(fullHp);
                hero.setHp(fullHp);
                break;

            // ================= ZAUN PATH =================
            case 2:
                System.out.println(
                        "\nYou descend into a corridor filled with steam, pipes, and toxic residue.\n"
                );

                if (pathEvent == 0) {
                    System.out.println(
                            "A pressurized pipe bursts, spraying corrosive chemicals!\n"
                    );
                    hero.takeDamage(25);
                    System.out.println("Current HP: " + hero.getHp() + "\n");

                    if (hero.getHp() <= 0) {
                        tavernMenu(shop, hero);
                        return;
                    }
                } else {
                    System.out.println(
                            "You salvage usable chems from abandoned machinery.\n"
                    );
                    hero.addGold(30);
                    System.out.println("You gain 30 Noxian Crowns.\n");
                }

                Thread.sleep(2000);

                System.out.println(
                        "\n========================================\n"
                                + ZaunSentinelArmor.getName() + " activates, blocking your advance!\n"
                                + "========================================\n"
                );

                Thread.sleep(2000);

                startBattle(hero, ZaunSentinelArmor);

                if (hero.getHp() <= 0) {
                    tavernMenu(shop, hero);
                    return;
                }

                System.out.println(
                        "\nWell done, warrior.\n"
                                + "The sentinel collapses, its systems failing.\n"
                );
                hero.setHp(fullHp);
                break;

            default:
                System.out.println("\nInvalid choice.\n");
                tavernMenu(shop, hero);
                return;
        }

        // ================= FINAL BOSS =================
        System.out.println(
                "\n========================================\n"
                        + "The Crimson Gorge trembles violently.\n"
                        + "A massive construct rises from the depths.\n"
                        + BrokenHexCoreGolem.getName() + " awakens,\n"
                        + "its Hex Core blazing uncontrollably.\n"
                        + "========================================\n"
        );

        System.out.println("[1] Fight\n[2] Retreat to the Tavern");
        choice = input.nextInt();

        if (choice == 2) {
            tavernMenu(shop, hero);
            return;
        } else if (choice != 1) {
            System.out.println("Invalid choice.");
            tavernMenu(shop, hero);
            return;
        }

        startBattle(hero, BrokenHexCoreGolem);

        if (checkFinalBattleResult(hero, shop)) {
            quest4Complete = true;
        }
    }

    /**
     * Executes the "Lost Citadel of Zaun-Mor" quest.
     * <p>
     * Features branching paths, resurrection mechanics
     * and a powerful drake boss encounter.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance for retreat handling
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void lostCitadelOfZaunMor(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        hero.setHp(hero.getMaxHp());

        // Enemies
        NPC CrimsonDrake = new NPC("Crimson Drake", 180, 180, 16, "ember spit", 30, 2);
        NPC RaptorBloodfang = new NPC("Raptor Bloodfang", 170, 170, 18, "razor dive", 32, 3);
        NPC ElderRedDrake = new NPC("Elder Red Drake", 360, 360, 24, "inferno roar", 55, 4);

        // Items
        Consumable smallHealPotion = new Consumable("Small Heal Potion", ItemType.HEAL, 15, 15);
        Consumable mediumHealPotion = new Consumable("Medium Heal Potion", ItemType.HEAL, 25, 30);

        // ================= INTRO =================
        System.out.println(
                "\nYou stand before the ruins of Zaun-Mor —\n"
                        + "a lost citadel swallowed by fire and corruption.\n"
                        + "Crimson-scaled creatures circle above.\n"
        );

        Thread.sleep(1000);

        // ================= PATH CHOICE =================
        System.out.println(
                "[1] Ascend the shattered battlements\n"
                        + "[2] Descend into the molten tunnels\n"
        );

        int choice = input.nextInt();
        int randomEvent = random.nextInt(2);

        switch (choice) {

            case 1:
                System.out.println("\nYou climb the broken battlements.\n");
                if (randomEvent == 0) {
                    hero.takeDamage(25);
                    System.out.println("Firestorm scorches you! HP: " + hero.getHp());
                } else {
                    hero.addItem(smallHealPotion);
                    System.out.println("You found a Small Heal Potion.\n");
                }
                break;

            case 2:
                System.out.println("\nYou descend into molten tunnels.\n");
                if (randomEvent == 0) {
                    hero.takeDamage(20);
                    System.out.println("Toxic fumes burn your lungs! HP: " + hero.getHp());
                } else {
                    hero.addGold(40);
                    System.out.println("You gain 40 Crowns.\n");
                }
                break;

            default:
                return;
        }

        Thread.sleep(1000);

        // ================= FIRST BATTLE =================
        NPC firstEnemy;
        int randomEnemy = random.nextInt(2);

        if (randomEnemy == 0) {
            firstEnemy = CrimsonDrake;
        } else {
            firstEnemy = RaptorBloodfang;
        }

        System.out.println(
                "\n" + firstEnemy.getName() + " descends upon you!\n"
        );

        startBattle(hero, firstEnemy);

        if (hero.getHp() <= 0) {
            return;
        }

        // ================= RESURRECTION MECHANIC =================
        int resurrection = random.nextInt(100);

        if (resurrection < 30) {
            System.out.println(
                    "\nThe creature ignites in crimson flames...\n"
                            + "It rises once more!\n"
            );

            firstEnemy.setHp(firstEnemy.getMaxHp() / 2);
            startBattle(hero, firstEnemy);

            if (hero.getHp() <= 0) {
                return;
            }
        }

        System.out.println(
                "\nThe creature finally falls.\n"
        );

        hero.addItem(mediumHealPotion);
        System.out.println("You receive a Medium Heal Potion.\n");

        Thread.sleep(1000);

        // ================= FINAL BOSS =================
        System.out.println(
                "\n========================================\n"
                        + ElderRedDrake.getName() + " descends from the sky!\n"
                        + "========================================\n"
        );

        System.out.println(
                "[1] Face the Elder Drake\n"
                        + "[2] Retreat\n"
        );

        choice = input.nextInt();

        if (choice != 1) {
            return;
        }

        startBattle(hero, ElderRedDrake);

        if (checkFinalBattleResult(hero, shop)) {
            quest5Complete = true;
        }
    }

    /**
     * Executes the Final Trial of Noxus.
     * <p>
     * The hero faces the three members of the Trifarix in sequence:
     * Strength, Cunning and Fear. Choices made here define the final ending.
     * </p>
     *
     * @param hero the player-controlled hero
     * @param shop the shop instance (used for flow control)
     * @throws InterruptedException if interrupted during narrative delays
     */
    public static void finalTrial(Hero hero, Shop shop) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        hero.setHp(hero.getMaxHp());
        int fullHP = hero.getMaxHp();

        //ENEMIES
        NPC VladimirBoss = new NPC(
                "Vladimir – Trifarix of Strength", 420, 420, 26, "blood spear", 60, 4
        );
        NPC LeBlancBoss = new NPC(
                "LeBlanc – Trifarix of Cunning", 120, 120, 22, "illusory strike", 45, 4
        );
        NPC MordekaiserBoss = new NPC(
                "Mordekaiser – Trifarix of Fear", 520, 520, 30, "iron mace", 75, 5
        );

        if (hero.getLevel() < 3) {
            System.out.println(
                    "This path is reserved for proven warriors.\n"
                            + "Reach at least Level 3 to face the Trifarix.\n"
            );
            Thread.sleep(2000);
            return;
        }

        // INTRO GERAL
        System.out.println(
                "You step forward into the Final Trial.\n\n"
                        + "This is not a mission.\n"
                        + "This is not a conquest.\n\n"
                        + "This is judgment.\n\n"
                        + "Before you stand the Trifarix — the pillars of Noxian dominion.\n"
                        + "Strength. Cunning. Fear.\n\n"
                        + "There will be no retreat.\n"
                        + "There will be no mercy.\n"
                        + "You will face all three — or be erased.\n"
        );
        Thread.sleep(1500);

        // 1) VLADIMIR – STRENGTH
        System.out.println(
                "The air grows heavy with the scent of iron.\n"
                        + "Crimson sigils form as blood bends to unseen will.\n\n"
                        + "A pale noble steps forward, eyes glowing with ancient power.\n"
                        + "Vladimir — Trifarix of Strength.\n\n"
                        + "\"Power is not taken. It is cultivated — in blood.\"\n"
        );
        Thread.sleep(1500);

        startBattle(hero, VladimirBoss);

        if (hero.getHp() <= 0) {
            System.out.println(
                    "Your strength is drained.\n"
                            + "Vladimir turns away as your blood feeds the Trial.\n"
            );
            return;
        }

        System.out.println(
                "Vladimir staggers.\n"
                        + "The crimson sigils unravel as his form dissolves into a storm of blood.\n"
                        + "For the first time, the Trifarix loses a pillar — and it will not return.\n"
        );

        GameHelper.printDivider();

        System.out.println(
                "\nUniversal Upgrade acquired: Crimson Essence.\n"
                        + "The upgrade has been permanently integrated into your weapon.\n"
                        + "Attack Damage increased by 12.\n"
        );
        hero.setAttack(hero.getAttack() + 12);
        hero.setHp(hero.getHp());

        GameHelper.printDivider();
        Thread.sleep(1500);

        // 2) LEBLANC – CUNNING
        System.out.println(
                "\nSlow applause echoes.\n"
                        + "Reality fractures into layered illusions.\n\n"
                        + "A figure emerges from deception itself.\n"
                        + "LeBlanc — Trifarix of Cunning.\n\n"
                        + "\"You seek truth?\"\n"
                        + "\"Truth is the first lie you believed.\"\n"
        );
        Thread.sleep(1500);

        riddleBattle(hero, LeBlancBoss);

        if (hero.getHp() <= 0) {
            System.out.println(
                    "Your mind collapses beneath endless deception.\n"
                            + "LeBlanc vanishes, her laughter lingering in the broken illusions.\n"
            );
            return;
        }

        System.out.println(
                "The illusions shatter.\n"
                        + "LeBlanc's smile fades as her form scatters into mirrored fragments.\n"
                        + "For now, Cunning yields — but you can feel her eyes on you still.\n"
        );
        hero.setHp(fullHP);
        hero.setMaxHp(fullHP);
        Thread.sleep(1500);

        // 3) MORDEKAISER – FEAR
        System.out.println(
                "The arena twists into a realm of cold iron and dying stars.\n"
                        + "Each breath becomes a weight, each heartbeat a fading drum.\n\n"
                        + "An armored colossus emerges, every step a funeral bell.\n"
                        + "Mordekaiser — Trifarix of Fear.\n\n"
                        + "\"All things kneel to death.\"\n"
        );
        Thread.sleep(1500);

        startBattle(hero, MordekaiserBoss);

        if (hero.getHp() <= 0) {

            System.out.println(
                    "Your body collapses, shattered beneath iron and hatred.\n"
                            + "Darkness claims your sight.\n\n"
                            + "But death does not come.\n"
            );
            Thread.sleep(1500);

            System.out.println(
                    "Cold chains coil around your soul.\n"
                            + "A voice echoes from beyond flesh and time.\n\n"
                            + "\"You endured what countless warriors could not.\"\n"
                            + "\"Kneel — and I shall spare you.\"\n\n"
                            + "Mordekaiser extends his will.\n"
                            + "He offers you a place among the Trifarix.\n"
            );
            Thread.sleep(1500);

            System.out.println(
                    "[1] Accept the Pact — Serve Mordekaiser\n"
                            + "[2] Refuse — Face Oblivion\n"
            );

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(
                            "\nYou surrender your mortal defiance.\n"
                                    + "Chains bind not your body — but your fate.\n\n"
                                    + "Mordekaiser laughs.\n"
                                    + "\"Rise. You are Trifarix now.\"\n"
                    );
                    Thread.sleep(1500);

                    System.out.println(
                            "The seat once held by Vladimir lies empty no longer.\n"
                                    + "The Trifarix is remade:\n\n"
                                    + "Mordekaiser.\n"
                                    + "LeBlanc.\n"
                                    + hero.getName() + ".\n\n"
                                    + "Your name is carved into the iron will of Noxus — forever.\n"
                    );
                    Thread.sleep(1500);

                    System.out.println("=== ENDING: TRIFARIX ASCENDANT ===");
                    System.exit(0);

                case 2:
                    System.out.println(
                            "\nYou defy him with your last breath.\n"
                                    + "Mordekaiser tightens the chains.\n\n"
                                    + "\"Then be forgotten.\"\n"
                    );
                    Thread.sleep(1500);

                    System.out.println(
                            "Your soul is crushed into the void between worlds.\n"
                                    + "The Final Trial must be faced again.\n"
                    );
                    return;

                default:
                    System.out.println(
                            "Your silence is taken as defiance.\n"
                                    + "The chains tighten.\n"
                    );
                    return;
            }
        }

        System.out.println(
                "The colossus staggers.\n"
                        + "Cracks spread across his iron shell as stolen souls burst free in screams and song.\n\n"
                        + "With a final blow, Mordekaiser falls.\n"
                        + "Fear itself bows before you.\n"
        );
        Thread.sleep(1500);

        System.out.println(
                "Before you, the Trifarix lies broken.\n"
                        + "Strength erased.\n"
                        + "Cunning humbled.\n"
                        + "Fear shattered.\n\n"
                        + "Noxus will remember this day.\n"
                        + "And it will remember your name: " + hero.getName() + ".\n"
        );
        Thread.sleep(1500);

        System.out.println("=== ENDING: NOXIAN LEGEND ===");
        System.exit(0);
    }

}




