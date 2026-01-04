package Game;

import java.util.ArrayList;
import java.util.Random;

public class Riddle {

    private String question;
    private int answer;
    private ArrayList<String> options;
    private boolean alreadyUsed;

    public Riddle(String question, int answer, ArrayList<String> options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.alreadyUsed = false;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public static ArrayList<Riddle> initRiddles() {

        ArrayList<Riddle> riddles = new ArrayList<>();

        riddles.add(new Riddle("What walks with its feet on its head?", 1,
                new ArrayList<>() {{
                    add("Louse");
                    add("Flea");
                    add("Ant");
                }}
        ));

        riddles.add(new Riddle("What gets bigger the more you take away?", 2,
                new ArrayList<>() {{
                    add("Bag");
                    add("Hole");
                    add("Void");
                }}
        ));

        riddles.add(new Riddle("What is born big and dies small?", 3,
                new ArrayList<>() {{
                    add("Candle");
                    add("Tree");
                    add("Pencil");
                }}
        ));

        riddles.add(new Riddle("What has a mouth but does not speak, and a bed but does not sleep?", 1,
                new ArrayList<>() {{
                    add("River");
                    add("Cave");
                    add("Valley");
                }}
        ));

        riddles.add(new Riddle("What goes up when the rain comes down?", 2,
                new ArrayList<>() {{
                    add("Cloud");
                    add("Umbrella");
                    add("River");
                }}
        ));

        riddles.add(new Riddle("What passes in front of the sun without casting a shadow?", 3,
                new ArrayList<>() {{
                    add("Cloud");
                    add("Bird");
                    add("Wind");
                }}
        ));

        riddles.add(new Riddle("What runs but never leaves its place?", 1,
                new ArrayList<>() {{
                    add("Clock");
                    add("River");
                    add("Person");
                }}
        ));

        riddles.add(new Riddle("What has teeth but cannot eat?", 2,
                new ArrayList<>() {{
                    add("Dog");
                    add("Comb");
                    add("Saw");
                }}
        ));

        riddles.add(new Riddle("What can be seen by everyone but cannot be touched?", 1,
                new ArrayList<>() {{
                    add("Shadow");
                    add("Air");
                    add("Light");
                }}
        ));

        riddles.add(new Riddle("What creature walks on four legs in the morning, two at noon, and three in the evening?", 3,
                new ArrayList<>() {{
                    add("Animal");
                    add("Old man");
                    add("Human");
                }}
        ));

        return riddles;
    }

    public static Riddle getRandomRiddle(ArrayList<Riddle> riddles) {
        Riddle randomRiddle = pickRandomRiddle(riddles);
        while (randomRiddle.alreadyUsed) {
            randomRiddle = pickRandomRiddle(riddles);
        }

        return randomRiddle;
    }

    private static Riddle pickRandomRiddle(ArrayList<Riddle> riddles){
        Random random = new Random();
        int index = random.nextInt(riddles.size());
        return riddles.get(index);
    }

    }

