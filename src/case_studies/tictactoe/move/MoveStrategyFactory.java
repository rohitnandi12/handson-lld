package case_studies.tictactoe.move;

public abstract class MoveStrategyFactory {

    public static MoveStrategy get(MoveStrategies strategies) {
        return switch (strategies) {
            case RANDOM -> new RandomMoveStrategy();
            case CONSOLE -> new ConsoleMoveStrategy();
            case MIN_MAX -> throw new UnsupportedOperationException("No yet implemented");
        };
    }
}
