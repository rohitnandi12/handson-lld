package case_studies.interviewready.ai_game_engine.notification.subscribers;

import case_studies.interviewready.ai_game_engine.notification.events.Event;

public class EmailService implements Subscriber {

    EmailService() {}
    private static class InstanceHolder {
        private static final EmailService INSTANCE = new EmailService();
    }
    public static EmailService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void notify(Event event) {
        System.out.println("Email sent: " + event);
    }
}
