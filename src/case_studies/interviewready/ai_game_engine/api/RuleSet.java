package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    List<Rule<T>> rules;

    public RuleSet() {
        this.rules = new ArrayList<>();
    }

    public void add(Rule<T> boardRule) {
        rules.add(boardRule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return rules.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule<T>> action) {
        rules.forEach(action);
    }

    @Override
    public Spliterator<Rule<T>> spliterator() {
        return rules.spliterator();
    }
}
