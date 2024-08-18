package case_studies.interviewready.ai_game_engine.game;

public class GameState {
    boolean isOver;
    String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "isOver=" + isOver +
                ", winner='" + winner + '\'' +
                '}';
    }
}
