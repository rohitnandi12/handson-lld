package case_studies.interviewready.ai_game_engine.game;

public class Game {

    private GameConfig gameConfig;
    private Board board;
    private Player winner;

    private int lastMoveTimeInMillis;
    private Integer maxTimePerMove;
    private Integer maxTimePerPlayer;

    public Game(GameConfig gameConfig, Board board, Player winner,
                Integer lastMoveTimeInMillis, Integer maxTimePerMove, Integer maxTimePerPlayer
    ) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTimeInMillis = lastMoveTimeInMillis;
        this.maxTimePerMove = maxTimePerMove;
        this.maxTimePerPlayer = maxTimePerPlayer;
    }

    public void move(Move move, int timeStampInMillis) {
        int timeTakenSinceLastMove = timeStampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if (gameConfig.isTimed()) {
            moveForTimedGame(move, timeStampInMillis);
        } else {
            board.move(move);
        }
        lastMoveTimeInMillis = timeStampInMillis;
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        boolean isTimePerMoveGameConfig = gameConfig.getTimePerMove() != null;
        int currentTime = isTimePerMoveGameConfig ? timeTakenSinceLastMove : move.getPlayer().getTimeUsedInMillis();
        int endTime = isTimePerMoveGameConfig ? maxTimePerMove : maxTimePerPlayer;
        if (currentTime < endTime) {
            board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }
}
