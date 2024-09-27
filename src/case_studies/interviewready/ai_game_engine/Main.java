package case_studies.interviewready.ai_game_engine;

import case_studies.interviewready.ai_game_engine.api.*;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        EmailService emailService = new EmailService();
        Board board = gameEngine.start("TicTacToe");

        Scanner sc = new Scanner(System.in);

        Player computer = new Player("O"), human = new Player("X");

        if (human.getUser().activeAfter(10, TimeUnit.DAYS)) {
            emailService.send(SendEmailCommandBuilder.builder()
                    .receiver(human.getUser())
                    .message("Winning notification email")
                    .build());
        }

        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!!");
            System.out.println(board);
            System.out.print("Enter row :");
            int row = sc.nextInt();
            System.out.print("Enter col :");
            int col = sc.nextInt();
            Move humanMove = new Move(human, new Cell(row, col));
            board = gameEngine.move(board, humanMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                board = gameEngine.move(board, computerMove);
            }
        }
        if (ruleEngine.getState(board).getWinner().equals(human.symbol())) {
            emailService.send(SendEmailCommandBuilder.builder()
                    .receiver(human.getUser())
                    .message("Winning notification email")
                    .build());
        }
        System.out.println("GameResult: " + ruleEngine.getState(board));
        ((TicTacToeBoard) board).getHistory().printHistory();
    }
}
