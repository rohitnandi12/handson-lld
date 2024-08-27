package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.*;
import case_studies.interviewready.ai_game_engine.placements.*;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            int threshold = 3;
            int turnsPassedTillNow = getTurnsPassedTillNow(ticTacToeBoard);

            Placement placementChain = new BinaryConditionalPlacement(() -> turnsPassedTillNow < threshold)
                    .setNextOnTrue(new FirstVacantPlacement())
                    .setNextOnFalse(
                            new BinaryConditionalPlacement(() -> turnsPassedTillNow < threshold + 1)
                                    .setNextOnTrue(getSmartPlacementChain())
                                    .setNextOnFalse(getOptimalMovePlacementChain())
                    );

            Cell suggestedCell =  placementChain.evaluatePlacement(ticTacToeBoard, player)
                    .orElseThrow(IllegalStateException::new);

            return new Move(player, suggestedCell);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private BasePlacement getOptimalMovePlacementChain() {
        BasePlacement firstLink = new OffensivePlacement(); //1. if you have a winning move, then play it

        firstLink
                .setNextPlacement(new DefensivePlacement()) //2. if opp have a winning move, then block it
                .setNextPlacement(new ForkPlacement()) //3. if you have a fork, then play it or 4. if opp have a fork, then block it
                .setNextPlacement(new CenterPlacement()) //5. if the center is available, take it
                .setNextPlacement(new CornerPlacement()) //6. if any corner is available take it
                .setTerminal(new FirstVacantPlacement());
        return firstLink;
    }

    public BasePlacement getSmartPlacementChain() {

        BasePlacement firstLink = new OffensivePlacement(); //1. if you have a winning move, then play it

        firstLink
                .setNextPlacement(new DefensivePlacement()) //2. if opp have a winning move, then block it
                .setNextPlacement(new CenterPlacement())
                .setTerminal(new FirstVacantPlacement());

        return firstLink;
    }

    public int getTurnsPassedTillNow(TicTacToeBoard board) {
        int count = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getSymbol(r, c) != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
