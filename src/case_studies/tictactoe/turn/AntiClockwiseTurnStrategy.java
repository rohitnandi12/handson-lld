package case_studies.tictactoe.turn;


import case_studies.tictactoe.player.Player;

import java.util.List;

public class AntiClockwiseTurnStrategy implements TurnStrategy {
    @Override
    public Turn nextTurn(Turn lastTurn, List<Player> players) {

        int lastPlayerIndex = lastTurn == null ?  1 : players.indexOf(lastTurn.player());

        int newPlayerIndex = lastPlayerIndex - 1;
        if(newPlayerIndex < 0) {
            newPlayerIndex = players.size() - 1;
        }

        return new Turn(players.get(newPlayerIndex));
    }
}
