package case_studies.tictactoe;

import case_studies.tictactoe.move.ConsoleMoveStrategy;
import case_studies.tictactoe.move.MoveStrategies;
import case_studies.tictactoe.move.MoveStrategyFactory;
import case_studies.tictactoe.move.RandomMoveStrategy;
import case_studies.tictactoe.player.Player;
import case_studies.tictactoe.player.PlayerType;
import case_studies.tictactoe.turn.TurnStrategies;
import case_studies.tictactoe.turn.TurnStrategyFactory;

import java.util.List;

public class GameDriver {

    public static void main(String[] args) {
        Game game = Game.builder()
                .dimensions(3)
                .players(
                        List.of(
                                new Player("Rohit", 'X', MoveStrategyFactory.get(MoveStrategies.CONSOLE), PlayerType.HUMAN),
                                new Player("Nandi", '0', MoveStrategyFactory.get(MoveStrategies.RANDOM), PlayerType.BOT)
                        ))
                .turnStrategy(TurnStrategyFactory.get(TurnStrategies.CLOCKWISE))
                .build();

        game.run();
    }
}
