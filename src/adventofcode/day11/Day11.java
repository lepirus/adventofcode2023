package src.adventofcode.day11;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11 {


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
        grids = duplicateRowsAndColumns(grids);
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
        int distance = position1.row > position2.row ? position1.row - position2.row : position2.row - position1.row;
        distance += position1.column > position2.column ? position1.column - position2.column : position2.column - position1.column;
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

    private static int[][] duplicateRowsAndColumns(int[][] grid) {
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

        int newRows = rows;
        int newColumns = columns;
        for (boolean duplicateRow : duplicateRows) {
            if (duplicateRow) {
                newRows++;
            }
        }
        for (boolean duplicateColumn : duplicateColumns) {
            if (duplicateColumn) {
                newColumns++;
            }
        }

        // Create a new grid with the improved size
        int[][] newGrid = new int[newRows][newColumns];

        // Copy the original grid to the top-left quadrant of the new grid
        int newRow = 0;
        for (int i = 0; i < rows; i++) {
            if (duplicateRows[i]) {
                newRow++;
            }
            int newColumn = 0;
            for (int j = 0; j < columns; j++) {
                newGrid[newRow][newColumn] = grid[i][j];
                newColumn++;
                if (duplicateColumns[j]) {
                    newGrid[newRow][newColumn] = grid[i][j];
                    newColumn++;
                }
            }
            newRow++;
        }

        return newGrid;
    }

    record Position(int row, int column) {
    }

}
