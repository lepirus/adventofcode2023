package src.adventofcode.day11;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11_2 {

    private static final List<Integer> duplicatedColumns = new ArrayList<>();
    private static final List<Integer> duplicatedRows = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final List<String> lines = Files.readAllLines(Path.of("src/adventofcode/day11/input"), StandardCharsets.UTF_8);
        int[][] grids = new int[lines.size()][lines.size()];
        int galaxies = 1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') {
                    grids[i][j] = galaxies;
                    galaxies++;
                } else {
                    grids[i][j] = 0;
                }
            }
        }
        findDuplicateRowsAndColumns(grids);

        List<Position> galaxyList = findGalaxies(grids, galaxies);

        System.out.println("Distance: " + calculateTotalMinDistance(galaxyList));
    }

    private static long calculateTotalMinDistance(List<Position> galaxyList) {
        long total = 0;
        for (int i = 0; i < galaxyList.size(); i++) {
            Position position1 = galaxyList.get(i);

            for (int j = i + 1; j < galaxyList.size(); j++) {
                Position position2 = galaxyList.get(j);
                total += calculateMinDistance(position1, position2);
            }
        }
        return total;
    }

    private static long calculateMinDistance(Position position1, Position position2) {
        int distance;
        List<Integer> columnsToAdd = new ArrayList<>();
        List<Integer> rowsToAdd = new ArrayList<>();

        if (position1.row > position2.row) {
            for (int i = position2.row; i <= position1.row; i++) {
                if (duplicatedRows.contains(i)) {
                    rowsToAdd.add(i);
                }
            }
            distance = (rowsToAdd.isEmpty()) ? position1.row - position2.row : ((rowsToAdd.size() * 999999) + position1.row) - position2.row;
        } else {
            for (int i = position1.row; i <= position2.row; i++) {
                if (duplicatedRows.contains(i)) {
                    rowsToAdd.add(i);
                }
            }
            distance = (rowsToAdd.isEmpty()) ? position2.row - position1.row : ((rowsToAdd.size() * 999999) + position2.row) - position1.row;
        }

        if (position1.column > position2.column) {
            for (int i = position2.column; i <= position1.column; i++) {
                if (duplicatedColumns.contains(i)) {
                    columnsToAdd.add(i);
                }
            }
            distance += (columnsToAdd.isEmpty()) ? position1.column - position2.column : ((columnsToAdd.size() * 999999) + position1.column) - position2.column;
        } else {
            for (int i = position1.column; i <= position2.column; i++) {
                if (duplicatedColumns.contains(i)) {
                    columnsToAdd.add(i);
                }
            }
            distance += (columnsToAdd.isEmpty()) ? position2.column - position1.column : ((columnsToAdd.size() * 999999) + position2.column) - position1.column;
        }

        return distance;
    }

    private static List<Position> findGalaxies(int[][] grid, int size) {
        List<Position> galaxyList = new ArrayList<>(size);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    galaxyList.add(new Position(i, j));
                }
            }
        }
        return galaxyList;
    }

    private static void findDuplicateRowsAndColumns(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        boolean[] duplicateRows = new boolean[rows];
        boolean[] duplicateColumns = new boolean[columns];

        // Find rows and columns that need duplication
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                if (!duplicateRows[i]) {
                    boolean allZero = true;
                    for (int k = 0; k < columns; k++) {
                        if (grid[i][k] != 0) {
                            allZero = false;
                            break;
                        }
                    }
                    duplicateRows[i] = allZero;
                }
                if (!duplicateColumns[j]) {
                    boolean allZero = true;
                    for (int[] ints : grid) {
                        if (ints[j] != 0) {
                            allZero = false;
                            break;
                        }
                    }
                    duplicateColumns[j] = allZero;
                }
            }
        }

        for (int i = 0; i < duplicateRows.length; i++) {
            if (duplicateRows[i]) {
                duplicatedRows.add(i);
            }
        }
        for (int i = 0; i < duplicateColumns.length; i++) {
            if (duplicateColumns[i]) {
                duplicatedColumns.add(i);
            }
        }
    }

    record Position(int row, int column) {
    }

}
