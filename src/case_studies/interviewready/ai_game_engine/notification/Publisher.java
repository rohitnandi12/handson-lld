package case_studies.interviewready.ai_game_engine.notification;

import case_studies.interviewready.ai_game_engine.notification.events.Event;

public class Publisher {
    private EventBroker eventBroker;

    public Publisher(EventBroker eventBroker) {
        this.eventBroker = eventBroker;
    }

    public void publishEvent(Event message) {
        eventBroker.publish(message);
    }
}
