package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.GameState;

import java.util.function.Function;

public class Rule<T extends Board> {

    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
