package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {
            String firstCharacter = "-";

            GameState rowWin = isVictory(tttBoard::getSymbol);
            if (rowWin != null) return rowWin;

            GameState colWin = isVictory((i, j) -> tttBoard.getSymbol(j, i));
            if (colWin != null) return colWin;

            firstCharacter = tttBoard.getSymbol(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int r = 0; r < 3 && diagComplete; r++) {
                if (!firstCharacter.equals(tttBoard.getSymbol(r, r))) {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete)
                return new GameState(true, firstCharacter);

            firstCharacter = tttBoard.getSymbol(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int r = 0; r < 3 && revDiagComplete; r++) {
                if (!firstCharacter.equals(tttBoard.getSymbol(r, 2 - r))) {
                    revDiagComplete = false;
                    break;
                }
            }
            if (revDiagComplete)
                return new GameState(true, firstCharacter);


            boolean isGameOver = true;
            for (int r = 0; r < 3 && isGameOver; r++) {
                for (int c = 0; c < 3; c++) {
                    if (tttBoard.getSymbol(r, c) == null) {
                        isGameOver = false;
                        break;
                    }
                }
            }
            if (isGameOver)
                return new GameState(true, "-");
        }
        return new GameState(false, "-");
    }

    public GameState isVictory(BiFunction<Integer, Integer, String> next) {
        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i, j) == null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if (possibleStreak) return new GameState(true, next.apply(i, 0));
        }
        return null;
    }
}
