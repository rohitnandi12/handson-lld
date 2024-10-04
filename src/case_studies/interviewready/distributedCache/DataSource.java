package case_studies.interviewready.distributedCache;

import java.util.concurrent.CompletionStage;

public interface DataSource<KEY, VALUE> {

    CompletionStage<Void> persist(KEY key, VALUE value, long timestamp);

    CompletionStage<VALUE> load(KEY key);
}
