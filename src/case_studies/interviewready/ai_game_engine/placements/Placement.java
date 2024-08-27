package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public interface Placement { // Handler

    Optional<Cell> evaluatePlacement(TicTacToeBoard board, Player player);

    default void logClass() {
        System.out.println(this.getClass().getSimpleName() + " is computing a move....");
    }
}