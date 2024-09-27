package case_studies.interviewready.ai_game_engine.game;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;

public class Cell {
    private int row;
    private int col;

    static Cell[][] cells = new Cell[][] {
            {new Cell(0,0), new Cell(0,1), new Cell(0,2)},
            {new Cell(1,0), new Cell(1,1), new Cell(1,2)},
            {new Cell(2,0), new Cell(2,1), new Cell(2,2)}
    };

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
