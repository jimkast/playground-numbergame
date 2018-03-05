package play.numbergame.procedural;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Game {

    public static void main(String... args) throws IOException {
        String level;
        BufferedReader smartReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome... Please answer the following questions");
        System.out.println("Please select level: ");
        System.out.println("A: Easy");
        System.out.println("B: Medium");
        System.out.println("C: Hard");
        String userInput = smartReader.readLine();
        while (!(userInput.equals("A") || userInput.equals("B") || userInput.equals("C"))) {
            System.out.println("Invalid level. Please try again...");
            userInput = smartReader.readLine();
        }

        level = userInput;
        int bound;
        switch (level) {
            case "A":
                bound = 10;
                break;
            case "B":
                bound = 100;
                break;
            default:
                bound = 1000;
                break;
        }

        Random random = new Random();
        int points = 0;

        Map<String, int[]> rewards = new HashMap<>();
        rewards.put("A", new int[]{3, 2, 1});
        rewards.put("B", new int[]{10, 8, 6});
        rewards.put("C", new int[]{20, 15, 10});


        for (int i = 1; i <= 8; i++) {
            int operation = random.nextInt(3);
            int a = random.nextInt(bound * i);
            int b = random.nextInt(bound * i);
            String correct;
            String symbol;

            if (operation == 0) {
                symbol = "+";
                correct = String.valueOf(a + b);
            } else if (operation == 1) {
                symbol = "-";
                correct = String.valueOf(a - b);
            } else if (operation == 2) {
                symbol = "x";
                correct = String.valueOf(a * b);
            } else {
                symbol = "÷";
                correct = String.valueOf(a / b);
            }

            System.out.println(i + ": " + a + " " + symbol + " " + b);

            for (int j = 0; j < 3; j++) {
                userInput = smartReader.readLine();

                if (userInput.equals(correct)) {
                    points += rewards.get(level)[j];
                    System.out.println("Correct!!");
                    System.out.println("Current score: " + points);
                    break;
                } else if (j == 2) {
                    System.out.println("Total points: " + points);
                    System.out.println("GAME OVER....");
                    return;
                } else {
                    System.out.println("Wrong!! Try again...");
                }
            }
        }


        System.out.println("You finished successfully!!");
        System.out.println("Total points: " + points);
    }
}
