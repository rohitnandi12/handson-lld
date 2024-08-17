package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.*;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;

public class GameEngine {

    public Board start(String gameType) {

        if (gameType.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Board move(Board board, Move move) {
        if (board instanceof TicTacToeBoard) {
            board.move(move);
        } else {
            throw new IllegalArgumentException();
        }
        return board;
    }
}

