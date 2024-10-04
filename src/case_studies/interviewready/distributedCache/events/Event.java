package case_studies.interviewready.distributedCache.events;

import case_studies.interviewready.distributedCache.models.Record;

import java.util.UUID;

public class Event<KEY, VALUE> {
    private final String id;
    private final Record<KEY, VALUE> element;
    private final long timestamp;

    public Event(Record<KEY, VALUE> element, long timestamp) {
        this.id = UUID.randomUUID().toString();
        this.element = element;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public Record<KEY, VALUE> getElement() {
        return element;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "element=" + element +
                ", timestamp=" + timestamp +
                "}\n";
    }
}
