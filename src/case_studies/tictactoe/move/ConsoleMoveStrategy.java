package case_studies.tictactoe.move;

import case_studies.tictactoe.Board;
import case_studies.tictactoe.player.Player;

import java.util.Scanner;

public class ConsoleMoveStrategy implements MoveStrategy {

    private final Scanner scanner = new Scanner(System.in);
    @Override
    public Move computeNextMove(Player player, Board board) {
        System.out.println("Enter your next move");
        System.out.print("Enter X:");
        int x = scanner.nextInt();
        System.out.print("Enter Y:");
        scanner.nextLine();
        int y = scanner.nextInt();
        return new Move(x, y, player);
    }

    @Override
    public boolean isUserGenerated() {
        return true;
    }
}
