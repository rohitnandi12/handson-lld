package case_studies.tictactoe.move;

import case_studies.tictactoe.player.Player;

public record Move(
        int x,
        int y,
        Player player
) {

}
