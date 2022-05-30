package src.sudoku.computationlogic;

import src.sudoku.constants.GameState;
import src.sudoku.constants.Rows;
import src.sudoku.problemdomain.SudokuGame;
import static src.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid());
    }

    public static GameState checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid))
            return GameState.ACTIVE;
        if (tilesAreNotFilled(grid))
            return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    protected static boolean sudokuIsInvalid(int[][] grid) {
        return rowsAreInvalid(grid)
                || colsAreInvalid(grid)
                || squaresAreInvalid(grid);
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row))
                return true;
        }
        return false;
    }

    private static boolean colsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row))
                return true;
        }
        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        return rowsOfSquaresIsInvalid(Rows.TOP, grid)
                || rowsOfSquaresIsInvalid(Rows.MIDDLE, grid)
                || rowsOfSquaresIsInvalid(Rows.BOTTOM, grid);
    }

    private static boolean rowsOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                return squareIsInvalid(0, 0, grid)
                        || squareIsInvalid(3, 0, grid)
                        || squareIsInvalid(6, 0, grid);
            case MIDDLE:
                return squareIsInvalid(0, 3, grid)
                        || squareIsInvalid(3, 3, grid)
                        || squareIsInvalid(6, 3, grid);
            case BOTTOM:
                return squareIsInvalid(0, 6, grid)
                        || squareIsInvalid(3, 6, grid)
                        || squareIsInvalid(6, 6, grid);
            default:
                return false;
        }
    }

    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {
            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]);

                xIndex++;
            }
            xIndex -= 3;
            yIndex++;
        }
        return collectionHasRepeats(square);
    }

    protected static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index = 1; index < GRID_BOUNDARY; index++) {
            if (Collections.frequency(collection, index) > 1)
                return true;
        }
        return false;
    }

    protected static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
