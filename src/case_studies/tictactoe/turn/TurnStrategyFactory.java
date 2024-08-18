package case_studies.tictactoe.turn;

public class TurnStrategyFactory {

    public static TurnStrategy get(TurnStrategies strategies) {
        return switch (strategies) {
            case CLOCKWISE -> new ClockwiseTurnStrategy();
            case ANTI_CLOCKWISE -> new AntiClockwiseTurnStrategy();
        };
    }
}
