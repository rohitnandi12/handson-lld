package case_studies.interviewready.ai_game_engine.notification;

import case_studies.interviewready.ai_game_engine.notification.events.Event;
import case_studies.interviewready.ai_game_engine.notification.events.EventType;
import case_studies.interviewready.ai_game_engine.notification.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBroker {

    Map<EventType, List<Subscriber>> eventBus = new HashMap<>();

    public void subscribe(EventType type, Subscriber subscriber) {
        eventBus.computeIfAbsent(type, k -> new ArrayList<>()).add(subscriber);
    }

    public void publish(Event event) {
        eventBus.getOrDefault(event.getEventType(), new ArrayList<>())
                .parallelStream()
                .forEach(s -> s.notify(event));
    }
}
