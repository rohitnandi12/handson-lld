package case_studies.interviewready.ai_game_engine.game;

public class Game {

    private GameConfig gameConfig;
    private Board board;
    private Player winner;

    private long lastMoveTimeInMillis;
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

    public void move(Move move, long timeStampInMillis) {
        long timeTakenSinceLastMove = timeStampInMillis - lastMoveTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if (gameConfig.isTimed()) {
            moveForTimedGame(move, timeStampInMillis);
        } else {
            board = board.move(move);
        }
        lastMoveTimeInMillis = timeStampInMillis;
    }

    private void moveForTimedGame(Move move, long timeTakenSinceLastMove) {
        if(gameConfig.isTimed()) {
            setMoveOrWinner(timeTakenSinceLastMove, maxTimePerMove, move);
        }
        setMoveOrWinner(move.getPlayer().getTimeUsedInMillis(), maxTimePerPlayer, move);
    }

    private void setMoveOrWinner(long timeTakenSinceLastMove, Integer maxTime, Move move) {
        boolean isTimePerMoveGameConfig = gameConfig.isTimed();
        if (timeTakenSinceLastMove < maxTime) {
            board = board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public Player getWinner() {
        return winner;
    }
}
