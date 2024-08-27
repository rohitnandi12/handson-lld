package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;
import java.util.function.Supplier;

public class BinaryConditionalPlacement implements Placement {
    private Supplier<Boolean> condition;
    private Placement nextOnTrue;
    private Placement nextOnFalse;

    @Override
    public Optional<Cell> evaluatePlacement(TicTacToeBoard board, Player player) {
        logClass();
        boolean conditionResult = condition.get();
        System.out.printf("Condition result %s Calling %s%n", conditionResult,
                conditionResult ? nextOnTrue.getClass().getSimpleName() : nextOnFalse.getClass().getSimpleName());
        if(conditionResult) {

            return nextOnTrue.evaluatePlacement(board, player);
        } else {
            return nextOnFalse.evaluatePlacement(board, player);
        }
    }

    public BinaryConditionalPlacement(Supplier<Boolean> condition) {
        this.condition = condition;
    }

    public BinaryConditionalPlacement(Supplier<Boolean> condition, BasePlacement nextOnTrue, BasePlacement nextOnFalse) {
        this.condition = condition;
        this.nextOnTrue = nextOnTrue;
        this.nextOnFalse = nextOnFalse;
    }

    public BinaryConditionalPlacement setNextOnTrue(Placement nextOnTrue) {
        this.nextOnTrue = nextOnTrue;
        return this;
    }

    public BinaryConditionalPlacement setNextOnFalse(Placement nextOnFalse) {
        this.nextOnFalse = nextOnFalse;
        return this;
    }
}
