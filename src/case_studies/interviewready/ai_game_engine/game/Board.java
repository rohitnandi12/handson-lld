package case_studies.interviewready.ai_game_engine.game;

import case_studies.interviewready.ai_game_engine.api.RuleSet;

public interface Board {

    void move(Move move);
    Board copy();
}
