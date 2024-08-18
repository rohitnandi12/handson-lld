package case_studies.tictactoe.winning;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.move.Move;

public interface WinningStrategy {

    boolean checkWinner(Move move, Board board);

}
