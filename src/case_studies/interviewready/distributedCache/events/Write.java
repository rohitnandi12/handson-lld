package case_studies.interviewready.distributedCache.events;

import case_studies.interviewready.distributedCache.models.Record;

public class Write<K, V> extends Event<K, V> {

    public Write(Record<K, V> element, long timestamp) {
        super(element, timestamp);
    }
}
