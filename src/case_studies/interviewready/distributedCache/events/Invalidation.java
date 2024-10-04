package case_studies.interviewready.distributedCache.events;

import case_studies.interviewready.distributedCache.models.Record;

public class Invalidation<KEY, VALUE> extends Event<KEY, VALUE> {
    private final Type type;

    public Invalidation(Record<KEY, VALUE> element, long timestamp, Type type) {
        super(element, timestamp);
        this.type = type;
    }

    public enum Type {
        EXPIRY, REPLACEMENT
    }
}
