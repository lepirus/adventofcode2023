package src.adventofcode.day2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {

    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;
    private static int total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day2/input"), StandardCharsets.UTF_8);

        for (String line : lines) {
            line = line.replace(" ", "").replace("Game", "").replace("blue", "b").replace("green", "g").replace("red", "r");
            String[] splitLine = line.split(":");
            int numGame = Integer.parseInt(splitLine[0]);
            String[] sets = splitLine[1].split(";");

            int i = 0;
            boolean isPossible = true;
            while (i < sets.length && isPossible) {
                String set = sets[i];
                isPossible = isGamePossible(set);
                i++;
            }
            if (isPossible) {
//                System.out.println("Game " + numGame + " is possible");
                total = total + numGame;
            }
        }

        System.out.println("Total: " + total);
    }

    private static boolean isGamePossible(String set) throws RuntimeException {
        int totalRed = 0;
        int totalBlue = 0;
        int totalGreen = 0;

        String[] elements = set.split(",");
        for (String element : elements) {
            char color = element.charAt(element.length() - 1);
            int value = Integer.parseInt(element.substring(0, element.length() - 1));

            switch (color) {
                case 'b':
                    totalBlue += value;
                    break;
                case 'r':
                    totalRed += value;
                    break;
                case 'g':
                    totalGreen += value;
                    break;
                default:
                    throw new RuntimeException("Color " + color + " not allowed");
            }

            if (totalBlue > MAX_BLUE || totalRed > MAX_RED || totalGreen > MAX_GREEN) {
                return false;
            }
        }
        return true;
    }


}
