package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.GameState;
import case_studies.tictactoe.Game;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {

            GameState rowWin = outerTraversal(tttBoard::getSymbol);
            if (rowWin.isOver()) return rowWin;

            GameState colWin = outerTraversal((i, j) -> tttBoard.getSymbol(j, i));
            if (colWin.isOver()) return colWin;

            GameState diagWin = traverse(i -> tttBoard.getSymbol(i, i));
            if (diagWin.isOver()) return diagWin;

            GameState revDiagWin = traverse(i -> tttBoard.getSymbol(i, 2 - i));
            if (revDiagWin.isOver()) return revDiagWin;

            boolean isGameOver = true;
            for (int r = 0; r < 3 && isGameOver; r++) {
                for (int c = 0; c < 3; c++) {
                    if (tttBoard.getSymbol(r, c) == null) {
                        isGameOver = false;
                        break;
                    }
                }
            }
            if (isGameOver) return new GameState(true, "-");
        }
        return new GameState(false, "-");
    }

    public GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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

    private GameState traverse(Function<Integer, String> traversal) {
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
}
