package helper;


/**
 * Utility class containing helper methods for displaying
 * game-related messages, dividers and maps.
 */
public class GameHelper {

    public static void printDivider() {
        System.out.println("\n------------------------------------------------------------\n");
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
                    "                                                ............... .                                             \n" +
                            "                                            ....                 ......                                       \n" +
                            "                                         ..                           ..                                      \n" +
                            "                                     ...                                .                                     \n" +
                            "                                  . .                                    ..... ..                             \n" +
                            "                                .           " + GRAY + "•THE LOST CITADEL OF ZAUN-MOR•" + RESET + "          .....                         \n" +
                            "           .... .           . .                                                      ....                     \n" +
                            "     .. ..        . ..  . ..                                                             .. ....              \n" +
                            "   ...                ..                                                                        . .          \n" +
                            "  .                                                                                                 ...      \n" +
                            " .                         " + RED + "•THE CRIMSON GORGE•" + RESET + "                                                    ..     \n" +
                            ".                                                                                                        .   \n" +
                            ".                                                                                                         .. \n" +
                            ".                                                                            " + DARK_RED + "•TRIFARIX — FINAL TRIAL•" + RESET + "       .\n" +
                            ".                                                                                                            .\n" +
                            ".                                                                                                            .\n" +
                            " .                                                                                                           .\n" +
                            " ..          " + GRAY + "•BLOOD RITUAL•" + RESET + "                  " + GREEN + "•NOXUS HEART TAVERN•" + RESET + "                                      ....\n" +
                            "  ..                                                                                               .. . ..   \n" +
                            "   ...                                                                                                    ..         \n" +
                            "     ..                                                                                                    .          \n" +
                            "       ..                                                                                               .          \n" +
                            "         ..                                                                                       ..         \n" +
                            "          ..                                                                                       .         \n" +
                            "           ..                                                                                      ..        \n" +
                            "            .                                                                                       .        \n" +
                            "            .                                                                                       .        \n" +
                            "            .                                                                                       .        \n" +
                            "             .                                                           " + GRAY + "•BORDERS OF NOXUS•" + RESET + "            .     \n" +
                            "              ..           " + RED + "•IMMORTAL BASTION SWAMP•" + RESET + "                                                   ..\n" +
                            "               ..                                                                                 .          \n" +
                            "                 ...                                                                            ..           \n" +
                            "                    ..                                                               ....... ....            \n" +
                            "                     ...                                                           ...                       \n" +
                            "                       ...                                                         .                         \n" +
                            "                         ..                                                        .                         \n" +
                            "                          ..            ....................                         .                         \n" +
                            "                            .............                 ......                 ...                         \n" +
                            "                                                                .... .  . .  . ..                            \n"
            );

            // LEGEND
            System.out.println(
                    "\n===================== QUESTS =====================\n" +
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
