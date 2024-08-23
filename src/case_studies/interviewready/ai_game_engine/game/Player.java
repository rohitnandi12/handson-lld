package case_studies.interviewready.ai_game_engine.game;

public class Player {

    private User user;
    private String playerSymbol;
    private int timeUsedInMillis;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(this.symbol().equals("X") ? "O" : "X");
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Player-'" + playerSymbol + "' ";
    }

    public void setTimeTaken(int timeInMillis) {
        timeUsedInMillis += timeInMillis;
    }

    public int getTimeUsedInMillis() {
        return this.timeUsedInMillis;
    }
}
