package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.Move;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class DefensivePlacement extends BasePlacement {

    @Override
    public Optional<Cell> findPlacement(TicTacToeBoard board, Player player) {
        return getDefensiveCell(board, player);
    }

    private Optional<Cell> getDefensiveCell(TicTacToeBoard board, Player player) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) == null) {
                    Move move = new Move(player.flip(), new Cell(r, c));
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return Optional.of(new Cell(r, c));
                    }
                }
            }
        }
        return Optional.empty();
    }
}
