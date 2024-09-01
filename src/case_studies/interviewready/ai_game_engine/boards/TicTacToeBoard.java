package case_studies.interviewready.ai_game_engine.boards;

import case_studies.interviewready.ai_game_engine.api.Rule;
import case_studies.interviewready.ai_game_engine.api.RuleSet;
import case_studies.interviewready.ai_game_engine.game.*;

import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {
    private final String[][] cells = new String[3][3];

    private History history = new History();

    public History getHistory() {
        return history;
    }

    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule(board -> outerTraversal(board::getSymbol)));
        rules.add(new Rule(board -> outerTraversal((row, col) -> board.getSymbol(col, row))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule(board -> {
            boolean isGameOver = true;
            for (int r = 0; r < 3 && isGameOver; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board.getSymbol(r, c) == null) {
                        isGameOver = false;
                        break;
                    }
                }
            }
            if (isGameOver) return new GameState(true, "-");
            return new GameState(false, "-");
        }));
        return rules;
    }

    private static GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    private Function<TicTacToeBoard, GameState> outerTraversals = (board -> outerTraversal(board::getSymbol));

    public static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    public String[][] getCells() {
        return cells;
    }

    public String getSymbol(int row, int col) {
        return this.cells[row][col];
    }

    public void setCell(Cell cell, String playerSymbol) {
        if (cells[cell.getRow()][cell.getCol()] == null)
            cells[cell.getRow()][cell.getCol()] = playerSymbol;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : this.cells) {
            for (String ele : row) {
                sb.append((ele == null ? "_" : ele));
                sb.append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public TicTacToeBoard move(Move move) {
        TicTacToeBoard tttBoardCopy = this.copy();
        tttBoardCopy.setCell(move.getCell(), move.getPlayer().symbol());
        return tttBoardCopy;
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard newTTTBoard = new TicTacToeBoard();
        for (int row = 0; row < 3; row++) {
            System.arraycopy(this.cells[row], 0, newTTTBoard.cells[row], 0, 3);
        }
        newTTTBoard.history = this.history;
        return newTTTBoard;
    }

    public void addToHistory() {
        history.add(new Representation(this));
    }
}
