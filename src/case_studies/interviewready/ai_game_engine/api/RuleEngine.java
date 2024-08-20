package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.GameInfo;
import case_studies.interviewready.ai_game_engine.game.GameState;
import case_studies.interviewready.ai_game_engine.game.Move;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, List<Rule<TicTacToeBoard>>> ruleMap;

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        ruleMap = new HashMap<>();
        ruleMap.put(key, new ArrayList<>());
        ruleMap.get(key).add(new Rule<>(board -> outerTraversal(board::getSymbol)));
        ruleMap.get(key).add(new Rule<>(board -> outerTraversal((row, col) -> board.getSymbol(col, row))));
        ruleMap.get(key).add(new Rule<>(board -> traverse(i -> board.getSymbol(i, i))));
        ruleMap.get(key).add(new Rule<>(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        ruleMap.get(key).add(new Rule<>(board -> {
            boolean isGameOver = true;
            for (int r = 0; r < 3 && isGameOver; r++) {
                for (int c = 0; c < 3; c++) {
                    if (board.getSymbol(r, c) == null) {
                        isGameOver = false;
                        break;
                    }
                }
            }
            if (isGameOver) return new GameState(true, "-");
            return new GameState(false, "-");
        }));

    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {

            List<Rule<TicTacToeBoard>> rules = ruleMap.get(TicTacToeBoard.class.getName());
            for (Rule<TicTacToeBoard> r : rules) {
                GameState gameState = r.condition.apply(tttBoard);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
        }
        return new GameState(false, "-");
    }

    private Function<TicTacToeBoard, GameState> outerTraversals = (board -> outerTraversal(board::getSymbol));

    public GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {
            GameState gameState = getState(tttBoard);

            final String[] players = new String[]{"X", "O"};
            for (int index = 0; index < 2; index++) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        Player opponent = player.flip();
                        b.move(new Move(player, new Cell(r, c)));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = board.copy();
                                b1.move(new Move(opponent, new Cell(k, l)));
                                if (getState(b1).getWinner().equals(opponent.symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (!canStillWin) break;
                        }
                        if (canStillWin) {
                            return GameInfo.builder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(opponent)
                                    .build();
                        }
                    }
                }
            }
            return GameInfo.builder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(false)
                    .player(null)
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
