package case_studies.interviewready.ai_game_engine.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {

    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex) {
        for (int i = boards.size() - 1; i >= 0; i--) {
            boards.remove(i);
        }
        return boards.get(moveIndex - 1);
    }

    public Representation undo() {
        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public void add(Representation representation) {
        boards.add(representation);
    }

    public void copy(History history) {
        this.boards = new ArrayList<>(Collections.nCopies(history.boards.size(), null));
        Collections.copy(this.boards, history.boards);
    }

    public void printHistory() {
        System.out.println("==================PRINTING HISTORIES================");
        this.boards.forEach(System.out::println);
        System.out.println("====================================================");
    }
}