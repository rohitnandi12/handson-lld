package case_studies.tictactoe.turn;


import case_studies.tictactoe.player.Player;

import java.util.List;

public interface TurnStrategy {

    Turn nextTurn(Turn lastTurn, List<Player> players);
}
