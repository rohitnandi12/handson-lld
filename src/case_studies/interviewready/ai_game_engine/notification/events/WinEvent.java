package case_studies.interviewready.ai_game_engine.notification.events;

import case_studies.interviewready.ai_game_engine.game.User;

public class WinEvent extends Event {

    private WinEvent(EventType eventType, User user, String message) {
        super(eventType, message, user);
    }

    public static WinEvent create(User user) {
        return new WinEvent(EventType.WIN_EVENT, user, "Winning message");
    }
}
