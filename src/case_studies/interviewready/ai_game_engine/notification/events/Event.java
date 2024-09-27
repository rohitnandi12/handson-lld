package case_studies.interviewready.ai_game_engine.notification.events;

import case_studies.interviewready.ai_game_engine.game.User;

public class Event {
    private EventType eventType;
    private String message;

    private User user;

    public Event(EventType eventType, String message, User user) {
        this.eventType = eventType;
        this.message = message;
        this.user = user;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
