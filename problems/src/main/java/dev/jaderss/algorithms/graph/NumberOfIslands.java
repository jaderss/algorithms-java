package dev.jaderss.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public final class NumberOfIslands {

    private record Cell(int row, int column) {

        Cell[] neighbors() {
            return new Cell[] {
                new Cell(row - 1, column),
                new Cell(row + 1, column),
                new Cell(row, column - 1),
                new Cell(row, column + 1)
            };
        }
    }

    private static final char LAND = '1';

    private NumberOfIslands() {}

    public static int count(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][];
        for (int row = 0; row < grid.length; row++) {
            visited[row] = new boolean[grid[row].length];
        }
        int islands = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (isUnvisitedLand(grid, visited, new Cell(row, column))) {
                    islands++;
                    visitIsland(grid, visited, new Cell(row, column));
                }
            }
        }
        return islands;
    }

    private static void visitIsland(char[][] grid, boolean[][] visited, Cell start) {
        Deque<Cell> pending = new ArrayDeque<>();
        visited[start.row()][start.column()] = true;
        pending.push(start);
        while (!pending.isEmpty()) {
            for (Cell neighbor : pending.pop().neighbors()) {
                if (isUnvisitedLand(grid, visited, neighbor)) {
                    visited[neighbor.row()][neighbor.column()] = true;
                    pending.push(neighbor);
                }
            }
        }
    }

    private static boolean isUnvisitedLand(char[][] grid, boolean[][] visited, Cell cell) {
        return cell.row() >= 0
                && cell.row() < grid.length
                && cell.column() >= 0
                && cell.column() < grid[cell.row()].length
                && grid[cell.row()][cell.column()] == LAND
                && !visited[cell.row()][cell.column()];
    }
}
