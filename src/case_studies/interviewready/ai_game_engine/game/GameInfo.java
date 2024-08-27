package case_studies.interviewready.ai_game_engine.game;

public class GameInfo {

    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Cell forkCell;
    private Player player;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player,
                    Cell forkCell) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.forkCell = forkCell;
    }

    public static GameInfoBuilder builder() {
        return new GameInfoBuilder();
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isHasFork() {
        return hasFork;
    }

    public Cell getForkCell() {
        return forkCell;
    }

    public Player getPlayer() {
        return player;
    }
}
