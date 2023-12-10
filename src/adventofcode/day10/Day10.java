package src.adventofcode.day10;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Day10 {

    private static final Set<Character> westDirections = Set.of('-', 'F', 'L');
    private static final Set<Character> eastDirections = Set.of('-', 'J', '7');
    private static final Set<Character> southDirections = Set.of('|', '7', 'F');
    private static final Set<Character> northDirections = Set.of('|', 'L', 'J');


    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day10/input"), StandardCharsets.UTF_8);
        char[][] grids = new char[lines.size()][lines.size()];
        Position start = null;

        System.out.println("lines size: " + lines.size());
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                grids[i][j] = line.charAt(j);
                if (line.charAt(j) == 'S') {
                    start = new Position(i, j);
                }
            }
        }

        System.out.println("lines: " + lines);
        System.out.println("grids: " + Arrays.deepToString(grids));
        System.out.println("start: " + start);

        assert start != null;
        List<Position> nextToStart = getNextStartPositions(start, grids);
        System.out.println("nextToStart: " + nextToStart);

        Position current1 = nextToStart.get(0);
        Position current2 = nextToStart.get(1);
        System.out.println("next1: " + current1 + ". Value: " + grids[current1.y][current1.x]);
        System.out.println("next2: " + current2 + ". Value: " + grids[current2.y][current2.x]);

        Position previous1 = start;
        Position previous2 = start;
        int cont = 1;

        while (!current1.equals(current2)) {
            Position next1 = getNextPosition(previous1, current1, grids[current1.y][current1.x]);
            Position next2 = getNextPosition(previous2, current2, grids[current2.y][current2.x]);

            previous1 = current1;
            previous2 = current2;

            current1 = next1;
            current2 = next2;
            assert current1 != null;
            System.out.println("next1: " + current1 + ". Value: " + grids[current1.y][current1.x]);
            assert current2 != null;
            System.out.println("next2: " + current2 + ". Value: " + grids[current2.y][current2.x]);
            cont++;
        }

        System.out.println("cont: " + cont);
    }

    private static Position getNextPosition(Position previous, Position current, char value) {
        switch (value) {
            case '-': {
                if (current.x > previous.x) {
                    return new Position(current.y, current.x + 1);
                } else {
                    return new Position(current.y, current.x - 1);
                }
            }
            case '|':
                if (current.y > previous.y) {
                    return new Position(current.y + 1, current.x);
                } else {
                    return new Position(current.y - 1, current.x);
                }
            case 'J':
                if (current.x > previous.x) {
                    return new Position(current.y - 1, current.x);
                } else {
                    return new Position(current.y, current.x - 1);
                }
            case '7':
                if (current.x > previous.x) {
                    return new Position(current.y + 1, current.x);
                } else {
                    return new Position(current.y, current.x - 1);
                }
            case 'F':
                if (current.y < previous.y) {
                    return new Position(current.y, current.x + 1);
                } else {
                    return new Position(current.y + 1, current.x);
                }
            case 'L':
                if (current.x < previous.x) {
                    return new Position(current.y - 1, current.x);
                } else {
                    return new Position(current.y, current.x + 1);
                }
            default:
                return null;
        }
    }

    private static List<Position> getNextStartPositions(Position start, char[][] grids) {
        List<Position> nextToStart = new ArrayList<>();

        // Check left
        if (start.x > 0 && westDirections.contains(grids[start.y][start.x - 1])) {
            nextToStart.add(new Position(start.y, start.x - 1));
        }

        // Check right
        if (start.x < grids.length - 1 && eastDirections.contains(grids[start.y][start.x + 1])) {
            nextToStart.add(new Position(start.y, start.x + 1));
        }

        // Check south
        if (start.y > 0 && southDirections.contains(grids[start.y - 1][start.x])) {
            nextToStart.add(new Position(start.y - 1, start.x));
        }

        // Check north
        if (start.y < grids.length - 1 && northDirections.contains(grids[start.y + 1][start.x])) {
            nextToStart.add(new Position(start.y + 1, start.x));
        }
        return nextToStart;
    }

    record Position(int y, int x) {
        public boolean equals(Position other) {
            return this.y == other.y() && this.x == other.x();
        }
    }
}
