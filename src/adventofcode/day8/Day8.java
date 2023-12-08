package src.adventofcode.day8;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    private static final Map<String, Node> nodeMap = new LinkedHashMap<>();
    private static int total = 0;

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day8/input"), StandardCharsets.UTF_8);

        // Delete empty lines
        lines.removeIf(String::isEmpty);

        String instructions = lines.remove(0);

        for (String line : lines) {
            line = line.replace(" ", "").replace("(", "").replace(")", "");
            String[] lineSplit = line.split("=");
            String[] directionSplit = lineSplit[1].split(",");
            nodeMap.put(lineSplit[0], new Node(directionSplit[0], directionSplit[1]));
        }

        String startNode = "AAA";
        int i = 0;
        while (!startNode.equals("ZZZ")) {
            Node node = nodeMap.get(startNode);
            char step = instructions.charAt(i);
            if (step == 'L') {
                startNode = node.left;
            } else if (step == 'R') {
                startNode = node.right;
            }
            i++;
            if (i == instructions.length()) {
                i = 0;
            }
            total++;
        }

        System.out.println("Total: " + total);
    }

    record Node(String left, String right) {
    }

}
