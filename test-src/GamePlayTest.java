import case_studies.interviewready.ai_game_engine.api.AIEngine;
import case_studies.interviewready.ai_game_engine.api.GameEngine;
import case_studies.interviewready.ai_game_engine.api.RuleEngine;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Move;
import case_studies.interviewready.ai_game_engine.game.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    AIEngine aiEngine;
    Board board;
    Map<String, Player> playerFactory;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
        aiEngine = new AIEngine();
        board = gameEngine.start("TicTacToe");
        playerFactory = new HashMap<>();
        playerFactory.put("humanX", new Player("X"));
        playerFactory.put("computerO", new Player("O"));
    }

    @Test
    public void checkForRowWin() {
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}}; // second row
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {
        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagnolWin() {
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForRevDiagnolWin() {
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForComputerWin() {
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDraw() {
        int[][] moves = new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 0}, {2, 2}};
        String[] playerTypes = new String[]{"humanX", "computerO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("-", ruleEngine.getState(board).getWinner());
    }

    private void playGame(Board board, int[][] moves, String[] playerTypes) {
        Player firstPlayer = playerFactory.get(playerTypes[0]);
        Player secondPlayer = playerFactory.get(playerTypes[1]);
        int row, col, next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!!");
            System.out.println(board);
            row = moves[next][0];
            col = moves[next][1];
            next += 1;
            Move firstPlayerMove = new Move(firstPlayer, new Cell(row, col));
            gameEngine.move(board, firstPlayerMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move secondPlayerMove = aiEngine.suggestMove(secondPlayer, board);
                gameEngine.move(board, secondPlayerMove);
            }
        }
    }
}
