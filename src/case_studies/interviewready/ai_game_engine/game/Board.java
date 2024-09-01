package case_studies.interviewready.ai_game_engine.game;

public interface Board {

    Board move(Move move);
    Board copy();
}
