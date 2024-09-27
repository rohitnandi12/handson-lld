package case_studies.interviewready.ai_game_engine.notification.subscribers;

import case_studies.interviewready.ai_game_engine.notification.events.Event;

public interface Subscriber {
    void notify(Event event);
}
