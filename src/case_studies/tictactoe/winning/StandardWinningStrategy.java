package case_studies.tictactoe.winning;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.Cell;
import case_studies.tictactoe.player.Player;
import case_studies.tictactoe.move.Move;

import java.util.function.BiFunction;

public class StandardWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Move move, Board board) {
        return checkColumn(move, board) || checkRow(move, board);
    }

    private boolean checkRow(Move move, Board board) {
        int x = move.x();
        int y = move.y();
        Cell[][] cells = board.getCells();
        BiFunction<Integer, Integer, Player> f = getPlayerFunction(cells);
        Player p = move.player();

        if (f.apply(x, y) != p)
            return false;

        return (p.equals(f.apply(x, y - 1)) && p.equals(f.apply(x, y + 1))) ||
                (p.equals(f.apply(x, y - 2)) && p.equals(f.apply(x, y - 1))) ||
                (p.equals(f.apply(x, y + 2)) && p.equals(f.apply(x, y + 1)));
    }

    private boolean checkColumn(Move move, Board board) {
        int x = move.x();
        int y = move.y();
        Cell[][] cells = board.getCells();
        BiFunction<Integer, Integer, Player> f = getPlayerFunction(cells);
        Player p = move.player();

        if (f.apply(x, y) != p)
            return false;

        return (p.equals(f.apply(x - 1, y)) && p.equals(f.apply(x + 1, y))) ||
                (p.equals(f.apply(x - 2, y)) && p.equals(f.apply(x - 1, y))) ||
                (p.equals(f.apply(x + 2, y)) && p.equals(f.apply(x + 1, y)));
    }

    private BiFunction<Integer, Integer, Player> getPlayerFunction(Cell[][] cells) {
        return (Integer x, Integer y) -> {
            if (x < 0 || x >= cells.length) {
                return null;
            } else if (y < 0 || y >= cells[0].length) {
                return null;
            } else {
                return cells[x][y].getPlayer();
            }
        };
    }

}
