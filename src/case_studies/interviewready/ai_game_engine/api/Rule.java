package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.Board;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.CellBoard;
import case_studies.interviewready.ai_game_engine.game.GameState;

import java.util.function.Function;

public class Rule {

    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
