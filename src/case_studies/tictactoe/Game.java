package case_studies.tictactoe;

import case_studies.tictactoe.move.Move;
import case_studies.tictactoe.player.Player;
import case_studies.tictactoe.turn.Turn;
import case_studies.tictactoe.turn.TurnStrategy;
import case_studies.tictactoe.winning.StandardWinningStrategy;
import case_studies.tictactoe.winning.WinningStatus;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Game {

    private final List<Player> players;

    private final TurnStrategy turnStrategy;

    private final Board board;

    private GameStatus gameStatus;
    private WinningStatus winningStatus;

    private Player winningPlayer;

    Stack<Move> appliedMoves;

    public Game(int dimension, List<Player> players, TurnStrategy turnStrategy) {
        this.players = players;
        this.turnStrategy = turnStrategy;
        this.gameStatus = GameStatus.OPEN;
        this.board = new Board(dimension, new StandardWinningStrategy());
        this.appliedMoves = new Stack<>();
    }

    public void run() {
        /*
        keep running in loop until board has reached terminal state or someone has won
         */
        Turn currTurn = null;
        Turn lastTurn = null;
        Scanner sc = new Scanner(System.in);

        while (!GameStatus.TERMINATED.equals(gameStatus)) {
            board.displayBoard();
            currTurn = turnStrategy.nextTurn(lastTurn, players);

            Move nextMove = currTurn.player().nextMove(board);
            System.out.println(nextMove);
            board.applyMove(nextMove);
            this.appliedMoves.push(nextMove);

            if (currTurn.player().canUndo()) {
                System.out.println("Do you want to undo your move (y/n) ? ");
                String userChoiceToUndo = sc.nextLine();
                if ("y".equalsIgnoreCase(userChoiceToUndo)) {
                    Move undoMove = appliedMoves.pop();
                    board.unApplyMove(undoMove);
                    currTurn = lastTurn;
                }
            }

            if (board.hasWinner(nextMove)) {
                winningPlayer = nextMove.player();
                this.winningStatus = WinningStatus.PLAYER_WON;
                this.gameStatus = GameStatus.TERMINATED;
            } else if (board.isTerminated()) {
                this.winningStatus = WinningStatus.DRAW;
                this.gameStatus = GameStatus.TERMINATED;
            }

            lastTurn = currTurn;
        }

        board.displayBoard();
        if (WinningStatus.PLAYER_WON.equals(this.winningStatus))
            System.out.println("The Winning Player is " + winningPlayer);
        else
            System.out.println("The Game resulted in a draw");
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private List<Player> players;
        private Integer dimension;

        private TurnStrategy turnStrategy;

        public Game build() {
            validateInputs();
            return new Game(dimension, players, turnStrategy);
        }

        public GameBuilder dimensions(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder turnStrategy(TurnStrategy turnStrategy) {
            this.turnStrategy = turnStrategy;
            return this;
        }

        public void validateInputs() {
            if (dimension == null || players == null || turnStrategy == null) {
                throw new RuntimeException("Please enter all inputs");
            }
        }
    }

}
