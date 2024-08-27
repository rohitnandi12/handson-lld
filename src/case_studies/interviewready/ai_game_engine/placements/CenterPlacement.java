package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class CenterPlacement extends BasePlacement {
    @Override
    public Optional<Cell> findPlacement(TicTacToeBoard board, Player player) {
        if (board.getSymbol(1, 1) == null) {
            return Optional.of(new Cell(1, 1));
        }
        return Optional.empty();
    }

}
