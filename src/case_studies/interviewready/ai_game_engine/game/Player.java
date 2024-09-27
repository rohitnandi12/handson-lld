package case_studies.interviewready.ai_game_engine.game;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {

    private User user;
    private String playerSymbol;
    private int timeUsedInMillis;

    public Player(String playerSymbol) {
        this.user = new User(playerSymbol.charAt(0));
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

    public void setTimeTaken(long timeInMillis) {
        timeUsedInMillis += timeInMillis;
    }

    public long getTimeUsedInMillis() {
        return this.timeUsedInMillis;
    }
}
