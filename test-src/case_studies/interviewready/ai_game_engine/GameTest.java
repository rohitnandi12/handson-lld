package case_studies.interviewready.ai_game_engine;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Game;
import case_studies.interviewready.ai_game_engine.game.GameConfig;
import case_studies.interviewready.ai_game_engine.game.GameFactory;
import org.junit.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTest() {
        Game game = gameFactory.createGame(3, 120);
    }
}
