package case_studies.interviewready.distributedCache.events;

import case_studies.interviewready.distributedCache.models.Record;

public class Eviction<K, V> extends Event<K, V> {
    public Eviction(Record<K, V> element, long timestamp) {
        super(element, timestamp);
    }
}
