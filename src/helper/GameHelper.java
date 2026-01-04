package helper;



public class GameHelper {

    public static final String SCENE_DIVIDER =
            "\n------------------------------------------------------------\n";


    public static void printDivider() {
        System.out.println(SCENE_DIVIDER);
    }

    public static void healMessage() {
        System.out.println("\n=================================   HEAL   ========================================");
        System.out.println("Passive Activated: Heroic Bravery! Your wounds regenerate after a victorious battle.");
        System.out.println("====================================================================================\n");
    }

    public static void printNoxusMap() {
        // NOXIAN COLOR PALETTE
        String RED = "\u001B[31m";
        String DARK_RED = "\u001B[38;5;88m";
        String GRAY = "\u001B[37m";
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        System.out.println(
                "                                                xxxxxxxxxxxxxxx x                                             \n" +
                        "                                            xxxx                 xxxxxx                                       \n" +
                        "                                         xx                           xx                                      \n" +
                        "                                     xxx                                x                                     \n" +
                        "                                  x x                                    xxxxx xx                             \n" +
                        "                                x           " + GRAY + "•THE LOST CITADEL OF ZAUN-MOR•" + RESET + "          xxxxx                         \n" +
                        "           xxxx x           x x                                                      xxxx                     \n" +
                        "     xx xx        x xx  x xx                                                             xx xxxx              \n" +
                        "   xxx                xx                                                                        x x          \n" +
                        "  x                                                                                                 xxx      \n" +
                        " x                         " + RED + "•THE CRIMSON GORGE•" + RESET + "                                                    xx     \n" +
                        "x                                                                                                        x   \n" +
                        "x                                                                                                         xx \n" +
                        "x                                                                            " + DARK_RED + "•TRIFARIX — FINAL TRIAL•" + RESET + "       x\n" +
                        "x                                                                                                            x\n" +
                        "x                                                                                                            x\n" +
                        " x                                                                                                           x\n" +
                        " xx          " + GRAY + "•BLOOD RITUAL•" + RESET + "                  " + GREEN + "•NOXUS HEART TAVERN•" + RESET + "                                      xxxx\n" +
                        "  xx                                                                                               xx x xx   \n" +
                        "   xxx                                                                                                                     xx         \n" +
                        "     xx                                                                                                                     x          \n" +
                        "       xx                                                                                                                   x          \n" +
                        "         xx                                                                                       xx         \n" +
                        "          xx                                                                                       x         \n" +
                        "           xx                                                                                      xx        \n" +
                        "            x                                                                                       x        \n" +
                        "            x                                                                                       x        \n" +
                        "            x                                                                                       x        \n" +
                        "             x                                                           " + GRAY + "•BORDERS OF NOXUS•" + RESET + "x     \n" +
                        "              xx           " + RED + "•IMMORTAL BASTION SWAMP•" + RESET + "                                      xx\n" +
                        "               xx                                                                                 x          \n" +
                        "                 xxx                                                                            xx           \n" +
                        "                    xx                                                               xxxxxxx xxxx            \n" +
                        "                     xxx                                                           xxx                       \n" +
                        "                       xxx                                                         x                         \n" +
                        "                         xx                                                        x                         \n" +
                        "                          xx            xxxxxxxxxxxxxxxxxx                         x                         \n" +
                        "                            xxxxxxxxxxxxx                 xxxxxx                 xxx                         \n" +
                        "                                                                xxxx x  x x  x xx                            \n"
        );

// LEGEND
        System.out.println(
                "\n===================== SUMMARY =====================\n" +
                        "[1] " + GRAY + "BORDERS OF NOXUS — BANDIT REBELLION" + RESET + "\n" +
                        "[2] " + GRAY + "BLOOD RITUAL — ACOLYTE OF VLADIMIR" + RESET + "\n" +
                        "[3] " + RED  + "IMMORTAL BASTION SWAMP — CHEM-BARON CAPTAIN" + RESET + "\n" +
                        "[4] " + RED  + "THE CRIMSON GORGE — ADVANCED DANGER ZONE" + RESET + "\n" +
                        "[5] " + RED + "THE LOST CITADEL OF ZAUN-MOR — HIGH THREAT" + RESET + "\n" +
                        "[6] " + DARK_RED + "TRIFARIX — FINAL TRIAL" + RESET + "\n" +
                        "[7] " + GREEN + "NOXUS HEART TAVERN" + RESET + "\n" +
                        "==================================================\n"
        );


    }

    public static void QuestComplete(){
        System.out.println("\n========================================");
        System.out.println("             QUEST COMPLETE             ");
        System.out.println("========================================\n");
    }
}
