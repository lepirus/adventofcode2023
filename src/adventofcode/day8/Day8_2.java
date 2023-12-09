package src.adventofcode.day8;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day8_2 {
    private static final Map<String, Node> nodeMap = new LinkedHashMap<>();
    private static long total = 0;

    public static void main(String[] args) throws Exception {
//        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day8/test_input_2"), StandardCharsets.UTF_8);
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day8/input_2"), StandardCharsets.UTF_8);

        // Delete empty lines
        lines.removeIf(String::isEmpty);

        String instructions = lines.remove(0);
        List<String> startNodes = new ArrayList<>();

        for (String line : lines) {
            line = line.replace(" ", "").replace("(", "").replace(")", "");
            String[] lineSplit = line.split("=");
            String[] directionSplit = lineSplit[1].split(",");
            nodeMap.put(lineSplit[0], new Node(directionSplit[0], directionSplit[1]));
            if (lineSplit[0].endsWith("A")) {
                startNodes.add(lineSplit[0]);
            }
        }

        int i = 0;
        List<String> nodes;
        while (!gameFinished(startNodes)) {
            nodes = new ArrayList<>(startNodes.size());
            for (String startNode : startNodes) {
                final Node node = nodeMap.get(startNode);
                final char step = instructions.charAt(i);
                if (step == 'L') {
                    nodes.add(node.left);
                } else if (step == 'R') {
                    nodes.add(node.right);
                }
            }
            if (++i == instructions.length()) {
                i = 0;
            }
            startNodes = nodes;
            total++;
//            System.out.println("Total: " + total);
        }
        System.out.println("TOTAL: " + total);
    }

    /**
     * Game finished when all nodes finish with Z
     */
    private static boolean gameFinished(List<String> nodes) {
//        return nodes.stream().allMatch(str -> str.endsWith("Z"));
        for (String str : nodes) {
            if (!str.endsWith("Z")) {
                return false;
            }
        }
        return true;
    }

    record Node(String left, String right) {
    }

}
