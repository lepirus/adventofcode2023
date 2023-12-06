package src.adventofcode.day6;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {
    private static final List<Integer> timeList = new ArrayList<>();
    private static final List<Integer> distanceList = new ArrayList<>();
    private static int total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day6/input"), StandardCharsets.UTF_8);

        String[] numbersAsString = lines.get(0).replace("Time:", "").trim().split("\\s+");
        Arrays.stream(numbersAsString).forEach(s -> timeList.add(Integer.parseInt(s)));

        numbersAsString = lines.get(1).replace("Distance:", "").trim().split("\\s+");
        Arrays.stream(numbersAsString).forEach(s -> distanceList.add(Integer.parseInt(s)));

        for (int i = 0; i < timeList.size(); i++) {
            int wins = getWinsForRace(timeList.get(i), distanceList.get(i));
            if (total == 0) {
                total = wins;
            } else {
                total *= wins;
            }
        }

        System.out.println("Total: " + total);
    }

    private static int getWinsForRace(int time, int distance) {
        int cont = 0;
        for (int i = 0; i <= time; i++) {
            if ((time - i) * i > distance) {
                cont++;
            }
        }
        return cont;
    }

}
