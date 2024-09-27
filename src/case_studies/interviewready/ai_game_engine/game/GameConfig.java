package case_studies.interviewready.ai_game_engine.game;

public class GameConfig {
    private boolean isTimed;
    private Integer timePerMove;

    public GameConfig(Integer timePerMove) {
        this.isTimed = timePerMove != null;
        this.timePerMove = timePerMove;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public Integer getTimePerMove() {
        return timePerMove;
    }
}
