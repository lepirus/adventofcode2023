package src.adventofcode.day2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2_2 {

    private static int total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day2/input"), StandardCharsets.UTF_8);

        for (String line : lines) {
            line = line.replace(" ", "").replace("Game", "").replace("blue", "b").replace("green", "g").replace("red", "r");
            String[] lineSplit = line.split(":");
            String[] sets = lineSplit[1].split(";");

            FewerGame fewerGame = new FewerGame(0, 0, 0);
            for (String set : sets) {
                fewerGame = getFewerCubeValues(set, fewerGame.fewerRed, fewerGame.fewerBlue, fewerGame.fewerGreen);
            }
//            System.out.println("fewerGame: " + fewerGame);
            int power = fewerGame.fewerBlue * fewerGame.fewerGreen * fewerGame.fewerRed;
//            System.out.println("power: " + power);
            total += power;
        }

        System.out.println("Total: " + total);
    }

    private static FewerGame getFewerCubeValues(String set, int fewerRed, int fewerBlue, int fewerGreen) throws RuntimeException {
        String[] elements = set.split(",");
        for (String element : elements) {
            char color = element.charAt(element.length() - 1);
            int value = Integer.parseInt(element.substring(0, element.length() - 1));

            switch (color) {
                case 'b':
                    fewerBlue = Math.max(fewerBlue, value);
                    break;
                case 'r':
                    fewerRed = Math.max(fewerRed, value);
                    break;
                case 'g':
                    fewerGreen = Math.max(fewerGreen, value);
                    break;
                default:
                    throw new RuntimeException("Color " + color + " not allowed");
            }
        }
        return new FewerGame(fewerRed, fewerBlue, fewerGreen);
    }

    public record FewerGame(int fewerRed, int fewerBlue, int fewerGreen) {
    }

}
