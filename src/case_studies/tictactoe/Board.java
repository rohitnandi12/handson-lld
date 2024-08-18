package case_studies.tictactoe;

import case_studies.tictactoe.move.Move;
import case_studies.tictactoe.winning.WinningStrategy;

import java.util.Arrays;

public class Board {

    private final Cell[][] cells;
    private final int dimension;
    private final WinningStrategy winningStrategy;


    Board(int dimension, WinningStrategy winningStrategy) {
        this.dimension = dimension;
        this.cells = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        this.winningStrategy = winningStrategy;
    }

    public void applyMove(Move move) {
        validateMove(move);
        Cell targetCell = this.cells[move.x()][move.y()];
        targetCell.setOccupied(true);
        targetCell.setPlayer(move.player());

    }

    public void unApplyMove(Move move) {
        validateMove(move);
        Cell targetCell = this.cells[move.x()][move.y()];
        targetCell.setOccupied(false);
        targetCell.setPlayer(null);

    }

    public void validateMove(Move move) {
        if (move == null || move.player() == null || move.x() < 0 || move.x() >= dimension || move.y() < 0 || move.y() >= dimension) {
            throw new RuntimeException("Invalid Move Exception");
        }
    }

    public boolean isTerminated() {
        return Arrays.stream(this.cells).noneMatch(row -> Arrays.stream(row).anyMatch(Cell::isAvailable));
    }

    public boolean hasWinner(Move move) {
        return winningStrategy.checkWinner(move, this);
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public void displayBoard() {
        System.out.println();
        for(Cell[] row : getCells()) {
            for(Cell c : row) {
                System.out.print("| " + c + " |");
            }
            System.out.println("\n______________________________");
        }
    }
}
