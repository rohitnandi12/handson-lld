package case_studies.interviewready.ai_game_engine.notification.subscribers;

import case_studies.interviewready.ai_game_engine.notification.events.Event;

public class SmsService implements Subscriber {

    private static class InstanceHolder {
        private static final SmsService INSTANCE = new SmsService();
    }
    public static SmsService getInstance() {
        return SmsService.InstanceHolder.INSTANCE;
    }

    @Override
    public void notify(Event event) {
        System.out.println("SMS sent :" + event);
    }
}
