package case_studies.interviewready.ai_game_engine.game;

public class GameInfoBuilder {

    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;


    public static GameInfoBuilder builder() {
        return new GameInfoBuilder();
    }

    public GameInfoBuilder isOver(boolean over) {
        isOver = over;
        return this;
    }

    public GameInfoBuilder winner(String winner) {
        this.winner = winner;
        return this;
    }

    public GameInfoBuilder hasFork(boolean hasFork) {
        this.hasFork = hasFork;
        return this;
    }

    public GameInfoBuilder player(Player player) {
        this.player = player;
        return this;
    }

    public GameInfo build() {
        return new GameInfo(this.isOver, this.winner, this.hasFork, this.player);
    }

}
