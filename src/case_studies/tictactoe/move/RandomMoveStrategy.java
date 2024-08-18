package case_studies.tictactoe.move;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.Cell;
import case_studies.tictactoe.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMoveStrategy implements MoveStrategy {
    @Override
    public Move computeNextMove(Player player, Board board) {
        List<Cell> availableCells = Arrays
                .stream(board.getCells())
                .flatMap(row -> Arrays.stream(row)
                        .filter(Cell::isAvailable))
                .toList();
        Cell nextCell = availableCells.get(ThreadLocalRandom.current().nextInt(availableCells.size()));
        return new Move(nextCell.getX(), nextCell.getY(), player);
    }
}
