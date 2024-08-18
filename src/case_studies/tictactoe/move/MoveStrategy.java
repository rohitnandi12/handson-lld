package case_studies.tictactoe.move;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.player.Player;

public interface MoveStrategy {

    Move computeNextMove(Player player, Board board);

    default boolean isUserGenerated() {
        return false;
    }
}
