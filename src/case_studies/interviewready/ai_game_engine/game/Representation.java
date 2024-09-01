package case_studies.interviewready.ai_game_engine.game;

import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;

public class Representation {

    private String representation;

    public Representation(TicTacToeBoard board) {
        this.representation = board.toString();
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return "Representation\n" + representation;
    }
}
