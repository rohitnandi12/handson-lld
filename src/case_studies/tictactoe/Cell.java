package case_studies.tictactoe;

import case_studies.tictactoe.player.Player;

public class Cell {

    private final int X;
    private final int Y;

    private Player player;
    private boolean isOccupied;

    public Cell(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return String.format("%s",
                player == null ? "_" : player.symbol()
        );
    }
}
