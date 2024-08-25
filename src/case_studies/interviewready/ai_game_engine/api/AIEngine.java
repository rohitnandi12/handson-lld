package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Move;
import case_studies.interviewready.ai_game_engine.game.Player;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            Move suggestedMove;
            if (isStarting(ticTacToeBoard, 3)) {
                suggestedMove = getBasicMove(player, ticTacToeBoard);
            } else {
                suggestedMove = getSmartMove(player, ticTacToeBoard);
            }

            if (suggestedMove != null)
                return suggestedMove;

            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Move getSmartMove(Player player, TicTacToeBoard board) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    TicTacToeBoard boardCopy = board.copy();
                    Move move = new Move(player, new Cell(r, c));
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // Defensive Moves
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    Move move = new Move(player.flip(), new Cell(r, c));
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(player, new Cell(r, c));
                    }
                }
            }
        }

        return getBasicMove(player, board);
    }

    public Move getBasicMove(Player player, TicTacToeBoard board) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    return new Move(player, new Cell(r, c));
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public boolean isStarting(TicTacToeBoard board, int threshold) {
        int count = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) != null) {
                    count++;
                }
            }
        }
        return count < threshold;
    }
}
