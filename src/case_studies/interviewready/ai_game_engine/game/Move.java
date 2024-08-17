package case_studies.interviewready.ai_game_engine.game;

public class Move {

    private Player player;
    private Cell cell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Cell getCell() {
        return this.cell;
    }

    public Player getPlayer() {
        return player;
    }
}
