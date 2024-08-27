package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class CornerPlacement extends BasePlacement {
    @Override
    public Optional<Cell> findPlacement(TicTacToeBoard board, Player player) {
        final int[][] optimalCells = new int[][]{{1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] optimalCell : optimalCells) {
            if (board.getSymbol(optimalCell[0], optimalCell[1]) == null) {
                return Optional.of(new Cell(optimalCell[0], optimalCell[1]));
            }
        }
        return Optional.empty();
    }
}
