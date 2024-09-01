package case_studies.interviewready.ai_game_engine;

import case_studies.interviewready.ai_game_engine.api.AIEngine;
import case_studies.interviewready.ai_game_engine.api.GameEngine;
import case_studies.interviewready.ai_game_engine.api.RuleEngine;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.game.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");

        Scanner sc = new Scanner(System.in);

        Player computer = new Player("O"), human = new Player("X");
        while(!ruleEngine.getState(board).isOver()) {
            System.out.println("Make your move!!");
            System.out.println(board);
            System.out.print("Enter row :");
            int row = sc.nextInt();
            System.out.print("Enter col :");
            int col = sc.nextInt();
            Move humanMove = new Move(human, new Cell(row, col));
            board = gameEngine.move(board, humanMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                board = gameEngine.move(board, computerMove);
            }
        }

        System.out.println("GameResult: " + ruleEngine.getState(board));
        ((TicTacToeBoard)board).getHistory().printHistory();
    }
}
