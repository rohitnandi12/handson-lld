package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.GameResult;

public class RuleEngine {

    public GameResult getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard gameBoard = (TicTacToeBoard) board;

            for (int r = 0; r < 3; r++) {
                String firstCharacter = gameBoard.getCell(r, 0);
                boolean rowComplete = firstCharacter != null;
                for (int c = 1; c < 3 && rowComplete; c++) {
                    if (!firstCharacter.equals(gameBoard.getCell(r, c))) {
                        rowComplete = false;
                    }
                }
                if (rowComplete)
                    return new GameResult(true, firstCharacter);
            }


            for (int c = 0; c < 3; c++) {
                String firstCharacter = gameBoard.getCell(0, c);
                boolean colComplete = firstCharacter != null;
                for (int r = 1; r < 3  && colComplete; r++) {
                    if (!firstCharacter.equals(gameBoard.getCell(r, c))) {
                        colComplete = false;
                        break;
                    }
                }
                if (colComplete)
                    return new GameResult(true, firstCharacter);
            }

            String firstCharacter = gameBoard.getCell(0, 0);
            boolean diagComplete = firstCharacter != null;
            for (int r = 0; r < 3 && diagComplete; r++) {
                if (!firstCharacter.equals(gameBoard.getCell(r, r))) {
                    diagComplete = false;
                    break;
                }
            }
            if (diagComplete)
                return new GameResult(true, firstCharacter);

            firstCharacter = gameBoard.getCell(0, 2);
            boolean revDiagComplete = firstCharacter != null;
            for (int r = 0; r < 3 && revDiagComplete; r++) {
                if (!firstCharacter.equals(gameBoard.getCell(r, 2 - r))) {
                    revDiagComplete = false;
                    break;
                }
            }
            if (revDiagComplete)
                return new GameResult(true, firstCharacter);


            boolean isGameOver = true;
            for (int r = 0; r < 3; r++) {
                for (int c = 1; c < 3; c++) {
                    if (gameBoard.getCell(r, c) == null) {
                        isGameOver = false;
                        break;
                    }
                }
                if (!isGameOver)
                    break;
            }
            if (isGameOver)
                return new GameResult(true, firstCharacter);
        }
        return new GameResult(false, "-");
    }
}
