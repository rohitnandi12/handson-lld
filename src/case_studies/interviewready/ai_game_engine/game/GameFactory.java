package case_studies.interviewready.ai_game_engine.game;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return new Game(
                new GameConfig(maxTimePerPlayer),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove
        );
    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard startingGame) {
        return new Game(
                new GameConfig(maxTimePerPlayer),
                startingGame,
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove
        );
    }
}
