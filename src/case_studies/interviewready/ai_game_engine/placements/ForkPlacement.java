package case_studies.interviewready.ai_game_engine.placements;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.Cell;
import case_studies.interviewready.ai_game_engine.game.GameInfo;
import case_studies.interviewready.ai_game_engine.game.Player;

import java.util.Optional;

public class ForkPlacement extends BasePlacement {
    @Override
    public Optional<Cell> findPlacement(TicTacToeBoard board, Player player) {
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.isHasFork()) {
            return Optional.of(gameInfo.getForkCell());
        }
        return Optional.empty();
    }
}
