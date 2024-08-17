package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.*;

public class AIEngine {

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard gameBoard = (TicTacToeBoard) board;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (gameBoard.getCell(r, c) == null) {
                        return new Move(player, new Cell(r, c));
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
