package case_studies.interviewready.ai_game_engine;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.*;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    GameFactory gameFactory = new GameFactory();

    @Test
    public void timeOutTestMaxTimePerMove() {
        Game game = gameFactory.createGame(3, 120);
        Player x = new Player("X");
        Player o = new Player("O");
        Cell c00 = Cell.getCell(0, 0);
        Cell c01 = Cell.getCell(0, 1);
        Cell c02 = Cell.getCell(0, 2);
        Cell c10 = Cell.getCell(1, 0);
        long ts = 5000;
        game.move(new Move(x, c00), ts); // X looses coz time per move is 3 and it takes 5.
        game.move(new Move(o, c01), ts); // X looses coz time per move is 3 and it takes 5.
        Assert.assertEquals(o.symbol(), game.getWinner().symbol());
    }

    // Write more test cases and debug the code
}

