package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet implements Iterable<Rule> {
    List<Rule> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public void add(Rule boardRule) {
        rules.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        rules.forEach(action);
    }

    @Override
    public Spliterator<Rule> spliterator() {
        return rules.spliterator();
    }
}
