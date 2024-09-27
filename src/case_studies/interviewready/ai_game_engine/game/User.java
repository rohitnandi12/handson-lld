package case_studies.interviewready.ai_game_engine.game;

import java.util.concurrent.TimeUnit;

public class User {
    private int id;
    long lastActiveTime;

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean activeAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshold);
    }
}
