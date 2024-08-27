package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class FirstVacantPlacement implements Placement {

    @Override
    public Optional<Cell> evaluatePlacement(TicTacToeBoard board, Player player) {
        logClass();
        return Optional.ofNullable(getBasicMove(board));
    }

    public Cell getBasicMove(TicTacToeBoard board) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    return new Cell(r, c);
                }
            }
        }
        throw new IllegalArgumentException();
    }
}

