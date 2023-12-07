package src.adventofcode.day7;

import src.adventofcode.day7.model.Hand;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 {
    private static final List<Hand> handsList = new ArrayList<>();
    private static int total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day7/input"), StandardCharsets.UTF_8);

        for (final String line : lines) {
            String[] hand_bid = line.split(" ");
            handsList.add(new Hand(hand_bid[0], Integer.parseInt(hand_bid[1])));
        }

        Collections.sort(handsList);

        for (int i = 0; i < handsList.size(); i++) {
            total += (handsList.get(i).getBid() * (i + 1));
        }

        System.out.println("Total: " + total);
    }

}
