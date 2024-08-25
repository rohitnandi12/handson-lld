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

public class RuleEngine<T extends Board> {

    Map<String, RuleSet<TicTacToeBoard>> ruleMap;

    public RuleEngine() {
        ruleMap = new HashMap<>();
        ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {
            RuleSet<TicTacToeBoard> rules = ruleMap.get(TicTacToeBoard.class.getName());
            for (Rule<TicTacToeBoard> r : rules) {
                GameState gameState = r.condition.apply(tttBoard);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
        }
        return new GameState(false, "-");
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
