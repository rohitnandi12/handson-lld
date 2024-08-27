package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.api.RuleEngine;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public abstract class BasePlacement implements Placement {
    protected Placement nextPlacement;
    protected RuleEngine ruleEngine = new RuleEngine();

    public BasePlacement setNextPlacement(BasePlacement placement) {
        this.nextPlacement = placement;
        return placement;
    }

    public void setTerminal(Placement placement) {
        this.nextPlacement = placement;
    }

    public boolean hasNextPlacement() {
        return this.nextPlacement != null;
    }

    @Override
    public Optional<Cell> evaluatePlacement(TicTacToeBoard board, Player player) {
        logClass();
        Optional<Cell> bestCell = this.findPlacement(board, player);
        if(bestCell.isEmpty() && hasNextPlacement()) {
            bestCell = nextPlacement.evaluatePlacement(board, player);
        }
        return bestCell;
    }
    public abstract Optional<Cell> findPlacement(TicTacToeBoard board, Player player);
}
