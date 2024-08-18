import case_studies.interviewready.ai_game_engine.api.AIEngine;
import case_studies.interviewready.ai_game_engine.api.GameEngine;
import case_studies.interviewready.ai_game_engine.api.RuleEngine;
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
        playerFactory.put("staticX", new Player("X"));
        playerFactory.put("staticO", new Player("O"));
        playerFactory.put("computerO", new Player("O"));
    }

    @Test
    public void checkForRowWin() {
        int[][][] moves = new int[][][]{
                {{1, 0}, {1, 1}, {1, 2}},
                {{0, 0}, {0, 1}, {0, 2}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForColWin() {
        int[][][] moves = new int[][][]{
                {{0, 0}, {1, 0}, {2, 0}},
                {{0, 1}, {1, 1}, {2, 1}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagnolWin() {
        int[][][] moves = new int[][][]{
                {{0, 0}, {1, 1}, {2, 2}},
                {{1, 0}, {0, 1}, {1, 2}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForRevDiagonalWin() {
        int[][][] moves = new int[][][]{
                {{0, 2}, {1, 1}, {2, 0}},
                {{1, 0}, {0, 1}, {1, 2}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForOWin() {
        int[][][] moves = new int[][][]{
                {{1, 0}, {1, 1}, {2, 0}},
                {{0, 0}, {0, 1}, {0, 2}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDraw() {
        int[][][] moves = new int[][][]{
                {{0, 0}, {0, 2}, {1, 0}, {1, 2},{2,1}},
                {{0, 1}, {1, 1}, {2, 0}, {2, 2}},
        };// second row
        String[] playerTypes = new String[]{"staticX", "staticO"};

        playGame(board, moves, playerTypes);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("-", ruleEngine.getState(board).getWinner());
    }

    private void playGame(Board board, int[][][] moves, String[] playerTypes) {
        Player firstPlayer = playerFactory.get(playerTypes[0]);
        Player secondPlayer = playerFactory.get(playerTypes[1]);
        int turn = 0;
        int[][] firstPlayerMoves = moves[0];
        int[][] secondPlayerMoves = moves[1];
        while (!ruleEngine.getState(board).isOver()) {
            playerMove(firstPlayer, turn, firstPlayerMoves);
            if(!ruleEngine.getState(board).isOver())
                playerMove(secondPlayer, turn, secondPlayerMoves);
            turn += 1;
        }
    }

    private void playerMove(Player player, int turn, int[][] playerMoves) {
        System.out.println(board);
        System.out.println(player + "is making move!!");
        int row = playerMoves[turn][0];
        int col = playerMoves[turn][1];
        Move currentPlayerMove = new Move(player, new Cell(row, col));
        gameEngine.move(board, currentPlayerMove);
    }
}
