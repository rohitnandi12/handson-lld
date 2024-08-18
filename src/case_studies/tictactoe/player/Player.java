package case_studies.tictactoe.player;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.move.Move;
import case_studies.tictactoe.move.MoveStrategy;

public record Player(
        String name,
        char symbol,
        MoveStrategy moveStrategy,
        PlayerType playerType
) {

    public Move nextMove(Board board) {
        return moveStrategy.computeNextMove(this, board);
    }

    @Override
    public String toString() {
        return "Player{" +
                "NAME='" + name + '\'' +
                ", SYMBOL=" + symbol +
                '}';
    }

    public boolean canUndo() {
        return moveStrategy != null && moveStrategy().isUserGenerated();
    }
}
