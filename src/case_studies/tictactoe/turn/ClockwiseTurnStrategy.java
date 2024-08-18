package case_studies.tictactoe.turn;

import case_studies.tictactoe.player.Player;

import java.util.List;

public class ClockwiseTurnStrategy implements TurnStrategy {
    @Override
    public Turn nextTurn(Turn lastTurn, List<Player> players) {

        int lastPlayerIndex = lastTurn == null ?  -1 : players.indexOf(lastTurn.player());

        int newPlayerIndex = (lastPlayerIndex + 1) % players.size();

        return new Turn(players.get(newPlayerIndex));
    }
}
