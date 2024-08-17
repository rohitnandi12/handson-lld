package case_studies.interviewready.ai_game_engine.boards;

import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Move;

import java.util.Arrays;

public class TicTacToeBoard extends Board {
    private final String[][] cells = new String[3][3];

    public String[][] getCells() {
        return cells;
    }

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String playerSymbol) {
        cells[cell.getRow()][cell.getCol()] = playerSymbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String[] row : this.cells) {
            for(String ele : row) {
                sb.append(ele + " | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    public String getSymbol(int r, int c) {
        return this.cells[r][c];
    }
}
