package case_studies.interviewready.ai_game_engine.game;

public interface Board {

    void move(Move move);
    Board copy();
}
