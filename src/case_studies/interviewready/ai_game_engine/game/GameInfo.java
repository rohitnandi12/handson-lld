package case_studies.interviewready.ai_game_engine.game;

public class GameInfo {

    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
    }

    public static GameInfoBuilder builder() {
        return new GameInfoBuilder();
    }
}
