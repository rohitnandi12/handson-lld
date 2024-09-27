package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.User;

public class EmailService {

    private void sentEmail(User user, String body) {
        System.out.println("Email was sent to user: " + user );
        System.out.println(body);
    }

    public void send(SendEmailCommand command) {
        sentEmail(command.receiver, command.message);
    }
}
