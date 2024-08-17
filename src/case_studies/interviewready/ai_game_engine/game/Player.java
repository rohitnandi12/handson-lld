package case_studies.interviewready.ai_game_engine.game;

public class Player {

    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(this.symbol().equals("X") ? "O" : "X");
    }
}
