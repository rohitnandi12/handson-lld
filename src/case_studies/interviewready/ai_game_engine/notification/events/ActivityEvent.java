package case_studies.interviewready.ai_game_engine.notification.events;

import case_studies.interviewready.ai_game_engine.game.User;

public class ActivityEvent extends Event {


    public ActivityEvent(User user, String message) {
        super(EventType.ACTIVITY_EVENT, message, user);
    }

    public static ActivityEvent create(User user) {
        // get the message template from database
        return new ActivityEvent(user, "Activity Event Message");
    }
}
