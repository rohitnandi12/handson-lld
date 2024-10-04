package case_studies.interviewready.distributedCache.events;

import case_studies.interviewready.distributedCache.models.Record;

public class Load<K, V> extends Event<K, V> {

    public Load(Record<K, V> element, long timestamp) {
        super(element, timestamp);
    }
}