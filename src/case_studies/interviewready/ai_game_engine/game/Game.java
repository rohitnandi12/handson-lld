package case_studies.interviewready.ai_game_engine.game;

public class Game {

    private GameConfig gameConfig;
    private Board board;
    private Player winner;

    private int lastMoveTimeInMillis;
    private int maxTimePerMove;
    private int maxTimePerPlayer;

    public void move(Move move, int timeStampInMillis) {
        int timeTakenSinceLastMove = timeStampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if(gameConfig.isTimed()) {
            moveForTimedGame(move, timeStampInMillis);
        } else {
            board.move(move);
        }
        lastMoveTimeInMillis = timeStampInMillis;
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if(gameConfig.getTimePerMove() != null) {
            if (moveMadeInTime(timeTakenSinceLastMove)) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        } else {
            if(moveMadeInTime(move.getPlayer())) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        }
    }

    private boolean moveMadeInTime(int timeTakenSinceLastMove) {
        return timeTakenSinceLastMove < maxTimePerMove;
    }

    private boolean moveMadeInTime(Player player) {
        return player.getTimeUsedInMillis() < maxTimePerPlayer;
    }
}
