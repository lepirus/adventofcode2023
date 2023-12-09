package src.adventofcode.day9;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day9_2 {

    private static long total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day9/input"), StandardCharsets.UTF_8);

        for (final String line : lines) {
            final String[] lineSplit = line.split(" ");
            List<Long> numbersList = Arrays.stream(lineSplit).map(Long::parseLong).collect(Collectors.toList());

            total += calculateSequence(numbersList);
        }

        System.out.println("Total: " + total);
    }

    private static long calculateSequence(List<Long> numbersList) {
        long nextValue = 0;

        final Stack<Long> leftmostValues = new Stack<>();
        leftmostValues.push(numbersList.get(0));

        while (!areAllZeros(numbersList)) {
            final List<Long> nextList = new ArrayList<>(numbersList.size() - 1);
            for (int i = 0; i < numbersList.size() - 1; i++) {
                nextList.add(numbersList.get(i + 1) - numbersList.get(i));
            }
            leftmostValues.push(nextList.get(0));
            numbersList = nextList;
        }

        while (!leftmostValues.empty()) {
            long val = leftmostValues.pop();
            nextValue = val - nextValue;
        }

        return nextValue;
    }

    private static boolean areAllZeros(List<Long> numbers) {
        for (Long value : numbers) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}
