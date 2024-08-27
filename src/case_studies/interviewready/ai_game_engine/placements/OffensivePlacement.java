package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Move;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class OffensivePlacement extends BasePlacement {

    @Override
    public Optional<Cell> findPlacement(TicTacToeBoard board, Player player) {
        return getOffensiveCell(board, player);
    }

    private Optional<Cell> getOffensiveCell(TicTacToeBoard board, Player player) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    TicTacToeBoard boardCopy = board.copy();
                    Move move = new Move(player, new Cell(r, c));
                    boardCopy.move(move);
                    if (this.ruleEngine.getState(boardCopy).isOver()) {
                        return Optional.of(move.getCell());
                    }
                }
            }
        }
        return Optional.empty();
    }
}
