package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard.Symbol;
import case_studies.interviewready.ai_game_engine.game.*;
import case_studies.interviewready.ai_game_engine.placements.DefensivePlacement;
import case_studies.interviewready.ai_game_engine.placements.OffensivePlacement;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, RuleSet> ruleMap;

    public RuleEngine() {
        ruleMap = new HashMap<>();
        ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard tttBoard) {
            RuleSet rules = ruleMap.get(TicTacToeBoard.class.getName());
            for (Rule r : rules) {
                GameState gameState = r.condition.apply(tttBoard);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
        }
        return new GameState(false, "-");
    }

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard tttBoard) {
            GameState gameState = getState(tttBoard);

            for (Symbol symbol : Symbol.values()) {
                Player player = new Player(symbol.name());
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if (tttBoard.getSymbol(r, c) != null) {
                            TicTacToeBoard b = tttBoard.move(new Move(player, new Cell(r, c)));
                            // force opponent to make a defensive move
                            // win still after that move
                            DefensivePlacement defense = new DefensivePlacement();
                            Optional<Cell> defensiveCell = defense.findPlacement(b, player.flip());
                            if (defensiveCell.isPresent()) {
                                b = b.move(new Move(player.flip(), defensiveCell.get()));
                                OffensivePlacement offense = new OffensivePlacement();
                                Optional<Cell> offensiveCell = offense.findPlacement(b, player);
                                if (offensiveCell.isPresent()) {
                                    return GameInfo.builder()
                                            .isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(new Cell(r, c))
                                            .player(player)
                                            .build();
                                }
                            }
                        }
                    }
                }
            }
            return GameInfo.builder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(false)
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
