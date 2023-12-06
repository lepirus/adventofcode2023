package src.adventofcode.day6;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day6_2 {

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day6/input"), StandardCharsets.UTF_8);

        String numbersAsString = lines.get(0).replace("Time:", "").replace(" ", "").trim();
        long time = Long.parseLong(numbersAsString);
        numbersAsString = lines.get(1).replace("Distance:", "").replace(" ", "").trim();
        long distance = Long.parseLong(numbersAsString);

        long total = getWinsForRace(time, distance);
        System.out.println("Total: " + total);
    }

    private static long getWinsForRace(long time, long distance) {
        int cont = 0;
        for (int i = 0; i <= time; i++) {
            if ((time - i) * i > distance) {
                cont++;
            }
        }
        return cont;
    }

}
