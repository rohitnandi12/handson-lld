package case_studies.interviewready.ai_game_engine;

import case_studies.interviewready.ai_game_engine.api.*;
import case_studies.interviewready.ai_game_engine.boards.TicTacToeBoard;
import case_studies.interviewready.ai_game_engine.notification.*;
import case_studies.interviewready.ai_game_engine.game.*;
import case_studies.interviewready.ai_game_engine.notification.events.ActivityEvent;
import case_studies.interviewready.ai_game_engine.notification.events.EventType;
import case_studies.interviewready.ai_game_engine.notification.events.WinEvent;
import case_studies.interviewready.ai_game_engine.notification.subscribers.EmailService;
import case_studies.interviewready.ai_game_engine.notification.subscribers.SmsService;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        RuleEngine ruleEngine = new RuleEngine();
        AIEngine aiEngine = new AIEngine();
        EventBroker eventBus = new EventBroker();
        eventBus.subscribe(EventType.ACTIVITY_EVENT, EmailService.getInstance());
        eventBus.subscribe(EventType.ACTIVITY_EVENT, SmsService.getInstance());
        eventBus.subscribe(EventType.WIN_EVENT, SmsService.getInstance());
        eventBus.subscribe(EventType.WIN_EVENT, EmailService.getInstance());
        Publisher publisher = new Publisher(eventBus);
        Board board = gameEngine.start("TicTacToe");

        Scanner sc = new Scanner(System.in);

        Player computer = new Player("O"), human = new Player("X");

        if (human.getUser().activeAfter(10, TimeUnit.DAYS)) {
            publisher.publishEvent(ActivityEvent.create(human.getUser()));
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
        System.out.println("GameResult: " + ruleEngine.getState(board));
        ((TicTacToeBoard) board).getHistory().printHistory();

        if (ruleEngine.getState(board).getWinner().equals(human.symbol())) {
            publisher.publishEvent(WinEvent.create(human.getUser()));
        }
    }
}
